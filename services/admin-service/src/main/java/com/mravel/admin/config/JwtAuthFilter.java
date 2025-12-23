package com.mravel.admin.config;

import com.mravel.common.security.JwtUserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

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
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws IOException, ServletException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        try {
            String base = Objects.requireNonNull(authBaseUrl, "mravel.services.auth.base-url must not be null");
            String path = Objects.requireNonNull(validatePath, "mravel.services.auth.validate-path must not be null");

            URI uri = UriComponentsBuilder.fromHttpUrl(Objects.requireNonNull(base))
                    .path(path)
                    .build(true)
                    .toUri();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(Objects.requireNonNull(header.substring(7), "token must not be null"));

            RequestEntity<Void> req = new RequestEntity<>(
                    headers,
                    Objects.requireNonNull(HttpMethod.GET),
                    Objects.requireNonNull(uri));

            ResponseEntity<AuthResponse> resp = restTemplate.exchange(req, AuthResponse.class);

            AuthResponse body = resp.getBody();
            if (body == null || !body.isValid()) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            // if (!"ADMIN".equalsIgnoreCase(body.getRole())) {
            // response.setStatus(HttpStatus.FORBIDDEN.value());
            // return;
            // }
            var auth = new UsernamePasswordAuthenticationToken(
                    new JwtUserPrincipal(body.getId(), body.getEmail(), body.getRole()),
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + body.getRole().toUpperCase())));

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