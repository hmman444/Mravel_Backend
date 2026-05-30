package com.mravel.catalog.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import com.mravel.catalog.search.RestaurantSearchService;
import com.mravel.catalog.search.es.IndexingService;
import com.mravel.catalog.search.dto.RestaurantSearchResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantSearchService restaurantSearchService;
    private final RestaurantDocRepository restaurantRepo;
    private final MongoTemplate mongoTemplate;
    private final AmenityCatalogRepository amenityCatalogRepo;
    private final AmenityCatalogService amenityCatalogService;
    private final IndexingService indexingService;

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    public Page<RestaurantSummaryDTO> searchRestaurants(RestaurantSearchRequest request, Pageable pageable) {
        Page<RestaurantSearchResult> page = restaurantSearchService.search(request, pageable);

        if (request != null) {
            LocalDate visitDate = parseDate(request.visitDate());
            LocalTime visitTime = parseTime(request.visitTime());

            if (visitDate != null && visitTime != null) {
                List<RestaurantSummaryDTO> filtered = page.getContent().stream()
                        .filter(r -> isOpenAt(r, visitDate, visitTime))
                        .map(RestaurantMapper::toSummary)
                        .toList();
                return new PageImpl<>(filtered, pageable, filtered.size());
            }
        }

        return page.map(RestaurantMapper::toSummary);
    }

    public RestaurantDetailDTO getBySlug(String slug, boolean includeInactive) {
        RestaurantDoc r = (includeInactive
                ? restaurantRepo.findBySlugAndModeration_Status(slug, RestaurantDoc.RestaurantStatus.APPROVED)
                : restaurantRepo.findBySlugAndActiveTrueAndModeration_Status(slug, RestaurantDoc.RestaurantStatus.APPROVED)
        ).orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        Set<String> codes = new HashSet<>();
        if (r.getAmenityCodes() != null) codes.addAll(r.getAmenityCodes());

        List<AmenityCatalogDoc> catalog = codes.isEmpty()
                ? List.of()
                : amenityCatalogRepo.findByScopeAndCodeInAndActiveTrue(AmenityScope.RESTAURANT, new ArrayList<>(codes));

        Map<String, AmenityCatalogDoc> catalogMap = HotelMapper.toCatalogMap(catalog);
        return RestaurantMapper.toDetail(r, catalogMap);
    }

    public void attachRestaurantAmenities(String restaurantId, List<String> codes) {
        restaurantRepo.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        amenityCatalogService.validateCodes(AmenityScope.RESTAURANT, codes);
        List<String> normalized = codes.stream().map(c -> c.trim().toUpperCase()).distinct().toList();
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(restaurantId)),
                new Update().addToSet("amenityCodes").each(normalized.toArray()),
                "restaurants");
        restaurantRepo.findById(restaurantId).ifPresent(indexingService::syncRestaurant);
    }

    public void detachRestaurantAmenities(String restaurantId, List<String> codes) {
        restaurantRepo.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        List<String> normalized = codes.stream().map(c -> c.trim().toUpperCase()).distinct().toList();
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(restaurantId)),
                new Update().pullAll("amenityCodes", normalized.toArray()),
                "restaurants");
        restaurantRepo.findById(restaurantId).ifPresent(indexingService::syncRestaurant);
    }

    private static LocalDate parseDate(String s) {
        if (s == null || s.isBlank()) return null;
        try { return LocalDate.parse(s.trim(), DateTimeFormatter.ISO_LOCAL_DATE); }
        catch (DateTimeParseException e) { return null; }
    }

    private static LocalTime parseTime(String s) {
        if (s == null || s.isBlank()) return null;
        try { return LocalTime.parse(s.trim(), TIME_FMT); }
        catch (DateTimeParseException e) { return null; }
    }

    private static boolean isOpenAt(RestaurantSearchResult r, LocalDate date, LocalTime time) {
        List<RestaurantSearchResult.OpeningHourRef> hours = r.openingHours();
        if (hours == null || hours.isEmpty()) return false;
        DayOfWeek dow = date.getDayOfWeek();
        for (RestaurantSearchResult.OpeningHourRef h : hours) {
            if (h == null || h.dayOfWeek() != dow) continue;
            if (Boolean.TRUE.equals(h.closed())) continue;
            if (Boolean.TRUE.equals(h.open24h())) return true;
            LocalTime open = h.openTime(), close = h.closeTime();
            if (open == null || close == null) continue;
            if (!close.isBefore(open)) {
                if (!time.isBefore(open) && time.isBefore(close)) return true;
            } else {
                if (!time.isBefore(open) || time.isBefore(close)) return true;
            }
        }
        return false;
    }
}
