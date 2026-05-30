package com.mravel.catalog.service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mravel.catalog.dto.hotel.HotelDtos.*;
import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.search.dto.HotelSearchResult;
import com.mravel.common.i18n.LocaleContext;
import com.mravel.common.i18n.LocaleUtil;

public class HotelMapper {

        private HotelMapper() {
        }

        // SUMMARY

        public static HotelSummaryDTO toSummary(HotelSearchResult h) {
                String cover = null;
                if (h.images() != null && !h.images().isEmpty()) {
                        cover = h.images().stream()
                                        .filter(Objects::nonNull)
                                        .sorted(Comparator
                                                        .comparing(HotelSearchResult.ImageRef::cover,
                                                                        Comparator.nullsLast(Boolean::compareTo))
                                                        .reversed()
                                                        .thenComparing(HotelSearchResult.ImageRef::sortOrder,
                                                                        Comparator.nullsLast(Integer::compareTo)))
                                        .map(HotelSearchResult.ImageRef::url)
                                        .findFirst()
                                        .orElse(null);
                }

                Double lat = null, lon = null;
                if (h.location() != null && h.location().length == 2) {
                        lon = h.location()[0];
                        lat = h.location()[1];
                }

                String mainFacilitiesSummary = null;
                Double distanceToCenter = null;
                if (h.generalInfo() != null) {
                        mainFacilitiesSummary = h.generalInfo().mainFacilitiesSummary();
                        distanceToCenter = h.generalInfo().distanceToCityCenterKm();
                }

                return new HotelSummaryDTO(
                                h.id(),
                                h.name(),
                                h.slug(),
                                h.active(),
                                h.starRating(),
                                h.hotelType(),
                                h.destinationSlug(),
                                h.cityName(),
                                h.districtName(),
                                h.wardName(),
                                h.addressLine(),
                                lat,
                                lon,
                                h.avgRating(),
                                h.reviewsCount(),
                                h.ratingLabel(),
                                h.minNightlyPrice(),
                                h.currencyCode(),
                                h.referenceNightlyPrice(),
                                h.discountPercent(),
                                mainFacilitiesSummary,
                                distanceToCenter,
                                cover,
                                List.of());
        }

        // DETAIL

