// src/main/java/com/mravel/booking/controller/HotelBookingController.java
package com.mravel.booking.controller;

import com.mravel.booking.dto.HotelBookingDtos.CreateHotelBookingRequest;
import com.mravel.booking.dto.HotelBookingDtos.HotelBookingCreatedDTO;
import com.mravel.booking.service.HotelBookingService;
import com.mravel.booking.utils.GuestSessionCookie;
import com.mravel.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking/hotels")
@RequiredArgsConstructor
public class HotelBookingController {

    private final HotelBookingService hotelBookingService;

    @PostMapping
    public ResponseEntity<ApiResponse<HotelBookingCreatedDTO>> createBooking(
            @RequestBody CreateHotelBookingRequest request,
            HttpServletRequest httpReq,
            HttpServletResponse httpResp
    ) {
        String guestSid = null;

        // ✅ only guest (userId null) mới dùng cookie
        if (request.userId() == null) {
            guestSid = GuestSessionCookie.ensure(httpReq, httpResp);
        }

        HotelBookingCreatedDTO dto = hotelBookingService.createHotelBooking(request, guestSid);

        return ResponseEntity.ok(
                ApiResponse.success("Tạo booking khách sạn thành công", dto)
        );
    }
}