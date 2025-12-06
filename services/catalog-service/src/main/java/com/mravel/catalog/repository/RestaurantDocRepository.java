package com.mravel.catalog.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mravel.catalog.model.doc.RestaurantDoc;

@Repository
public interface RestaurantDocRepository
        extends MongoRepository<RestaurantDoc, String>, RestaurantDocRepositoryCustom {

    Optional<RestaurantDoc> findBySlugAndActiveTrue(String slug);

    boolean existsBySlug(String slug);
}