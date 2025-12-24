package com.mravel.catalog.controller.partner;

import com.mravel.catalog.dto.partner.PartnerCatalogDtos;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.service.partner.PartnerHotelService;
import com.mravel.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalog/partners/hotels")
@RequiredArgsConstructor
public class PartnerHotelController {

    private final PartnerHotelService service;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<HotelDoc>>> list(
            @RequestParam Long partnerId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        var data = service.listMyHotels(partnerId, status, page, size);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HotelDoc>> getByIdForPartner(
            @PathVariable String id,
            @RequestParam Long partnerId
    ) {
        var doc = service.getByIdForPartner(id, partnerId);
        return ResponseEntity.ok(ApiResponse.success("OK", doc));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HotelDoc>> create(
            @RequestHeader(value="Authorization", required=false) String bearer,
            @RequestBody @Valid PartnerCatalogDtos.UpsertWrapper<PartnerCatalogDtos.UpsertHotelReq> body
    ) {
        var data = service.create(body.partnerId(), body.pendingReason(), body.payload(), bearer);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

   @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HotelDoc>> update(
            @RequestHeader(value="Authorization", required=false) String bearer,
            @PathVariable String id,
            @RequestBody @Valid PartnerCatalogDtos.UpsertWrapper<PartnerCatalogDtos.UpsertHotelReq> body
    ) {
        var data = service.update(id, body.partnerId(), body.pendingReason(), body.payload(), bearer);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<HotelDoc>> softDelete(
            @PathVariable String id,
            @RequestParam Long partnerId
    ) {
        var data = service.softDelete(id, partnerId);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @PostMapping("/{id}/pause")
    public ResponseEntity<ApiResponse<HotelDoc>> pause(
            @PathVariable String id,
            @RequestParam Long partnerId
    ) {
        var data = service.pause(id, partnerId);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @PostMapping("/{id}/resume")
    public ResponseEntity<ApiResponse<HotelDoc>> resume(
            @PathVariable String id,
            @RequestParam Long partnerId
    ) {
        var data = service.resume(id, partnerId);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @PostMapping("/{id}/unlock-request")
    public ResponseEntity<ApiResponse<HotelDoc>> unlockRequest(
            @PathVariable String id,
            @RequestBody @Valid PartnerCatalogDtos.UnlockRequestWrapper body
    ) {
        var data = service.unlockRequest(id, body.partnerId(), body.reason());
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }
}