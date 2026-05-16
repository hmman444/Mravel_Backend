package com.mravel.catalog.search;

import com.mravel.catalog.dto.SearchRequests.HotelSearchRequest;
import com.mravel.catalog.search.dto.HotelSearchResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelSearchService {
    Page<HotelSearchResult> search(HotelSearchRequest request, Pageable pageable);
}
