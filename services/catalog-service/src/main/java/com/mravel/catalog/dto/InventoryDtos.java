// src/main/java/com/mravel/catalog/dto/InventoryDtos.java
package com.mravel.catalog.dto;

import java.time.LocalDate;
import java.util.List;

public class InventoryDtos {

    public record RoomRequestDTO(
            String roomTypeId,
            int quantity
    ) {}

    /**
     * Request check tồn kho cho 1 booking.
     */
    public record CheckInventoryRequest(
            String hotelId,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            List<RoomRequestDTO> rooms
    ) {}

    /**
     * Request trừ tồn kho khi booking đã được xác nhận / đã thanh toán.
     */
    public record DeductInventoryRequest(
            String hotelId,
            String hotelSlug,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            List<RoomRequestDTO> rooms
    ) {}

    /**
     * Request rollback tồn kho khi hủy booking (đã trừ tồn kho trước đó).
     */
    public record RollbackInventoryRequest(
            String hotelId,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            List<RoomRequestDTO> rooms
    ) {}

    public record AvailabilityResponse(
            Integer remainingRooms,
            Integer requestedRooms,
            Boolean isEnough
    ) {}
}