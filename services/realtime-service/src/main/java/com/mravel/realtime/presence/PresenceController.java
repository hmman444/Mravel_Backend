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

    /**
     * GET /api/realtime/presence?userIds=1,2,3
     * Returns the subset of requested userIds that are currently online.
     */
    @GetMapping
    public Set<Long> getOnlineUsers(@RequestParam List<Long> userIds) {
        return registry.filterOnline(userIds);
    }
}
