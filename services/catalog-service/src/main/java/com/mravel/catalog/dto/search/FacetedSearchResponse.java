package com.mravel.catalog.dto.search;

import java.util.List;

public record FacetedSearchResponse<T, F>(
        List<T> results,
        long totalHits,
        int page,
        int size,
        int totalPages,
        F facets
) {}
