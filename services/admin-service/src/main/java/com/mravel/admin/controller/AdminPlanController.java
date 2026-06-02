package com.mravel.admin.controller;

import com.mravel.admin.client.PlanAdminClient;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Admin: thống kê + kiểm duyệt plan/post. Proxy sang plan-service.
 * Bảo mật: SecurityConfig đã gắn /api/admin/** -> hasRole('ADMIN').
 */
@RestController
@RequestMapping("/api/admin/plans")
@RequiredArgsConstructor
public class AdminPlanController {

    private final PlanAdminClient planAdminClient;

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<?>> stats(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(defaultValue = "30") int days) {
        return planAdminClient.stats(days, extractBearer(authorization));
    }

    @GetMapping("/reports")
    public ResponseEntity<ApiResponse<?>> reports(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        return planAdminClient.listReports(status, page, size, extractBearer(authorization));
    }

    @PatchMapping("/{id}/takedown")
    public ResponseEntity<ApiResponse<?>> takedown(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id,
            @RequestParam(required = false) String reason) {
        return planAdminClient.takedown(id, reason, extractBearer(authorization));
    }

    @PostMapping("/reports/{id}/resolve")
    public ResponseEntity<ApiResponse<?>> resolveReport(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id,
            @RequestParam(defaultValue = "NONE") String action,
            @RequestParam(required = false) String reason) {
        return planAdminClient.resolveReport(id, action, reason, extractBearer(authorization));
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
