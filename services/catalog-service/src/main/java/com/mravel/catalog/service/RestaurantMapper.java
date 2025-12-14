package com.mravel.catalog.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.mravel.catalog.dto.restaurant.RestaurantDtos.*;
import com.mravel.catalog.model.doc.RestaurantDoc;

public class RestaurantMapper {

    // ================== SUMMARY ==================

    public static RestaurantSummaryDTO toSummary(RestaurantDoc r) {
        // Cover image
        String cover = null;
        if (r.getImages() != null && !r.getImages().isEmpty()) {
            cover = r.getImages().stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator
                            .comparing(RestaurantDoc.Image::getCover, Comparator.nullsLast(Boolean::compareTo)).reversed()
                            .thenComparing(RestaurantDoc.Image::getSortOrder, Comparator.nullsLast(Integer::compareTo)))
                    .map(RestaurantDoc.Image::getUrl)
                    .findFirst()
                    .orElse(null);
        }

        // Location
        Double lat = null, lon = null;
        if (r.getLocation() != null && r.getLocation().length == 2) {
            lon = r.getLocation()[0];
            lat = r.getLocation()[1];
        }

        // Cuisines
        List<String> cuisineNames = r.getCuisines() == null
                ? List.of()
                : r.getCuisines().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantDoc.CuisineTag::getName)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();

        // Ambience
        List<String> ambienceLabels = r.getAmbience() == null
                ? List.of()
                : r.getAmbience().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantDoc.AmbienceTag::getLabel)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();

        // Capacity
        Integer totalCapacity = r.getCapacity() == null ? null : r.getCapacity().getTotalCapacity();

        // Highlight tags – tạm thời để rỗng, sau này tính thêm
        List<String> highlightTags = List.of();

        return new RestaurantSummaryDTO(
                r.getId(),
                r.getName(),
                r.getSlug(),
                r.getRestaurantType() == null ? null : r.getRestaurantType().name(),

                r.getDestinationSlug(),
                r.getCityName(),
                r.getDistrictName(),
                r.getWardName(),
                r.getAddressLine(),
                lat,
                lon,

                cuisineNames,
                ambienceLabels,
                totalCapacity,

                r.getAvgRating(),
                r.getReviewsCount(),
                r.getRatingLabel(),

                r.getMinPricePerPerson(),
                r.getMaxPricePerPerson(),
                r.getCurrencyCode(),
                r.getPriceLevel() == null ? null : r.getPriceLevel().name(),
                r.getPriceBucket() == null ? null : r.getPriceBucket().name(),

                cover,
                highlightTags
        );
    }

    // ================== DETAIL ==================

    public static RestaurantDetailDTO toDetail(RestaurantDoc r) {
        // Location
        Double lat = null, lon = null;
        if (r.getLocation() != null && r.getLocation().length == 2) {
            lon = r.getLocation()[0];
            lat = r.getLocation()[1];
        }

        // Cuisines
        List<CuisineTagDTO> cuisines = r.getCuisines() == null
                ? List.of()
                : r.getCuisines().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toCuisineTag)
                    .toList();

        // Opening hours
        List<OpeningHourDTO> openingHours = r.getOpeningHours() == null
                ? List.of()
                : r.getOpeningHours().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toOpeningHour)
                    .toList();

        // SuitableFor
        List<SuitableForDTO> suitableFor = r.getSuitableFor() == null
                ? List.of()
                : r.getSuitableFor().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toSuitableFor)
                    .toList();

        // Ambience
        List<AmbienceTagDTO> ambience = r.getAmbience() == null
                ? List.of()
                : r.getAmbience().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toAmbience)
                    .toList();

        // Capacity
        CapacityInfoDTO capacity = r.getCapacity() == null ? null : toCapacity(r.getCapacity());

        // Signature dishes
        List<SignatureDishDTO> signatureDishes = r.getSignatureDishes() == null
                ? List.of()
                : r.getSignatureDishes().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toSignatureDish)
                    .toList();

        // Images (gallery)
        List<ImageDTO> images = r.getImages() == null
                ? List.of()
                : r.getImages().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toImage)
                    .sorted(Comparator
                            .comparing(ImageDTO::cover).reversed()
                            .thenComparing(ImageDTO::sortOrder, Comparator.nullsLast(Integer::compareTo)))
                    .toList();

        // Menu images
        List<ImageDTO> menuImages = r.getMenuImages() == null
                ? List.of()
                : r.getMenuImages().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toImage)
                    .sorted(Comparator
                            .comparing(ImageDTO::cover).reversed()
                            .thenComparing(ImageDTO::sortOrder, Comparator.nullsLast(Integer::compareTo)))
                    .toList();

        // Content blocks
        List<ContentBlockDTO> content = r.getContent() == null
                ? List.of()
                : r.getContent().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toContentBlock)
                    .filter(Objects::nonNull)
                    .toList();

        // Menu sections
        List<MenuSectionDTO> menuSections = r.getMenuSections() == null
                ? List.of()
                : r.getMenuSections().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toMenuSection)
                    .toList();

        // Parking
        ParkingInfoDTO parking = r.getParking() == null ? null : toParking(r.getParking());

        // Policy
        RestaurantPolicyDTO policy = r.getPolicy() == null ? null : toPolicy(r.getPolicy());

        // Review stats
        ReviewStatsDTO reviewStats = r.getReviewStats() == null ? null : toReviewStats(r.getReviewStats());

        // Publisher & moderation
        PublisherDTO publisher = r.getPublisher() == null ? null : toPublisher(r.getPublisher());
        ModerationDTO moderation = r.getModeration() == null ? null : toModeration(r.getModeration());

        List<TableTypeDTO> tableTypes = r.getTableTypes() == null ? List.of()
        : r.getTableTypes().stream().filter(Objects::nonNull).map(RestaurantMapper::toTableType).toList();

        RestaurantBookingConfigDTO bookingConfig = r.getBookingConfig() == null ? null : toBookingConfig(r.getBookingConfig());

        return new RestaurantDetailDTO(
                r.getId(),
                r.getName(),
                r.getSlug(),
                r.getRestaurantType() == null ? null : r.getRestaurantType().name(),

                cuisines,

                r.getShortDescription(),
                r.getDescription(),

                r.getDestinationSlug(),
                r.getParentPlaceSlug(),
                r.getCityName(),
                r.getDistrictName(),
                r.getWardName(),
                r.getAddressLine(),
                lat,
                lon,

                r.getPhone(),
                r.getEmail(),
                r.getWebsite(),
                r.getFacebookPage(),
                r.getBookingHotline(),

                r.getMinPricePerPerson(),
                r.getMaxPricePerPerson(),
                r.getCurrencyCode(),
                r.getPriceLevel() == null ? null : r.getPriceLevel().name(),
                r.getPriceBucket() == null ? null : r.getPriceBucket().name(),

                openingHours,

                suitableFor,
                ambience,
                capacity,

                signatureDishes,
                images,
                menuImages,
                content,
                menuSections,

                parking,

                r.getAmenityCodes() == null ? List.of() : r.getAmenityCodes(),

                policy,

                r.getAvgRating(),
                r.getReviewsCount(),
                r.getRatingLabel(),
                reviewStats,

                publisher,
                moderation,

                tableTypes,
                bookingConfig
        );
    }

    // ================== SUB MAPPERS ==================

    // Image
    public static ImageDTO toImage(RestaurantDoc.Image img) {
        return new ImageDTO(
                img.getUrl(),
                img.getCaption(),
                Boolean.TRUE.equals(img.getCover()),
                img.getSortOrder()
        );
    }

    // CuisineTag
    public static CuisineTagDTO toCuisineTag(RestaurantDoc.CuisineTag c) {
        return new CuisineTagDTO(
                c.getCode(),
                c.getName(),
                c.getRegion()
        );
    }

    // OpeningHour
    public static OpeningHourDTO toOpeningHour(RestaurantDoc.OpeningHour oh) {
        return new OpeningHourDTO(
                oh.getDayOfWeek(),
                oh.getOpenTime(),
                oh.getCloseTime(),
                Boolean.TRUE.equals(oh.getOpen24h()),
                Boolean.TRUE.equals(oh.getClosed())
        );
    }

    // SuitableFor
    public static SuitableForDTO toSuitableFor(RestaurantDoc.SuitableFor sf) {
        return new SuitableForDTO(
                sf.getCode(),
                sf.getLabel()
        );
    }

    // Ambience
    public static AmbienceTagDTO toAmbience(RestaurantDoc.AmbienceTag a) {
        return new AmbienceTagDTO(
                a.getCode(),
                a.getLabel()
        );
    }

    // Capacity
    public static CapacityInfoDTO toCapacity(RestaurantDoc.CapacityInfo c) {
        return new CapacityInfoDTO(
                c.getTotalCapacity(),
                c.getMaxGroupSize(),
                Boolean.TRUE.equals(c.getHasPrivateRooms()),
                c.getPrivateRoomCount(),
                c.getMaxPrivateRoomCapacity(),
                Boolean.TRUE.equals(c.getHasOutdoorSeating())
        );
    }

    // SignatureDish
    public static SignatureDishDTO toSignatureDish(RestaurantDoc.SignatureDish d) {
        return new SignatureDishDTO(
                d.getName(),
                d.getDescription(),
                d.getEstimatedPrice(),
                Boolean.TRUE.equals(d.getHighlight())
        );
    }

    // ContentBlock
    public static ContentBlockDTO toContentBlock(RestaurantDoc.ContentBlock b) {
        if (b == null) return null;

        ImageDTO image = b.getImage() == null ? null : toImage(b.getImage());

        List<ImageDTO> gallery = b.getGallery() == null
                ? List.of()
                : b.getGallery().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toImage)
                    .toList();

        Double mapLat = null, mapLon = null;
        if (b.getMapLocation() != null && b.getMapLocation().length == 2) {
            mapLon = b.getMapLocation()[0];
            mapLat = b.getMapLocation()[1];
        }

        ContentBlockDTO.BlockType type = ContentBlockDTO.BlockType.valueOf(b.getType().name());
        String section = b.getSection() == null ? null : b.getSection().name();

        return new ContentBlockDTO(
                type,
                section,
                b.getText(),
                image,
                gallery,
                mapLat,
                mapLon
        );
    }

    // MenuSection
    public static MenuSectionDTO toMenuSection(RestaurantDoc.MenuSection ms) {
        List<MenuItemDTO> items = ms.getItems() == null
                ? List.of()
                : ms.getItems().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toMenuItem)
                    .toList();

        return new MenuSectionDTO(
                ms.getCode(),
                ms.getName(),
                items
        );
    }

    // MenuItem
    public static MenuItemDTO toMenuItem(RestaurantDoc.MenuItem item) {
        return new MenuItemDTO(
                item.getName(),
                item.getDescription(),
                item.getPriceFrom(),
                item.getPriceTo(),
                item.getUnit(),
                Boolean.TRUE.equals(item.getCombo()),
                Boolean.TRUE.equals(item.getBuffetItem()),
                item.getTags() == null ? List.of() : item.getTags()
        );
    }

    // Parking
    public static ParkingInfoDTO toParking(RestaurantDoc.ParkingInfo p) {
        String carFeeType = p.getCarParkingFeeType() == null ? null : p.getCarParkingFeeType().name();
        String motorFeeType = p.getMotorbikeParkingFeeType() == null ? null : p.getMotorbikeParkingFeeType().name();

        return new ParkingInfoDTO(
                Boolean.TRUE.equals(p.getHasCarParking()),
                p.getCarParkingLocation(),
                carFeeType,
                p.getCarParkingFeeAmount(),
                Boolean.TRUE.equals(p.getHasMotorbikeParking()),
                p.getMotorbikeParkingLocation(),
                motorFeeType,
                p.getMotorbikeParkingFeeAmount(),
                p.getNotes()
        );
    }

    // Policy
    public static RestaurantPolicyDTO toPolicy(RestaurantDoc.RestaurantPolicy p) {
        List<BlackoutDateRuleDTO> blackoutRules = p.getBlackoutRules() == null
                ? List.of()
                : p.getBlackoutRules().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toBlackoutRule)
                    .toList();

        List<OutsideDrinkFeeDTO> outsideDrinkFees = p.getOutsideDrinkFees() == null
                ? List.of()
                : p.getOutsideDrinkFees().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toOutsideDrinkFee)
                    .toList();

        return new RestaurantPolicyDTO(
                Boolean.TRUE.equals(p.getDepositRequired()),
                p.getDepositMinGuests(),
                p.getDepositAmount(),
                p.getDepositCurrencyCode(),
                p.getDepositNotes(),

                Boolean.TRUE.equals(p.getHasPromotion()),
                p.getPromotionSummary(),
                p.getPromotionMaxDiscountPercent(),
                p.getPromotionNote(),
                blackoutRules,

                p.getMinBookingLeadTimeMinutes(),
                p.getMaxHoldTimeMinutes(),
                p.getMinGuestsPerBooking(),

                Boolean.TRUE.equals(p.getVatInvoiceAvailable()),
                p.getVatPercent(),
                Boolean.TRUE.equals(p.getDirectInvoiceAvailable()),
                p.getInvoiceNotes(),

                p.getServiceChargePercent(),
                p.getServiceChargeNotes(),

                Boolean.TRUE.equals(p.getAllowOutsideFood()),
                Boolean.TRUE.equals(p.getAllowOutsideDrink()),
                p.getOutsideFoodPolicy(),
                p.getOutsideDrinkPolicy(),
                outsideDrinkFees
        );
    }

    public static BlackoutDateRuleDTO toBlackoutRule(RestaurantDoc.BlackoutDateRule b) {
        String dateType = b.getDateType() == null ? null : b.getDateType().name();

        return new BlackoutDateRuleDTO(
                dateType,
                b.getMonth(),
                b.getDay(),
                b.getFromDate(),
                b.getToDate(),
                b.getLunarMonth(),
                b.getLunarDay(),
                b.getDescription()
        );
    }

    public static OutsideDrinkFeeDTO toOutsideDrinkFee(RestaurantDoc.OutsideDrinkFee f) {
        return new OutsideDrinkFeeDTO(
                f.getDrinkType(),
                f.getFeeAmount(),
                f.getCurrencyCode(),
                f.getNote()
        );
    }

    // ReviewStats
    public static ReviewStatsDTO toReviewStats(RestaurantDoc.ReviewStats rs) {
        List<ReviewKeywordDTO> keywords = rs.getKeywords() == null
                ? List.of()
                : rs.getKeywords().stream()
                    .filter(Objects::nonNull)
                    .map(RestaurantMapper::toReviewKeyword)
                    .toList();

        return new ReviewStatsDTO(
                rs.getFoodScore(),
                rs.getSpaceScore(),
                rs.getServiceScore(),
                rs.getPriceScore(),
                rs.getLocationScore(),
                keywords
        );
    }

    public static ReviewKeywordDTO toReviewKeyword(RestaurantDoc.ReviewKeywordStat k) {
        return new ReviewKeywordDTO(
                k.getCode(),
                k.getLabel(),
                k.getCount()
        );
    }

    // Publisher
    public static PublisherDTO toPublisher(RestaurantDoc.PublisherInfo p) {
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

    // Moderation
    public static ModerationDTO toModeration(RestaurantDoc.ModerationInfo m) {
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

    public static TableTypeDTO toTableType(RestaurantDoc.TableType t) {
        if (t == null) return null;
        return new TableTypeDTO(
                t.getId(),
                t.getName(),
                t.getSeats(),
                t.getMinPeople(),
                t.getMaxPeople(),
                t.getTotalTables(),
                t.getDepositPrice(),
                t.getCurrencyCode(),
                Boolean.TRUE.equals(t.getVip()),
                Boolean.TRUE.equals(t.getPrivateRoom()),
                t.getAllowedDurationsMinutes() == null ? List.of() : t.getAllowedDurationsMinutes(),
                t.getDefaultDurationMinutes(),
                t.getNote()
        );
    }

    // BookingConfig
    public static RestaurantBookingConfigDTO toBookingConfig(RestaurantDoc.BookingConfig c) {
        if (c == null) return null;
        return new RestaurantBookingConfigDTO(
                c.getSlotMinutes(),
                c.getAllowedDurationsMinutes() == null ? List.of() : c.getAllowedDurationsMinutes(),
                c.getDefaultDurationMinutes(),
                c.getMinBookingLeadTimeMinutes(),
                c.getGraceArrivalMinutes(),
                c.getFreeCancelMinutes(),
                c.getPendingPaymentExpireMinutes(),
                Boolean.TRUE.equals(c.getDepositOnly()),
                c.getMaxTablesPerBooking()
        );
    }
}