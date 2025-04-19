package com.example.api_sem4.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/students/**").permitAll()
                        .requestMatchers("/classes/**").permitAll()
                        .requestMatchers("/menus/**").permitAll()
                        .requestMatchers("/students/**").permitAll()
                        .requestMatchers("/schedules/**").permitAll()
                        .requestMatchers("/programs/**").permitAll()
                        .requestMatchers("/attendances/**").permitAll()

                        .anyRequest().authenticated()
                );

        return http.build();
    }
}