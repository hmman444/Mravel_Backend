package com.mravel.auth.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mravel.auth.model.Role;
import com.mravel.auth.service.AdminUserService;
import com.mravel.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/users")
@RequiredArgsConstructor
public class AuthAdminUserController {

    private final AdminUserService adminUserService;

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

    @PatchMapping("/{id}/lock")
    public ResponseEntity<ApiResponse<?>> lock(@PathVariable Long id) {
        adminUserService.lock(id);
        return ResponseEntity.ok(ApiResponse.success("Đã khóa tài khoản", null));
    }

    @PatchMapping("/{id}/unlock")
    public ResponseEntity<ApiResponse<?>> unlock(@PathVariable Long id) {
        adminUserService.unlock(id);
        return ResponseEntity.ok(ApiResponse.success("Đã mở khóa tài khoản", null));
    }
}
