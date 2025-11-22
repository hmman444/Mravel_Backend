// src/main/java/com/mravel/catalog/controller/HotelController.java
package com.mravel.catalog.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mravel.catalog.dto.SearchRequests.HotelSearchRequest;
import com.mravel.catalog.dto.hotel.HotelDtos.HotelDetailDTO;
import com.mravel.catalog.dto.hotel.HotelDtos.HotelSummaryDTO;
import com.mravel.catalog.service.HotelService;
import com.mravel.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    /**
     * Tìm kiếm khách sạn.
     * Body: HotelSearchRequest { location, checkIn, checkOut, adults, children, rooms }
     * Query: page, size, sort...
     *
     * Ví dụ FE:
     *  POST /api/catalog/hotels/search?page=0&size=10
     *  {
     *    "location": "da-nang",
     *    "checkIn": "2025-12-01",
     *    "checkOut": "2025-12-03",
     *    "adults": 2,
     *    "children": 1,
     *    "rooms": 1
     *  }
     */
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<Page<HotelSummaryDTO>>> searchHotels(
            @RequestBody(required = false) HotelSearchRequest request,
            @ParameterObject Pageable pageable
    ) {
        Page<HotelSummaryDTO> result = hotelService.searchHotels(request, pageable);
        return ResponseEntity.ok(
                ApiResponse.success("Tìm kiếm khách sạn thành công", result)
        );
    }

    /**
     * Xem chi tiết khách sạn theo slug.
     */
    @GetMapping("/{slug}")
    public ResponseEntity<ApiResponse<HotelDetailDTO>> getHotelDetail(@PathVariable String slug) {
        HotelDetailDTO dto = hotelService.getBySlug(slug);
        return ResponseEntity.ok(
                ApiResponse.success("Lấy chi tiết khách sạn thành công", dto)
        );
    }
}