package com.proiect.scd.proiectSCD.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                    .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.GET, "/api/v1/department/all").authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/v1/department/byId/{id}").authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/v1/department/byName/{name}").authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/v1/department/{parentId}").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/department/{id}").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/department/{id}").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/v1/employee/byDepartament/employees/{departmentName}").authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/v1/employee/byDepartament/managers/{departmentName}").authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/v1/employee/{id}").authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/v1/employee").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/employee/{id}").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/employee/{id}").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/v1/users/login").permitAll()
                .anyRequest()
                .permitAll()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .cors();

        return http.build();
    }
}