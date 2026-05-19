package com.mravel.catalog.dto.search;

import java.util.List;

public record FacetedPlaceSearchRequest(
        String parentSlug,
        String q,
        List<String> categorySlugs,
        List<String> venueTypes,
        String priceLevel,
        Double minRating
) {}
