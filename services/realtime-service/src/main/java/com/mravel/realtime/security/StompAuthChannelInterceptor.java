package com.mravel.realtime.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class StompAuthChannelInterceptor implements ChannelInterceptor {

    private static final Pattern BOARD_TOPIC = Pattern.compile("^/topic/plans/(\\d+)/board(/v2)?$");

    private final Key signingKey;
    private final RestTemplate restTemplate;
    private final String planServiceBaseUrl;
    private final String userServiceBaseUrl;

    public StompAuthChannelInterceptor(
            @Value("${mravel.jwt.secret}") String secret,
            @Value("${mravel.services.plan.base-url:http://localhost:8086}") String planServiceBaseUrl,
            @Value("${mravel.services.user.base-url:http://localhost:8082}") String userServiceBaseUrl) {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(2000);
        requestFactory.setReadTimeout(2000);
        this.restTemplate = new RestTemplate(requestFactory);
        this.planServiceBaseUrl = planServiceBaseUrl;
        this.userServiceBaseUrl = userServiceBaseUrl;
    }

    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor == null)
            return message;
        StompCommand command = accessor.getCommand();
        if (command == null)
            return message;

        // CONNECT: validate JWT
        if (StompCommand.CONNECT.equals(command)) {
            String authHeader = accessor.getFirstNativeHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                log.warn("ws_subscription_rejected reason=missing_auth sessionId={}",
                        accessor.getSessionId());
                throw new IllegalArgumentException("Missing or invalid Authorization header on CONNECT");
            }

            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(signingKey)
                        .setAllowedClockSkewSeconds(60)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                Long userId = extractUserId(claims);
                if (userId == null) {
                    userId = resolveUserIdByEmail(claims.getSubject());
                }

                if (userId == null) {
                    log.warn("ws_subscription_rejected reason=missing_user_id_claim sessionId={}",
                            accessor.getSessionId());
                    throw new IllegalArgumentException("JWT token does not contain a resolvable user id");
                }

                if (accessor.getSessionAttributes() != null && userId != null) {
                    accessor.getSessionAttributes().put("userId", userId);
                    accessor.getSessionAttributes().put("accessToken", token);
                }
                log.debug("WS CONNECT accepted userId={} sessionId={}", userId, accessor.getSessionId());

            } catch (JwtException e) {
                log.warn("ws_subscription_rejected reason=invalid_jwt sessionId={} msg={}",
                        accessor.getSessionId(), e.getMessage());
                throw new IllegalArgumentException("Invalid or expired JWT token");
            }
        }

        // SUBSCRIBE: enforce plan membership
        if (StompCommand.SUBSCRIBE.equals(command)) {
            String destination = accessor.getDestination();
            if (destination == null)
                return message;

            Matcher m = BOARD_TOPIC.matcher(destination);
            if (m.matches()) {
                long planId = Long.parseLong(m.group(1));
                Long userId = getUserIdFromSession(accessor);

                if (userId == null) {
                    log.warn("ws_subscription_rejected planId={} reason=no_user_in_session destination={}",
                            planId, destination);
                    throw new IllegalArgumentException("Unauthenticated SUBSCRIBE to board topic");
                }

                if (!hasAccess(accessor, planId, userId)) {
                    log.warn("ws_subscription_rejected planId={} userId={} reason=not_member",
                            planId, userId);
                    throw new IllegalArgumentException("Access denied to plan " + planId);
                }

                log.debug("WS SUBSCRIBE accepted planId={} userId={}", planId, userId);
            }
        }

        return message;
    }

    // helpers ─

    private Long extractUserId(Claims claims) {
        // Backward/forward compatible claim extraction:
        // - auth-service currently puts user id in claim "id"
        // - older/newer tokens may use "userId"
        Object idValue = claims.get("id");
        if (idValue == null) {
            idValue = claims.get("userId");
        }

        if (idValue instanceof Number numberValue) {
            return numberValue.longValue();
        }

        if (idValue instanceof String stringValue && !stringValue.isBlank()) {
            try {
                return Long.parseLong(stringValue);
            } catch (NumberFormatException ignored) {
                // Fall through to subject parsing.
            }
        }

        try {
            return Long.parseLong(claims.getSubject());
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    private Long getUserIdFromSession(StompHeaderAccessor accessor) {
        Map<String, Object> attrs = accessor.getSessionAttributes();
        if (attrs == null)
            return null;
        Object val = attrs.get("userId");
        if (val instanceof Long l)
            return l;
        if (val instanceof Integer i)
            return i.longValue();
        return null;
    }

    private String getAccessTokenFromSession(StompHeaderAccessor accessor) {
        Map<String, Object> attrs = accessor.getSessionAttributes();
        if (attrs == null) {
            return null;
        }
        Object token = attrs.get("accessToken");
        if (token instanceof String tokenStr && !tokenStr.isBlank()) {
            return tokenStr;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private boolean hasAccess(StompHeaderAccessor accessor, long planId, long userId) {
        try {
            String url = planServiceBaseUrl + "/api/plans/" + planId
                    + "/board/access-check?userId=" + userId;

            HttpHeaders headers = new HttpHeaders();
            String token = getAccessTokenFromSession(accessor);
            if (token != null && !token.isBlank()) {
                headers.setBearerAuth(token);
            }

            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    Map.class);

            Map<String, Object> body = response.getBody();
            return body != null && Boolean.TRUE.equals(body.get("canAccess"));
        } catch (Exception e) {
            if (e instanceof HttpStatusCodeException ex) {
                int code = ex.getStatusCode().value();
                if (code == 401 || code == 403) {
                    log.warn("Access check denied planId={} userId={} status={}", planId, userId, code);
                    return false;
                }
            }
            // Keep service available if access-check endpoint is temporarily unreachable.
            log.warn("Access check failed for planId={} userId={} — failing open: {}",
                    planId, userId, e.getMessage());
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    private Long resolveUserIdByEmail(String email) {
        if (email == null || email.isBlank()) {
            return null;
        }
        try {
            String url = userServiceBaseUrl + "/api/users/by-email?email=" + email;
            Map<String, Object> user = restTemplate.getForObject(url, Map.class);
            if (user == null) {
                return null;
            }
            Object idValue = user.get("id");
            if (idValue instanceof Number numberValue) {
                return numberValue.longValue();
            }
            if (idValue instanceof String stringValue && !stringValue.isBlank()) {
                return Long.parseLong(stringValue);
            }
            return null;
        } catch (Exception e) {
            log.warn("Could not resolve user id from email {}: {}", email, e.getMessage());
            return null;
        }
    }
}
