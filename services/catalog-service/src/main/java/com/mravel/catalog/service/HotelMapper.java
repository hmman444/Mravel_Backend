package com.mravel.catalog.service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mravel.catalog.dto.hotel.HotelDtos.*;
import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.enums.AmenityScope;

public class HotelMapper {

        private HotelMapper() {
        }

        // ================== SUMMARY ==================

        public static HotelSummaryDTO toSummary(HotelDoc h) {
                String cover = null;
                if (h.getImages() != null && !h.getImages().isEmpty()) {
                        cover = h.getImages().stream()
                                        .filter(Objects::nonNull)
                                        .sorted(Comparator
                                                        .comparing(HotelDoc.Image::getCover,
                                                                        Comparator.nullsLast(Boolean::compareTo))
                                                        .reversed()
                                                        .thenComparing(HotelDoc.Image::getSortOrder,
                                                                        Comparator.nullsLast(Integer::compareTo)))
                                        .map(HotelDoc.Image::getUrl)
                                        .findFirst()
                                        .orElse(null);
                }

                Double lat = null, lon = null;
                if (h.getLocation() != null && h.getLocation().length == 2) {
                        lon = h.getLocation()[0];
                        lat = h.getLocation()[1];
                }

                String mainFacilitiesSummary = null;
                Double distanceToCenter = null;
                if (h.getGeneralInfo() != null) {
                        mainFacilitiesSummary = h.getGeneralInfo().getMainFacilitiesSummary();
                        distanceToCenter = h.getGeneralInfo().getDistanceToCityCenterKm();
                }

                List<String> highlightTags = List.of();

                return new HotelSummaryDTO(
                                h.getId(),
                                h.getName(),
                                h.getSlug(),
                                h.getStarRating(),
                                h.getHotelType() == null ? null : h.getHotelType().name(),
                                h.getDestinationSlug(),
                                h.getCityName(),
                                h.getDistrictName(),
                                h.getWardName(),
                                h.getAddressLine(),
                                lat,
                                lon,
                                h.getAvgRating(),
                                h.getReviewsCount(),
                                h.getRatingLabel(),
                                h.getMinNightlyPrice(),
                                h.getCurrencyCode(),
                                h.getReferenceNightlyPrice(),
                                h.getDiscountPercent(),
                                mainFacilitiesSummary,
                                distanceToCenter,
                                cover,
                                highlightTags);
        }

        // ================== DETAIL ==================
        /**
         * Resolve amenityCodes -> AmenityDTO bằng catalogMap (đã query từ DB).
         *
         * - catalogMap: key = amenityCode (UPPERCASE), value = AmenityCatalogDoc
         * - Hotel-level: lấy từ h.getAmenityCodes()
         * - Room-level: lấy từ rt.getAmenityCodes()
         */
        public static HotelDetailDTO toDetail(HotelDoc h,
                        Map<String, AmenityCatalogDoc> hotelCatalogMap,
                        Map<String, AmenityCatalogDoc> roomCatalogMap) {
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

                // amenities lấy từ amenityCodes + catalogMap
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
                                                .map(f -> new FaqDTO(f.getQuestion(), f.getAnswer()))
                                                .toList();

                ReviewStatsDTO reviewStats = h.getReviewStats() == null ? null : toReviewStats(h.getReviewStats());

                // roomType mapper cần catalogMap để resolve amenityCodes
                List<RoomTypeDTO> roomTypes = h.getRoomTypes() == null
                                ? List.of()
                                : h.getRoomTypes().stream()
                                                .filter(Objects::nonNull)
                                                .map(rt -> toRoomType(rt, roomCatalogMap)) // <-- ROOM map
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
                                h.getName(),
                                h.getSlug(),
                                h.getStarRating(),
                                h.getHotelType() == null ? null : h.getHotelType().name(),
                                h.getShortDescription(),
                                h.getDescription(),
                                h.getDestinationSlug(),
                                h.getCityName(),
                                h.getDistrictName(),
                                h.getWardName(),
                                h.getAddressLine(),
                                lat,
                                lon,
                                h.getPhone(),
                                h.getEmail(),
                                h.getWebsite(),
                                h.getDefaultCheckInTime(),
                                h.getDefaultCheckOutTime(),
                                h.getAvgRating(),
                                h.getReviewsCount(),
                                h.getRatingLabel(),
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

        // ================== HELPERS: RESOLVE AMENITIES ==================

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
                return new AmenityDTO(
                                a.getCode(),
                                a.getName(),
                                a.getGroup(),
                                a.getSection(),
                                a.getIcon());
        }

