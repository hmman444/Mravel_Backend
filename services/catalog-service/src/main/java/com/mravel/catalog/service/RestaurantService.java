package com.mravel.catalog.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mravel.catalog.dto.SearchRequests.RestaurantSearchRequest;
import com.mravel.catalog.dto.restaurant.RestaurantDtos.RestaurantDetailDTO;
import com.mravel.catalog.dto.restaurant.RestaurantDtos.RestaurantSummaryDTO;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.repository.AmenityCatalogRepository;
import com.mravel.catalog.repository.RestaurantDocRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final AmenityCatalogService amenityCatalogService;
    private final RestaurantDocRepository restaurantRepo;
    private final MongoTemplate mongoTemplate;
    private final AmenityCatalogRepository amenityCatalogRepo;

    public Page<RestaurantSummaryDTO> searchRestaurants(RestaurantSearchRequest request, Pageable pageable) {
        if (request == null) {
            // Không filter gì, chỉ active + APPROVED
            return restaurantRepo.searchRestaurants(null, null, null, pageable)
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

        return restaurantRepo.searchRestaurants(location, people, cuisineSlugs, pageable)
                .map(RestaurantMapper::toSummary);
    }

    public RestaurantDetailDTO getBySlug(String slug) {
        RestaurantDoc r = restaurantRepo.findBySlugAndActiveTrue(slug)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        Set<String> codes = new HashSet<>();
        if (r.getAmenityCodes() != null)
            codes.addAll(r.getAmenityCodes());

        List<AmenityCatalogDoc> catalog = codes.isEmpty()
                ? List.of()
                : amenityCatalogRepo.findByScopeAndCodeInAndActiveTrue(
                        AmenityScope.RESTAURANT,
                        new ArrayList<>(codes));

        Map<String, AmenityCatalogDoc> catalogMap = HotelMapper.toCatalogMap(catalog);
        // dùng lại helper map code->doc của bạn cũng được

        return RestaurantMapper.toDetail(r, catalogMap);
    }

    public void attachRestaurantAmenities(String restaurantId, List<String> codes) {
        restaurantRepo.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        amenityCatalogService.validateCodes(AmenityScope.RESTAURANT, codes);

        List<String> normalized = codes.stream().map(c -> c.trim().toUpperCase()).distinct().toList();
        Query q = Query.query(Criteria.where("_id").is(restaurantId));
        Update u = new Update().addToSet("amenityCodes").each(normalized.toArray());
        mongoTemplate.updateFirst(q, u, "restaurants");
    }

    public void detachRestaurantAmenities(String restaurantId, List<String> codes) {
        restaurantRepo.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        List<String> normalized = codes.stream().map(c -> c.trim().toUpperCase()).distinct().toList();
        Query q = Query.query(Criteria.where("_id").is(restaurantId));
        Update u = new Update().pullAll("amenityCodes", normalized.toArray());
        mongoTemplate.updateFirst(q, u, "restaurants");
    }
}