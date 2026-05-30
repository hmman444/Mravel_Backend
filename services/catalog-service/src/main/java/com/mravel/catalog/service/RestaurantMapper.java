package com.mravel.catalog.service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.mravel.catalog.dto.hotel.HotelDtos.AmenityDTO;
import com.mravel.catalog.dto.restaurant.RestaurantDtos.*;
import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.search.dto.RestaurantSearchResult;
import com.mravel.common.i18n.LocaleContext;
import com.mravel.common.i18n.LocaleUtil;

public class RestaurantMapper {

        public static RestaurantSummaryDTO toSummary(RestaurantSearchResult r) {
                String cover = null;
                if (r.images() != null && !r.images().isEmpty()) {
                        cover = r.images().stream()
                                        .filter(Objects::nonNull)
                                        .sorted(Comparator
                                                        .comparing(RestaurantSearchResult.ImageRef::cover,
                                                                        Comparator.nullsLast(Boolean::compareTo))
                                                        .reversed()
                                                        .thenComparing(RestaurantSearchResult.ImageRef::sortOrder,
                                                                        Comparator.nullsLast(Integer::compareTo)))
                                        .map(RestaurantSearchResult.ImageRef::url)
                                        .findFirst()
                                        .orElse(null);
                }

                Double lat = null, lon = null;
                if (r.location() != null && r.location().length == 2) {
                        lon = r.location()[0];
                        lat = r.location()[1];
                }

                List<String> cuisineNames = r.cuisines() == null ? List.of()
                                : r.cuisines().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantSearchResult.CuisineRef::name)
                                                .filter(Objects::nonNull)
                                                .distinct()
                                                .toList();

