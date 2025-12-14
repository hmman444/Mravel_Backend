// src/main/java/com/mravel/catalog/dto/RestaurantInventoryDtos.java
package com.mravel.catalog.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RestaurantInventoryDtos {

    public record TableRequestDTO(String tableTypeId, int quantity) {}

    public record CheckTableInventoryRequest(
        String restaurantId,
        LocalDate reservationDate,
        LocalTime reservationTime,
        Integer durationMinutes,
        List<TableRequestDTO> tables
    ) {}

    public record HoldTableInventoryRequest(
        String restaurantId,
        String restaurantSlug,
        LocalDate reservationDate,
        LocalTime reservationTime,
        Integer durationMinutes,
        List<TableRequestDTO> tables
    ) {}

    public record ReleaseTableHoldRequest(
        String restaurantId,
        LocalDate reservationDate,
        LocalTime reservationTime,
        Integer durationMinutes,
        List<TableRequestDTO> tables
    ) {}

    public record TableAvailabilityResponse(
        int remainingTablesMin,
        int requestedTables,
        boolean isEnough
    ) {}
}