package com.mravel.realtime.presence;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class UserPresenceRegistry {

    /** userId → set of active sessionIds */
    private final ConcurrentHashMap<Long, Set<String>> sessions = new ConcurrentHashMap<>();

    public void connect(Long userId, String sessionId) {
        sessions.computeIfAbsent(userId, k -> ConcurrentHashMap.newKeySet()).add(sessionId);
    }

    public void disconnect(Long userId, String sessionId) {
        Set<String> userSessions = sessions.get(userId);
        if (userSessions != null) {
            userSessions.remove(sessionId);
            if (userSessions.isEmpty()) {
                sessions.remove(userId, userSessions);
            }
        }
    }

    public boolean isOnline(Long userId) {
        Set<String> s = sessions.get(userId);
        return s != null && !s.isEmpty();
    }

    public Set<Long> filterOnline(Collection<Long> userIds) {
        return userIds.stream().filter(this::isOnline).collect(Collectors.toSet());
    }
}
