package com.mravel.realtime.presence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketSessionListener {

    private final UserPresenceRegistry registry;

    @EventListener
    public void onConnect(SessionConnectedEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        Long userId = getUserId(accessor);
        String sessionId = accessor.getSessionId();
        if (userId != null && sessionId != null) {
            registry.connect(userId, sessionId);
            log.debug("WS presence connect userId={} sessionId={}", userId, sessionId);
        }
    }

    @EventListener
    public void onDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        Long userId = getUserId(accessor);
        String sessionId = accessor.getSessionId();
        if (userId != null && sessionId != null) {
            registry.disconnect(userId, sessionId);
            log.debug("WS presence disconnect userId={} sessionId={}", userId, sessionId);
        }
    }

    private Long getUserId(StompHeaderAccessor accessor) {
        Map<String, Object> attrs = accessor.getSessionAttributes();
        if (attrs == null) return null;
        Object val = attrs.get("userId");
        if (val instanceof Long l) return l;
        if (val instanceof Integer i) return i.longValue();
        return null;
    }
}
