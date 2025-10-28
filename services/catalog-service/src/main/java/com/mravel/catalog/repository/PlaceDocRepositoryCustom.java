package com.mravel.catalog.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;

public interface PlaceDocRepositoryCustom {
  Page<PlaceDoc> searchHotels(String location, String checkIn, String checkOut,
                              Integer adults, Integer rooms, Pageable pageable);
  Page<PlaceDoc> searchRestaurants(String location, Set<String> cuisineSlugs, Pageable pageable);
  Page<PlaceDoc> searchPlaces(String q, Pageable pageable);
  Page<PlaceDoc> findChildrenByParentSlug(String parentSlug, PlaceKind kind, Pageable pageable);
}