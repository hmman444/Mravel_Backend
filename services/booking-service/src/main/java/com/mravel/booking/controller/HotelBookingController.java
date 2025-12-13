// src/main/java/com/mravel/booking/controller/HotelBookingController.java
package com.mravel.booking.controller;

import com.mravel.booking.dto.HotelBookingDtos.CreateHotelBookingRequest;
import com.mravel.booking.dto.HotelBookingDtos.HotelBookingCreatedDTO;
import com.mravel.booking.service.HotelBookingService;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking/hotels")
@RequiredArgsConstructor
public class HotelBookingController {

    private final HotelBookingService hotelBookingService;

    /**
     * Tạo booking khách sạn.
     * FE sẽ gọi endpoint này sau khi user chọn phòng + điền thông tin liên hệ.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<HotelBookingCreatedDTO>> createBooking(
            @RequestBody CreateHotelBookingRequest request
    ) {
        HotelBookingCreatedDTO dto = hotelBookingService.createHotelBooking(request);
        return ResponseEntity.ok(
                ApiResponse.success("Tạo booking khách sạn thành công", dto)
        );
    }
}