package com.mravel.catalog.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mravel.catalog.model.doc.RestaurantDoc;

public interface RestaurantDocRepositoryCustom {

    /**
     * Tìm nhà hàng:
     *  - active = true
     *  - moderation.status = APPROVED
     *  - match theo location (destinationSlug / parentPlaceSlug / city / district / address)
     *  - filter theo sức chứa (people) nếu có
     *  - filter theo cuisineSlugs nếu có (map lên cuisines.code)
     */
    Page<RestaurantDoc> searchRestaurants(
            String location,
            Integer people,
            Set<String> cuisineSlugs,
            Pageable pageable
    );
}