package com.example.kdh.common.util;

import com.example.kdh.common.exception.ApiResponseEnum;
import com.example.kdh.common.exception.CustomApiException;
import com.example.kdh.user.model.vo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
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
                .id(user.getId().toString())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 8))
                .signWith(Keys.hmacShaKeyFor(tokenKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public Jws<Claims> validToken(String token) {
        try{
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(tokenKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token);
            return jws;
        }catch(ExpiredJwtException e){
            throw new CustomApiException(ApiResponseEnum.VALIDITY_PERIOD_EXPIRED);
        }catch(Exception e){
            throw new CustomApiException(ApiResponseEnum.INCORRECT_NAME_AND_EMAIL);
        }
    }
}
