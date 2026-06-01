package com.mravel.user.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.user.dto.VisibilityContextResponse;
import com.mravel.user.service.BlockService;
import com.mravel.user.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Endpoint NỘI BỘ service-to-service (không dành cho FE).
 * Bảo vệ bằng shared-secret header X-Internal-Secret; gateway có route /api/users/**
 * nhưng caller ngoài không biết secret nên bị 403.
 */
@RestController
@RequestMapping("/api/users/internal")
@RequiredArgsConstructor
public class InternalUserController {

    private final FriendService friendService;
    private final BlockService blockService;

    @Value("${mravel.internal.secret:}")
    private String internalSecret;

    private void assertInternal(String secret) {
        if (internalSecret == null || internalSecret.isBlank() || !internalSecret.equals(secret)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Internal access denied");
        }
    }

    /** Gộp friendIds + blockedIds (hợp nhất 2 chiều) trong 1 lần gọi — cho tầng feed/search. */
    @GetMapping("/visibility-context/{userId}")
    public ApiResponse<VisibilityContextResponse> visibilityContext(
            @RequestHeader(value = "X-Internal-Secret", required = false) String secret,
            @PathVariable Long userId) {
        assertInternal(secret);
        List<Long> blocked = blockService.getAllBlockedIds(userId);
        List<Long> friends = friendService.getFriendIds(userId);
        if (!blocked.isEmpty()) {
            friends = friends.stream().filter(id -> !blocked.contains(id)).toList();
        }
        return ApiResponse.success("OK", new VisibilityContextResponse(friends, blocked));
    }

    /** Kiểm tra block 2 chiều giữa a và b — cho tầng WRITE (chat/notification). */
    @GetMapping("/block-check")
    public ApiResponse<Boolean> blockCheck(
            @RequestHeader(value = "X-Internal-Secret", required = false) String secret,
            @RequestParam Long a,
            @RequestParam Long b) {
        assertInternal(secret);
        return ApiResponse.success("OK", blockService.isBlocked(a, b));
    }
}
