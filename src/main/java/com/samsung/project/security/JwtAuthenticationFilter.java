package com.samsung.project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static com.samsung.project.constant.SecurityConstant.*;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value(("${jwt.signing.key}"))
    private String signingKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(AUTHORIZATION);

        if (jwt == null) {
            response.resetBuffer();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            response.getOutputStream().print("Please login!");
            response.flushBuffer();
        } else {
            SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            String username = String.valueOf(claims.get(USERNAME));
            List<String> authorities = (List<String>) claims.get(AUTHORITIES);
            List<GrantedAuthority> grantedAuthorities = authorities
                    .stream()
                    .map(s -> new SimpleGrantedAuthority("ROLE_" + s.toUpperCase()))
                    .collect(Collectors.toList());

            UsernamePasswordAuthentication auth = new UsernamePasswordAuthentication(username, null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login")
                || request.getServletPath().equals("/auth/user/add")
                || request.getServletPath().equals("/auth/logout")
                ;
    }
}
