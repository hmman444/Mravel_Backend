package com.mravel.partner.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.partner.client.BookingPartnerClient;
import com.mravel.partner.dto.PartnerDtos;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner/bookings")
@RequiredArgsConstructor
public class PartnerBookingController {

    private final BookingPartnerClient bookingClient;

    // ---------------- HOTEL BOOKINGS ----------------

    @GetMapping("/hotels")
    public ResponseEntity<ApiResponse<?>> listHotelBookings(
            @RequestHeader("Authorization") String bearer,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        ResponseEntity<ApiResponse<?>> resp = bookingClient.listHotelBookings(status, page, size, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @GetMapping("/hotels/{bookingCode}")
    public ResponseEntity<ApiResponse<?>> getHotelBookingDetail(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String bookingCode
    ) {
        ResponseEntity<ApiResponse<?>> resp = bookingClient.getHotelBookingDetail(bookingCode, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PostMapping("/hotels/{bookingCode}/cancel")
    public ResponseEntity<ApiResponse<?>> cancelHotelBooking(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String bookingCode,
            @Valid @RequestBody PartnerDtos.CancelBookingReq req
    ) {
        ResponseEntity<ApiResponse<?>> resp = bookingClient.cancelHotelBooking(bookingCode, req, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    // ---------------- RESTAURANT BOOKINGS ----------------

    @GetMapping("/restaurants")
    public ResponseEntity<ApiResponse<?>> listRestaurantBookings(
            @RequestHeader("Authorization") String bearer,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        ResponseEntity<ApiResponse<?>> resp = bookingClient.listRestaurantBookings(status, page, size, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @GetMapping("/restaurants/{bookingCode}")
    public ResponseEntity<ApiResponse<?>> getRestaurantBookingDetail(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String bookingCode
    ) {
        ResponseEntity<ApiResponse<?>> resp = bookingClient.getRestaurantBookingDetail(bookingCode, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @PostMapping("/restaurants/{bookingCode}/cancel")
    public ResponseEntity<ApiResponse<?>> cancelRestaurantBooking(
            @RequestHeader("Authorization") String bearer,
            @PathVariable String bookingCode,
            @Valid @RequestBody PartnerDtos.CancelBookingReq req
    ) {
        ResponseEntity<ApiResponse<?>> resp = bookingClient.cancelRestaurantBooking(bookingCode, req, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }
}