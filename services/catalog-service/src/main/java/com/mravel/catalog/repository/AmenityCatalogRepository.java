package com.mravel.catalog.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.enums.AmenityScope;

public interface AmenityCatalogRepository extends MongoRepository<AmenityCatalogDoc, String> {

        Optional<AmenityCatalogDoc> findByCodeAndScope(String code, AmenityScope scope);

        List<AmenityCatalogDoc> findByScopeAndActiveTrue(AmenityScope scope);

        List<AmenityCatalogDoc> findByScopeInAndCodeInAndActiveTrue(
                        List<AmenityScope> scopes,
                        List<String> codes);

        List<AmenityCatalogDoc> findByScopeAndCodeInAndActiveTrue(
                        AmenityScope scope,
                        List<String> codes);

        List<AmenityCatalogDoc> findByScopeAndCodeInAndActiveTrue(AmenityScope scope, Collection<String> codes);
}