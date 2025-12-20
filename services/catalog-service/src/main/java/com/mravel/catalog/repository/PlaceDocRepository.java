package com.mravel.catalog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.model.enums.VenueType;

@Repository
public interface PlaceDocRepository extends MongoRepository<PlaceDoc, String>, PlaceDocRepositoryCustom {
  Optional<PlaceDoc> findBySlug(String slug);

  long countByKind(PlaceKind kind);

  long countByKindAndVenueType(PlaceKind kind, VenueType venueType);

  boolean existsBySlug(String slug);

  boolean existsBySlugAndIdNot(String slug, String id);

  long countByParentSlugAndActiveTrue(String parentSlug);

  Page<PlaceDoc> findAllByKind(PlaceKind kind, Pageable pageable);

  long countByParentSlug(String parentSlug);
}