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
@RequestMapping("/api/partner/hotels")
@RequiredArgsConstructor
public class PartnerHotelController {

    private final CatalogPartnerClient catalogClient;
    private final CurrentPartnerService currentPartnerService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> listMyHotels(
            @RequestHeader("Authorization") String bearer,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.listMyHotels(partnerId, status, page, size, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createHotel(
            @RequestHeader("Authorization") String bearer,
            @Valid @RequestBody PartnerDtos.UpsertHotelReq req
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.createHotel(partnerId, req, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateHotel(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String id,
            @Valid @RequestBody PartnerDtos.UpsertHotelReq req
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.updateHotel(id, partnerId, req, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> softDeleteHotel(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String id
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.softDeleteHotel(id, partnerId, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PostMapping("/{id}/pause")
    public ResponseEntity<ApiResponse<?>> pause(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String id
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.pauseHotel(id, partnerId, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PostMapping("/{id}/resume")
    public ResponseEntity<ApiResponse<?>> resume(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String id
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.resumeHotel(id, partnerId, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PostMapping("/{id}/unlock-request")
    public ResponseEntity<ApiResponse<?>> unlockRequest(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String id,
            @Valid @RequestBody PartnerDtos.UnlockRequestReq req
    ) {
        Long partnerId = currentPartnerService.getCurrentPartnerIdOrThrow();
        var resp = catalogClient.requestUnlockHotel(id, partnerId, req, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }
}