        // ================== SUB MAPPERS ==================

        public static ImageDTO toImage(HotelDoc.Image img) {
                return new ImageDTO(
                                img.getUrl(),
                                img.getCaption(),
                                Boolean.TRUE.equals(img.getCover()),
                                img.getSortOrder());
        }

        public static ContentBlockDTO toContentBlock(HotelDoc.ContentBlock b) {
                if (b == null)
                        return null;

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
                                b.getText(),
                                image,
                                gallery,
                                mapLat,
                                mapLon);
        }

        public static NearbyPlaceDTO toNearbyPlace(HotelDoc.NearbyPlace np) {
                return new NearbyPlaceDTO(
                                np.getPlaceSlug(),
                                np.getName(),
                                np.getCategory(),
                                np.getDistanceMeters());
        }

        public static HotelPolicyDTO toPolicy(HotelDoc.HotelPolicy p) {
                List<PolicyItemDTO> items = p.getItems() == null
                                ? List.of()
                                : p.getItems().stream()
                                                .filter(Objects::nonNull)
                                                .map(item -> new PolicyItemDTO(
                                                                item.getSection() == null ? null
                                                                                : item.getSection().name(),
                                                                item.getTitle(),
                                                                item.getContent()))
                                                .toList();

                return new HotelPolicyDTO(
                                p.getCheckInFrom(),
                                p.getCheckOutUntil(),
                                items);
        }

        public static GeneralInfoDTO toGeneralInfo(HotelDoc.GeneralInfo g) {
                return new GeneralInfoDTO(
                                g.getMainFacilitiesSummary(),
                                g.getDistanceToCityCenterKm(),
                                g.getPopularAreaSummary(),
                                g.getTotalRooms(),
                                g.getTotalFloors(),
                                g.getOtherHighlightFacilities(),
                                g.getInterestingPlacesSummary());
        }

        public static ReviewStatsDTO toReviewStats(HotelDoc.ReviewStats rs) {
                List<ReviewKeywordDTO> keywords = rs.getKeywords() == null
                                ? List.of()
                                : rs.getKeywords().stream()
                                                .filter(Objects::nonNull)
                                                .map(k -> new ReviewKeywordDTO(k.getCode(), k.getLabel(), k.getCount()))
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
                List<ImageDTO> images = rt.getImages() == null
                                ? List.of()
                                : rt.getImages().stream()
                                                .filter(Objects::nonNull)
                                                .map(HotelMapper::toImage)
                                                .toList();

                // RoomType cũng resolve từ amenityCodes
                List<AmenityDTO> amenities = resolveAmenities(rt.getAmenityCodes(), roomCatalogMap);

                List<RatePlanDTO> ratePlans = rt.getRatePlans() == null
                                ? List.of()
                                : rt.getRatePlans().stream()
                                                .filter(Objects::nonNull)
                                                .map(HotelMapper::toRatePlan)
                                                .toList();

                return new RoomTypeDTO(
                                rt.getId(),
                                rt.getName(),
                                rt.getShortDescription(),
                                rt.getDescription(),
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
                return new RatePlanDTO(
                                rp.getId(),
                                rp.getName(),
                                rp.getBoardType() == null ? null : rp.getBoardType().name(),
                                rp.getPaymentType() == null ? null : rp.getPaymentType().name(),
                                rp.getRefundable(),
                                rp.getCancellationPolicy(),
                                rp.getPricePerNight(),
                                rp.getReferencePricePerNight(),
                                rp.getDiscountPercent(),
                                rp.getTaxPercent(),
                                rp.getServiceFeePercent(),
                                rp.getPriceIncludesTax(),
                                rp.getPriceIncludesServiceFee(),
                                rp.getPromoLabel(),
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

        // ================== OPTIONAL: build catalogMap helper ==================
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
                                                (a, b) -> a, // nếu trùng code (không nên), lấy cái đầu
                                                LinkedHashMap::new));
        }
}
