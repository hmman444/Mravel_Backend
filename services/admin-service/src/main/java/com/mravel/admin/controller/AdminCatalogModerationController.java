package com.mravel.admin.controller;

import com.mravel.admin.client.CatalogClient;
import com.mravel.admin.dto.catalog.AdminCatalogDtos;
import com.mravel.common.response.ApiResponse;
import com.mravel.common.security.JwtUserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/catalog")
@RequiredArgsConstructor
public class AdminCatalogModerationController {

    private final CatalogClient catalogClient;

    // =============== LIST (giữ nguyên như trước) ===============
    @GetMapping("/hotels")
    public ResponseEntity<ApiResponse<?>> listHotels(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String partnerId,
            @RequestParam(required = false) String destinationSlug,
            @RequestParam(required = false) Boolean unlockRequested,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            HttpServletRequest request) {
        return catalogClient.adminListHotels(status, active, partnerId, destinationSlug, unlockRequested, q, page, size,
                extractToken(request));
    }

    @GetMapping("/restaurants")
    public ResponseEntity<ApiResponse<?>> listRestaurants(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String partnerId,
            @RequestParam(required = false) String destinationSlug,
            @RequestParam(required = false) Boolean unlockRequested,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            HttpServletRequest request) {
        return catalogClient.adminListRestaurants(status, active, partnerId, destinationSlug, unlockRequested, q, page,
                size, extractToken(request));
    }

    // =============== HOTEL ACTIONS ===============
    @PostMapping("/hotels/{id}:approve")
    public ResponseEntity<ApiResponse<?>> approveHotel(@PathVariable String id, HttpServletRequest request) {
        Long adminId = currentUserId();
        return catalogClient.adminApproveHotel(id, adminId, extractToken(request));
    }

    @PostMapping("/hotels/{id}:reject")
    public ResponseEntity<ApiResponse<?>> rejectHotel(
            @PathVariable String id,
            @RequestBody @Valid AdminCatalogDtos.ReasonReq body,
            HttpServletRequest request) {
        Long adminId = currentUserId();
        return catalogClient.adminRejectHotel(id, adminId, body.reason(), extractToken(request));
    }

    @PostMapping("/hotels/{id}:block")
    public ResponseEntity<ApiResponse<?>> blockHotel(
            @PathVariable String id,
            @RequestBody @Valid AdminCatalogDtos.ReasonReq body,
            HttpServletRequest request) {
        Long adminId = currentUserId();
        return catalogClient.adminBlockHotel(id, adminId, body.reason(), extractToken(request));
    }

    @PostMapping("/hotels/{id}:unblock")
    public ResponseEntity<ApiResponse<?>> unblockHotel(@PathVariable String id, HttpServletRequest request) {
        Long adminId = currentUserId();
        return catalogClient.adminUnblockHotel(id, adminId, extractToken(request));
    }

    @PostMapping("/restaurants/{id}:approve")
    public ResponseEntity<ApiResponse<?>> approveRestaurant(@PathVariable String id, HttpServletRequest request) {
        Long adminId = currentUserId();
        return catalogClient.adminApproveRestaurant(id, adminId, extractToken(request));
    }

    @PostMapping("/restaurants/{id}:reject")
    public ResponseEntity<ApiResponse<?>> rejectRestaurant(
            @PathVariable String id,
            @RequestBody @Valid AdminCatalogDtos.ReasonReq body,
            HttpServletRequest request) {
        Long adminId = currentUserId();
        return catalogClient.adminRejectRestaurant(id, adminId, body.reason(), extractToken(request));
    }

    @PostMapping("/restaurants/{id}:block")
    public ResponseEntity<ApiResponse<?>> blockRestaurant(
            @PathVariable String id,
            @RequestBody @Valid AdminCatalogDtos.ReasonReq body,
            HttpServletRequest request) {
        Long adminId = currentUserId();
        return catalogClient.adminBlockRestaurant(id, adminId, body.reason(), extractToken(request));
    }

    @PostMapping("/restaurants/{id}:unblock")
    public ResponseEntity<ApiResponse<?>> unblockRestaurant(@PathVariable String id, HttpServletRequest request) {
        Long adminId = currentUserId();
        return catalogClient.adminUnblockRestaurant(id, adminId, extractToken(request));
    }

    private Long currentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof JwtUserPrincipal p)
            return p.getId(); // hoặc getId() tuỳ class của bạn
        throw new RuntimeException("Missing principal");
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer "))
            throw new RuntimeException("Missing token");
        return header.substring(7);
    }
}
