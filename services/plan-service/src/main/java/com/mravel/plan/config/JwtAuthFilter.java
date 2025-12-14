package com.mravel.plan.config;

import com.mravel.plan.security.JwtUserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.auth.base-url}")
    private String authBaseUrl;

    @Value("${mravel.services.auth.validate-path}")
    private String authValidatePath;

    private String authValidateUrl() {
        return authBaseUrl + authValidatePath;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Không có token -> cho qua
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);

            ResponseEntity<AuthValidationResponse> resp = restTemplate.exchange(
                    authValidateUrl(),
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    AuthValidationResponse.class);

            AuthValidationResponse body = resp.getBody();

            if (resp.getStatusCode() == HttpStatus.OK && body != null && body.isValid()) {
                var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + body.getRole()));

                JwtUserPrincipal principal = new JwtUserPrincipal(body.getId(), body.getEmail(), body.getRole());

                var auth = new UsernamePasswordAuthenticationToken(principal, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);

                filterChain.doFilter(request, response);
                return;
            }

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } catch (RestClientException ex) {
            log.warn("JWT validation error calling auth-service: {}", ex.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @Data
    private static class AuthValidationResponse {
        private boolean valid;
        private Long id;
        private String role;
        private String email;
    }
}