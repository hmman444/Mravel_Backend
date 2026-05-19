package com.mravel.catalog.dto.search;

import java.util.List;

public record PlaceFacets(
        List<FacetBucket> categories,
        List<FacetBucket> priceLevels,
        List<FacetBucket> venueTypes
) {}
