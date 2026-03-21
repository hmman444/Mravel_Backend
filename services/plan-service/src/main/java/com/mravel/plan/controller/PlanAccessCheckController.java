package com.mravel.plan.controller;

import com.mravel.plan.service.PlanPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Phase 1c — lightweight endpoint used by realtime-service to verify
 * WebSocket SUBSCRIBE permission without loading the full board.
 *
 * Called by StompAuthChannelInterceptor on every SUBSCRIBE to /topic/plans/{planId}/board.
 * Response is a simple {canAccess: true/false} — no board data loaded.
 */
@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanAccessCheckController {

    private final PlanPermissionService permissionService;

    /**
     * GET /api/plans/{planId}/board/access-check?userId={userId}
     *
     * Returns 200 {canAccess:true} if user may view the plan board.
     * Returns 200 {canAccess:false} if not (caller decides what to do).
     * No authentication filter needed here — the realtime-service calls
     * this internally from its own backend context, not from the browser.
     */
    @GetMapping("/{planId}/board/access-check")
    public ResponseEntity<Map<String, Boolean>> check(
            @PathVariable Long planId,
            @RequestParam Long userId) {
        // isFriend=false: conservative default for internal check.
        // For FRIENDS visibility the subscription will still be rejected
        // unless the user is an explicit member. This is intentional —
        // friend-based visibility is resolved at board-load time, not here.
        boolean canAccess = permissionService.canView(planId, userId, false);
        return ResponseEntity.ok(Map.of("canAccess", canAccess));
    }
}
