package com.eventeasy.EventEasy.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import io.jsonwebtoken.JwtParser;

import java.security.Key;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final static String SECRET_KEY = "";
    public String extractUserName(String token){
        return null;
    }
    private Claims getAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getPublicSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private Key getPublicSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode();
    }
}
