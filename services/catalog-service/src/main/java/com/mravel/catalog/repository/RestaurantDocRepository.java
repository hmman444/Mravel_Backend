package com.mravel.catalog.repository;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantDocRepository extends MongoRepository<RestaurantDoc, String>, RestaurantDocRepositoryCustom {

    /** Projection cho danh sách partner: loại field nặng, giữ field list cần (name/type/images/moderation/publisher/shortDescription). */
    String LIST_PROJECTION = "{ 'content': 0, 'policy': 0, 'reviewStats': 0, 'menuSections': 0, 'signatureDishes': 0, 'tableTypes': 0, 'menuImages': 0, 'openingHours': 0, 'suitableFor': 0, 'ambience': 0, 'bookingConfig': 0 }";

    Optional<RestaurantDoc> findBySlugAndActiveTrue(String slug);
    Optional<RestaurantDoc> findBySlugAndModeration_Status(String slug, RestaurantDoc.RestaurantStatus status);

    boolean existsBySlug(String slug);

    boolean existsBySlugAndIdNot(String slug, String id);

    Optional<RestaurantDoc> findBySlug(String slug);

    @Query(value = "{ 'publisher.partnerId': ?0, 'deletedAt': null }", fields = LIST_PROJECTION)
    Page<RestaurantDoc> findByPublisher_PartnerIdAndDeletedAtIsNull(String partnerId, Pageable pageable);

    Optional<RestaurantDoc> findByIdAndDeletedAtIsNull(String id);

    @Query(value = "{ 'publisher.partnerId': ?0, 'deletedAt': null, 'moderation.status': ?1 }", fields = LIST_PROJECTION)
    Page<RestaurantDoc> findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_Status(
            String partnerId,
            RestaurantDoc.RestaurantStatus status,
            Pageable pageable);

    @Query(value = "{ 'publisher.partnerId': ?0, 'deletedAt': null, 'moderation.status': ?1, 'active': ?2 }", fields = LIST_PROJECTION)
    Page<RestaurantDoc> findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_StatusAndActive(
            String partnerId,
            RestaurantDoc.RestaurantStatus status,
            boolean active,
            Pageable pageable);

    Optional<RestaurantDoc> findBySlugAndActiveTrueAndModeration_Status(String slug,
            RestaurantDoc.RestaurantStatus status);

}