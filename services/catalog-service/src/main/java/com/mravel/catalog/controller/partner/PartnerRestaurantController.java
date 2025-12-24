package com.mravel.catalog.controller.partner;

import com.mravel.catalog.dto.partner.PartnerCatalogDtos;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.service.partner.PartnerRestaurantService;
import com.mravel.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalog/partners/restaurants")
@RequiredArgsConstructor
public class PartnerRestaurantController {

    private final PartnerRestaurantService service;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<RestaurantDoc>>> list(
            @RequestParam Long partnerId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        var data = service.listMyRestaurants(partnerId, status, page, size);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantDoc>> getByIdForPartner(
            @PathVariable String id,
            @RequestParam Long partnerId
    ) {
        var doc = service.getByIdForPartner(id, partnerId);
        return ResponseEntity.ok(ApiResponse.success("OK", doc));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RestaurantDoc>> create(
        @RequestHeader(value = "Authorization", required = false) String bearer,
        @RequestBody @Valid PartnerCatalogDtos.UpsertWrapper<PartnerCatalogDtos.UpsertRestaurantReq> body
    ) {
        var data = service.create(body.partnerId(), body.pendingReason(), body.payload(), bearer);
        return ResponseEntity.ok(ApiResponse.<RestaurantDoc>success("OK", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantDoc>> update(
        @RequestHeader(value = "Authorization", required = false) String bearer,
        @PathVariable String id,
        @RequestBody @Valid PartnerCatalogDtos.UpsertWrapper<PartnerCatalogDtos.UpsertRestaurantReq> body
    ) {
        var data = service.update(id, body.partnerId(), body.pendingReason(), body.payload(), bearer);
        return ResponseEntity.ok(ApiResponse.<RestaurantDoc>success("OK", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantDoc>> softDelete(
            @PathVariable String id,
            @RequestParam Long partnerId
    ) {
        var data = service.softDelete(id, partnerId);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @PostMapping("/{id}/pause")
    public ResponseEntity<ApiResponse<RestaurantDoc>> pause(
            @PathVariable String id,
            @RequestParam Long partnerId
    ) {
        var data = service.pause(id, partnerId);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @PostMapping("/{id}/resume")
    public ResponseEntity<ApiResponse<RestaurantDoc>> resume(
            @PathVariable String id,
            @RequestParam Long partnerId
    ) {
        var data = service.resume(id, partnerId);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @PostMapping("/{id}/unlock-request")
    public ResponseEntity<ApiResponse<RestaurantDoc>> unlockRequest(
            @PathVariable String id,
            @RequestBody @Valid PartnerCatalogDtos.UnlockRequestWrapper body
    ) {
        var data = service.unlockRequest(id, body.partnerId(), body.reason());
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }
}