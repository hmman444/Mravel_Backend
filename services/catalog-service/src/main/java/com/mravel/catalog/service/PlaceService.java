package com.mravel.catalog.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mravel.catalog.dto.place.PlaceDtos;
import com.mravel.catalog.dto.place.PlaceDtos.*;
import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.repository.PlaceDocRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {

  private final PlaceDocRepository repo;

  public Page<PlaceSummaryDTO> searchHotels(String location,
                                            String checkIn,
                                            String checkOut,
                                            Integer adults,
                                            Integer rooms,
                                            Pageable pageable) {
    return repo.searchHotels(location, checkIn, checkOut, adults, rooms, pageable)
               .map(PlaceMapper::toSummary);
  }

  public Page<PlaceSummaryDTO> searchRestaurants(String location,
                                                 Set<String> cuisineSlugs,
                                                 Pageable pageable) {
    return repo.searchRestaurants(location, cuisineSlugs, pageable)
               .map(PlaceMapper::toSummary);
  }

  public Page<PlaceSummaryDTO> searchPlaces(String q, Pageable pageable) {
    return repo.searchPlaces(q, pageable).map(PlaceMapper::toSummary);
  }

  public PlaceDetailDTO getBySlug(String slug) {
    PlaceDoc p = repo.findBySlug(slug).orElseThrow(() -> new IllegalArgumentException("Place not found"));

    var images = p.getImages()==null ? List.<ImageDTO>of()
      : p.getImages().stream().map(PlaceMapper::toImage).toList();

    var hours = p.getOpenHours()==null ? List.<OpenHourDTO>of()
      : p.getOpenHours().stream().map(PlaceMapper::toOpenHour).toList();

    var categories = p.getCategories()==null ? List.<PlaceDtos.CategoryDTO>of()
      : p.getCategories().stream().map(c -> new PlaceDtos.CategoryDTO(null, c.getName(), c.getSlug())).toList();

    var tags = p.getTags()==null ? List.<TagDTO>of()
      : p.getTags().stream().map(PlaceMapper::toTag).toList();

    return PlaceMapper.toDetail(p, images, hours, categories, tags);
  }

  public Page<PlaceSummaryDTO> findChildrenByParentSlug(String slug, PlaceKind kind, Pageable pageable) {
    return repo.findChildrenByParentSlug(slug, kind, pageable)
              .map(PlaceMapper::toSummary);
  }
}