package com.mravel.catalog.repository;

import com.mravel.catalog.model.doc.RoomInventoryDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomInventoryRepository extends MongoRepository<RoomInventoryDoc, String> {

    List<RoomInventoryDoc> findByHotelIdAndRoomTypeIdAndDateBetween(
            String hotelId,
            String roomTypeId,
            LocalDate start,
            LocalDate end
    );

    List<RoomInventoryDoc> findByHotelSlugAndRoomTypeIdAndDateBetween(
            String hotelSlug,
            String roomTypeId,
            LocalDate start,
            LocalDate end
    );

    Optional<RoomInventoryDoc> findByHotelIdAndRoomTypeIdAndDate(
            String hotelId,
            String roomTypeId,
            LocalDate date
    );

    List<RoomInventoryDoc> findByHotelIdAndRoomTypeIdInAndDateBetween(
            String hotelId,
            List<String> roomTypeIds,
            LocalDate start,
            LocalDate end
    );
}