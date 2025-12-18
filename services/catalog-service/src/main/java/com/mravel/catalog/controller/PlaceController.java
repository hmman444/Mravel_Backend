package com.mravel.catalog.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.mravel.common.response.ApiResponse;
import com.mravel.catalog.dto.place.PlaceAdminDtos.PlaceAdminResponse;
import com.mravel.catalog.dto.place.PlaceAdminDtos.UpsertPlaceRequest;
import com.mravel.catalog.dto.place.PlaceDtos.PlaceDetailDTO;
import com.mravel.catalog.dto.place.PlaceDtos.PlaceSummaryDTO;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.service.PlaceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    // Search POI (q optional)
    @GetMapping("/poi")
    public ApiResponse<Page<PlaceSummaryDTO>> searchPlaces(
            @RequestParam(required = false) String q,
            @ParameterObject Pageable pageable) {

        Page<PlaceSummaryDTO> page = placeService.searchPlaces(q, pageable);
        return ApiResponse.success("OK", page);
    }

    // Public detail by slug
    @GetMapping("/{slug}")
    public ApiResponse<PlaceDetailDTO> getDetail(@PathVariable String slug) {
        return ApiResponse.success("OK", placeService.getBySlug(slug));
    }

    @GetMapping("/{slug}/children")
    public ApiResponse<Page<PlaceSummaryDTO>> children(
            @PathVariable String slug,
            @ParameterObject Pageable pageable,
            @RequestParam(required = false, defaultValue = "POI") PlaceKind kind) {

        Page<PlaceSummaryDTO> page = placeService.findChildrenByParentSlug(slug, kind, pageable);
        return ApiResponse.success("OK", page);
    }

    @PostMapping
    public ApiResponse<PlaceAdminResponse> create(@RequestBody UpsertPlaceRequest req) {
        return ApiResponse.success("Tạo địa điểm thành công", placeService.create(req));
    }

    @PutMapping("/{id}")
    public ApiResponse<PlaceAdminResponse> update(@PathVariable String id, @RequestBody UpsertPlaceRequest req) {
        return ApiResponse.success("Cập nhật địa điểm thành công", placeService.update(id, req));
    }

    // soft delete
    @DeleteMapping("/{id}")
    public ApiResponse<Void> softDelete(@PathVariable String id) {
        placeService.softDelete(id);
        return ApiResponse.success("Xóa địa điểm (mềm) thành công", null);
    }
}
