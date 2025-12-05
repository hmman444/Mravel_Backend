package com.mravel.catalog.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mravel.catalog.dto.SearchRequests.RestaurantSearchRequest;
import com.mravel.catalog.dto.restaurant.RestaurantDtos.RestaurantDetailDTO;
import com.mravel.catalog.dto.restaurant.RestaurantDtos.RestaurantSummaryDTO;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.repository.RestaurantDocRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantDocRepository repo;

    public Page<RestaurantSummaryDTO> searchRestaurants(RestaurantSearchRequest request, Pageable pageable) {
        if (request == null) {
            // Không filter gì, chỉ active + APPROVED
            return repo.searchRestaurants(null, null, null, pageable)
                       .map(RestaurantMapper::toSummary);
        }

        String location = request.location();

        Integer people = request.people();
        if (people != null && people <= 0) {
            people = null;
        }

        Set<String> cuisineSlugs = request.cuisineSlugs();
        if (cuisineSlugs != null && cuisineSlugs.isEmpty()) {
            cuisineSlugs = null;
        }

        // visitDate / visitTime hiện tại CHƯA dùng filter,
        // để dành bước sau áp dụng rule giờ mở cửa theo OpeningHours.

        return repo.searchRestaurants(location, people, cuisineSlugs, pageable)
                   .map(RestaurantMapper::toSummary);
    }

    public RestaurantDetailDTO getBySlug(String slug) {
        RestaurantDoc r = repo.findBySlugAndActiveTrue(slug)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        return RestaurantMapper.toDetail(r);
    }
}