package com.example.springSecurity.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    private final String SECRET_KEY="jdsjbcjhbcakjsehfjagfjyasbglghfjhdjhcghfxhgbnsugwlbflierhgfyuawegvfipuawegfuyweavfuwevlcjbsdgv";
    public String getUserNameFromJwtToken(String token) {
        System.out.println("******************");
        System.out.println(token);
        return Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String generateTokenFromUsername(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        System.out.println(token);
        return token;

    }
}
