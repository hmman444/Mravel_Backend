package com.mravel.catalog.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mravel.catalog.dto.SearchRequests.PlaceSearchRequest;
import com.mravel.catalog.dto.place.PlaceAdminDtos.PlaceAdminResponse;
import com.mravel.catalog.dto.place.PlaceAdminDtos.UpsertPlaceRequest;
import com.mravel.catalog.dto.place.PlaceDtos.PlaceDetailDTO;
import com.mravel.catalog.dto.place.PlaceDtos.PlaceSummaryDTO;
import com.mravel.catalog.dto.search.FacetedPlaceSearchRequest;
import com.mravel.catalog.dto.search.FacetedSearchResponse;
import com.mravel.catalog.dto.search.PlaceFacets;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.service.PlaceService;
import com.mravel.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping("/search")
    public ApiResponse<Page<PlaceSummaryDTO>> searchPlaces(
            @RequestBody(required = false) PlaceSearchRequest request,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {

        String q = request != null ? request.q() : null;
        return ApiResponse.success("OK", placeService.searchPlaces(q, null, pageable));
    }

    @PostMapping("/search/faceted")
    public ApiResponse<FacetedSearchResponse<PlaceSummaryDTO, PlaceFacets>> searchPlacesFaceted(
            @RequestBody(required = false) FacetedPlaceSearchRequest request,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {

        return ApiResponse.success("OK", placeService.searchPlacesFaceted(request, pageable));
    }

    // kept for backward compatibility
    @GetMapping("/poi")
    public ApiResponse<Page<PlaceSummaryDTO>> searchPlacesLegacy(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) PlaceKind kind,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {

        return ApiResponse.success("OK", placeService.searchPlaces(q, kind, pageable));
    }

    // Public detail by slug
    @GetMapping("/{slug}")
    public ApiResponse<PlaceDetailDTO> getDetail(@PathVariable String slug) {
        return ApiResponse.success("OK", placeService.getBySlug(slug));
    }

    @GetMapping("/{slug}/children")
    public ApiResponse<Page<PlaceSummaryDTO>> children(
            @PathVariable String slug,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false, defaultValue = "POI") PlaceKind kind) {

        return ApiResponse.success("OK", placeService.findChildrenByParentSlug(slug, kind, pageable));
    }

    @GetMapping("/{slug}/children/all")
    public ApiResponse<Page<PlaceSummaryDTO>> childrenAll(
            @PathVariable String slug,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false, defaultValue = "POI") PlaceKind kind) {

        return ApiResponse.success("OK", placeService.findChildrenByParentSlugAll(slug, kind, pageable));
    }

    @PostMapping
    public ApiResponse<PlaceAdminResponse> create(@RequestBody UpsertPlaceRequest req) {
        return ApiResponse.success("Tạo địa điểm thành công", placeService.create(req));
    }

    @PutMapping("/{id}")
    public ApiResponse<PlaceAdminResponse> update(@PathVariable String id, @RequestBody UpsertPlaceRequest req) {
        return ApiResponse.success("Cập nhật địa điểm thành công", placeService.update(id, req));
    }

    @GetMapping("/all")
    public ApiResponse<Page<PlaceAdminResponse>> listAllForAdmin(
            @RequestParam(defaultValue = "POI") PlaceKind kind,
            @ParameterObject Pageable pageable) {

        Page<PlaceAdminResponse> page = placeService.findAllByKind(kind, pageable);
        return ApiResponse.success("OK", page);
    }

    @PatchMapping("/{id}/lock")
    public ApiResponse<Void> lock(@PathVariable String id) {
        placeService.lock(id);
        return ApiResponse.success("Khóa địa điểm thành công", null);
    }

    @PatchMapping("/{id}/unlock")
    public ApiResponse<Void> unlock(@PathVariable String id) {
        placeService.unlock(id);
        return ApiResponse.success("Mở khóa địa điểm thành công", null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> hardDelete(@PathVariable String id) {
        placeService.hardDelete(id);
        return ApiResponse.success("Xóa địa điểm (cứng) thành công", null);
    }
}
