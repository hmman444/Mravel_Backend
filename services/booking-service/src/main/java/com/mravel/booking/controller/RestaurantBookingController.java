// src/main/java/com/mravel/booking/controller/RestaurantBookingController.java
package com.mravel.booking.controller;

import com.mravel.booking.dto.RestaurantBookingDtos.*;
import com.mravel.booking.service.RestaurantBookingService;
import com.mravel.booking.utils.GuestSessionCookie;
import com.mravel.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking/restaurants")
@RequiredArgsConstructor
public class RestaurantBookingController {

  private final RestaurantBookingService service;

  @PostMapping
  public ResponseEntity<ApiResponse<RestaurantBookingCreatedDTO>> create(
      @RequestBody CreateRestaurantBookingRequest req,
      HttpServletRequest httpReq,
      HttpServletResponse httpResp
  ) {
    String sid = GuestSessionCookie.ensure(httpReq, httpResp);
    var dto = service.create(req, sid);
    return ResponseEntity.ok(ApiResponse.success("Tạo booking nhà hàng thành công", dto));
  }
}