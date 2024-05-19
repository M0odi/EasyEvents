package com.eventeasy.EventEasy.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    @Value(value = "${jwt.lifetime}")
    private int lifeTime;
    private final static String SECRET_KEY = "WKdAO3WKox0aKUbZP1zANU+bPg/DnI3m31FJn/jVA27ZWZqgND6id7yLfy6Z6zSt/2/+AIczcvUvtiOtkT+BPrAcTo61+6R4E6D+AaRARpXWdplcHmhgMOp0mXcyYD0QnzvoLuHRRJ+GoK5RWdPgSNYViPRzot3V3eMIcq/ViVN4txh5b4RjOPMn9ucChfQ4DPBCT1dquh1/aXZ+jJf5OWU5wQRhAYZKDBGEbEJ4uL+vBkYI/LZhk6ZLAtXNq7zY6TaGriW14flLd8T18qJwrqx71OdPEZDyv8iGcajAoLV8hw7kZvaGnFCX3NpG5Gc8b8csk1NwIdZ1ftpL4SZwkRDvHcNICtFy0aZSEEHkuM8=";

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build().parseSignedClaims(token).getPayload();
    }

    public String generateToken(
            UserDetails userDetails
    ) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(HashMap<String, Object> extraClaims, UserDetails userDetails) {
        if (userDetails.getUsername() == null || userDetails.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        extraClaims.put("roles", userDetails.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet())
        );
        return Jwts.builder()
                .claims(extraClaims).subject(userDetails.getUsername()).
                issuedAt(new Date(System.currentTimeMillis())).
                expiration(new Date(System.currentTimeMillis() + Duration.ofMinutes(lifeTime).toMillis())) // Setting token lifetime to 30 minutes
                .signWith(getSigningKey())
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
