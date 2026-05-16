package com.mravel.catalog.search;

import com.mravel.catalog.dto.SearchRequests.RestaurantSearchRequest;
import com.mravel.catalog.search.dto.RestaurantSearchResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantSearchService {
    Page<RestaurantSearchResult> search(RestaurantSearchRequest request, Pageable pageable);
}
