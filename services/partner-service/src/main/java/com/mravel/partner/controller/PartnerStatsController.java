package com.mravel.partner.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.partner.client.BookingPartnerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/partner/stats")
@RequiredArgsConstructor
public class PartnerStatsController {

    private final BookingPartnerClient bookingClient;

    @GetMapping("/status")
    public ResponseEntity<ApiResponse<?>> statsByStatus(
            @RequestHeader("Authorization") String bearer,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to
    ) {
        var resp = bookingClient.statsByStatus(from, to, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }

    @GetMapping("/revenue")
    public ResponseEntity<ApiResponse<?>> revenue(
            @RequestHeader("Authorization") String bearer,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to,
            @RequestParam(required = false) String group
    ) {
        var resp = bookingClient.revenue(from, to, group, bearer);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }
}