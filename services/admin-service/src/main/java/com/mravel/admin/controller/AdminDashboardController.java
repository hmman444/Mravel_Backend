package com.mravel.admin.controller;

import com.mravel.admin.service.AdminDashboardService;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Dashboard tổng quan admin: tổng hợp số liệu từ booking/catalog/auth/plan.
 * Bảo mật: SecurityConfig đã gắn /api/admin/** -> hasRole('ADMIN').
 */
@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService dashboardService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> dashboard(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(defaultValue = "weekly") String range) {
        return ResponseEntity.ok(ApiResponse.success("OK",
                dashboardService.build(range, extractBearer(authorization))));
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
