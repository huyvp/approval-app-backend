package com.samsung.project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.samsung.project.exception.ErrorCode;
import com.samsung.project.model.Authority;
import com.samsung.project.model.User;
import com.samsung.project.response.AppResponse;
import com.samsung.project.service.AuthorityService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.samsung.project.constant.SecurityConstant.*;


@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final AuthorityService authorityService;

    @Value(("${jwt.signing.key}"))
    private String signingKey;

    @Autowired
    public InitialAuthenticationFilter(AuthenticationManager authenticationManager, AuthorityService authorityService) {
        this.authenticationManager = authenticationManager;
        this.authorityService = authorityService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        User user = mapper.readValue(sb.toString(), User.class);

        if (user.getUsername() == null || user.getPassword() == null) {
            setResponse(HttpStatus.BAD_REQUEST, response, mapper);
        } else {
            Authentication authentication = new UsernamePasswordAuthentication(user.getUsername(), user.getPassword());
            try {
                authenticationManager.authenticate(authentication);
                SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

                Map<String, Object> claimsUsername = new HashMap<>();
                claimsUsername.put(USERNAME, user.getUsername());

                Map<String, Object> claimsAuthorities = new HashMap<>();
                claimsAuthorities.put(AUTHORITIES, this.getUserAuthorities(user.getUsername()));

                LocalDateTime now = LocalDateTime.now().plusDays(1);

                String jwt = Jwts.builder()
                        .setClaims(claimsUsername)
                        .addClaims(claimsAuthorities)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
                        .signWith(key)
                        .compact();

                response.setHeader(AUTHORIZATION, jwt);
                response.addCookie(new Cookie(TOKEN, jwt));
                response.resetBuffer();
                response.setStatus(HttpStatus.OK.value());
                response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                response.getOutputStream().print(jwt);
                response.flushBuffer();
            } catch (Exception e) {
                setResponse(HttpStatus.UNAUTHORIZED, response, mapper);
            }
        }


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login") || !request.getMethod().equalsIgnoreCase("POST");
    }

    private void setResponse(HttpStatus httpStatus, HttpServletResponse response, ObjectMapper mapper) {
        AppResponse<?> appResponse = AppResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(ErrorCode.UNAUTHORIZED.getCode())
                .status(httpStatus)
                .message(ErrorCode.UNAUTHORIZED.getMessage())
                .build();
        try {
            response.resetBuffer();
            response.setStatus(httpStatus.value());
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            response.getOutputStream().print(mapper.writeValueAsString(appResponse));
            response.flushBuffer();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private List<String> getUserAuthorities(String username) {
        List<Authority> authorities = authorityService.getAuthoritiesByUsername(username);
        if (authorities.isEmpty()) return Arrays.asList("user");
        return authorities
                .stream()
                .map(Authority::getAuthority)
                .collect(Collectors.toList());
    }
}
