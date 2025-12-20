package com.mravel.notification.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.notification.dto.NotificationDtos.*;
import com.mravel.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    public ResponseEntity<ApiResponse<NotificationResponse>> create(@RequestBody CreateNotificationRequest req) {
        return ResponseEntity.ok(ApiResponse.success("Tạo notification thành công", service.create(req)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<NotificationResponse>>> list(
            @RequestParam Long recipientId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(ApiResponse.success("OK", service.list(recipientId, page, size)));
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<ApiResponse<Long>> markRead(
            @PathVariable Long id,
            @RequestParam Long recipientId) {
        long unread = service.markRead(recipientId, id);
        return ResponseEntity.ok(ApiResponse.success("OK", unread));
    }

    @PatchMapping("/read-all")
    public ResponseEntity<ApiResponse<Long>> markAllRead(@RequestParam Long recipientId) {
        long unread = service.markAllRead(recipientId);
        return ResponseEntity.ok(ApiResponse.success("OK", unread));
    }
}
