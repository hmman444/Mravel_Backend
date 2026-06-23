package com.mravel.notification.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.notification.dto.NotificationDtos;
import com.mravel.notification.dto.NotificationDtos.*;
import com.mravel.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    /** Internal endpoint — called by other services to persist a notification. */
    @PostMapping
    public ResponseEntity<ApiResponse<NotificationResponse>> create(
            @Valid @RequestBody CreateNotificationRequest req) {
        return ResponseEntity.ok(ApiResponse.success("Tạo notification thành công", service.create(req)));
    }

    /**
     * User-facing inbox.
     * recipientId must match the authenticated user (X-User-Id forwarded by gateway).
     * Optional {@code category} filter: SOCIAL, BOOKING, ACCOUNT, PARTNER, ADMIN.
     */
    @GetMapping
    public ResponseEntity<ApiResponse<NotificationDtos.PagedResult<NotificationResponse>>> list(
            @RequestHeader(value = "X-User-Id", required = false) Long callerId,
            @RequestParam Long recipientId,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {

        if (callerId != null && !callerId.equals(recipientId)) {
            return ResponseEntity.status(403)
                    .body(ApiResponse.error("Bạn không có quyền xem thông báo này"));
        }

        return ResponseEntity.ok(ApiResponse.success("OK",
                service.list(recipientId, category, page, size)));
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<ApiResponse<Long>> markRead(
            @RequestHeader(value = "X-User-Id", required = false) Long callerId,
            @PathVariable Long id,
            @RequestParam Long recipientId) {

        if (callerId != null && !callerId.equals(recipientId)) {
            return ResponseEntity.status(403).body(ApiResponse.error("Forbidden"));
        }

        long unread = service.markRead(recipientId, id);
        return ResponseEntity.ok(ApiResponse.success("OK", unread));
    }

    @PatchMapping("/read-all")
    public ResponseEntity<ApiResponse<Long>> markAllRead(
            @RequestHeader(value = "X-User-Id", required = false) Long callerId,
            @RequestParam Long recipientId) {

        if (callerId != null && !callerId.equals(recipientId)) {
            return ResponseEntity.status(403).body(ApiResponse.error("Forbidden"));
        }

        long unread = service.markAllRead(recipientId);
        return ResponseEntity.ok(ApiResponse.success("OK", unread));
    }
}
