package com.mravel.realtime.presence;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/realtime/presence")
@RequiredArgsConstructor
public class PresenceController {

    private final UserPresenceRegistry registry;
    private final PresenceBlockGuard blockGuard;

    /**
     * GET /api/realtime/presence?userIds=1,2,3
     * Returns the subset of requested userIds that are currently online.
     * Nếu có X-User-Id (viewer), loại bỏ những người bị chặn 2 chiều để không lộ online status.
     */
    @GetMapping
    public Set<Long> getOnlineUsers(
            @RequestHeader(value = "X-User-Id", required = false) Long viewerId,
            @RequestParam List<Long> userIds) {
        Set<Long> online = registry.filterOnline(userIds);
        if (viewerId != null && !online.isEmpty()) {
            online.removeIf(id -> blockGuard.isBlocked(viewerId, id));
        }
        return online;
    }
}
