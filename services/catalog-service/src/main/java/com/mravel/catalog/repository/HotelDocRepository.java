// src/main/java/com/mravel/catalog/repository/HotelDocRepository.java
package com.mravel.catalog.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mravel.catalog.model.doc.HotelDoc;

@Repository
public interface HotelDocRepository extends MongoRepository<HotelDoc, String>, HotelDocRepositoryCustom {

    Optional<HotelDoc> findBySlugAndActiveTrue(String slug);

    boolean existsBySlug(String slug);
}