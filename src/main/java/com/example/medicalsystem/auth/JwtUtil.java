package com.example.medicalsystem.auth;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET = "bd090N7wIV+JHnv78pWtcfAekL4YMM9gbnJMs8SJ9sI=";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            // Remove Bearer prefix and clean the token
            String processedToken = cleanToken(token);
            
            // Debugging output
            System.out.println("Processed Token: " + processedToken);
    
            Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(processedToken);
            return true;
        } catch (Exception e) {
            System.err.println("Token validation error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean isTokenValid(String token, String email) {
        String processedToken = cleanToken(token);
        final String extractedEmail = extractEmail(processedToken);
        return (extractedEmail.equals(email) && !isTokenExpired(processedToken));
    }

    public String extractEmail(String token) {
        return extractAllClaims(cleanToken(token)).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private String cleanToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }
        
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        return token.replaceAll("\\s+", "");
    }
}