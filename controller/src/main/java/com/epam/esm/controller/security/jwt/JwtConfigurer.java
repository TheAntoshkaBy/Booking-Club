package com.epam.esm.controller.security.jwt;

import com.epam.esm.exception.ExceptionHandlerFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

public class JwtConfigurer extends
    SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;
    private final HandlerExceptionResolver exceptionResolver;

    public JwtConfigurer(JwtTokenProvider jwtTokenProvider,
        HandlerExceptionResolver exceptionResolver) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new ExceptionHandlerFilter(exceptionResolver), SecurityContextPersistenceFilter.class);
    }
}
