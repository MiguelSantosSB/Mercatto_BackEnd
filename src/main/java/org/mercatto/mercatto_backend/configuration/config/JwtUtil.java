package org.mercatto.mercatto_backend.configuration.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${api.security.token.secret}")
    private String jwtSecret;

    @Value("${api.security.token.expiration}")
    private int jwtExpiration;

    private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
    return Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
       return Jwts.parser()
               .verifyWith(getSigningKey())
               .build()
               .parseSignedClaims(token).getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
                return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration * 10000L))
                .signWith(getSigningKey())
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
////    private Key getSigningKey() {
//////        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
////        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
////    }
//
//    private SecretKey getSigningKey() {
//    byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
//    return Keys.hmacShaKeyFor(keyBytes);
//}
//
//    public String generateToken(Authentication authentication) {
//        String username = authentication.getName();
//        SecretKey key = getSigningKey();
//
//        return Jwts.builder()
//                .subject(username)
//                .claim("role", username)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + jwtExpiration * 1000L))
//                .signWith(key)
//                .compact();
//    }
//
//    public String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            SecretKey key = getSigningKey();
//
//            Jwts.parser()
//                    .verifyWith(key)
//                    .build()
//                    .parseSignedClaims(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String getUserNameFromToken(String token) {
//        Claims claims = Jwts.parser()
//                .verifyWith(getSigningKey())
//                .build().parseSignedClaims(token)
//                .getPayload();
//        return claims.getSubject();
//    }
