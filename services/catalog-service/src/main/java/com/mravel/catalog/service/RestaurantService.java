package com.mravel.catalog.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
            return restaurantRepo.searchRestaurants(null, null, null, pageable)
                    .map(RestaurantMapper::toSummary);
        }

        String location = request.location();
        if (location != null) {
            location = location.trim();
            if (location.isBlank())
                location = null;
        }

        Integer people = request.people();
        if (people != null && people <= 0)
            people = null;

        Set<String> cuisineSlugs = request.cuisineSlugs();
        if (cuisineSlugs != null) {
            cuisineSlugs = cuisineSlugs.stream()
                    .filter(s -> s != null)
                    .map(s -> s.trim().toUpperCase())
                    .filter(s -> !s.isBlank())
                    .collect(java.util.stream.Collectors.toSet());
            if (cuisineSlugs.isEmpty())
                cuisineSlugs = null;
        }

        // ===== NEW: parse visitDate + visitTime =====
        LocalDate visitDate = parseDate(request.visitDate()); // "YYYY-MM-DD"
        LocalTime visitTime = parseTime(request.visitTime()); // "HH:mm"

        // Nếu user không nhập date/time -> giữ nguyên logic cũ
        if (visitDate == null || visitTime == null) {
            return restaurantRepo.searchRestaurants(location, people, cuisineSlugs, pageable)
                    .map(RestaurantMapper::toSummary);
        }

        // ===== NEW: để lọc giờ cho đúng paging -> lấy hết rồi tự paginate =====
        // (dataset đồ án thường nhỏ nên cách này ok)
        List<RestaurantDoc> allMatched = restaurantRepo
                .searchRestaurants(location, people, cuisineSlugs, Pageable.unpaged())
                .getContent();

        List<RestaurantDoc> opened = allMatched.stream()
                .filter(r -> isOpenAt(r, visitDate, visitTime))
                .toList();

        // paginate thủ công
        if (pageable == null || pageable.isUnpaged()) {
            return new org.springframework.data.domain.PageImpl<>(
                    opened.stream().map(RestaurantMapper::toSummary).toList());
        }

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), opened.size());
        List<RestaurantDoc> pageSlice = (start >= opened.size()) ? List.of() : opened.subList(start, end);

        List<RestaurantSummaryDTO> dto = pageSlice.stream()
                .map(RestaurantMapper::toSummary)
                .toList();

        return new org.springframework.data.domain.PageImpl<>(dto, pageable, opened.size());
    }

    public RestaurantDetailDTO getBySlug(String slug, boolean includeInactive) {

        RestaurantDoc r = (includeInactive
                ? restaurantRepo.findBySlugAndModeration_Status(slug, RestaurantDoc.RestaurantStatus.APPROVED) // ✅ bỏ active
                : restaurantRepo.findBySlugAndActiveTrueAndModeration_Status(slug, RestaurantDoc.RestaurantStatus.APPROVED)
        ).orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        Set<String> codes = new HashSet<>();
        if (r.getAmenityCodes() != null) codes.addAll(r.getAmenityCodes());

        List<AmenityCatalogDoc> catalog = codes.isEmpty()
                ? List.of()
                : amenityCatalogRepo.findByScopeAndCodeInAndActiveTrue(
                        AmenityScope.RESTAURANT,
                        new ArrayList<>(codes));

        Map<String, AmenityCatalogDoc> catalogMap = HotelMapper.toCatalogMap(catalog);
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

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    private static LocalDate parseDate(String s) {
        if (s == null || s.isBlank())
            return null;
        try {
            return LocalDate.parse(s.trim(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return null; // hoặc throw IllegalArgumentException nếu bạn muốn strict
        }
    }

    private static LocalTime parseTime(String s) {
        if (s == null || s.isBlank())
            return null;
        try {
            return LocalTime.parse(s.trim(), TIME_FMT); // "19:00"
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static boolean isOpenAt(RestaurantDoc r, LocalDate date, LocalTime time) {
        if (r == null || date == null || time == null)
            return true;

        List<RestaurantDoc.OpeningHour> hours = r.getOpeningHours();
        if (hours == null || hours.isEmpty())
            return false;

        DayOfWeek dow = date.getDayOfWeek();

        for (RestaurantDoc.OpeningHour h : hours) {
            if (h == null)
                continue;
            if (h.getDayOfWeek() != dow)
                continue;
            if (Boolean.TRUE.equals(h.getClosed()))
                continue;
            if (Boolean.TRUE.equals(h.getOpen24h()))
                return true;

            LocalTime open = h.getOpenTime();
            LocalTime close = h.getCloseTime();
            if (open == null || close == null)
                continue;

            // Case bình thường: open <= time < close
            if (!close.isBefore(open)) {
                if (!time.isBefore(open) && time.isBefore(close))
                    return true;
            } else {
                // Case qua nửa đêm: 18:00 - 02:00
                if (!time.isBefore(open) || time.isBefore(close))
                    return true;
            }
        }
        return false;
    }
}