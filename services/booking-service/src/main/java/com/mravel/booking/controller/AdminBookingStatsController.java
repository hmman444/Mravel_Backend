package com.mravel.booking.controller;

import com.mravel.booking.client.AuthValidateClient;
import com.mravel.booking.service.AdminBookingStatsService;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Thống kê booking phục vụ admin dashboard. Chỉ ADMIN (xác thực qua auth-service).
 * Được admin-service gọi qua BookingAdminClient, forward Bearer token của admin.
 */
@RestController
@RequestMapping("/api/booking/admin/dashboard")
@RequiredArgsConstructor
public class AdminBookingStatsController {

    private final AuthValidateClient authValidateClient;
    private final AdminBookingStatsService statsService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> stats(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(defaultValue = "weekly") String range) {
        authValidateClient.requireAdminId(authorization); // ném SecurityException nếu không phải ADMIN
        return ResponseEntity.ok(ApiResponse.success("OK", statsService.getStats(range)));
    }
}