                List<String> ambienceLabels = r.ambience() == null ? List.of()
                                : r.ambience().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantSearchResult.AmbienceRef::label)
                                                .filter(Objects::nonNull)
                                                .distinct()
                                                .toList();

                Integer totalCapacity = r.capacity() == null ? null : r.capacity().totalCapacity();

                return new RestaurantSummaryDTO(
                                r.id(),
                                r.name(),
                                r.slug(),
                                r.active(),
                                r.restaurantType(),

                                r.destinationSlug(),
                                r.cityName(),
                                r.districtName(),
                                r.wardName(),
                                r.addressLine(),
                                lat,
                                lon,

                                cuisineNames,
                                ambienceLabels,
                                totalCapacity,

                                r.avgRating(),
                                r.reviewsCount(),
                                r.ratingLabel(),

                                r.minPricePerPerson(),
                                r.maxPricePerPerson(),
                                r.currencyCode(),
                                r.priceLevel(),
                                r.priceBucket(),

                                cover,
                                List.of());
        }

        public static RestaurantDetailDTO toDetail(RestaurantDoc r, Map<String, AmenityCatalogDoc> catalogMap) {
                String locale = LocaleContext.get();

                Double lat = null, lon = null;
                if (r.getLocation() != null && r.getLocation().length == 2) {
                        lon = r.getLocation()[0];
                        lat = r.getLocation()[1];
                }

                List<CuisineTagDTO> cuisines = r.getCuisines() == null
                                ? List.of()
                                : r.getCuisines().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantMapper::toCuisineTag)
                                                .toList();

                List<OpeningHourDTO> openingHours = r.getOpeningHours() == null
                                ? List.of()
                                : r.getOpeningHours().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantMapper::toOpeningHour)
                                                .toList();

                List<SuitableForDTO> suitableFor = r.getSuitableFor() == null
                                ? List.of()
                                : r.getSuitableFor().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantMapper::toSuitableFor)
                                                .toList();

                List<AmbienceTagDTO> ambience = r.getAmbience() == null
                                ? List.of()
                                : r.getAmbience().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantMapper::toAmbience)
                                                .toList();

                CapacityInfoDTO capacity = r.getCapacity() == null ? null : toCapacity(r.getCapacity());

                List<SignatureDishDTO> signatureDishes = r.getSignatureDishes() == null
                                ? List.of()
                                : r.getSignatureDishes().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantMapper::toSignatureDish)
                                                .toList();

                List<ImageDTO> images = r.getImages() == null
                                ? List.of()
                                : r.getImages().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantMapper::toImage)
                                                .sorted(Comparator
                                                                .comparing(ImageDTO::cover).reversed()
                                                                .thenComparing(ImageDTO::sortOrder,
                                                                                Comparator.nullsLast(
                                                                                                Integer::compareTo)))
                                                .toList();

                List<ImageDTO> menuImages = r.getMenuImages() == null
                                ? List.of()
                                : r.getMenuImages().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantMapper::toImage)
                                                .sorted(Comparator
                                                                .comparing(ImageDTO::cover).reversed()
                                                                .thenComparing(ImageDTO::sortOrder,
                                                                                Comparator.nullsLast(
                                                                                                Integer::compareTo)))
                                                .toList();

                List<ContentBlockDTO> content = r.getContent() == null
                                ? List.of()
                                : r.getContent().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantMapper::toContentBlock)
                                                .filter(Objects::nonNull)
                                                .toList();

                List<MenuSectionDTO> menuSections = r.getMenuSections() == null
                                ? List.of()
                                : r.getMenuSections().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantMapper::toMenuSection)
                                                .toList();

                ParkingInfoDTO parking = r.getParking() == null ? null : toParking(r.getParking());

                RestaurantPolicyDTO policy = r.getPolicy() == null ? null : toPolicy(r.getPolicy());

                ReviewStatsDTO reviewStats = r.getReviewStats() == null ? null : toReviewStats(r.getReviewStats());

                PublisherDTO publisher = r.getPublisher() == null ? null : toPublisher(r.getPublisher());
                ModerationDTO moderation = r.getModeration() == null ? null : toModeration(r.getModeration());

                List<TableTypeDTO> tableTypes = r.getTableTypes() == null ? List.of()
                                : r.getTableTypes().stream().filter(Objects::nonNull).map(RestaurantMapper::toTableType)
                                                .toList();

                RestaurantBookingConfigDTO bookingConfig = r.getBookingConfig() == null ? null
                                : toBookingConfig(r.getBookingConfig());

                return new RestaurantDetailDTO(
                                r.getId(),
                                LocaleUtil.pick(r.getName(), locale),
                                r.getSlug(),
                                r.getActive(),
                                r.getRestaurantType() == null ? null : r.getRestaurantType().name(),

                                cuisines,

                                LocaleUtil.pick(r.getShortDescription(), locale),
                                LocaleUtil.pick(r.getDescription(), locale),

                                r.getDestinationSlug(),
                                r.getParentPlaceSlug(),
                                LocaleUtil.pick(r.getCityName(), locale),
                                LocaleUtil.pick(r.getDistrictName(), locale),
                                LocaleUtil.pick(r.getWardName(), locale),
                                LocaleUtil.pick(r.getAddressLine(), locale),
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

                                resolveAmenities(r.getAmenityCodes(), catalogMap),

                                policy,

                                r.getAvgRating(),
                                r.getReviewsCount(),
                                LocaleUtil.pick(r.getRatingLabel(), locale),
                                reviewStats,

                                publisher,
                                moderation,

                                tableTypes,
                                bookingConfig);
        }

        public static ImageDTO toImage(RestaurantDoc.Image img) {
                String locale = LocaleContext.get();
                return new ImageDTO(
                                img.getUrl(),
                                LocaleUtil.pick(img.getCaption(), locale),
                                Boolean.TRUE.equals(img.getCover()),
                                img.getSortOrder());
        }

        public static CuisineTagDTO toCuisineTag(RestaurantDoc.CuisineTag c) {
                String locale = LocaleContext.get();
                return new CuisineTagDTO(
                                c.getCode(),
                                LocaleUtil.pick(c.getName(), locale),
                                c.getRegion());
        }

        public static OpeningHourDTO toOpeningHour(RestaurantDoc.OpeningHour oh) {
                return new OpeningHourDTO(
                                oh.getDayOfWeek(),
                                oh.getOpenTime(),
                                oh.getCloseTime(),
                                Boolean.TRUE.equals(oh.getOpen24h()),
                                Boolean.TRUE.equals(oh.getClosed()));
        }

        public static SuitableForDTO toSuitableFor(RestaurantDoc.SuitableFor sf) {
                String locale = LocaleContext.get();
                return new SuitableForDTO(
                                sf.getCode(),
                                LocaleUtil.pick(sf.getLabel(), locale));
        }

        public static AmbienceTagDTO toAmbience(RestaurantDoc.AmbienceTag a) {
                String locale = LocaleContext.get();
                return new AmbienceTagDTO(
                                a.getCode(),
                                LocaleUtil.pick(a.getLabel(), locale));
        }

        public static CapacityInfoDTO toCapacity(RestaurantDoc.CapacityInfo c) {
                return new CapacityInfoDTO(
                                c.getTotalCapacity(),
                                c.getMaxGroupSize(),
                                Boolean.TRUE.equals(c.getHasPrivateRooms()),
                                c.getPrivateRoomCount(),
                                c.getMaxPrivateRoomCapacity(),
                                Boolean.TRUE.equals(c.getHasOutdoorSeating()));
        }

        public static SignatureDishDTO toSignatureDish(RestaurantDoc.SignatureDish d) {
                String locale = LocaleContext.get();
                return new SignatureDishDTO(
                                LocaleUtil.pick(d.getName(), locale),
                                LocaleUtil.pick(d.getDescription(), locale),
                                d.getEstimatedPrice(),
                                Boolean.TRUE.equals(d.getHighlight()));
        }

        public static ContentBlockDTO toContentBlock(RestaurantDoc.ContentBlock b) {
                if (b == null)
                        return null;
                String locale = LocaleContext.get();

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
                                LocaleUtil.pick(b.getText(), locale),
                                image,
                                gallery,
                                mapLat,
                                mapLon);
        }

        public static MenuSectionDTO toMenuSection(RestaurantDoc.MenuSection ms) {
                String locale = LocaleContext.get();
                List<MenuItemDTO> items = ms.getItems() == null
                                ? List.of()
                                : ms.getItems().stream()
                                                .filter(Objects::nonNull)
                                                .map(RestaurantMapper::toMenuItem)
                                                .toList();

                return new MenuSectionDTO(
                                ms.getCode(),
                                LocaleUtil.pick(ms.getName(), locale),
                                items);
        }

        public static MenuItemDTO toMenuItem(RestaurantDoc.MenuItem item) {
                String locale = LocaleContext.get();
                return new MenuItemDTO(
                                LocaleUtil.pick(item.getName(), locale),
                                LocaleUtil.pick(item.getDescription(), locale),
                                item.getPriceFrom(),
                                item.getPriceTo(),
                                LocaleUtil.pick(item.getUnit(), locale),
                                Boolean.TRUE.equals(item.getCombo()),
                                Boolean.TRUE.equals(item.getBuffetItem()),
                                item.getTags() == null ? List.of() : item.getTags());
        }

        public static ParkingInfoDTO toParking(RestaurantDoc.ParkingInfo p) {
                String locale = LocaleContext.get();
                String carFeeType = p.getCarParkingFeeType() == null ? null : p.getCarParkingFeeType().name();
                String motorFeeType = p.getMotorbikeParkingFeeType() == null ? null
                                : p.getMotorbikeParkingFeeType().name();

                return new ParkingInfoDTO(
                                Boolean.TRUE.equals(p.getHasCarParking()),
                                LocaleUtil.pick(p.getCarParkingLocation(), locale),
                                carFeeType,
                                p.getCarParkingFeeAmount(),
                                Boolean.TRUE.equals(p.getHasMotorbikeParking()),
                                LocaleUtil.pick(p.getMotorbikeParkingLocation(), locale),
                                motorFeeType,
                                p.getMotorbikeParkingFeeAmount(),
                                LocaleUtil.pick(p.getNotes(), locale));
        }

        public static RestaurantPolicyDTO toPolicy(RestaurantDoc.RestaurantPolicy p) {
                String locale = LocaleContext.get();
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
                                LocaleUtil.pick(p.getDepositNotes(), locale),

                                Boolean.TRUE.equals(p.getHasPromotion()),
                                LocaleUtil.pick(p.getPromotionSummary(), locale),
                                p.getPromotionMaxDiscountPercent(),
                                LocaleUtil.pick(p.getPromotionNote(), locale),
                                blackoutRules,

                                p.getMinBookingLeadTimeMinutes(),
                                p.getMaxHoldTimeMinutes(),
                                p.getMinGuestsPerBooking(),

                                Boolean.TRUE.equals(p.getVatInvoiceAvailable()),
                                p.getVatPercent(),
                                Boolean.TRUE.equals(p.getDirectInvoiceAvailable()),
                                LocaleUtil.pick(p.getInvoiceNotes(), locale),

                                p.getServiceChargePercent(),
                                LocaleUtil.pick(p.getServiceChargeNotes(), locale),

                                Boolean.TRUE.equals(p.getAllowOutsideFood()),
                                Boolean.TRUE.equals(p.getAllowOutsideDrink()),
                                LocaleUtil.pick(p.getOutsideFoodPolicy(), locale),
                                LocaleUtil.pick(p.getOutsideDrinkPolicy(), locale),
                                outsideDrinkFees);
        }

        public static BlackoutDateRuleDTO toBlackoutRule(RestaurantDoc.BlackoutDateRule b) {
                String locale = LocaleContext.get();
                String dateType = b.getDateType() == null ? null : b.getDateType().name();

                return new BlackoutDateRuleDTO(
                                dateType,
                                b.getMonth(),
                                b.getDay(),
                                b.getFromDate(),
                                b.getToDate(),
                                b.getLunarMonth(),
                                b.getLunarDay(),
                                LocaleUtil.pick(b.getDescription(), locale));
        }

        public static OutsideDrinkFeeDTO toOutsideDrinkFee(RestaurantDoc.OutsideDrinkFee f) {
                String locale = LocaleContext.get();
                return new OutsideDrinkFeeDTO(
                                f.getDrinkType(),
                                f.getFeeAmount(),
                                f.getCurrencyCode(),
                                LocaleUtil.pick(f.getNote(), locale));
        }

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
                                keywords);
        }

        public static ReviewKeywordDTO toReviewKeyword(RestaurantDoc.ReviewKeywordStat k) {
                String locale = LocaleContext.get();
                return new ReviewKeywordDTO(
                                k.getCode(),
                                LocaleUtil.pick(k.getLabel(), locale),
                                k.getCount());
        }

        public static PublisherDTO toPublisher(RestaurantDoc.PublisherInfo p) {
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

        public static ModerationDTO toModeration(RestaurantDoc.ModerationInfo m) {
                String lastActionAt = m.getLastActionAt() == null ? null : m.getLastActionAt().toString();

                return new ModerationDTO(
                                m.getStatus() == null ? null : m.getStatus().name(),
                                m.getRejectionReason(),
                                m.getBlockedReason(),
                                m.getReportCount(),
                                m.getLastActionByAdminId(),
                                lastActionAt);
        }

        public static TableTypeDTO toTableType(RestaurantDoc.TableType t) {
                if (t == null)
                        return null;
                String locale = LocaleContext.get();
                return new TableTypeDTO(
                                t.getId(),
                                LocaleUtil.pick(t.getName(), locale),
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
                                LocaleUtil.pick(t.getNote(), locale));
        }

        public static RestaurantBookingConfigDTO toBookingConfig(RestaurantDoc.BookingConfig c) {
                if (c == null)
                        return null;
                return new RestaurantBookingConfigDTO(
                                c.getSlotMinutes(),
                                c.getAllowedDurationsMinutes() == null ? List.of() : c.getAllowedDurationsMinutes(),
                                c.getDefaultDurationMinutes(),
                                c.getMinBookingLeadTimeMinutes(),
                                c.getGraceArrivalMinutes(),
                                c.getFreeCancelMinutes(),
                                c.getPendingPaymentExpireMinutes(),
                                Boolean.TRUE.equals(c.getDepositOnly()),
                                c.getMaxTablesPerBooking());
        }

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
                                .map(RestaurantMapper::toAmenityFromCatalog)
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

}
