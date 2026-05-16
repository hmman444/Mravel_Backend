package com.mravel.catalog.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mravel.catalog.dto.hotel.HotelDtos.HotelSummaryDTO;
import com.mravel.catalog.dto.search.FacetedHotelSearchRequest;
import com.mravel.catalog.dto.search.FacetedSearchResponse;
import com.mravel.catalog.dto.search.HotelFacets;
import com.mravel.catalog.search.es.ElasticsearchHotelSearchService;
import com.mravel.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog/hotels")
@RequiredArgsConstructor
@ConditionalOnProperty(name = "catalog.search.engine", havingValue = "elasticsearch")
public class HotelFacetedController {

    private final ElasticsearchHotelSearchService esHotelSearchService;

    @PostMapping("/search/faceted")
    public ResponseEntity<ApiResponse<FacetedSearchResponse<HotelSummaryDTO, HotelFacets>>> searchFaceted(
            @RequestBody(required = false) FacetedHotelSearchRequest request,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        FacetedSearchResponse<HotelSummaryDTO, HotelFacets> result =
                esHotelSearchService.searchFaceted(request, pageable);
        return ResponseEntity.ok(ApiResponse.success("Tìm kiếm khách sạn thành công", result));
    }
}
