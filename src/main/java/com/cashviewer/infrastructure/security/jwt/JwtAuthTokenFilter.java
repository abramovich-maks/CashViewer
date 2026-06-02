package com.cashviewer.infrastructure.security.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private final JwtTokenValidator jwtTokenValidator;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (token != null) {
            try {
                DecodedJWT decodedJWT = jwtTokenValidator.verifyAccessToken(token);
                String login = decodedJWT.getSubject();
                List<SimpleGrantedAuthority> roles = decodedJWT.getClaim("roles")
                        .asList(String.class)
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(login, null, roles);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException ex) {
                log.warn("Invalid or expired JWT token: {}", ex.getMessage());
                SecurityContextHolder.clearContext();
            } catch (Exception ex) {
                log.error("Unexpected error while verifying JWT: {}", ex.getMessage(), ex);
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}