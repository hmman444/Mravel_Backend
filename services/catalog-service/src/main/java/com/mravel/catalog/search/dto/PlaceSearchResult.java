package com.mravel.catalog.search.dto;

import java.util.List;

import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.model.enums.PriceLevel;
import com.mravel.catalog.model.enums.VenueType;

public record PlaceSearchResult(
        String id,
        String name,
        String slug,
        PlaceKind kind,
        VenueType venueType,
        String addressLine,
        String provinceName,
        double[] location,
        PriceLevel priceLevel,
        Double avgRating,
        Integer reviewsCount,
        List<ImageRef> images,
        Boolean active
) {
    public record ImageRef(String url, String caption, Boolean cover, Integer sortOrder) {}
}
