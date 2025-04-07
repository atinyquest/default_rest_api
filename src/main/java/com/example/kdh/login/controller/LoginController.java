package com.example.kdh.login.controller;

import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
import com.example.kdh.common.util.TokenManager;
import com.example.kdh.user.model.vo.User;
import com.example.kdh.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
@SecurityRequirement(name="Bearer Authentication")
public class LoginController {

    private final TokenManager tokenManager;
    private final UserRepository userRepository;

    @GetMapping("/tokenPrint")
    public void test() {
        tokenManager.getTokenKeyPrint();
    }

    @GetMapping("/login")
    public String login(@RequestParam(name="name") String name,@RequestParam(name="email") String email) {
        User user = userRepository.findByNameAndEmail(name, email).orElseThrow(() -> new CustomApiException(ApiResponseEnum.INCORRECT_NAME_AND_EMAIL));
        return tokenManager.generateToken(user);
    }

    @GetMapping("/jwtValidate")
    public String jwtValidate(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        System.out.println(token);
        Jws<Claims> jws =  tokenManager.validToken(token.substring("Bearer ".length()));
        return "정상토큰 " + jws.getPayload().getId();
    }
}
