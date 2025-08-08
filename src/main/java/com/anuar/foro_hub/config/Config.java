package com.anuar.foro_hub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .build();

        /*
         * .sessionManagement(sm ->
         * sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         * .authorizeHttpRequests(req -> {
         * req.requestMatchers(HttpMethod.POST, "/login").permitAll();
         * req.anyRequest().authenticated();
         * })
         * .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
         * .build();
         */
    }
}