        public static HotelDetailDTO toDetail(HotelDoc h,
                        Map<String, AmenityCatalogDoc> hotelCatalogMap,
                        Map<String, AmenityCatalogDoc> roomCatalogMap) {
                String locale = LocaleContext.get();

                Double lat = null, lon = null;
                if (h.getLocation() != null && h.getLocation().length == 2) {
                        lon = h.getLocation()[0];
                        lat = h.getLocation()[1];
                }

                List<ImageDTO> images = h.getImages() == null
                                ? List.of()
                                : h.getImages().stream()
                                                .filter(Objects::nonNull)
                                                .map(HotelMapper::toImage)
                                                .sorted(Comparator
                                                                .comparing(ImageDTO::cover).reversed()
                                                                .thenComparing(ImageDTO::sortOrder,
                                                                                Comparator.nullsLast(
                                                                                                Integer::compareTo)))
                                                .toList();

                List<ContentBlockDTO> content = h.getContent() == null
                                ? List.of()
                                : h.getContent().stream()
                                                .filter(Objects::nonNull)
                                                .map(HotelMapper::toContentBlock)
                                                .filter(Objects::nonNull)
                                                .toList();

                List<AmenityDTO> amenities = resolveAmenities(h.getAmenityCodes(), hotelCatalogMap);

                List<NearbyPlaceDTO> nearbyPlaces = h.getNearbyPlaces() == null
                                ? List.of()
                                : h.getNearbyPlaces().stream()
                                                .filter(Objects::nonNull)
                                                .map(HotelMapper::toNearbyPlace)
                                                .toList();

                HotelPolicyDTO policy = h.getPolicy() == null ? null : toPolicy(h.getPolicy());
                GeneralInfoDTO generalInfo = h.getGeneralInfo() == null ? null : toGeneralInfo(h.getGeneralInfo());

                List<FaqDTO> faqs = h.getFaqs() == null
                                ? List.of()
                                : h.getFaqs().stream()
                                                .filter(Objects::nonNull)
                                                .map(f -> new FaqDTO(
                                                                LocaleUtil.pick(f.getQuestion(), locale),
                                                                LocaleUtil.pick(f.getAnswer(), locale)))
                                                .toList();

                ReviewStatsDTO reviewStats = h.getReviewStats() == null ? null : toReviewStats(h.getReviewStats());

                List<RoomTypeDTO> roomTypes = h.getRoomTypes() == null
                                ? List.of()
                                : h.getRoomTypes().stream()
                                                .filter(Objects::nonNull)
                                                .map(rt -> toRoomType(rt, roomCatalogMap))
                                                .toList();

                PublisherDTO publisher = h.getPublisher() == null ? null : toPublisher(h.getPublisher());
                ModerationDTO moderation = h.getModeration() == null ? null : toModeration(h.getModeration());

                BookingConfigDTO bookingConfigDTO = null;
                if (h.getBookingConfig() != null) {
                        var bc = h.getBookingConfig();
                        bookingConfigDTO = new BookingConfigDTO(
                                        bc.getAllowFullPayment(),
                                        bc.getAllowDeposit(),
                                        bc.getDepositPercent(),
                                        bc.getFreeCancelMinutes());
                }

                return new HotelDetailDTO(
                                h.getId(),
                                LocaleUtil.pick(h.getName(), locale),
                                h.getSlug(),
                                h.getActive(),
                                h.getStarRating(),
                                h.getHotelType() == null ? null : h.getHotelType().name(),
                                LocaleUtil.pick(h.getShortDescription(), locale),
                                LocaleUtil.pick(h.getDescription(), locale),
                                h.getDestinationSlug(),
                                LocaleUtil.pick(h.getCityName(), locale),
                                LocaleUtil.pick(h.getDistrictName(), locale),
                                LocaleUtil.pick(h.getWardName(), locale),
                                LocaleUtil.pick(h.getAddressLine(), locale),
                                lat,
                                lon,
                                h.getPhone(),
                                h.getEmail(),
                                h.getWebsite(),
                                h.getDefaultCheckInTime(),
                                h.getDefaultCheckOutTime(),
                                h.getAvgRating(),
                                h.getReviewsCount(),
                                LocaleUtil.pick(h.getRatingLabel(), locale),
                                h.getMinNightlyPrice(),
                                h.getCurrencyCode(),
                                h.getReferenceNightlyPrice(),
                                h.getDiscountPercent(),
                                policy,
                                generalInfo,
                                amenities,
                                nearbyPlaces,
                                reviewStats,
                                faqs,
                                images,
                                content,
                                roomTypes,
                                publisher,
                                moderation,
                                bookingConfigDTO);
        }

        // HELPERS: RESOLVE AMENITIES

        private static List<AmenityDTO> resolveAmenities(List<String> codes,
                        Map<String, AmenityCatalogDoc> catalogMap) {
                if (codes == null || codes.isEmpty())
                        return List.of();
                if (catalogMap == null || catalogMap.isEmpty())
                        return List.of();

                return codes.stream()
                                .filter(Objects::nonNull)
                                .map(String::trim)
                                .filter(s -> !s.isBlank())
                                .map(s -> s.toUpperCase(Locale.ROOT))
                                .distinct()
                                .map(catalogMap::get)
                                .filter(Objects::nonNull)
                                .sorted(Comparator.comparingInt(a -> Optional.ofNullable(a.getSortOrder()).orElse(0)))
                                .map(HotelMapper::toAmenityFromCatalog)
                                .toList();
        }

        private static AmenityDTO toAmenityFromCatalog(AmenityCatalogDoc a) {
                String locale = LocaleContext.get();
                return new AmenityDTO(
                                a.getCode(),
                                LocaleUtil.pick(a.getName(), locale),
                                a.getGroup(),
                                a.getSection(),
                                a.getIcon());
        }

        // SUB MAPPERS

