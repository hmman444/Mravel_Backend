// src/main/java/com/mravel/booking/client/CatalogInventoryDtos.java
package com.mravel.booking.client;

import java.time.LocalDate;
import java.util.List;

public class CatalogInventoryDtos {

    public record RoomRequestDTO(
            String roomTypeId,
            int quantity
    ) {}

    public record CheckInventoryRequest(
            String hotelId,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            List<RoomRequestDTO> rooms
    ) {}

    public record DeductInventoryRequest(
            String hotelId,
            String hotelSlug,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            List<RoomRequestDTO> rooms
    ) {}

    public record RollbackInventoryRequest(
            String hotelId,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            List<RoomRequestDTO> rooms
    ) {}
}