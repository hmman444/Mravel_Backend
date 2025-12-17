// src/main/java/com/mravel/catalog/service/HotelService.java
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

import com.mravel.catalog.dto.SearchRequests.HotelSearchRequest;
import com.mravel.catalog.dto.hotel.HotelDtos.HotelDetailDTO;
import com.mravel.catalog.dto.hotel.HotelDtos.HotelSummaryDTO;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.repository.AmenityCatalogRepository;
import com.mravel.catalog.repository.HotelDocRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final AmenityCatalogService amenityCatalogService;
    private final HotelDocRepository hotelRepo;
    private final MongoTemplate mongoTemplate;
    private final AmenityCatalogRepository amenityCatalogRepo;

    public Page<HotelSummaryDTO> searchHotels(HotelSearchRequest request, Pageable pageable) {
        if (request == null) {
            // không có bộ lọc gì, chỉ location = null, capacity = null
            return hotelRepo.searchHotels(null, null, null, pageable)
                    .map(HotelMapper::toSummary);
        }

        String location = request.location();
        Integer adults = safeInt(request.adults());
        Integer children = safeInt(request.children());
        Integer rooms = safeInt(request.rooms());

        // ----- Quy đổi trẻ em -----
        // 2 trẻ em = 1 người lớn, dùng ceil(children / 2.0)
        int childEquivalent = (children <= 0) ? 0 : (int) Math.ceil(children / 2.0);
        int guestsNormalized = adults + childEquivalent;

        Integer minGuestsPerRoom = null;
        Integer requiredRooms = (rooms != null && rooms > 0) ? rooms : null;

        if (guestsNormalized > 0 && rooms != null && rooms > 0) {
            // chia đều số khách cho số phòng, làm tròn lên
            minGuestsPerRoom = (int) Math.ceil(guestsNormalized * 1.0 / rooms);
        } else if (guestsNormalized > 0) {
            // không chọn số phòng => giả sử 1 phòng
            minGuestsPerRoom = guestsNormalized;
        }

        // checkIn / checkOut hiện tại CHƯA dùng lọc, để dành cho later
        // (booking-service)

        return hotelRepo.searchHotels(location, minGuestsPerRoom, requiredRooms, pageable)
                .map(HotelMapper::toSummary);
    }

    public HotelDetailDTO getBySlug(String slug) {
        HotelDoc h = hotelRepo.findBySlugAndActiveTrue(slug)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        Set<String> codes = new HashSet<>();
        if (h.getAmenityCodes() != null)
            codes.addAll(h.getAmenityCodes());
        if (h.getRoomTypes() != null) {
            for (var rt : h.getRoomTypes()) {
                if (rt != null && rt.getAmenityCodes() != null) {
                    codes.addAll(rt.getAmenityCodes());
                }
            }
        }

        List<AmenityCatalogDoc> catalog = codes.isEmpty()
                ? List.of()
                : amenityCatalogRepo.findByScopeInAndCodeInAndActiveTrue(
                        List.of(AmenityScope.HOTEL, AmenityScope.ROOM),
                        new ArrayList<>(codes));

        Map<String, AmenityCatalogDoc> hotelCatalogMap = HotelMapper.toCatalogMapByScope(catalog, AmenityScope.HOTEL);
        Map<String, AmenityCatalogDoc> roomCatalogMap = HotelMapper.toCatalogMapByScope(catalog, AmenityScope.ROOM);

        return HotelMapper.toDetail(h, hotelCatalogMap, roomCatalogMap);
    }

    private Integer safeInt(Integer i) {
        return i == null ? 0 : i;
    }

    public void attachHotelAmenities(String hotelId, List<String> codes) {
        // ensure hotel exists
        hotelRepo.findById(hotelId).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        // validate codes
        amenityCatalogService.validateCodes(AmenityScope.HOTEL, codes);
        List<String> normalized = codes.stream().map(c -> c.trim().toUpperCase()).distinct().toList();

        Query q = Query.query(Criteria.where("_id").is(hotelId));
        Update u = new Update().addToSet("amenityCodes").each(normalized.toArray());
        mongoTemplate.updateFirst(q, u, "hotels"); // collection name = "hotels" nếu HotelDoc @Document("hotels")
    }

    public void detachHotelAmenities(String hotelId, List<String> codes) {
        hotelRepo.findById(hotelId).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        List<String> normalized = codes.stream().map(c -> c.trim().toUpperCase()).distinct().toList();

        Query q = Query.query(Criteria.where("_id").is(hotelId));
        Update u = new Update().pullAll("amenityCodes", normalized.toArray());
        mongoTemplate.updateFirst(q, u, "hotels");
    }
}