// src/main/java/com/mravel/catalog/repository/TableInventoryRepository.java
package com.mravel.catalog.repository;

import com.mravel.catalog.model.doc.TableInventoryDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TableInventoryRepository extends MongoRepository<TableInventoryDoc, String> {

    Optional<TableInventoryDoc> findByRestaurantIdAndTableTypeIdAndSlotStart(
        String restaurantId, String tableTypeId, LocalDateTime slotStart
    );

    List<TableInventoryDoc> findByRestaurantIdAndTableTypeIdInAndSlotStartBetween(
        String restaurantId, List<String> tableTypeIds, LocalDateTime start, LocalDateTime end
    );
}