package com.mravel.catalog.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mravel.catalog.dto.place.PlaceDtos.PlaceSummaryDTO;
import com.mravel.catalog.dto.search.FacetedPlaceSearchRequest;
import com.mravel.catalog.dto.search.FacetedSearchResponse;
import com.mravel.catalog.dto.search.PlaceFacets;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.search.dto.PlaceSearchResult;

public interface PlaceSearchService {

    Page<PlaceSearchResult> search(String q, PlaceKind kind, Pageable pageable);

    default FacetedSearchResponse<PlaceSummaryDTO, PlaceFacets> searchFaceted(
            FacetedPlaceSearchRequest request, Pageable pageable) {
        throw new UnsupportedOperationException("Faceted search requires Elasticsearch engine");
    }
}
