// src/main/java/com/mravel/catalog/repository/HotelDocRepository.java
package com.mravel.catalog.repository;

import com.mravel.catalog.model.doc.HotelDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelDocRepository extends MongoRepository<HotelDoc, String>, HotelDocRepositoryCustom {

    /**
     * Projection cho danh sách partner: loại bỏ các field nặng (content, roomTypes,
     * faqs, nearbyPlaces, policy, generalInfo, reviewStats) — list chỉ cần
     * name/slug/type/moderation/publisher/images/shortDescription. Giảm mạnh payload.
     */
    String LIST_PROJECTION = "{ 'content': 0, 'roomTypes': 0, 'faqs': 0, 'nearbyPlaces': 0, 'policy': 0, 'generalInfo': 0, 'reviewStats': 0 }";

    Optional<HotelDoc> findBySlugAndActiveTrue(String slug);

    boolean existsBySlug(String slug);

    boolean existsBySlugAndIdNot(String slug, String id);

    @Query(value = "{ 'publisher.partnerId': ?0, 'deletedAt': null }", fields = LIST_PROJECTION)
    Page<HotelDoc> findByPublisher_PartnerIdAndDeletedAtIsNull(String partnerId, Pageable pageable);

    Optional<HotelDoc> findBySlugAndActiveTrueAndModeration_Status(String slug, HotelDoc.HotelStatus status);

    Optional<HotelDoc> findByIdAndDeletedAtIsNull(String id);
    Optional<HotelDoc> findBySlugAndModeration_Status(String slug, HotelDoc.HotelStatus status);

    @Query(value = "{ 'publisher.partnerId': ?0, 'deletedAt': null, 'moderation.status': ?1 }", fields = LIST_PROJECTION)
    Page<HotelDoc> findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_Status(
            String partnerId,
            HotelDoc.HotelStatus status,
            Pageable pageable);

    @Query(value = "{ 'publisher.partnerId': ?0, 'deletedAt': null, 'moderation.status': ?1, 'active': ?2 }", fields = LIST_PROJECTION)
    Page<HotelDoc> findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_StatusAndActive(
            String partnerId,
            HotelDoc.HotelStatus status,
            boolean active,
            Pageable pageable);

    Optional<HotelDoc> findBySlug(String slug);

    // ===== Admin dashboard counts (loại bản ghi đã xoá mềm) =====
    long countByModeration_StatusAndDeletedAtIsNull(HotelDoc.HotelStatus status);

    long countByActiveTrueAndModeration_StatusAndDeletedAtIsNull(HotelDoc.HotelStatus status);
}