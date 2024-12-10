package com.example.dockerTest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {"/login","/register"};

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    AuthEntry unauthorizeHandler;

    DefaultSecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll().anyRequest().authenticated())
                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint(unauthorizeHandler))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
}
