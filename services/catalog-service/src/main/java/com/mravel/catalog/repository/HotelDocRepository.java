// src/main/java/com/mravel/catalog/repository/HotelDocRepository.java
package com.mravel.catalog.repository;

import com.mravel.catalog.model.doc.HotelDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelDocRepository extends MongoRepository<HotelDoc, String>, HotelDocRepositoryCustom {

    Optional<HotelDoc> findBySlugAndActiveTrue(String slug);

    boolean existsBySlug(String slug);

    boolean existsBySlugAndIdNot(String slug, String id);

    Page<HotelDoc> findByPublisher_PartnerIdAndDeletedAtIsNull(String partnerId, Pageable pageable);

    Optional<HotelDoc> findBySlugAndActiveTrueAndModeration_Status(String slug, HotelDoc.HotelStatus status);

    Optional<HotelDoc> findByIdAndDeletedAtIsNull(String id);

    Page<HotelDoc> findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_Status(
            String partnerId,
            HotelDoc.HotelStatus status,
            Pageable pageable);

    Page<HotelDoc> findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_StatusAndActive(
            String partnerId,
            HotelDoc.HotelStatus status,
            boolean active,
            Pageable pageable);

    Optional<HotelDoc> findBySlug(String slug);
}