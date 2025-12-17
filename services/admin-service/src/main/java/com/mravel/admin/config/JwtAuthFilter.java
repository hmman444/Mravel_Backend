package com.mravel.admin.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import com.mravel.common.security.JwtUserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.auth.base-url}")
    private String authBaseUrl;

    @Value("${mravel.services.auth.validate-path}")
    private String validatePath;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(header.substring(7));

            ResponseEntity<AuthResponse> resp = restTemplate.exchange(
                    authBaseUrl + validatePath,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    AuthResponse.class);

            AuthResponse body = resp.getBody();

            if (body == null || !body.isValid()) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            // 🔒 CHỐT CHẶN
            if (!"ADMIN".equalsIgnoreCase(body.getRole())) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            }

            var auth = new UsernamePasswordAuthenticationToken(
                    new JwtUserPrincipal(body.getId(), body.getEmail(), body.getRole()),
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @Data
    static class AuthResponse {
        private boolean valid;
        private Long id;
        private String email;
        private String role;
    }
}
