package com.mravel.auth.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mravel.auth.config.JwtUtil;
import com.mravel.auth.model.Role;
import com.mravel.auth.service.AdminUserService;
import com.mravel.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/users")
@RequiredArgsConstructor
public class AuthAdminUserController {

    private final AdminUserService adminUserService;
    private final JwtUtil jwtUtils;

    /** Endpoint quản trị user → bắt buộc token role ADMIN (auth-service không có resource-server riêng). */
    private boolean isAdmin(String authHeader) {
        try {
            String token = (authHeader != null && authHeader.startsWith("Bearer "))
                    ? authHeader.substring(7)
                    : null;
            return token != null && "ADMIN".equalsIgnoreCase(jwtUtils.extractRole(token));
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> list(
            @RequestParam(defaultValue = "USER,PARTNER") String role,
            @RequestParam(required = false, defaultValue = "") String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        List<Role> roles = Arrays.stream(role.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(String::toUpperCase)
                .map(Role::valueOf)
                .toList();

        return ResponseEntity.ok(ApiResponse.success("OK", adminUserService.list(roles, q, page, size)));
    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<?>> stats() {
        return ResponseEntity.ok(ApiResponse.success("OK", adminUserService.stats()));
    }

    @PatchMapping("/{id}/lock")
    public ResponseEntity<ApiResponse<?>> lock(@PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (!isAdmin(authHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ApiResponse.error("Chỉ ADMIN mới được khóa tài khoản"));
        }
        adminUserService.lock(id);
        return ResponseEntity.ok(ApiResponse.success("Đã khóa tài khoản", null));
    }

    @PatchMapping("/{id}/unlock")
    public ResponseEntity<ApiResponse<?>> unlock(@PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (!isAdmin(authHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ApiResponse.error("Chỉ ADMIN mới được mở khóa tài khoản"));
        }
        adminUserService.unlock(id);
        return ResponseEntity.ok(ApiResponse.success("Đã mở khóa tài khoản", null));
    }
}
