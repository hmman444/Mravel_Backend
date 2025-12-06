package com.mravel.catalog.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mravel.catalog.dto.SearchRequests.RestaurantSearchRequest;
import com.mravel.catalog.dto.restaurant.RestaurantDtos.RestaurantDetailDTO;
import com.mravel.catalog.dto.restaurant.RestaurantDtos.RestaurantSummaryDTO;
import com.mravel.catalog.service.RestaurantService;
import com.mravel.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * Tìm kiếm nhà hàng.
     *
     * Ví dụ FE:
     *  POST /api/catalog/restaurants/search?page=0&size=10
     *  {
     *    "location": "hoi-an",
     *    "visitDate": "2025-12-01",
     *    "visitTime": "19:00",
     *    "people": 4,
     *    "cuisineSlugs": ["viet-nam", "buffet-viet-a"]
     *  }
     */
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<Page<RestaurantSummaryDTO>>> searchRestaurants(
            @RequestBody(required = false) RestaurantSearchRequest request,
            @ParameterObject Pageable pageable
    ) {
        Page<RestaurantSummaryDTO> result = restaurantService.searchRestaurants(request, pageable);
        return ResponseEntity.ok(ApiResponse.success("Tìm kiếm nhà hàng thành công", result));
    }

    /**
     * Xem chi tiết nhà hàng theo slug.
     */
    @GetMapping("/{slug}")
    public ResponseEntity<ApiResponse<RestaurantDetailDTO>> getRestaurantDetail(@PathVariable String slug) {
        RestaurantDetailDTO dto = restaurantService.getBySlug(slug);
        return ResponseEntity.ok(
                ApiResponse.success("Lấy chi tiết nhà hàng thành công", dto)
        );
    }
}