        public static ImageDTO toImage(HotelDoc.Image img) {
                String locale = LocaleContext.get();
                return new ImageDTO(
                                img.getUrl(),
                                LocaleUtil.pick(img.getCaption(), locale),
                                Boolean.TRUE.equals(img.getCover()),
                                img.getSortOrder());
        }

        public static ContentBlockDTO toContentBlock(HotelDoc.ContentBlock b) {
                if (b == null)
                        return null;
                String locale = LocaleContext.get();

                ImageDTO image = b.getImage() == null ? null : toImage(b.getImage());

                List<ImageDTO> gallery = b.getGallery() == null
                                ? List.of()
                                : b.getGallery().stream()
                                                .filter(Objects::nonNull)
                                                .map(HotelMapper::toImage)
                                                .toList();

                Double mapLat = null, mapLon = null;
                if (b.getMapLocation() != null && b.getMapLocation().length == 2) {
                        mapLon = b.getMapLocation()[0];
                        mapLat = b.getMapLocation()[1];
                }

                ContentBlockDTO.BlockType type = ContentBlockDTO.BlockType.valueOf(b.getType().name());

                return new ContentBlockDTO(
                                type,
                                LocaleUtil.pick(b.getText(), locale),
                                image,
                                gallery,
                                mapLat,
                                mapLon);
        }

        public static NearbyPlaceDTO toNearbyPlace(HotelDoc.NearbyPlace np) {
                String locale = LocaleContext.get();
                return new NearbyPlaceDTO(
                                np.getPlaceSlug(),
                                LocaleUtil.pick(np.getName(), locale),
                                LocaleUtil.pick(np.getCategory(), locale),
                                np.getDistanceMeters());
        }

        public static HotelPolicyDTO toPolicy(HotelDoc.HotelPolicy p) {
                String locale = LocaleContext.get();
                List<PolicyItemDTO> items = p.getItems() == null
                                ? List.of()
                                : p.getItems().stream()
                                                .filter(Objects::nonNull)
                                                .map(item -> new PolicyItemDTO(
                                                                item.getSection() == null ? null
                                                                                : item.getSection().name(),
                                                                LocaleUtil.pick(item.getTitle(), locale),
                                                                LocaleUtil.pick(item.getContent(), locale)))
                                                .toList();

                return new HotelPolicyDTO(
                                p.getCheckInFrom(),
                                p.getCheckOutUntil(),
                                items);
        }

        public static GeneralInfoDTO toGeneralInfo(HotelDoc.GeneralInfo g) {
                String locale = LocaleContext.get();
                return new GeneralInfoDTO(
                                LocaleUtil.pick(g.getMainFacilitiesSummary(), locale),
                                g.getDistanceToCityCenterKm(),
                                LocaleUtil.pick(g.getPopularAreaSummary(), locale),
                                g.getTotalRooms(),
                                g.getTotalFloors(),
                                LocaleUtil.pick(g.getOtherHighlightFacilities(), locale),
                                LocaleUtil.pick(g.getInterestingPlacesSummary(), locale));
        }

        public static ReviewStatsDTO toReviewStats(HotelDoc.ReviewStats rs) {
                String locale = LocaleContext.get();
                List<ReviewKeywordDTO> keywords = rs.getKeywords() == null
                                ? List.of()
                                : rs.getKeywords().stream()
                                                .filter(Objects::nonNull)
                                                .map(k -> new ReviewKeywordDTO(
                                                                k.getCode(),
                                                                LocaleUtil.pick(k.getLabel(), locale),
                                                                k.getCount()))
                                                .toList();

                return new ReviewStatsDTO(
                                rs.getCleanlinessScore(),
                                rs.getRoomAmenitiesScore(),
                                rs.getFoodScore(),
                                rs.getLocationScore(),
                                rs.getServiceScore(),
                                keywords);
        }

        public static Map<String, AmenityCatalogDoc> toCatalogMapByScope(List<AmenityCatalogDoc> items,
                        AmenityScope scope) {
                if (items == null)
                        return Map.of();
                return items.stream()
                                .filter(Objects::nonNull)
                                .filter(AmenityCatalogDoc::isActive)
                                .filter(a -> a.getCode() != null)
                                .filter(a -> a.getScope() == scope)
                                .collect(Collectors.toMap(
                                                a -> a.getCode().toUpperCase(Locale.ROOT),
                                                Function.identity(),
                                                (a, b) -> a,
                                                LinkedHashMap::new));
        }

