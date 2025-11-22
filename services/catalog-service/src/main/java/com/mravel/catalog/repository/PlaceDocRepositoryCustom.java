package com.mravel.catalog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;

public interface PlaceDocRepositoryCustom {
  Page<PlaceDoc> searchPlaces(String q, Pageable pageable);
  Page<PlaceDoc> findChildrenByParentSlug(String parentSlug, PlaceKind kind, Pageable pageable);
}