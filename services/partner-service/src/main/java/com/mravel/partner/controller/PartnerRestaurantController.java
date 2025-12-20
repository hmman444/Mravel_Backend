package com.mravel.partner.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.partner.client.CatalogPartnerClient;
import com.mravel.partner.dto.PartnerDtos;
import com.mravel.partner.security.CurrentPartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner/restaurants")
@RequiredArgsConstructor
public class PartnerRestaurantController {

    private final CatalogPartnerClient catalogClient;
    private final CurrentPartnerService currentPartnerService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> listMyRestaurants(
            @RequestHeader("Authorization") String bearer,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.listMyRestaurants(partnerId, status, page, size, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createRestaurant(
            @RequestHeader("Authorization") String bearer,
            @Valid @RequestBody PartnerDtos.UpsertRestaurantReq req
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.createRestaurant(partnerId, req, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateRestaurant(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String id,
            @Valid @RequestBody PartnerDtos.UpsertRestaurantReq req
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.updateRestaurant(id, partnerId, req, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> softDeleteRestaurant(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String id
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.softDeleteRestaurant(id, partnerId, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PostMapping("/{id}/pause")
    public ResponseEntity<ApiResponse<?>> pause(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String id
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.pauseRestaurant(id, partnerId, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PostMapping("/{id}/resume")
    public ResponseEntity<ApiResponse<?>> resume(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String id
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.resumeRestaurant(id, partnerId, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PostMapping("/{id}/unlock-request")
    public ResponseEntity<ApiResponse<?>> unlockRequest(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String id,
            @Valid @RequestBody PartnerDtos.UnlockRequestReq req
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.requestUnlockRestaurant(id, partnerId, req, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }
}