package com.mravel.user.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth
                // PUBLIC
                .requestMatchers(
                        "/api/users/by-email",
                        "/api/users/{id}",
                        "/api/users/{id}/profile-page",
                        "/api/users/me/**" // 👈 cho phép qua, controller tự validate
                ).permitAll()

                .anyRequest().permitAll());

        // Entry point này chỉ còn dùng cho chỗ nào thật sự authenticated
        http.exceptionHandling(ex -> ex
                .authenticationEntryPoint((req, res, authEx) -> {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    res.setContentType("application/json");
                    res.getWriter().write("{\"message\":\"JWT expired\"}");
                }));

        http.httpBasic(basic -> basic.disable());
        http.formLogin(login -> login.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
