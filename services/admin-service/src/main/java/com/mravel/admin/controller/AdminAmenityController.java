package com.mravel.admin.controller;

import com.mravel.admin.client.CatalogClient;
import com.mravel.admin.dto.amenity.AmenityUpsertRequest;
import com.mravel.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/amenities")
@RequiredArgsConstructor
public class AdminAmenityController {

    private final CatalogClient catalogClient;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody AmenityUpsertRequest req, HttpServletRequest request) {
        return catalogClient.createAmenity(req, extractToken(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(
            @PathVariable String id,
            @RequestBody AmenityUpsertRequest req,
            HttpServletRequest request) {
        return catalogClient.updateAmenity(id, req, extractToken(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable String id, HttpServletRequest request) {
        return catalogClient.deleteAmenity(id, extractToken(request));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> list(
            @RequestParam(required = false) String scope,
            @RequestParam(defaultValue = "true") boolean active,
            @RequestParam(defaultValue = "false") boolean grouped,
            HttpServletRequest request) {
        return catalogClient.listAmenities(scope, active, grouped, extractToken(request));
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer "))
            throw new RuntimeException("Missing token");
        return header.substring(7);
    }
}