        public static RoomTypeDTO toRoomType(HotelDoc.RoomType rt, Map<String, AmenityCatalogDoc> roomCatalogMap) {
                String locale = LocaleContext.get();

                List<ImageDTO> images = rt.getImages() == null
                                ? List.of()
                                : rt.getImages().stream()
                                                .filter(Objects::nonNull)
                                                .map(HotelMapper::toImage)
                                                .toList();

                List<AmenityDTO> amenities = resolveAmenities(rt.getAmenityCodes(), roomCatalogMap);

                List<RatePlanDTO> ratePlans = rt.getRatePlans() == null
                                ? List.of()
                                : rt.getRatePlans().stream()
                                                .filter(Objects::nonNull)
                                                .map(HotelMapper::toRatePlan)
                                                .toList();

                return new RoomTypeDTO(
                                rt.getId(),
                                LocaleUtil.pick(rt.getName(), locale),
                                LocaleUtil.pick(rt.getShortDescription(), locale),
                                LocaleUtil.pick(rt.getDescription(), locale),
                                rt.getAreaSqm(),
                                rt.getBedType() == null ? null : rt.getBedType().name(),
                                rt.getBedsCount(),
                                rt.getMaxAdults(),
                                rt.getMaxChildren(),
                                rt.getMaxGuests(),
                                rt.getTotalRooms(),
                                images,
                                amenities,
                                ratePlans);
        }

        public static RatePlanDTO toRatePlan(HotelDoc.RatePlan rp) {
                String locale = LocaleContext.get();
                return new RatePlanDTO(
                                rp.getId(),
                                LocaleUtil.pick(rp.getName(), locale),
                                rp.getBoardType() == null ? null : rp.getBoardType().name(),
                                rp.getPaymentType() == null ? null : rp.getPaymentType().name(),
                                rp.getRefundable(),
                                LocaleUtil.pick(rp.getCancellationPolicy(), locale),
                                rp.getPricePerNight(),
                                rp.getReferencePricePerNight(),
                                rp.getDiscountPercent(),
                                rp.getTaxPercent(),
                                rp.getServiceFeePercent(),
                                rp.getPriceIncludesTax(),
                                rp.getPriceIncludesServiceFee(),
                                LocaleUtil.pick(rp.getPromoLabel(), locale),
                                rp.getShowLowAvailability());
        }

        public static PublisherDTO toPublisher(HotelDoc.PublisherInfo p) {
                String createdAt = p.getCreatedAt() == null ? null : p.getCreatedAt().toString();
                String lastUpdatedAt = p.getLastUpdatedAt() == null ? null : p.getLastUpdatedAt().toString();

                return new PublisherDTO(
                                p.getPartnerId(),
                                p.getPartnerName(),
                                p.getPartnerEmail(),
                                p.getPartnerType() == null ? null : p.getPartnerType().name(),
                                createdAt,
                                lastUpdatedAt);
        }

        public static ModerationDTO toModeration(HotelDoc.ModerationInfo m) {
                String lastActionAt = m.getLastActionAt() == null ? null : m.getLastActionAt().toString();

                return new ModerationDTO(
                                m.getStatus() == null ? null : m.getStatus().name(),
                                m.getRejectionReason(),
                                m.getBlockedReason(),
                                m.getReportCount(),
                                m.getLastActionByAdminId(),
                                lastActionAt);
        }

        public static Map<String, AmenityCatalogDoc> toCatalogMap(List<AmenityCatalogDoc> items) {
                if (items == null)
                        return Map.of();
                return items.stream()
                                .filter(Objects::nonNull)
                                .filter(AmenityCatalogDoc::isActive)
                                .filter(a -> a.getCode() != null)
                                .collect(Collectors.toMap(
                                                a -> a.getCode().toUpperCase(Locale.ROOT),
                                                Function.identity(),
                                                (a, b) -> a,
                                                LinkedHashMap::new));
        }
}
