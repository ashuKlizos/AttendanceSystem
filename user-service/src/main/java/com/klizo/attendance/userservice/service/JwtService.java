package com.klizo.attendance.userservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;
import com.klizo.attendance.userservice.entity.Employee;

@Log4j2
@Service
public class JwtService {

    private final String SECRET_KEY = "b33611cb858fa9c4f9e381e7f00dcc623285e0f3a3e01b4369347f910eff911b";
    private final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24 hours

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // Subject = email
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public Long extractEmployeeId(String token) {
        return extractClaim(token, claims -> claims.get("employeeId", Long.class));
    }

    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);
        log.info("Validating token for user: {} token: {}", username , token);
        boolean isValid = (username.equals(user.getUsername())) && !isTokenExpired(token);
        log.info("Token is valid: " + isValid);
        return isValid;
    }

    private Claims extractAllClaims(String token) {
        log.info("Extracting all claims from token");
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String generateToken(Employee employee) {
        return Jwts.builder()
                .subject(employee.getEmail())
                .claim("role", employee.getRole().name())
                .claim("employeeId", employee.getId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigninKey())
                .compact();
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

