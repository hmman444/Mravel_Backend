package com.mravel.catalog.search.es;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.model.index.HotelIndex;
import com.mravel.catalog.model.index.PlaceIndex;
import com.mravel.catalog.model.index.RestaurantIndex;
import com.mravel.catalog.repository.HotelDocRepository;
import com.mravel.catalog.repository.PlaceDocRepository;
import com.mravel.catalog.repository.RestaurantDocRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class IndexingService {

    private static final int BATCH_SIZE = 200;

    @Value("${catalog.search.engine:mongo}")
    private String searchEngine;

    private final HotelDocRepository hotelRepo;
    private final RestaurantDocRepository restaurantRepo;
    private final PlaceDocRepository placeRepo;
    private final ElasticsearchOperations esOps;

    public int reindexHotels() {
        int page = 0, total = 0;
        Page<HotelDoc> batch;
        do {
            batch = hotelRepo.findAll(PageRequest.of(page++, BATCH_SIZE));
            List<HotelIndex> docs = batch.getContent().stream()
                    .filter(h -> h.getDeletedAt() == null)
                    .map(IndexingService::toHotelIndex)
                    .toList();
            if (!docs.isEmpty()) {
                esOps.save(docs);
                total += docs.size();
            }
        } while (batch.hasNext());
        log.info("[ES] Reindexed {} hotels", total);
        return total;
    }

    public int reindexRestaurants() {
        int page = 0, total = 0;
        Page<RestaurantDoc> batch;
        do {
            batch = restaurantRepo.findAll(PageRequest.of(page++, BATCH_SIZE));
            List<RestaurantIndex> docs = batch.getContent().stream()
                    .filter(r -> r.getDeletedAt() == null)
                    .map(IndexingService::toRestaurantIndex)
                    .toList();
            if (!docs.isEmpty()) {
                esOps.save(docs);
                total += docs.size();
            }
        } while (batch.hasNext());
        log.info("[ES] Reindexed {} restaurants", total);
        return total;
    }

    public int reindexPlaces() {
        int page = 0, total = 0;
        Page<PlaceDoc> batch;
        do {
            batch = placeRepo.findAll(PageRequest.of(page++, BATCH_SIZE));
            List<PlaceIndex> docs = batch.getContent().stream()
                    .map(IndexingService::toPlaceIndex)
                    .toList();
            if (!docs.isEmpty()) {
                esOps.save(docs);
                total += docs.size();
            }
        } while (batch.hasNext());
        log.info("[ES] Reindexed {} places", total);
        return total;
    }

    public Map<String, Integer> reindexAll() {
        return Map.of(
                "hotels",      reindexHotels(),
                "restaurants", reindexRestaurants(),
                "places",      reindexPlaces()
        );
    }

    // =========================================================
    // REAL-TIME SYNC (called after each Mongo write)
    // =========================================================

    public void syncHotel(HotelDoc doc) {
        if (!"elasticsearch".equals(searchEngine)) return;
        try {
            esOps.save(toHotelIndex(doc));
        } catch (Exception e) {
            log.warn("[ES] sync hotel {} failed: {}", doc.getId(), e.getMessage());
        }
    }

    public void deleteHotel(String id) {
        if (!"elasticsearch".equals(searchEngine)) return;
        try {
            esOps.delete(id, HotelIndex.class);
        } catch (Exception e) {
            log.warn("[ES] delete hotel {} failed: {}", id, e.getMessage());
        }
    }

    public void syncRestaurant(RestaurantDoc doc) {
        if (!"elasticsearch".equals(searchEngine)) return;
        try {
            esOps.save(toRestaurantIndex(doc));
        } catch (Exception e) {
            log.warn("[ES] sync restaurant {} failed: {}", doc.getId(), e.getMessage());
        }
    }

    public void deleteRestaurant(String id) {
        if (!"elasticsearch".equals(searchEngine)) return;
        try {
            esOps.delete(id, RestaurantIndex.class);
        } catch (Exception e) {
            log.warn("[ES] delete restaurant {} failed: {}", id, e.getMessage());
        }
    }

    public void syncPlace(PlaceDoc doc) {
        if (!"elasticsearch".equals(searchEngine)) return;
        try {
            esOps.save(toPlaceIndex(doc));
        } catch (Exception e) {
            log.warn("[ES] sync place {} failed: {}", doc.getId(), e.getMessage());
        }
    }

    public void deletePlace(String id) {
        if (!"elasticsearch".equals(searchEngine)) return;
        try {
            esOps.delete(id, PlaceIndex.class);
        } catch (Exception e) {
            log.warn("[ES] delete place {} failed: {}", id, e.getMessage());
        }
    }

    // =========================================================
    // MAPPING: HotelDoc → HotelIndex
    // =========================================================

    static HotelIndex toHotelIndex(HotelDoc h) {
        String moderationStatus = (h.getModeration() != null && h.getModeration().getStatus() != null)
                ? h.getModeration().getStatus().name() : null;

        List<HotelIndex.RoomTypeData> roomTypes = h.getRoomTypes() == null ? List.of()
                : h.getRoomTypes().stream()
                        .filter(rt -> rt != null)
                        .map(rt -> new HotelIndex.RoomTypeData(rt.getId(), rt.getMaxGuests(), rt.getTotalRooms()))
                        .toList();

        List<HotelIndex.ImageData> images = h.getImages() == null ? List.of()
                : h.getImages().stream()
                        .filter(img -> img != null)
                        .map(img -> new HotelIndex.ImageData(img.getUrl(), img.getCaption(), img.getCover(), img.getSortOrder()))
                        .toList();

        HotelIndex.GeneralInfoData generalInfo = null;
        if (h.getGeneralInfo() != null) {
            generalInfo = new HotelIndex.GeneralInfoData(
                    h.getGeneralInfo().getMainFacilitiesSummary(),
                    h.getGeneralInfo().getDistanceToCityCenterKm());
        }

        return HotelIndex.builder()
                .id(h.getId())
                .name(h.getName())
                .slug(h.getSlug())
                .active(h.getActive())
                .moderationStatus(moderationStatus)
                .starRating(h.getStarRating())
                .hotelType(h.getHotelType() == null ? null : h.getHotelType().name())
                .destinationSlug(h.getDestinationSlug())
                .cityName(h.getCityName())
                .districtName(h.getDistrictName())
                .wardName(h.getWardName())
                .addressLine(h.getAddressLine())
                .location(toGeoPoint(h.getLocation()))
                .avgRating(h.getAvgRating())
                .reviewsCount(h.getReviewsCount())
                .ratingLabel(h.getRatingLabel())
                .minNightlyPrice(h.getMinNightlyPrice())
                .currencyCode(h.getCurrencyCode())
                .referenceNightlyPrice(h.getReferenceNightlyPrice())
                .discountPercent(h.getDiscountPercent())
                .amenityCodes(h.getAmenityCodes())
                .generalInfo(generalInfo)
                .images(images)
                .roomTypes(roomTypes)
                .build();
    }

    // =========================================================
    // MAPPING: RestaurantDoc → RestaurantIndex
    // =========================================================

    static RestaurantIndex toRestaurantIndex(RestaurantDoc r) {
        String moderationStatus = (r.getModeration() != null && r.getModeration().getStatus() != null)
                ? r.getModeration().getStatus().name() : null;

        List<String> cuisineCodes = r.getCuisines() == null ? List.of()
                : r.getCuisines().stream()
                        .filter(c -> c != null && c.getCode() != null)
                        .map(RestaurantDoc.CuisineTag::getCode)
                        .toList();

        List<RestaurantIndex.CuisineData> cuisines = r.getCuisines() == null ? List.of()
                : r.getCuisines().stream()
                        .filter(c -> c != null)
                        .map(c -> new RestaurantIndex.CuisineData(c.getCode(), c.getName()))
                        .toList();

        List<RestaurantIndex.AmbienceData> ambience = r.getAmbience() == null ? List.of()
                : r.getAmbience().stream()
                        .filter(a -> a != null)
                        .map(a -> new RestaurantIndex.AmbienceData(a.getCode(), a.getLabel()))
                        .toList();

        Integer totalCapacity = null, maxGroupSize = null;
        if (r.getCapacity() != null) {
            totalCapacity = r.getCapacity().getTotalCapacity();
            maxGroupSize  = r.getCapacity().getMaxGroupSize();
        }

        List<RestaurantIndex.ImageData> images = r.getImages() == null ? List.of()
                : r.getImages().stream()
                        .filter(img -> img != null)
                        .map(img -> new RestaurantIndex.ImageData(img.getUrl(), img.getCaption(), img.getCover(), img.getSortOrder()))
                        .toList();

        List<RestaurantIndex.OpeningHourData> openingHours = r.getOpeningHours() == null ? List.of()
                : r.getOpeningHours().stream()
                        .filter(oh -> oh != null)
                        .map(oh -> new RestaurantIndex.OpeningHourData(
                                oh.getDayOfWeek() == null  ? null : oh.getDayOfWeek().name(),
                                oh.getOpenTime()  == null  ? null : oh.getOpenTime().toString(),
                                oh.getCloseTime() == null  ? null : oh.getCloseTime().toString(),
                                oh.getOpen24h(),
                                oh.getClosed()))
                        .toList();

        return RestaurantIndex.builder()
                .id(r.getId())
                .name(r.getName())
                .slug(r.getSlug())
                .active(r.getActive())
                .moderationStatus(moderationStatus)
                .restaurantType(r.getRestaurantType() == null ? null : r.getRestaurantType().name())
                .destinationSlug(r.getDestinationSlug())
                .parentPlaceSlug(r.getParentPlaceSlug())
                .cityName(r.getCityName())
                .districtName(r.getDistrictName())
                .wardName(r.getWardName())
                .addressLine(r.getAddressLine())
                .location(toGeoPoint(r.getLocation()))
                .cuisineCodes(cuisineCodes)
                .cuisines(cuisines)
                .ambience(ambience)
                .totalCapacity(totalCapacity)
                .maxGroupSize(maxGroupSize)
                .avgRating(r.getAvgRating())
                .reviewsCount(r.getReviewsCount())
                .ratingLabel(r.getRatingLabel())
                .minPricePerPerson(r.getMinPricePerPerson())
                .maxPricePerPerson(r.getMaxPricePerPerson())
                .currencyCode(r.getCurrencyCode())
                .priceLevel(r.getPriceLevel()   == null ? null : r.getPriceLevel().name())
                .priceBucket(r.getPriceBucket() == null ? null : r.getPriceBucket().name())
                .amenityCodes(r.getAmenityCodes())
                .images(images)
                .openingHours(openingHours)
                .build();
    }

    // =========================================================
    // MAPPING: PlaceDoc → PlaceIndex
    // =========================================================

    static PlaceIndex toPlaceIndex(PlaceDoc p) {
        List<PlaceIndex.ImageData> images = p.getImages() == null ? List.of()
                : p.getImages().stream()
                        .filter(img -> img != null)
                        .map(img -> new PlaceIndex.ImageData(img.getUrl(), img.getCaption(), img.getCover(), img.getSortOrder()))
                        .toList();

        List<String> categorySlugs = p.getCategories() == null ? List.of()
                : p.getCategories().stream()
                        .filter(c -> c != null && c.getSlug() != null)
                        .map(PlaceDoc.CategoryMini::getSlug)
                        .toList();

        // Encode as "slug::DisplayName" — self-contained label, no post-hoc lookup needed
        List<String> categoryLabels = p.getCategories() == null ? List.of()
                : p.getCategories().stream()
                        .filter(c -> c != null && c.getSlug() != null)
                        .map(c -> c.getSlug() + "::" + (c.getName() != null ? c.getName() : c.getSlug()))
                        .toList();

        List<String> bestVisitTime = p.getBestVisitTime() == null ? List.of()
                : p.getBestVisitTime().stream()
                        .filter(t -> t != null)
                        .map(PlaceDoc.BestVisitTime::name)
                        .toList();

        return PlaceIndex.builder()
                .id(p.getId())
                .name(p.getName())
                .slug(p.getSlug())
                .active(p.getActive())
                .kind(p.getKind()         == null ? null : p.getKind().name())
                .venueType(p.getVenueType()    == null ? null : p.getVenueType().name())
                .parentSlug(p.getParentSlug())
                .addressLine(p.getAddressLine())
                .provinceName(p.getProvinceName())
                .districtName(p.getDistrictName())
                .location(toGeoPoint(p.getLocation()))
                .priceLevel(p.getPriceLevel()    == null ? null : p.getPriceLevel().name())
                .avgRating(p.getAvgRating())
                .reviewsCount(p.getReviewsCount())
                .categorySlugs(categorySlugs)
                .categoryLabels(categoryLabels)
                .images(images)
                // v2 discovery fields
                .crowdLevel(p.getCrowdLevel()    == null ? null : p.getCrowdLevel().name())
                .noiseLevel(p.getNoiseLevel()    == null ? null : p.getNoiseLevel().name())
                .venueContext(p.getVenueContext() == null ? null : p.getVenueContext().name())
                .bestVisitTime(bestVisitTime)
                .bestSeason(p.getBestSeason()    == null ? null : p.getBestSeason().name())
                .vibes(p.getVibes())
                .suitableFor(p.getSuitableFor())
                .visitPurpose(p.getVisitPurpose())
                .isFamilyFriendly(p.getIsFamilyFriendly())
                .visitDurationMinutes(p.getVisitDurationMinutes())
                .photographyScore(p.getPhotographyScore())
                .romanticScore(p.getRomanticScore())
                .localExperienceScore(p.getLocalExperienceScore())
                .build();
    }

    // =========================================================
    // UTILITY
    // =========================================================

    /** MongoDB stores location as [longitude, latitude]; GeoPoint constructor is (lat, lon). */
    private static GeoPoint toGeoPoint(double[] loc) {
        if (loc == null || loc.length < 2) return null;
        return new GeoPoint(loc[1], loc[0]);
    }
}
