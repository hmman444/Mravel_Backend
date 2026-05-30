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
import com.mravel.common.i18n.LocaleConstants;

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
                        .map(img -> new HotelIndex.ImageData(
                                img.getUrl(),
                                pick(img.getCaption(), LocaleConstants.VI),
                                pick(img.getCaption(), LocaleConstants.EN),
                                img.getCover(),
                                img.getSortOrder()))
                        .toList();

        HotelIndex.GeneralInfoData generalInfo = null;
        if (h.getGeneralInfo() != null) {
            generalInfo = new HotelIndex.GeneralInfoData(
                    pick(h.getGeneralInfo().getMainFacilitiesSummary(), LocaleConstants.VI),
                    pick(h.getGeneralInfo().getMainFacilitiesSummary(), LocaleConstants.EN),
                    h.getGeneralInfo().getDistanceToCityCenterKm());
        }

        return HotelIndex.builder()
                .id(h.getId())
                .nameVi(pick(h.getName(), LocaleConstants.VI))
                .nameEn(pick(h.getName(), LocaleConstants.EN))
                .slug(h.getSlug())
                .active(h.getActive())
                .moderationStatus(moderationStatus)
                .starRating(h.getStarRating())
                .hotelType(h.getHotelType() == null ? null : h.getHotelType().name())
                .destinationSlug(h.getDestinationSlug())
                .cityNameVi(pick(h.getCityName(), LocaleConstants.VI))
                .cityNameEn(pick(h.getCityName(), LocaleConstants.EN))
                .districtNameVi(pick(h.getDistrictName(), LocaleConstants.VI))
                .districtNameEn(pick(h.getDistrictName(), LocaleConstants.EN))
                .wardNameVi(pick(h.getWardName(), LocaleConstants.VI))
                .wardNameEn(pick(h.getWardName(), LocaleConstants.EN))
                .addressLineVi(pick(h.getAddressLine(), LocaleConstants.VI))
                .addressLineEn(pick(h.getAddressLine(), LocaleConstants.EN))
                .location(toGeoPoint(h.getLocation()))
                .avgRating(h.getAvgRating())
                .reviewsCount(h.getReviewsCount())
                .ratingLabelVi(pick(h.getRatingLabel(), LocaleConstants.VI))
                .ratingLabelEn(pick(h.getRatingLabel(), LocaleConstants.EN))
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
                        .map(c -> new RestaurantIndex.CuisineData(
                                c.getCode(),
                                pick(c.getName(), LocaleConstants.VI),
                                pick(c.getName(), LocaleConstants.EN)))
                        .toList();

        List<RestaurantIndex.AmbienceData> ambience = r.getAmbience() == null ? List.of()
                : r.getAmbience().stream()
                        .filter(a -> a != null)
                        .map(a -> new RestaurantIndex.AmbienceData(
                                a.getCode(),
                                pick(a.getLabel(), LocaleConstants.VI),
                                pick(a.getLabel(), LocaleConstants.EN)))
                        .toList();

        Integer totalCapacity = null, maxGroupSize = null;
        if (r.getCapacity() != null) {
            totalCapacity = r.getCapacity().getTotalCapacity();
            maxGroupSize  = r.getCapacity().getMaxGroupSize();
        }

        List<RestaurantIndex.ImageData> images = r.getImages() == null ? List.of()
                : r.getImages().stream()
                        .filter(img -> img != null)
                        .map(img -> new RestaurantIndex.ImageData(
                                img.getUrl(),
                                pick(img.getCaption(), LocaleConstants.VI),
                                pick(img.getCaption(), LocaleConstants.EN),
                                img.getCover(),
                                img.getSortOrder()))
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
                .nameVi(pick(r.getName(), LocaleConstants.VI))
                .nameEn(pick(r.getName(), LocaleConstants.EN))
                .slug(r.getSlug())
                .active(r.getActive())
                .moderationStatus(moderationStatus)
                .restaurantType(r.getRestaurantType() == null ? null : r.getRestaurantType().name())
                .destinationSlug(r.getDestinationSlug())
                .parentPlaceSlug(r.getParentPlaceSlug())
                .cityNameVi(pick(r.getCityName(), LocaleConstants.VI))
                .cityNameEn(pick(r.getCityName(), LocaleConstants.EN))
                .districtNameVi(pick(r.getDistrictName(), LocaleConstants.VI))
                .districtNameEn(pick(r.getDistrictName(), LocaleConstants.EN))
                .wardNameVi(pick(r.getWardName(), LocaleConstants.VI))
                .wardNameEn(pick(r.getWardName(), LocaleConstants.EN))
                .addressLineVi(pick(r.getAddressLine(), LocaleConstants.VI))
                .addressLineEn(pick(r.getAddressLine(), LocaleConstants.EN))
                .location(toGeoPoint(r.getLocation()))
                .cuisineCodes(cuisineCodes)
                .cuisines(cuisines)
                .ambience(ambience)
                .totalCapacity(totalCapacity)
                .maxGroupSize(maxGroupSize)
                .avgRating(r.getAvgRating())
                .reviewsCount(r.getReviewsCount())
                .ratingLabelVi(pick(r.getRatingLabel(), LocaleConstants.VI))
                .ratingLabelEn(pick(r.getRatingLabel(), LocaleConstants.EN))
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
                        .map(img -> new PlaceIndex.ImageData(
                                img.getUrl(),
                                pick(img.getCaption(), LocaleConstants.VI),
                                pick(img.getCaption(), LocaleConstants.EN),
                                img.getCover(),
                                img.getSortOrder()))
                        .toList();

        List<String> categorySlugs = p.getCategories() == null ? List.of()
                : p.getCategories().stream()
                        .filter(c -> c != null && c.getSlug() != null)
                        .map(PlaceDoc.CategoryMini::getSlug)
                        .toList();

        // Encode as "slug::nameVi::nameEn" — pick per locale at extract time
        List<String> categoryLabels = p.getCategories() == null ? List.of()
                : p.getCategories().stream()
                        .filter(c -> c != null && c.getSlug() != null)
                        .map(c -> {
                            String vi = pick(c.getName(), LocaleConstants.VI);
                            String en = pick(c.getName(), LocaleConstants.EN);
                            return c.getSlug() + "::" + nullToEmpty(vi) + "::" + nullToEmpty(en);
                        })
                        .toList();

        List<String> bestVisitTime = p.getBestVisitTime() == null ? List.of()
                : p.getBestVisitTime().stream()
                        .filter(t -> t != null)
                        .map(PlaceDoc.BestVisitTime::name)
                        .toList();

        return PlaceIndex.builder()
                .id(p.getId())
                .nameVi(pick(p.getName(), LocaleConstants.VI))
                .nameEn(pick(p.getName(), LocaleConstants.EN))
                .slug(p.getSlug())
                .active(p.getActive())
                .kind(p.getKind()         == null ? null : p.getKind().name())
                .venueType(p.getVenueType()    == null ? null : p.getVenueType().name())
                .parentSlug(p.getParentSlug())
                .addressLineVi(pick(p.getAddressLine(), LocaleConstants.VI))
                .addressLineEn(pick(p.getAddressLine(), LocaleConstants.EN))
                .provinceNameVi(pick(p.getProvinceName(), LocaleConstants.VI))
                .provinceNameEn(pick(p.getProvinceName(), LocaleConstants.EN))
                .districtNameVi(pick(p.getDistrictName(), LocaleConstants.VI))
                .districtNameEn(pick(p.getDistrictName(), LocaleConstants.EN))
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

    /** Direct read of a single locale value from localized Map (no cross-locale fallback for indexing). */
    private static String pick(Map<String, String> localized, String locale) {
        if (localized == null) return null;
        String v = localized.get(locale);
        return (v == null || v.isBlank()) ? null : v;
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }
}
