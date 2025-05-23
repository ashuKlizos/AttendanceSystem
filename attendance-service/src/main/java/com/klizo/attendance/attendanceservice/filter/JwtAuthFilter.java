package com.klizo.attendance.attendanceservice.filter;

import com.klizo.attendance.attendanceservice.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException;
import java.util.List;

@Log4j2
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String token = extractToken(authorizationHeader);
        log.info("Authorization Header: {}", authorizationHeader);
        log.info("Extracted Token: {}", token);

        if (token == null || token.isEmpty()) {
            log.warn("Token is missing or empty");
            filterChain.doFilter(request, response);
            return;
        }

        if (!jwtService.isValid(token)) {
            log.warn("Invalid token");
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtService.extractUsername(token);
        String role = jwtService.extractRole(token);
        log.info("Extracted Username: {}", username);
        log.info("Extracted Role: {}", role);
        String normalizedRole = role.toUpperCase();
        String authorityName = normalizedRole.startsWith("ROLE_") ? normalizedRole : "ROLE_" + normalizedRole;

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(username, token, List.of(new SimpleGrantedAuthority(authorityName)))
        );

        request.setAttribute("jwtToken", token);

        filterChain.doFilter(request, response);
    }

    private String extractToken(String authorizationHeader) {
        return (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
                ? authorizationHeader.substring(7)
                : null;
    }
}
