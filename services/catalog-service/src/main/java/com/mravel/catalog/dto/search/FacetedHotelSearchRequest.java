package com.mravel.catalog.dto.search;

import java.util.List;
import java.util.Set;

public record FacetedHotelSearchRequest(
        // (A) Context — drives aggregation counts, unaffected by filters
        String location,
        Boolean destOnly,

        // (B) Filters — applied via post_filter, does NOT affect aggregation counts
        List<Integer> starRatings,
        List<String> hotelTypes,
        Set<String> amenities,
        Integer minPrice,
        Integer maxPrice,
        Double minRating
) {}
