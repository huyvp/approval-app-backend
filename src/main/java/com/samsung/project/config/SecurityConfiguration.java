package com.samsung.project.config;

import com.samsung.project.security.CorsFilter;
import com.samsung.project.security.InitialAuthenticationFilter;
import com.samsung.project.security.JwtAuthenticationFilter;
import com.samsung.project.security.UsernamePasswordAuthenticationProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    InitialAuthenticationFilter initialAuthenticationFilter;
    JwtAuthenticationFilter jwtAuthenticationFilter;
    UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
    CorsFilter corsFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .mvcMatchers("/auth/**").permitAll()
                .mvcMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
                .addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
    }


}
