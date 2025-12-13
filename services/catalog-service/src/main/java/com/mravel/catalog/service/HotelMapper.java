// src/main/java/com/mravel/catalog/service/HotelMapper.java
package com.mravel.catalog.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.mravel.catalog.dto.hotel.HotelDtos.*;
import com.mravel.catalog.model.doc.HotelDoc;

public class HotelMapper {

    // ================== SUMMARY ==================

    public static HotelSummaryDTO toSummary(HotelDoc h) {
        String cover = null;
        if (h.getImages() != null && !h.getImages().isEmpty()) {
            cover = h.getImages().stream()
                    .sorted(Comparator
                            .comparing(HotelDoc.Image::getCover, Comparator.nullsLast(Boolean::compareTo)).reversed()
                            .thenComparing(HotelDoc.Image::getSortOrder, Comparator.nullsLast(Integer::compareTo)))
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

        // Highlight tags tạm thời để rỗng, sau này backend có thể tính thêm
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
                highlightTags
        );
    }

    // ================== DETAIL ==================

    public static HotelDetailDTO toDetail(HotelDoc h) {
        Double lat = null, lon = null;
        if (h.getLocation() != null && h.getLocation().length == 2) {
            lon = h.getLocation()[0];
            lat = h.getLocation()[1];
        }

        // images
        List<ImageDTO> images = h.getImages() == null
                ? List.of()
                : h.getImages().stream()
                    .filter(Objects::nonNull)
                    .map(HotelMapper::toImage)
                    .sorted(Comparator
                            .comparing(ImageDTO::cover).reversed()
                            .thenComparing(ImageDTO::sortOrder, Comparator.nullsLast(Integer::compareTo)))
                    .toList();

        // content
        List<ContentBlockDTO> content = h.getContent() == null
                ? List.of()
                : h.getContent().stream()
                    .filter(Objects::nonNull)
                    .map(HotelMapper::toContentBlock)
                    .filter(Objects::nonNull)
                    .toList();

        // amenities
        List<AmenityDTO> amenities = h.getAmenities() == null
                ? List.of()
                : h.getAmenities().stream()
                    .filter(Objects::nonNull)
                    .map(HotelMapper::toAmenity)
                    .toList();

        // nearby places
        List<NearbyPlaceDTO> nearbyPlaces = h.getNearbyPlaces() == null
                ? List.of()
                : h.getNearbyPlaces().stream()
                    .filter(Objects::nonNull)
                    .map(HotelMapper::toNearbyPlace)
                    .toList();

        // policy & generalInfo
        HotelPolicyDTO policy = h.getPolicy() == null ? null : toPolicy(h.getPolicy());
        GeneralInfoDTO generalInfo = h.getGeneralInfo() == null ? null : toGeneralInfo(h.getGeneralInfo());

        // FAQs
        List<FaqDTO> faqs = h.getFaqs() == null
                ? List.of()
                : h.getFaqs().stream()
                    .filter(Objects::nonNull)
                    .map(f -> new FaqDTO(f.getQuestion(), f.getAnswer()))
                    .toList();

        // Review stats
        ReviewStatsDTO reviewStats = h.getReviewStats() == null ? null : toReviewStats(h.getReviewStats());

        // Room types
        List<RoomTypeDTO> roomTypes = h.getRoomTypes() == null
                ? List.of()
                : h.getRoomTypes().stream()
                    .filter(Objects::nonNull)
                    .map(HotelMapper::toRoomType)
                    .toList();

        // Publisher & moderation
        PublisherDTO publisher = h.getPublisher() == null ? null : toPublisher(h.getPublisher());
        ModerationDTO moderation = h.getModeration() == null ? null : toModeration(h.getModeration());

        BookingConfigDTO bookingConfigDTO = null;
        if (h.getBookingConfig() != null) {
            var bc = h.getBookingConfig();
            bookingConfigDTO = new BookingConfigDTO(
                    bc.getAllowFullPayment(),
                    bc.getAllowDeposit(),
                    bc.getDepositPercent(),
                    bc.getFreeCancelMinutes()
            );
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
                bookingConfigDTO
        );
    }

