package com.mravel.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mravel.admin.client.CatalogClient;
import com.mravel.admin.dto.place.PlaceAdminDtos.UpsertPlaceRequest;
import com.mravel.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/places")
@RequiredArgsConstructor
public class AdminPlaceController {

    private final CatalogClient catalogClient;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> search(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        String bearer = extractBearer(authorization);
        return catalogClient.searchPlaces(q, page, size, bearer);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ApiResponse<?>> detail(
            @RequestHeader("Authorization") String authorization,
            @PathVariable String slug) {
        String bearer = extractBearer(authorization);
        return catalogClient.getPlaceDetailBySlug(slug, bearer);
    }

    @GetMapping("/{slug}/children")
    public ResponseEntity<ApiResponse<?>> children(
            @RequestHeader("Authorization") String authorization,
            @PathVariable String slug,
            @RequestParam(required = false, defaultValue = "POI") String kind,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        String bearer = extractBearer(authorization);
        return catalogClient.getChildrenByParentSlug(slug, kind, page, size, bearer);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(
            @RequestHeader("Authorization") String authorization,
            @RequestBody UpsertPlaceRequest req) {
        String bearer = extractBearer(authorization);
        return catalogClient.createPlace(req, bearer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(
            @RequestHeader("Authorization") String authorization,
            @PathVariable String id,
            @RequestBody UpsertPlaceRequest req) {
        String bearer = extractBearer(authorization);
        return catalogClient.updatePlace(id, req, bearer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> softDelete(
            @RequestHeader("Authorization") String authorization,
            @PathVariable String id) {
        String bearer = extractBearer(authorization);
        return catalogClient.softDeletePlace(id, bearer);
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
