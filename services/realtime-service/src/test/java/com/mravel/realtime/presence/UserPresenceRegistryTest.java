package com.mravel.realtime.presence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserPresenceRegistryTest {

    private UserPresenceRegistry registry;

    @BeforeEach
    void setUp() {
        registry = new UserPresenceRegistry();
    }

    // ── connect / isOnline ────────────────────────────────────────────────────

    @Test
    void connect_makesUserOnline() {
        registry.connect(1L, "session-a");

        assertThat(registry.isOnline(1L)).isTrue();
    }

    @Test
    void isOnline_unknownUser_returnsFalse() {
        assertThat(registry.isOnline(99L)).isFalse();
    }

    // ── disconnect ────────────────────────────────────────────────────────────

    @Test
    void disconnect_lastSession_makesUserOffline() {
        registry.connect(1L, "session-a");
        registry.disconnect(1L, "session-a");

        assertThat(registry.isOnline(1L)).isFalse();
    }

    @Test
    void disconnect_oneOfTwoSessions_userStaysOnline() {
        registry.connect(1L, "session-a");
        registry.connect(1L, "session-b");
        registry.disconnect(1L, "session-a");

        assertThat(registry.isOnline(1L)).isTrue();
    }

    @Test
    void disconnect_allSessions_userGoesOffline() {
        registry.connect(1L, "session-a");
        registry.connect(1L, "session-b");
        registry.disconnect(1L, "session-a");
        registry.disconnect(1L, "session-b");

        assertThat(registry.isOnline(1L)).isFalse();
    }

    @Test
    void disconnect_nonExistentSession_doesNotThrow() {
        registry.connect(1L, "session-a");
        registry.disconnect(1L, "session-x"); // không tồn tại

        assertThat(registry.isOnline(1L)).isTrue();
    }

    @Test
    void disconnect_userNotInRegistry_doesNotThrow() {
        registry.disconnect(99L, "session-x"); // user chưa từng connect

        assertThat(registry.isOnline(99L)).isFalse();
    }

    // ── filterOnline ──────────────────────────────────────────────────────────

    @Test
    void filterOnline_returnsOnlyOnlineUsers() {
        registry.connect(1L, "s1");
        registry.connect(3L, "s3");
        // user 2 not connected

        Set<Long> result = registry.filterOnline(List.of(1L, 2L, 3L));

        assertThat(result).containsExactlyInAnyOrder(1L, 3L);
    }

    @Test
    void filterOnline_emptyInput_returnsEmptySet() {
        registry.connect(1L, "s1");

        Set<Long> result = registry.filterOnline(List.of());

        assertThat(result).isEmpty();
    }

    @Test
    void filterOnline_noneOnline_returnsEmptySet() {
        Set<Long> result = registry.filterOnline(List.of(1L, 2L));

        assertThat(result).isEmpty();
    }

    @Test
    void filterOnline_resultIsMutable() {
        registry.connect(1L, "s1");

        Set<Long> result = registry.filterOnline(List.of(1L));
        result.remove(1L); // phải không ném UnsupportedOperationException

        assertThat(result).isEmpty();
    }
}
