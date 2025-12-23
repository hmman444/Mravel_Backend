// src/main/java/com/mravel/admin/controller/AdminUserController.java
package com.mravel.admin.controller;

import com.mravel.admin.client.AuthAdminClient;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/auth/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AuthAdminClient authAdminClient;

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
            @PathVariable Long id) {
        String bearer = extractBearer(authorization);
        return authAdminClient.lock(id, bearer);
    }

    @PatchMapping("/{id}/unlock")
    public ResponseEntity<ApiResponse<?>> unlock(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id) {
        String bearer = extractBearer(authorization);
        return authAdminClient.unlock(id, bearer);
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
