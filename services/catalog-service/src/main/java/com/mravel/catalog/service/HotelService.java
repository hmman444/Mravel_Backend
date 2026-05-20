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
import com.mravel.catalog.search.HotelSearchService;
import com.mravel.catalog.search.es.IndexingService;
import com.mravel.catalog.search.dto.HotelSearchResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelSearchService hotelSearchService;
    private final HotelInventoryService inventoryService;
    private final HotelDocRepository hotelRepo;
    private final MongoTemplate mongoTemplate;
    private final AmenityCatalogRepository amenityCatalogRepo;
    private final AmenityCatalogService amenityCatalogService;
    private final IndexingService indexingService;

    public Page<HotelSummaryDTO> searchHotels(HotelSearchRequest request, Pageable pageable) {
        Page<HotelSearchResult> page = hotelSearchService.search(request, pageable);

        if (request != null
                && request.checkIn() != null
                && request.checkOut() != null
                && request.checkOut().isAfter(request.checkIn())) {

            int roomsNeeded = request.rooms() != null && request.rooms() > 0 ? request.rooms() : 1;
            Integer minGuests = request.calcMinGuestsPerRoom();

            List<HotelSummaryDTO> filtered = page.getContent().stream()
                    .filter(h -> hasAnyRoomTypeAvailable(h, request.checkIn(), request.checkOut(), roomsNeeded, minGuests))
                    .map(HotelMapper::toSummary)
                    .toList();
            return new PageImpl<>(filtered, pageable, page.getTotalElements());
        }

        return page.map(HotelMapper::toSummary);
    }

    private boolean hasAnyRoomTypeAvailable(
            HotelSearchResult hotel,
            LocalDate checkIn,
            LocalDate checkOut,
            int roomsNeeded,
            Integer minGuestsPerRoom) {

        if (hotel.roomTypes() == null || hotel.roomTypes().isEmpty()) return false;

        for (HotelSearchResult.RoomTypeMini rt : hotel.roomTypes()) {
            if (rt == null || rt.id() == null) continue;
            if (minGuestsPerRoom != null && (rt.maxGuests() == null || rt.maxGuests() < minGuestsPerRoom)) continue;
            if (rt.totalRooms() != null && rt.totalRooms() < roomsNeeded) continue;

            var av = inventoryService.getAvailability(
                    hotel.id(), hotel.slug(), rt.id(), checkIn, checkOut, roomsNeeded);
            if (Boolean.TRUE.equals(av.isEnough())) return true;
        }
        return false;
    }

    public HotelDetailDTO getBySlug(String slug, boolean includeInactive) {
        HotelDoc h = (includeInactive
                ? hotelRepo.findBySlugAndModeration_Status(slug, HotelDoc.HotelStatus.APPROVED)
                : hotelRepo.findBySlugAndActiveTrueAndModeration_Status(slug, HotelDoc.HotelStatus.APPROVED)
        ).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

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
                        List.of(AmenityScope.HOTEL, AmenityScope.ROOM), new ArrayList<>(codes));

        Map<String, AmenityCatalogDoc> hotelCatalogMap = HotelMapper.toCatalogMapByScope(catalog, AmenityScope.HOTEL);
        Map<String, AmenityCatalogDoc> roomCatalogMap  = HotelMapper.toCatalogMapByScope(catalog, AmenityScope.ROOM);

        return HotelMapper.toDetail(h, hotelCatalogMap, roomCatalogMap);
    }

    public void attachHotelAmenities(String hotelId, List<String> codes) {
        hotelRepo.findById(hotelId).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        amenityCatalogService.validateCodes(AmenityScope.HOTEL, codes);
        List<String> normalized = codes.stream().map(c -> c.trim().toUpperCase()).distinct().toList();
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(hotelId)),
                new Update().addToSet("amenityCodes").each(normalized.toArray()),
                "hotels");
        hotelRepo.findById(hotelId).ifPresent(indexingService::syncHotel);
    }

    public void detachHotelAmenities(String hotelId, List<String> codes) {
        hotelRepo.findById(hotelId).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        List<String> normalized = codes.stream().map(c -> c.trim().toUpperCase()).distinct().toList();
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(hotelId)),
                new Update().pullAll("amenityCodes", normalized.toArray()),
                "hotels");
        hotelRepo.findById(hotelId).ifPresent(indexingService::syncHotel);
    }
}
