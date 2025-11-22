// src/main/java/com/mravel/catalog/repository/HotelDocRepositoryCustom.java
package com.mravel.catalog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mravel.catalog.model.doc.HotelDoc;

public interface HotelDocRepositoryCustom {

    /**
     * Tìm khách sạn:
     *  - active = true
     *  - moderation.status = APPROVED
     *  - match theo location (destinationSlug / city / district / address)
     *  - có ít nhất 1 roomType thỏa:
     *      maxGuests >= minGuestsPerRoom
     *      totalRooms >= requiredRooms
     */
    Page<HotelDoc> searchHotels(
            String location,
            Integer minGuestsPerRoom,
            Integer requiredRooms,
            Pageable pageable
    );
}