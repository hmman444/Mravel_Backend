package com.mravel.catalog.dto.search;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record HotelFacets(
        List<FacetBucket> starRatings,
        List<FacetBucket> hotelTypes,
        List<FacetBucket> amenities,
        List<FacetBucket> priceRanges,
        Map<String, String> facetErrors
) {}
