package com.mravel.catalog.dto.search;

import java.util.List;

public record RestaurantFacets(
        List<FacetBucket> cuisines,
        List<FacetBucket> priceLevels,
        List<FacetBucket> amenities
) {}
