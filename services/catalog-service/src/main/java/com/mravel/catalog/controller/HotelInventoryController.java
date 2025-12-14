// src/main/java/com/mravel/catalog/controller/HotelInventoryController.java
package com.mravel.catalog.controller;

import com.mravel.catalog.dto.InventoryDtos.AvailabilityResponse;
import com.mravel.catalog.dto.InventoryDtos.CheckInventoryRequest;
import com.mravel.catalog.dto.InventoryDtos.DeductInventoryRequest;
import com.mravel.catalog.dto.InventoryDtos.RollbackInventoryRequest;
import com.mravel.catalog.dto.InventoryDtos.RoomRequestDTO;
import com.mravel.catalog.service.HotelInventoryService;
import com.mravel.catalog.service.HotelInventoryService.RoomRequest;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/catalog/hotels/inventory")
@RequiredArgsConstructor
public class HotelInventoryController {

    private final HotelInventoryService inventoryService;

    private List<RoomRequest> mapRooms(List<RoomRequestDTO> rooms) {
        return rooms == null ? List.of() :
                rooms.stream()
                        .map(r -> new RoomRequest(r.roomTypeId(), r.quantity()))
                        .toList();
    }

    @GetMapping("/availability")
    public ResponseEntity<ApiResponse<AvailabilityResponse>> availability(
                @RequestParam(required = false) String hotelId,
                @RequestParam(required = false) String hotelSlug,
                @RequestParam String roomTypeId,
                @RequestParam LocalDate checkIn,
                @RequestParam LocalDate checkOut,
                @RequestParam(defaultValue = "1") Integer rooms
        ) {
        AvailabilityResponse dto = inventoryService.getAvailability(
                hotelId,
                hotelSlug,
                roomTypeId,
                checkIn,
                checkOut,
                rooms != null ? rooms : 1
        );
        return ResponseEntity.ok(ApiResponse.success("OK", dto));
        }    

    /**
     * Check tồn kho cho 1 booking (KHÔNG trừ).
     * Booking-service sẽ gọi endpoint này trước khi tạo booking.
     */
    @PostMapping("/check")
    public ResponseEntity<ApiResponse<Void>> checkAvailability(
            @RequestBody CheckInventoryRequest req
    ) {
        inventoryService.assertAvailability(
                req.hotelId(),
                req.checkInDate(),
                req.checkOutDate(),
                mapRooms(req.rooms())
        );
        return ResponseEntity.ok(ApiResponse.success("Còn phòng", null));
    }

    /**
     * Trừ tồn kho cho 1 booking (sau khi thanh toán thành công / xác nhận).
     */
    @PostMapping("/deduct")
    public ResponseEntity<ApiResponse<Void>> deductInventory(
            @RequestBody DeductInventoryRequest req
    ) {
        inventoryService.deductInventoryForBooking(
                req.hotelId(),
                req.hotelSlug(),
                req.checkInDate(),
                req.checkOutDate(),
                mapRooms(req.rooms())
        );
        return ResponseEntity.ok(ApiResponse.success("Đã trừ tồn kho", null));
    }

    /**
     * Rollback tồn kho khi hủy booking đã trừ tồn kho trước đó.
     */
    @PostMapping("/rollback")
    public ResponseEntity<ApiResponse<Void>> rollbackInventory(
            @RequestBody RollbackInventoryRequest req
    ) {
        inventoryService.rollbackInventoryForBooking(
                req.hotelId(),
                req.checkInDate(),
                req.checkOutDate(),
                mapRooms(req.rooms())
        );
        return ResponseEntity.ok(ApiResponse.success("Đã rollback tồn kho", null));
    }

    @PostMapping("/hold")
        public ResponseEntity<ApiResponse<Void>> hold(@RequestBody DeductInventoryRequest req) {
        inventoryService.holdInventoryForPendingBooking(
                req.hotelId(), req.hotelSlug(), req.checkInDate(), req.checkOutDate(), mapRooms(req.rooms())
        );
        return ResponseEntity.ok(ApiResponse.success("Đã hold tồn kho", null));
        }

        @PostMapping("/commit")
        public ResponseEntity<ApiResponse<Void>> commit(@RequestBody DeductInventoryRequest req) {
        inventoryService.commitInventoryAfterPaid(
                req.hotelId(), req.hotelSlug(), req.checkInDate(), req.checkOutDate(), mapRooms(req.rooms())
        );
        return ResponseEntity.ok(ApiResponse.success("Đã commit tồn kho", null));
        }

        @PostMapping("/release")
        public ResponseEntity<ApiResponse<Void>> release(@RequestBody RollbackInventoryRequest req) {
        inventoryService.releaseHoldForCancelledBooking(
                req.hotelId(), req.checkInDate(), req.checkOutDate(), mapRooms(req.rooms())
        );
        return ResponseEntity.ok(ApiResponse.success("Đã release hold", null));
        }
}