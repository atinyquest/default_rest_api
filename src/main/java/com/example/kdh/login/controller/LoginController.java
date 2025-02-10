package com.example.kdh.login.controller;

import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
import com.example.kdh.common.util.TokenManager;
import com.example.kdh.user.model.vo.User;
import com.example.kdh.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    private final TokenManager tokenManager;
    private final UserRepository userRepository;

    @GetMapping("/tokenPrint")
    public void test() {
        tokenManager.getTokenKeyPrint();
    }

    @GetMapping("/login")
    public String login(String name, String email) {
        User user = userRepository.findByNameAndEmail(name, email).orElseThrow(() -> new CustomApiException(ApiResponseEnum.USER_NOT_FOUND));
        return "토큰발행예정";
    }
}
