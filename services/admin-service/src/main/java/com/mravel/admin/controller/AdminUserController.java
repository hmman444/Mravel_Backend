// src/main/java/com/mravel/admin/controller/AdminUserController.java
package com.mravel.admin.controller;

import com.mravel.admin.client.AuthAdminClient;
import com.mravel.admin.client.NotificationClient;
import com.mravel.common.notification.NotificationTypes;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/auth/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AuthAdminClient authAdminClient;
    private final NotificationClient notificationClient;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> list(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(required = false, defaultValue = "USER") String role,
            @RequestParam(required = false, defaultValue = "") String q,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        String bearer = extractBearer(authorization);
        return authAdminClient.listUsers(role, q, page, size, bearer);
    }

    @PatchMapping("/{id}/lock")
    public ResponseEntity<ApiResponse<?>> lock(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader(value = "X-User-Id", required = false) Long adminId,
            @PathVariable Long id) {
        String bearer = extractBearer(authorization);
        ResponseEntity<ApiResponse<?>> result = authAdminClient.lock(id, bearer);
        notificationClient.createNotification(
                id, adminId,
                NotificationTypes.ACCOUNT_LOCKED,
                "Tài khoản bị khóa",
                "Tài khoản của bạn đã bị quản trị viên khóa",
                Map.of("deepLink", "/account/profile"));
        return result;
    }

    @PatchMapping("/{id}/unlock")
    public ResponseEntity<ApiResponse<?>> unlock(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader(value = "X-User-Id", required = false) Long adminId,
            @PathVariable Long id) {
        String bearer = extractBearer(authorization);
        ResponseEntity<ApiResponse<?>> result = authAdminClient.unlock(id, bearer);
        notificationClient.createNotification(
                id, adminId,
                NotificationTypes.ACCOUNT_UNLOCKED,
                "Tài khoản đã mở khóa",
                "Tài khoản của bạn đã được quản trị viên mở khóa",
                Map.of("deepLink", "/account/profile"));
        return result;
    }

    private String extractBearer(String authorizationHeader) {
        if (authorizationHeader == null)
            return "";
        String s = authorizationHeader.trim();
        if (s.toLowerCase().startsWith("bearer "))
            return s.substring(7).trim();
        return s;
    }
}
