package com.mravel.plan.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import com.mravel.plan.security.JwtUserPrincipal;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Value("${auth.service.url:http://localhost:8081/api/auth/validate}")
    private String authValidateUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // Không có token thì cho qua, controller nào cần auth có thể annotate
        // @PreAuthorize
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            // Gọi auth-service để xác minh token
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<AuthValidationResponse> resp = restTemplate.exchange(
                    authValidateUrl, HttpMethod.GET, entity, AuthValidationResponse.class);

            if (resp.getStatusCode() == HttpStatus.OK && resp.getBody() != null && resp.getBody().isValid()) {
                var body = resp.getBody();
                List<SimpleGrantedAuthority> authorities = Collections
                        .singletonList(new SimpleGrantedAuthority("ROLE_" + body.getRole()));

                JwtUserPrincipal principal = new JwtUserPrincipal(body.getId(), body.getEmail(), body.getRole());

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, null,
                        authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }

        } catch (Exception e) {
            log.error("JWT validation error: {}", e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        filterChain.doFilter(request, response);
    }

    // DTO đại diện phản hồi từ auth-service
    private static class AuthValidationResponse {
        private boolean valid;
        private Long id;
        private String role;
        private String email;

        public boolean isValid() {
            return valid;
        }

        public String getEmail() {
            return email;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