    // ================== SUB MAPPERS ==================

    public static ImageDTO toImage(HotelDoc.Image img) {
        return new ImageDTO(
                img.getUrl(),
                img.getCaption(),
                Boolean.TRUE.equals(img.getCover()),
                img.getSortOrder()
        );
    }

    public static ContentBlockDTO toContentBlock(HotelDoc.ContentBlock b) {
        if (b == null) return null;

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
                mapLon
        );
    }

    public static AmenityDTO toAmenity(HotelDoc.Amenity a) {
        return new AmenityDTO(
                a.getCode(),
                a.getName(),
                a.getGroup() == null ? null : a.getGroup().name()
        );
    }

    public static NearbyPlaceDTO toNearbyPlace(HotelDoc.NearbyPlace np) {
        return new NearbyPlaceDTO(
                np.getPlaceSlug(),
                np.getName(),
                np.getCategory(),
                np.getDistanceMeters()
        );
    }

    public static HotelPolicyDTO toPolicy(HotelDoc.HotelPolicy p) {
        List<PolicyItemDTO> items = p.getItems() == null
                ? List.of()
                : p.getItems().stream()
                    .filter(Objects::nonNull)
                    .map(item -> new PolicyItemDTO(
                            item.getSection() == null ? null : item.getSection().name(),
                            item.getTitle(),
                            item.getContent()
                    ))
                    .toList();

        return new HotelPolicyDTO(
                p.getCheckInFrom(),
                p.getCheckOutUntil(),
                items
        );
    }

    public static GeneralInfoDTO toGeneralInfo(HotelDoc.GeneralInfo g) {
        return new GeneralInfoDTO(
                g.getMainFacilitiesSummary(),
                g.getDistanceToCityCenterKm(),
                g.getPopularAreaSummary(),
                g.getTotalRooms(),
                g.getTotalFloors(),
                g.getOtherHighlightFacilities(),
                g.getInterestingPlacesSummary()
        );
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
                keywords
        );
    }

    public static RoomTypeDTO toRoomType(HotelDoc.RoomType rt) {
        List<ImageDTO> images = rt.getImages() == null
                ? List.of()
                : rt.getImages().stream()
                    .filter(Objects::nonNull)
                    .map(HotelMapper::toImage)
                    .toList();

        List<AmenityDTO> amenities = rt.getAmenities() == null
                ? List.of()
                : rt.getAmenities().stream()
                    .filter(Objects::nonNull)
                    .map(HotelMapper::toAmenity)
                    .toList();

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
                ratePlans
        );
    }

    public static RatePlanDTO toRatePlan(HotelDoc.RatePlan rp) {
        return new RatePlanDTO(
                rp.getId(),
                rp.getName(),
                rp.getBoardType() == null ? null : rp.getBoardType().name(),
                rp.getPaymentType() == null ? null : rp.getPaymentType().name(),
                rp.getRefundable(),
                rp.getCancellationPolicy(),

                // Giá
                rp.getPricePerNight(),
                rp.getReferencePricePerNight(),
                rp.getDiscountPercent(),

                // Thuế / phí
                rp.getTaxPercent(),
                rp.getServiceFeePercent(),
                rp.getPriceIncludesTax(),
                rp.getPriceIncludesServiceFee(),

                // Promo
                rp.getPromoLabel(),
                rp.getShowLowAvailability()
        );
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
                lastUpdatedAt
        );
    }

    public static ModerationDTO toModeration(HotelDoc.ModerationInfo m) {
        String lastActionAt = m.getLastActionAt() == null ? null : m.getLastActionAt().toString();

        return new ModerationDTO(
                m.getStatus() == null ? null : m.getStatus().name(),
                m.getRejectionReason(),
                m.getBlockedReason(),
                m.getReportCount(),
                m.getLastActionByAdminId(),
                lastActionAt
        );
    }
}