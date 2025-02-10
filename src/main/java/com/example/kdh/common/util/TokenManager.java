package com.example.kdh.common.util;

import com.example.kdh.user.model.vo.User;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {

    @Value("${token.key}")
    private String tokenKey;

    public void getTokenKeyPrint() {
        System.out.println("tokenKey = " + tokenKey);
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject("userToken")
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 8))
                .compact();
    }
}
