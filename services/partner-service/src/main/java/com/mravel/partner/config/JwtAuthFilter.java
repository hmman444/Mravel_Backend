package com.mravel.partner.config;

import com.mravel.partner.security.JwtUserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

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
            filterChain.doFilter(request, response);
            return;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(header.substring(7));

            ResponseEntity<AuthResponse> resp = restTemplate.exchange(
                    authBaseUrl + validatePath,
                    HttpMethod.GET,
                    new HttpEntity<>(null, headers),
                    AuthResponse.class
            );

            AuthResponse body = resp.getBody();

            if (body == null || !body.isValid()) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            // 🔒 CHỐT CHẶN: chỉ PARTNER được vào partner-service
            if (!"PARTNER".equalsIgnoreCase(body.getRole())) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            }

            var auth = new UsernamePasswordAuthenticationToken(
                new JwtUserPrincipal(
                    body.getId(),
                    body.getEmail(),
                    body.getRole(),
                    body.getFullname(),
                    body.getAvatar()
                ),
                null,
                List.of(new SimpleGrantedAuthority("ROLE_PARTNER"))
            );

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
        private String fullname;
        private String avatar;
    }
}