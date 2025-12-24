// src/main/java/com/mravel/catalog/service/HotelService.java
package com.mravel.catalog.service;

import java.time.LocalDate;
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
    private final HotelInventoryService inventoryService;
    private final HotelDocRepository hotelRepo;
    private final MongoTemplate mongoTemplate;
    private final AmenityCatalogRepository amenityCatalogRepo;

    public Page<HotelSummaryDTO> searchHotels(HotelSearchRequest request, Pageable pageable) {
        if (request == null) {
            return hotelRepo.searchHotels(null, null, null, pageable).map(HotelMapper::toSummary);
        }

        String location = request.location();
        Integer adults = safeInt(request.adults());
        Integer children = safeInt(request.children());
        Integer rooms = safeInt(request.rooms());

        int childEquivalent = (children <= 0) ? 0 : (int) Math.ceil(children / 2.0);
        int guestsNormalized = adults + childEquivalent;

        Integer requiredRooms = (rooms != null && rooms > 0) ? rooms : null;

        Integer minGuestsPerRoom = (guestsNormalized > 0)
                ? ((requiredRooms != null)
                        ? (int) Math.ceil(guestsNormalized * 1.0 / requiredRooms)
                        : guestsNormalized)
                : null;

        // ==== NEW: availability filter nếu có ngày ====
        var checkIn = request.checkIn();
        var checkOut = request.checkOut();

        if (checkIn != null && checkOut != null && checkOut.isAfter(checkIn)) {
            // 1) Lấy ALL candidates (unpaged) rồi tự paginate sau khi filter
            Page<HotelDoc> all = hotelRepo.searchHotels(location, minGuestsPerRoom, requiredRooms, Pageable.unpaged());

            int roomsNeeded = (requiredRooms != null && requiredRooms > 0) ? requiredRooms : 1;

            List<HotelDoc> available = all.getContent().stream()
                    .filter(h -> hasAnyRoomTypeAvailable(h, checkIn, checkOut, roomsNeeded, minGuestsPerRoom))
                    .toList();

            // 2) paginate thủ công theo pageable hiện tại
            int page = pageable.getPageNumber();
            int size = pageable.getPageSize();
            int from = Math.min(page * size, available.size());
            int to = Math.min(from + size, available.size());

            List<HotelSummaryDTO> slice = new ArrayList<>(
                    available.subList(from, to).stream()
                            .map(HotelMapper::toSummary)
                            .toList());
            return new PageImpl<>(slice, pageable, available.size());
        }

        // fallback: như cũ
        return hotelRepo.searchHotels(location, minGuestsPerRoom, requiredRooms, pageable)
                .map(HotelMapper::toSummary);
    }

    private boolean hasAnyRoomTypeAvailable(
            HotelDoc hotel,
            LocalDate checkIn,
            LocalDate checkOut,
            int roomsNeeded,
            Integer minGuestsPerRoom) {
        if (hotel.getRoomTypes() == null || hotel.getRoomTypes().isEmpty())
            return false;

        for (HotelDoc.RoomType rt : hotel.getRoomTypes()) {
            if (rt == null || rt.getId() == null)
                continue;

            Integer maxGuests = rt.getMaxGuests();
            Integer totalRooms = rt.getTotalRooms();

            // giữ logic khớp với search (đừng check lung tung)
            if (minGuestsPerRoom != null && (maxGuests == null || maxGuests < minGuestsPerRoom))
                continue;
            if (totalRooms != null && totalRooms < roomsNeeded)
                continue;

            var av = inventoryService.getAvailability(
                    hotel.getId(),
                    hotel.getSlug(),
                    rt.getId(),
                    checkIn,
                    checkOut,
                    roomsNeeded);

            if (Boolean.TRUE.equals(av.isEnough()))
                return true;
        }

        return false;
    }

    public HotelDetailDTO getBySlug(String slug, boolean includeInactive) {

        HotelDoc h = (includeInactive
                ? hotelRepo.findBySlugAndModeration_Status(slug, HotelDoc.HotelStatus.APPROVED)
                : hotelRepo.findBySlugAndActiveTrueAndModeration_Status(slug, HotelDoc.HotelStatus.APPROVED)
        ).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        // phần map amenity giữ nguyên như bạn đang làm...
        Set<String> codes = new HashSet<>();
        if (h.getAmenityCodes() != null) codes.addAll(h.getAmenityCodes());
        if (h.getRoomTypes() != null) {
            for (var rt : h.getRoomTypes()) {
                if (rt != null && rt.getAmenityCodes() != null) codes.addAll(rt.getAmenityCodes());
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