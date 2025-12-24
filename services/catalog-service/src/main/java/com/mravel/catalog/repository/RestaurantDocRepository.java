package com.mravel.catalog.repository;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantDocRepository extends MongoRepository<RestaurantDoc, String>, RestaurantDocRepositoryCustom {

    Optional<RestaurantDoc> findBySlugAndActiveTrue(String slug);

    boolean existsBySlug(String slug);

    Optional<RestaurantDoc> findBySlug(String slug);

    Page<RestaurantDoc> findByPublisher_PartnerIdAndDeletedAtIsNull(String partnerId, Pageable pageable);

    Optional<RestaurantDoc> findByIdAndDeletedAtIsNull(String id);

    Page<RestaurantDoc> findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_Status(
            String partnerId,
            RestaurantDoc.RestaurantStatus status,
            Pageable pageable);

    Page<RestaurantDoc> findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_StatusAndActive(
            String partnerId,
            RestaurantDoc.RestaurantStatus status,
            boolean active,
            Pageable pageable);

    Optional<RestaurantDoc> findBySlugAndActiveTrueAndModeration_Status(String slug,
            RestaurantDoc.RestaurantStatus status);

}