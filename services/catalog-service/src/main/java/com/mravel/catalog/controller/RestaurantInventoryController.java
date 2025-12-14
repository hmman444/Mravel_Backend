// src/main/java/com/mravel/catalog/controller/RestaurantInventoryController.java
package com.mravel.catalog.controller;

import com.mravel.catalog.dto.RestaurantInventoryDtos.*;
import com.mravel.catalog.service.RestaurantInventoryService;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/catalog/restaurants/inventory")
@RequiredArgsConstructor
public class RestaurantInventoryController {

    private final RestaurantInventoryService inventoryService;

    @GetMapping("/availability")
    public ResponseEntity<ApiResponse<TableAvailabilityResponse>> availability(
        @RequestParam(required = false) String restaurantId,
        @RequestParam(required = false) String restaurantSlug,
        @RequestParam String tableTypeId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationDate,
        @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime reservationTime,
        @RequestParam(required = false) Integer durationMinutes,
        @RequestParam(defaultValue = "1") Integer tables
    ) {
        TableAvailabilityResponse dto = inventoryService.getAvailability(
            restaurantId, restaurantSlug,
            tableTypeId,
            reservationDate, reservationTime,
            durationMinutes, tables
        );

        return ResponseEntity.ok(ApiResponse.<TableAvailabilityResponse>success("OK", dto));
    }

    @PostMapping("/check")
    public ResponseEntity<ApiResponse<Void>> check(@RequestBody CheckTableInventoryRequest req) {
        inventoryService.assertAvailability(req);
        return ResponseEntity.ok(ApiResponse.success("Còn bàn", null));
    }

    @PostMapping("/hold")
    public ResponseEntity<ApiResponse<Void>> hold(@RequestBody HoldTableInventoryRequest req) {
        inventoryService.holdForPending(req);
        return ResponseEntity.ok(ApiResponse.success("Đã hold bàn", null));
    }

    @PostMapping("/commit")
    public ResponseEntity<ApiResponse<Void>> commit(@RequestBody HoldTableInventoryRequest req) {
        inventoryService.commitAfterPaid(req);
        return ResponseEntity.ok(ApiResponse.success("Đã commit bàn", null));
    }

    @PostMapping("/release")
    public ResponseEntity<ApiResponse<Void>> release(@RequestBody ReleaseTableHoldRequest req) {
        inventoryService.releaseHold(req);
        return ResponseEntity.ok(ApiResponse.success("Đã release hold", null));
    }

    @PostMapping("/rollback")
    public ResponseEntity<ApiResponse<Void>> rollback(@RequestBody ReleaseTableHoldRequest req) {
        inventoryService.rollbackBooked(req);
        return ResponseEntity.ok(ApiResponse.success("Đã rollback booked", null));
    }
}