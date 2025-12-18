package com.mravel.catalog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.mravel.common.response.ApiResponse;
import com.mravel.catalog.dto.amenity.AmenityUpsertRequest;
import com.mravel.catalog.dto.amenity.AmenityGroupedResponse;
import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.service.AmenityCatalogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog/amenities")
@RequiredArgsConstructor
public class AmenityCatalogController {

    private final AmenityCatalogService service;

    @PostMapping
    public ApiResponse<AmenityCatalogDoc> create(@RequestBody AmenityUpsertRequest req) {
        return ApiResponse.success("Tạo tiện ích thành công", service.create(req));
    }

    @PutMapping("/{id}")
    public ApiResponse<AmenityCatalogDoc> update(@PathVariable String id, @RequestBody AmenityUpsertRequest req) {
        return ApiResponse.success("Cập nhật tiện ích thành công", service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable String id) {
        service.softDelete(id);
        return ApiResponse.success("Xóa tiện ích (mềm) thành công", null);
    }

    @GetMapping
    public ApiResponse<?> list(
            @RequestParam(required = false) AmenityScope scope,
            @RequestParam(defaultValue = "true") boolean active,
            @RequestParam(defaultValue = "false") boolean grouped) {
        if (grouped) {
            if (scope == null)
                return ApiResponse.error("scope là bắt buộc khi grouped=true");
            AmenityGroupedResponse data = service.grouped(scope);
            return ApiResponse.success("OK", data);
        }
        List<AmenityCatalogDoc> data = service.list(scope, active);
        return ApiResponse.success("OK", data);
    }
}