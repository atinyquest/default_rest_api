package com.example.kdh.login.controller;

import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
import com.example.kdh.common.util.TokenManager;
import com.example.kdh.user.model.vo.User;
import com.example.kdh.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    private final TokenManager tokenManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("")
    public String login(@RequestParam(name = "name") String name,
            @RequestParam(name = "password") String password) {
        User user = userRepository.findByName(name).orElseThrow(() -> new CustomApiException(ApiResponseEnum.INCORRECT_NAME_AND_PASSWORD));

        // 🔐 암호화된 비밀번호와 비교
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomApiException(ApiResponseEnum.INCORRECT_NAME_AND_PASSWORD);
        }
        return tokenManager.generateToken(user,"ROLE_USER");
    }

    @GetMapping("/jwtValidate")
    public String jwtValidate(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        Jws<Claims> jws =  tokenManager.validateToken(token.substring("Bearer ".length()));
        return "정상토큰 " + jws.getPayload().getId();
    }
}
