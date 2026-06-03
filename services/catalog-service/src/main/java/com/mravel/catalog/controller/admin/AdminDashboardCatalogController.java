package com.mravel.catalog.controller.admin;

import com.mravel.catalog.dto.admin.AdminDashboardDtos.ResolveRequest;
import com.mravel.catalog.service.admin.AdminDashboardCatalogService;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Số liệu dịch vụ + resolve partner cho admin dashboard (gọi từ admin-service). */
@RestController
@RequestMapping("/api/catalog/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardCatalogController {

    private final AdminDashboardCatalogService service;

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<?>> summary() {
        return ResponseEntity.ok(ApiResponse.success("OK", service.summary()));
    }

    @PostMapping("/partner-resolve")
    public ResponseEntity<ApiResponse<?>> resolvePartners(@RequestBody ResolveRequest req) {
        return ResponseEntity.ok(ApiResponse.success("OK",
                service.resolvePartners(
                        req == null ? null : req.hotelIds(),
                        req == null ? null : req.restaurantIds())));
    }
}
