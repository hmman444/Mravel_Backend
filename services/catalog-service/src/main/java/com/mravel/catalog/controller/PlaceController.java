package com.mravel.catalog.controller;

import java.util.Set;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.mravel.catalog.dto.place.PlaceDtos.PlaceDetailDTO;
import com.mravel.catalog.dto.place.PlaceDtos.PlaceSummaryDTO;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.service.PlaceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    // Dịch vụ → Khách sạn (4 field: location, checkIn, checkOut, adults/rooms)
    @GetMapping("/hotels")
    public Page<PlaceSummaryDTO> searchHotels(
            @RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "checkIn", required = false) String checkIn,
            @RequestParam(name = "checkOut", required = false) String checkOut,
            @RequestParam(name = "adults", required = false, defaultValue = "1") Integer adults,
            @RequestParam(name = "rooms", required = false, defaultValue = "1") Integer rooms,
            @ParameterObject Pageable pageable
    ) {
        return placeService.searchHotels(location, checkIn, checkOut, adults, rooms, pageable);
    }

    // Dịch vụ → Quán ăn (2 field: location, cuisineSlugs)
    @GetMapping("/restaurants")
    public Page<PlaceSummaryDTO> searchRestaurants(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Set<String> cuisineSlugs,
            @ParameterObject Pageable pageable
    ) {
        return placeService.searchRestaurants(location, cuisineSlugs, pageable);
    }

    // Địa điểm (1 field: q)
    @GetMapping("/poi")
    public Page<PlaceSummaryDTO> searchPlaces(
            @RequestParam(required = false) String q,
            @ParameterObject Pageable pageable
    ) {
        return placeService.searchPlaces(q, pageable);
    }

    // Xem chi tiết theo slug
    @GetMapping("/{slug}")
    public PlaceDetailDTO getDetail(@PathVariable String slug) {
        return placeService.getBySlug(slug);
    }

    @GetMapping("/{slug}/children")
    public Page<PlaceSummaryDTO> children(
        @PathVariable String slug,
        @ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "POI") PlaceKind kind
    ) {
    return placeService.findChildrenByParentSlug(slug, kind, pageable);
    }
}