package com.eventeasy.EventEasy.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private final static String SECRET_KEY = "WKdAO3WKox0aKUbZP1zANU+bPg/DnI3m31FJn/jVA27ZWZqgND6id7yLfy6Z6zSt/2/+AIczcvUvtiOtkT+BPrAcTo61+6R4E6D+AaRARpXWdplcHmhgMOp0mXcyYD0QnzvoLuHRRJ+GoK5RWdPgSNYViPRzot3V3eMIcq/ViVN4txh5b4RjOPMn9ucChfQ4DPBCT1dquh1/aXZ+jJf5OWU5wQRhAYZKDBGEbEJ4uL+vBkYI/LZhk6ZLAtXNq7zY6TaGriW14flLd8T18qJwrqx71OdPEZDyv8iGcajAoLV8hw7kZvaGnFCX3NpG5Gc8b8csk1NwIdZ1ftpL4SZwkRDvHcNICtFy0aZSEEHkuM8=";

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }
    public String generateToken(
            UserDetails userDetails
    ){
        return generateToken(Collections.emptyMap() , userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        if (userDetails.getUsername() == null || userDetails.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 24 minutes
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpire(String token) {
        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenValidate(UserDetails userDetails, String token) {
        final String name = extractUserName(token);
        return (name.equals(userDetails.getUsername())) && !isTokenExpire(token);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
