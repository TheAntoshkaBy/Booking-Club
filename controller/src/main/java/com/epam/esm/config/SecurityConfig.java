package com.epam.esm.config;

import com.epam.esm.controller.security.jwt.JwtConfigurer;
import com.epam.esm.controller.security.jwt.JwtTokenProvider;
import com.epam.esm.exception.RestAuthenticationExceptionHandler;
import com.epam.esm.exception.RestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADD_ORDER_ENDPOINT = "/users/{id}/orders";
    private static final String ADD_USER_CHECK_LIST_ENDPOINT = "/users";
    private static final String ORDER_ENDPOINT = "/orders/**";
    private static final String USER_ENDPOINT = "/users/{id}/**";
    private static final String USER_ENDPOINT_FOR_REGISTRATION = "/users";
    private static final String USER_ENDPOINT_FOR_LOGIN = "/users/login";
    private static final String CERTIFICATE_ENDPOINT = "/certificates/**";
    private static final String TAG_ENDPOINT = "/reviews/**";
    private static final String ROLE_ADMIN = "ADMIN";
    private final JwtTokenProvider jwtTokenProvider;
    private RestAccessDeniedHandler accessDeniedHandler;
    private RestAuthenticationExceptionHandler authenticationEntryPoint;
    private final HandlerExceptionResolver exceptionResolver;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider,
        @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.exceptionResolver = exceptionResolver;
    }

    @Autowired
    public void setAccessDeniedHandler(RestAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Autowired
    public void setAuthenticationEntryPoint(
        RestAuthenticationExceptionHandler authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(USER_ENDPOINT_FOR_LOGIN).permitAll()
            .mvcMatchers(HttpMethod.GET, CERTIFICATE_ENDPOINT).permitAll()
            .mvcMatchers(HttpMethod.POST, USER_ENDPOINT_FOR_REGISTRATION).permitAll()
            .mvcMatchers(HttpMethod.GET, USER_ENDPOINT).authenticated()
            .mvcMatchers(HttpMethod.PATCH, USER_ENDPOINT).authenticated()
            .mvcMatchers(HttpMethod.DELETE, USER_ENDPOINT).hasRole(ROLE_ADMIN)
            .mvcMatchers(HttpMethod.GET, TAG_ENDPOINT).authenticated()
            .mvcMatchers(HttpMethod.GET, ORDER_ENDPOINT).authenticated()
            .mvcMatchers(HttpMethod.PATCH, ADD_ORDER_ENDPOINT).authenticated()
            .mvcMatchers(HttpMethod.GET, ADD_USER_CHECK_LIST_ENDPOINT).hasRole(ROLE_ADMIN)
            .antMatchers(TAG_ENDPOINT).hasRole(ROLE_ADMIN)
            .antMatchers(CERTIFICATE_ENDPOINT).hasRole(ROLE_ADMIN)
            .antMatchers(ORDER_ENDPOINT).hasRole(ROLE_ADMIN)
            .mvcMatchers(HttpMethod.GET, CERTIFICATE_ENDPOINT).permitAll()
            .anyRequest().permitAll()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider, exceptionResolver))
            .and().csrf().disable();
    }
}
