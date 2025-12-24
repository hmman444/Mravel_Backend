package com.mravel.catalog.controller.admin;

import com.mravel.catalog.dto.admin.AdminCatalogDtos.AdminServiceSummaryDTO;
import com.mravel.catalog.dto.admin.AdminCatalogDtos.ReasonBody;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.service.admin.AdminCatalogQueryService;
import com.mravel.catalog.service.admin.AdminRestaurantModerationService;
import com.mravel.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalog/admin/restaurants")
@RequiredArgsConstructor
public class AdminRestaurantController {

    private final AdminCatalogQueryService queryService;
    private final AdminRestaurantModerationService moderationService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<AdminServiceSummaryDTO>>> list(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String partnerId,
            @RequestParam(required = false) String destinationSlug,
            @RequestParam(required = false) Boolean unlockRequested,
            @RequestParam(required = false) String q,
            @ParameterObject Pageable pageable) {

        var data = queryService.listRestaurants(status, active, partnerId, destinationSlug, unlockRequested, q,
                pageable);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    // adminId = query param
    @PostMapping("/{id}:approve")
    public ResponseEntity<ApiResponse<RestaurantDoc>> approve(
            @PathVariable String id,
            @RequestParam Long adminId) {

        var data = moderationService.approve(id, adminId);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @PostMapping("/{id}:reject")
    public ResponseEntity<ApiResponse<RestaurantDoc>> reject(
            @PathVariable String id,
            @RequestParam Long adminId,
            @RequestBody @Valid ReasonBody body) {

        var data = moderationService.reject(id, adminId, body.reason());
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @PostMapping("/{id}:block")
    public ResponseEntity<ApiResponse<RestaurantDoc>> block(
            @PathVariable String id,
            @RequestParam Long adminId,
            @RequestBody @Valid ReasonBody body) {

        var data = moderationService.block(id, adminId, body.reason());
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }

    @PostMapping("/{id}:unblock")
    public ResponseEntity<ApiResponse<RestaurantDoc>> unblock(
            @PathVariable String id,
            @RequestParam Long adminId) {

        var data = moderationService.unblock(id, adminId);
        return ResponseEntity.ok(ApiResponse.success("OK", data));
    }
}
