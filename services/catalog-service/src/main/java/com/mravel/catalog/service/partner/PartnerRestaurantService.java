package com.mravel.catalog.service.partner;

import com.mravel.catalog.client.UserServiceClient;
import com.mravel.catalog.dto.partner.PartnerCatalogDtos;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.repository.RestaurantDocRepository;
import com.mravel.catalog.search.es.IndexingService;
import com.mravel.catalog.service.AmenityCatalogService;
import com.mravel.catalog.translation.LocalizedTranslator;
import com.mravel.common.i18n.LocaleConstants;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PartnerRestaurantService {

    private final RestaurantDocRepository repo;
    private final AmenityCatalogService amenityCatalogService;
    private final UserServiceClient userServiceClient;
    private final IndexingService indexingService;
    private final LocalizedTranslator localizedTranslator;

    public Page<RestaurantDoc> listMyRestaurants(Long partnerId, String status, Integer page, Integer size) {
        String pid = String.valueOf(partnerId);

        Pageable pageable = PageRequest.of(
                page == null ? 0 : page,
                size == null ? 20 : size,
                Sort.by(Sort.Direction.DESC, "publisher.lastUpdatedAt"));

        if (status == null || status.isBlank()) {
            return repo.findByPublisher_PartnerIdAndDeletedAtIsNull(pid, pageable);
        }

        String st = status.trim().toUpperCase(Locale.ROOT);

        return switch (st) {
            case "PENDING" ->
                repo.findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_Status(
                        pid, RestaurantDoc.RestaurantStatus.PENDING_REVIEW, pageable);

            case "REJECTED" ->
                repo.findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_Status(
                        pid, RestaurantDoc.RestaurantStatus.REJECTED, pageable);

            case "ADMIN_BLOCKED" ->
                repo.findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_Status(
                        pid, RestaurantDoc.RestaurantStatus.BLOCKED, pageable);

            case "ACTIVE" ->
                repo.findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_StatusAndActive(
                        pid, RestaurantDoc.RestaurantStatus.APPROVED, true, pageable);

            case "PARTNER_PAUSED" ->
                repo.findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_StatusAndActive(
                        pid, RestaurantDoc.RestaurantStatus.APPROVED, false, pageable);

            default -> throw new IllegalArgumentException("Invalid status: " + status);
        };
    }

    public RestaurantDoc create(Long partnerId,
            PartnerCatalogDtos.PendingReason pendingReason,
            PartnerCatalogDtos.UpsertRestaurantReq req,
            String bearer) {
        pendingReason = PartnerCatalogDtos.PendingReason.CREATE;

        RestaurantDoc doc = new RestaurantDoc();
        doc.setActive(true);
        doc.setDeletedAt(null);

        if (req.name() != null)
            doc.setName(wrap(req.name()));
        rejectBlankIfPresent(req.name(), "name");
        String slugBase = (req.slug() != null && !req.slug().isBlank()) ? req.slug() : req.name();
        doc.setSlug(genUniqueSlugForCreate(slugBase));

        doc.setDestinationSlug(req.destinationSlug());
        doc.setParentPlaceSlug(req.parentPlaceSlug());
        doc.setCityName(wrap(req.cityName()));
        doc.setDistrictName(wrap(req.districtName()));
        doc.setWardName(wrap(req.wardName()));
        doc.setAddressLine(wrap(req.addressLine()));

        if (req.latitude() != null && req.longitude() != null) {
            doc.setLocation(new double[] { req.longitude(), req.latitude() });
        } else {
            doc.setLocation(null);
        }

        if (req.restaurantType() != null)
            doc.setRestaurantType(parseEnum(req.restaurantType(), RestaurantDoc.RestaurantType.class, "restaurantType"));

        doc.setMinPricePerPerson(req.minPrice());
        doc.setMaxPricePerPerson(req.maxPrice());
        doc.setCurrencyCode(req.currencyCode() == null || req.currencyCode().isBlank() ? "VND" : req.currencyCode());
        if (req.priceLevel() != null)
            doc.setPriceLevel(parseEnum(req.priceLevel(), RestaurantDoc.PriceLevel.class, "priceLevel"));
        if (req.priceBucket() != null)
            doc.setPriceBucket(parseEnum(req.priceBucket(), RestaurantDoc.PriceBucket.class, "priceBucket"));

        doc.setShortDescription(wrap(req.shortDescription()));
        doc.setDescription(wrap(req.description()));
        doc.setWebsite(req.website());
        doc.setPhone(req.phone());
        doc.setEmail(req.email());
        doc.setFacebookPage(req.facebookPage());
        doc.setBookingHotline(req.bookingHotline());

        doc.setCuisines(req.cuisines() == null ? List.of() : mapCuisines(req.cuisines()));
        doc.setOpeningHours(req.openingHours() == null ? List.of() : mapOpeningHours(req.openingHours()));
        doc.setSuitableFor(req.suitableFor() == null ? List.of() : mapSuitableFor(req.suitableFor()));
        doc.setAmbience(req.ambience() == null ? List.of() : mapAmbience(req.ambience()));
        doc.setSignatureDishes(req.signatureDishes() == null ? List.of() : mapSignatureDishes(req.signatureDishes()));

        doc.setCapacity(mapCapacity(req.capacity()));
        doc.setParking(mapParking(req.parking()));
        doc.setPolicy(mapPolicy(req.policy()));

        doc.setImages(req.images() == null ? List.of() : mapResImages(req.images()));
        doc.setMenuImages(req.menuImages() == null ? List.of() : mapResImages(req.menuImages()));
        doc.setMenuSections(req.menuSections() == null ? List.of() : mapMenuSections(req.menuSections()));
        doc.setContent(req.content() == null ? List.of() : mapContentBlocks(req.content()));

        if (req.amenityCodes() != null) {
            amenityCatalogService.validateCodes(AmenityScope.RESTAURANT, req.amenityCodes());
            doc.setAmenityCodes(normalizeCodes(req.amenityCodes()));
        } else {
            doc.setAmenityCodes(List.of());
        }

        doc.setTableTypes(req.tableTypes() == null ? List.of() : mapTableTypes(req.tableTypes()));

        doc.setBookingConfig(buildBookingConfigOrDefault(req.bookingConfig()));

        RestaurantDoc.PublisherInfo pub = RestaurantDoc.PublisherInfo.builder()
                .partnerId(String.valueOf(partnerId))
                .createdAt(Instant.now())
                .lastUpdatedAt(Instant.now())
                .build();

        enrichPublisherFromBearerIfPossible(pub, bearer);
        doc.setPublisher(pub);

        RestaurantDoc.ModerationInfo mod = RestaurantDoc.ModerationInfo.builder()
                .status(RestaurantDoc.RestaurantStatus.PENDING_REVIEW)
                .pendingReason(pendingReason.name())
                .build();
        doc.setModeration(mod);

        recomputePriceAggregates(doc);
        RestaurantDoc saved = repo.save(doc);
        indexingService.syncRestaurant(saved);
        return saved;
    }

    public RestaurantDoc update(String id,
            Long partnerId,
            PartnerCatalogDtos.PendingReason pendingReason,
            PartnerCatalogDtos.UpsertRestaurantReq req,
            String bearer) {
        pendingReason = PartnerCatalogDtos.PendingReason.UPDATE;

        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        assertOwner(doc, partnerId);

        if (req.name() != null)
            doc.setName(wrap(req.name()));
        rejectBlankIfPresent(req.name(), "name");

        if (req.slug() != null && !req.slug().isBlank()) {
            String nextSlug = slugify(req.slug());
            if (doc.getSlug() == null || !nextSlug.equals(doc.getSlug())) {
                doc.setSlug(genUniqueSlugForUpdate(nextSlug, doc.getId()));
            }
        }

        if (req.destinationSlug() != null)
            doc.setDestinationSlug(req.destinationSlug());
        if (req.parentPlaceSlug() != null)
            doc.setParentPlaceSlug(req.parentPlaceSlug());
        if (req.cityName() != null)
            doc.setCityName(wrap(req.cityName()));
        if (req.districtName() != null)
            doc.setDistrictName(wrap(req.districtName()));
        if (req.wardName() != null)
            doc.setWardName(wrap(req.wardName()));
        if (req.addressLine() != null)
            doc.setAddressLine(wrap(req.addressLine()));

        if (req.latitude() != null && req.longitude() != null) {
            doc.setLocation(new double[] { req.longitude(), req.latitude() });
        }

        if (req.restaurantType() != null)
            doc.setRestaurantType(parseEnum(req.restaurantType(), RestaurantDoc.RestaurantType.class, "restaurantType"));
        if (req.shortDescription() != null)
            doc.setShortDescription(wrap(req.shortDescription()));
        if (req.description() != null)
            doc.setDescription(wrap(req.description()));

        if (req.phone() != null)
            doc.setPhone(req.phone());
        if (req.email() != null)
            doc.setEmail(req.email());
        if (req.website() != null)
            doc.setWebsite(req.website());
        if (req.facebookPage() != null)
            doc.setFacebookPage(req.facebookPage());
        if (req.bookingHotline() != null)
            doc.setBookingHotline(req.bookingHotline());

        if (req.minPrice() != null)
            doc.setMinPricePerPerson(req.minPrice());
        if (req.maxPrice() != null)
            doc.setMaxPricePerPerson(req.maxPrice());
        if (req.currencyCode() != null)
            doc.setCurrencyCode(req.currencyCode());
        if (req.priceLevel() != null)
            doc.setPriceLevel(parseEnum(req.priceLevel(), RestaurantDoc.PriceLevel.class, "priceLevel"));
        if (req.priceBucket() != null)
            doc.setPriceBucket(parseEnum(req.priceBucket(), RestaurantDoc.PriceBucket.class, "priceBucket"));

        if (req.cuisines() != null)
            doc.setCuisines(mapCuisines(req.cuisines()));
        if (req.openingHours() != null)
            doc.setOpeningHours(mapOpeningHours(req.openingHours()));
        if (req.suitableFor() != null)
            doc.setSuitableFor(mapSuitableFor(req.suitableFor()));
        if (req.ambience() != null)
            doc.setAmbience(mapAmbience(req.ambience()));
        if (req.signatureDishes() != null)
            doc.setSignatureDishes(mapSignatureDishes(req.signatureDishes()));

        if (req.capacity() != null)
            doc.setCapacity(mapCapacity(req.capacity()));
        if (req.parking() != null)
            doc.setParking(mapParking(req.parking()));
        if (req.policy() != null)
            doc.setPolicy(mapPolicy(req.policy()));

        if (req.images() != null) {
            doc.setImages(mapResImages(req.images()));
        }
        if (req.menuImages() != null) {
            doc.setMenuImages(mapResImages(req.menuImages()));
        }
        if (req.menuSections() != null) {
            doc.setMenuSections(mapMenuSections(req.menuSections()));
        }
        if (req.content() != null) {
            doc.setContent(mapContentBlocks(req.content()));
        }

        if (req.amenityCodes() != null) {
            amenityCatalogService.validateCodes(AmenityScope.RESTAURANT, req.amenityCodes());
            doc.setAmenityCodes(normalizeCodes(req.amenityCodes()));
        }

        if (req.tableTypes() != null) {
            doc.setTableTypes(mapTableTypes(req.tableTypes()));
        }

        if (req.bookingConfig() != null) {
            var c = req.bookingConfig();

            if (doc.getBookingConfig() == null) {
                doc.setBookingConfig(buildBookingConfigOrDefault(null));
            }

            if (c.slotMinutes() != null)
                doc.getBookingConfig().setSlotMinutes(c.slotMinutes());
            if (c.allowedDurationsMinutes() != null)
                doc.getBookingConfig().setAllowedDurationsMinutes(c.allowedDurationsMinutes());
            if (c.defaultDurationMinutes() != null)
                doc.getBookingConfig().setDefaultDurationMinutes(c.defaultDurationMinutes());
            if (c.minBookingLeadTimeMinutes() != null)
                doc.getBookingConfig().setMinBookingLeadTimeMinutes(c.minBookingLeadTimeMinutes());
            if (c.graceArrivalMinutes() != null)
                doc.getBookingConfig().setGraceArrivalMinutes(c.graceArrivalMinutes());
            if (c.freeCancelMinutes() != null)
                doc.getBookingConfig().setFreeCancelMinutes(c.freeCancelMinutes());
            if (c.pendingPaymentExpireMinutes() != null)
                doc.getBookingConfig().setPendingPaymentExpireMinutes(c.pendingPaymentExpireMinutes());
            if (c.depositOnly() != null)
                doc.getBookingConfig().setDepositOnly(c.depositOnly());
            if (c.maxTablesPerBooking() != null)
                doc.getBookingConfig().setMaxTablesPerBooking(c.maxTablesPerBooking());
        }

        if (doc.getModeration() == null)
            doc.setModeration(new RestaurantDoc.ModerationInfo());
        doc.getModeration().setStatus(RestaurantDoc.RestaurantStatus.PENDING_REVIEW);
        doc.getModeration().setRejectionReason(null);
        doc.getModeration().setPendingReason(pendingReason.name());
        if (doc.getPublisher() != null) {
            enrichPublisherFromBearerIfPossible(doc.getPublisher(), bearer);
        }

        touchPublisher(doc);
        recomputePriceAggregates(doc);
        RestaurantDoc saved = repo.save(doc);
        indexingService.syncRestaurant(saved);
        return saved;
    }

    public RestaurantDoc softDelete(String id, Long partnerId) {
        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        assertOwner(doc, partnerId);

        doc.setDeletedAt(Instant.now());
        doc.setActive(false);
        touchPublisher(doc);

        RestaurantDoc saved = repo.save(doc);
        indexingService.deleteRestaurant(saved.getId());
        return saved;
    }

    public RestaurantDoc pause(String id, Long partnerId) {
        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        assertOwner(doc, partnerId);

        if (doc.getModeration() == null || doc.getModeration().getStatus() != RestaurantDoc.RestaurantStatus.APPROVED) {
            throw new IllegalStateException("Only APPROVED restaurant can be paused");
        }

        doc.setActive(false);
        touchPublisher(doc);

        RestaurantDoc saved = repo.save(doc);
        indexingService.syncRestaurant(saved);
        return saved;
    }

    public RestaurantDoc resume(String id, Long partnerId) {
        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        assertOwner(doc, partnerId);

        if (doc.getModeration() == null || doc.getModeration().getStatus() != RestaurantDoc.RestaurantStatus.APPROVED) {
            throw new IllegalStateException("Only APPROVED restaurant can be resumed");
        }

        doc.setActive(true);
        touchPublisher(doc);

        RestaurantDoc saved = repo.save(doc);
        indexingService.syncRestaurant(saved);
        return saved;
    }

    public RestaurantDoc unlockRequest(String id, Long partnerId, String reason) {
        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        assertOwner(doc, partnerId);

        if (doc.getModeration() == null || doc.getModeration().getStatus() != RestaurantDoc.RestaurantStatus.BLOCKED) {
            throw new IllegalStateException("Only BLOCKED restaurant can request unlock");
        }

        doc.getModeration().setUnlockRequestedAt(Instant.now());
        doc.getModeration().setUnlockRequestReason(reason);
        touchPublisher(doc);

        RestaurantDoc saved = repo.save(doc);
        indexingService.syncRestaurant(saved);
        return saved;
    }

    public RestaurantDoc getByIdForPartner(String id, Long partnerId) {
        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        assertOwner(doc, partnerId);
        return doc;
    }

    private RestaurantDoc.BookingConfig buildBookingConfigOrDefault(
            PartnerCatalogDtos.UpsertRestaurantBookingConfigReq c) {
        return RestaurantDoc.BookingConfig.builder()
                .slotMinutes(c != null && c.slotMinutes() != null ? c.slotMinutes() : 30)
                .allowedDurationsMinutes(c != null && c.allowedDurationsMinutes() != null ? c.allowedDurationsMinutes()
                        : List.of(60, 90, 120))
                .defaultDurationMinutes(
                        c != null && c.defaultDurationMinutes() != null ? c.defaultDurationMinutes() : 90)
                .minBookingLeadTimeMinutes(
                        c != null && c.minBookingLeadTimeMinutes() != null ? c.minBookingLeadTimeMinutes() : 60)
                .graceArrivalMinutes(c != null && c.graceArrivalMinutes() != null ? c.graceArrivalMinutes() : 15)
                .freeCancelMinutes(c != null && c.freeCancelMinutes() != null ? c.freeCancelMinutes() : 30)
                .pendingPaymentExpireMinutes(
                        c != null && c.pendingPaymentExpireMinutes() != null ? c.pendingPaymentExpireMinutes() : 30)
                .depositOnly(c == null || c.depositOnly() == null ? true : c.depositOnly())
                .maxTablesPerBooking(c != null && c.maxTablesPerBooking() != null ? c.maxTablesPerBooking() : 5)
                .build();
    }

    private List<RestaurantDoc.Image> mapResImages(List<PartnerCatalogDtos.ImageReq> images) {
        if (images == null)
            return null;

        List<RestaurantDoc.Image> list = images.stream()
                .filter(Objects::nonNull)
                .map(i -> RestaurantDoc.Image.builder()
                        .url(i.url())
                        .caption(wrap(i.caption()))
                        .cover(Boolean.TRUE.equals(i.cover()))
                        .sortOrder(i.sortOrder() == null ? 0 : i.sortOrder())
                        .build())
                .toList();

        if (!list.isEmpty() && list.stream().noneMatch(im -> Boolean.TRUE.equals(im.getCover()))) {
            list.get(0).setCover(true);
        }
        return list;
    }

    private static void rejectBlankIfPresent(String value, String field) {
        if (value != null && value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
    }

    private List<RestaurantDoc.TableType> mapTableTypes(List<PartnerCatalogDtos.UpsertTableTypeReq> tableTypes) {
        return tableTypes.stream()
                .filter(Objects::nonNull)
                .map(t -> RestaurantDoc.TableType.builder()
                        .id((t.id() == null || t.id().isBlank()) ? UUID.randomUUID().toString() : t.id())
                        .name(wrap(t.name()))
                        .seats(t.seats())
                        .minPeople(t.minPeople())
                        .maxPeople(t.maxPeople())
                        .totalTables(t.totalTables())
                        .depositPrice(t.depositPrice())
                        .currencyCode(t.currencyCode() == null ? "VND" : t.currencyCode())
                        .vip(Boolean.TRUE.equals(t.vip()))
                        .privateRoom(Boolean.TRUE.equals(t.privateRoom()))
                        .allowedDurationsMinutes(t.allowedDurationsMinutes())
                        .defaultDurationMinutes(t.defaultDurationMinutes())
                        .note(wrap(t.note()))
                        .build())
                .toList();
    }

    private List<RestaurantDoc.CuisineTag> mapCuisines(List<PartnerCatalogDtos.UpsertCuisineTagReq> items) {
        if (items == null) return null;
        return items.stream()
                .filter(Objects::nonNull)
                .map(c -> RestaurantDoc.CuisineTag.builder()
                        .code(c.code())
                        .name(wrap(c.name()))
                        .region(c.region())
                        .build())
                .toList();
    }

    private List<RestaurantDoc.OpeningHour> mapOpeningHours(List<PartnerCatalogDtos.UpsertOpeningHourReq> items) {
        if (items == null) return null;
        return items.stream()
                .filter(Objects::nonNull)
                .map(o -> RestaurantDoc.OpeningHour.builder()
                        .dayOfWeek(parseEnum(o.dayOfWeek(), DayOfWeek.class, "openingHours.dayOfWeek"))
                        .openTime(parseTimeOrNull(o.openTime()))
                        .closeTime(parseTimeOrNull(o.closeTime()))
                        .open24h(Boolean.TRUE.equals(o.open24h()))
                        .closed(Boolean.TRUE.equals(o.closed()))
                        .build())
                .toList();
    }

    private List<RestaurantDoc.SuitableFor> mapSuitableFor(List<PartnerCatalogDtos.UpsertCodeNameReq> items) {
        if (items == null) return null;
        return items.stream()
                .filter(Objects::nonNull)
                .map(x -> RestaurantDoc.SuitableFor.builder()
                        .code(x.code())
                        .label(wrap(x.name()))
                        .build())
                .toList();
    }

    private List<RestaurantDoc.AmbienceTag> mapAmbience(List<PartnerCatalogDtos.UpsertCodeNameReq> items) {
        if (items == null) return null;
        return items.stream()
                .filter(Objects::nonNull)
                .map(x -> RestaurantDoc.AmbienceTag.builder()
                        .code(x.code())
                        .label(wrap(x.name()))
                        .build())
                .toList();
    }

    private List<RestaurantDoc.SignatureDish> mapSignatureDishes(List<PartnerCatalogDtos.UpsertSignatureDishReq> items) {
        if (items == null) return null;
        return items.stream()
                .filter(Objects::nonNull)
                .map(d -> RestaurantDoc.SignatureDish.builder()
                        .name(wrap(d.name()))
                        .description(wrap(d.description()))
                        .estimatedPrice(d.estimatedPrice())
                        .highlight(d.highlight() == null ? Boolean.TRUE : d.highlight())
                        .build())
                .toList();
    }

    private RestaurantDoc.CapacityInfo mapCapacity(PartnerCatalogDtos.UpsertCapacityInfoReq c) {
        if (c == null) return null;
        return RestaurantDoc.CapacityInfo.builder()
                .totalCapacity(c.totalCapacity())
                .maxGroupSize(c.maxGroupSize())
                .hasPrivateRooms(Boolean.TRUE.equals(c.hasPrivateRooms()))
                .privateRoomCount(c.privateRoomCount())
                .maxPrivateRoomCapacity(c.maxPrivateRoomCapacity())
                .hasOutdoorSeating(Boolean.TRUE.equals(c.hasOutdoorSeating()))
                .build();
    }

    private RestaurantDoc.ParkingInfo mapParking(PartnerCatalogDtos.UpsertParkingInfoReq p) {
        if (p == null) return null;
        return RestaurantDoc.ParkingInfo.builder()
                .hasCarParking(Boolean.TRUE.equals(p.hasCarParking()))
                .carParkingLocation(wrap(p.carParkingLocation()))
                .carParkingFeeType(parseEnum(p.carParkingFeeType(), RestaurantDoc.ParkingFeeType.class,
                        "parking.carParkingFeeType"))
                .carParkingFeeAmount(p.carParkingFeeAmount())
                .hasMotorbikeParking(Boolean.TRUE.equals(p.hasMotorbikeParking()))
                .motorbikeParkingLocation(wrap(p.motorbikeParkingLocation()))
                .motorbikeParkingFeeType(parseEnum(p.motorbikeParkingFeeType(), RestaurantDoc.ParkingFeeType.class,
                        "parking.motorbikeParkingFeeType"))
                .motorbikeParkingFeeAmount(p.motorbikeParkingFeeAmount())
                .notes(wrap(p.notes()))
                .build();
    }

    private List<RestaurantDoc.MenuSection> mapMenuSections(List<PartnerCatalogDtos.UpsertMenuSectionReq> sections) {
        if (sections == null) return null;
        return sections.stream()
                .filter(Objects::nonNull)
                .map(s -> RestaurantDoc.MenuSection.builder()
                        .code(s.code())
                        .name(wrap(s.name()))
                        .items(mapMenuItems(s.items()))
                        .build())
                .toList();
    }

    private List<RestaurantDoc.MenuItem> mapMenuItems(List<PartnerCatalogDtos.UpsertMenuItemReq> items) {
        if (items == null) return null;
        return items.stream()
                .filter(Objects::nonNull)
                .map(i -> RestaurantDoc.MenuItem.builder()
                        .name(wrap(i.name()))
                        .description(wrap(i.description()))
                        .priceFrom(i.priceFrom())
                        .priceTo(i.priceTo())
                        .unit(wrap(i.unit()))
                        .combo(Boolean.TRUE.equals(i.combo()))
                        .buffetItem(Boolean.TRUE.equals(i.buffetItem()))
                        .tags(i.tags())
                        .build())
                .toList();
    }

    private RestaurantDoc.RestaurantPolicy mapPolicy(PartnerCatalogDtos.UpsertRestaurantPolicyReq p) {
        if (p == null) return null;

        List<RestaurantDoc.BlackoutDateRule> blackoutRules = null;
        if (p.blackoutRules() != null) {
            blackoutRules = p.blackoutRules().stream()
                    .filter(Objects::nonNull)
                    .map(b -> RestaurantDoc.BlackoutDateRule.builder()
                            .dateType(parseEnum(b.dateType(), RestaurantDoc.BlackoutDateType.class,
                                    "policy.blackoutRules.dateType"))
                            .month(b.month())
                            .day(b.day())
                            .fromDate(b.fromDate())
                            .toDate(b.toDate())
                            .lunarMonth(b.lunarMonth())
                            .lunarDay(b.lunarDay())
                            .description(wrap(b.description()))
                            .build())
                    .toList();
        }

        List<RestaurantDoc.OutsideDrinkFee> outsideDrinkFees = null;
        if (p.outsideDrinkFees() != null) {
            outsideDrinkFees = p.outsideDrinkFees().stream()
                    .filter(Objects::nonNull)
                    .map(f -> RestaurantDoc.OutsideDrinkFee.builder()
                            .drinkType(f.drinkType())
                            .feeAmount(f.feeAmount())
                            .currencyCode(f.currencyCode() == null ? "VND" : f.currencyCode())
                            .note(wrap(f.note()))
                            .build())
                    .toList();
        }

        return RestaurantDoc.RestaurantPolicy.builder()
                .depositRequired(Boolean.TRUE.equals(p.depositRequired()))
                .depositMinGuests(p.depositMinGuests())
                .depositAmount(p.depositAmount())
                .depositCurrencyCode(p.depositCurrencyCode() == null ? "VND" : p.depositCurrencyCode())
                .depositNotes(wrap(p.depositNotes()))
                .hasPromotion(Boolean.TRUE.equals(p.hasPromotion()))
                .promotionSummary(wrap(p.promotionSummary()))
                .promotionMaxDiscountPercent(p.promotionMaxDiscountPercent())
                .promotionNote(wrap(p.promotionNote()))
                .blackoutRules(blackoutRules)
                .minBookingLeadTimeMinutes(p.minBookingLeadTimeMinutes())
                .maxHoldTimeMinutes(p.maxHoldTimeMinutes())
                .minGuestsPerBooking(p.minGuestsPerBooking())
                .vatInvoiceAvailable(Boolean.TRUE.equals(p.vatInvoiceAvailable()))
                .vatPercent(p.vatPercent())
                .directInvoiceAvailable(Boolean.TRUE.equals(p.directInvoiceAvailable()))
                .invoiceNotes(wrap(p.invoiceNotes()))
                .serviceChargePercent(p.serviceChargePercent())
                .serviceChargeNotes(wrap(p.serviceChargeNotes()))
                .allowOutsideFood(Boolean.TRUE.equals(p.allowOutsideFood()))
                .allowOutsideDrink(Boolean.TRUE.equals(p.allowOutsideDrink()))
                .outsideFoodPolicy(wrap(p.outsideFoodPolicy()))
                .outsideDrinkPolicy(wrap(p.outsideDrinkPolicy()))
                .outsideDrinkFees(outsideDrinkFees)
                .build();
    }

    private List<RestaurantDoc.ContentBlock> mapContentBlocks(List<PartnerCatalogDtos.UpsertContentBlockReq> blocks) {
        if (blocks == null) return null;
        return blocks.stream()
                .filter(Objects::nonNull)
                .map(b -> {
                    RestaurantDoc.ContentBlock.ContentBlockBuilder cb = RestaurantDoc.ContentBlock.builder()
                            .type(parseEnum(b.type(), RestaurantDoc.ContentBlock.BlockType.class, "content.type"))
                            .section(parseEnum(b.section(), RestaurantDoc.ContentBlock.ContentSection.class,
                                    "content.section"))
                            .text(wrap(b.text()));

                    if (b.image() != null) {
                        cb.image(RestaurantDoc.Image.builder()
                                .url(b.image().url())
                                .caption(wrap(b.image().caption()))
                                .cover(Boolean.TRUE.equals(b.image().cover()))
                                .sortOrder(b.image().sortOrder() == null ? 0 : b.image().sortOrder())
                                .build());
                    }

                    if (b.gallery() != null) {
                        cb.gallery(mapResImages(b.gallery()));
                    }

                    if (b.mapLon() != null && b.mapLat() != null) {
                        cb.mapLocation(new double[] { b.mapLon(), b.mapLat() });
                    }

                    return cb.build();
                })
                .toList();
    }

    private static LocalTime parseTimeOrNull(String s) {
        if (s == null || s.isBlank()) return null;
        return LocalTime.parse(s.trim());
    }

    private static <E extends Enum<E>> E parseEnum(String s, Class<E> cls, String fieldName) {
        if (s == null || s.isBlank()) return null;
        try {
            return Enum.valueOf(cls, s.trim().toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid " + fieldName + ": " + s);
        }
    }

    private void recomputePriceAggregates(RestaurantDoc doc) {
        BigDecimal max = doc.getMaxPricePerPerson();
        if (max == null) max = doc.getMinPricePerPerson();
        if (max == null) return;

        if (doc.getPriceBucket() == null) {
            RestaurantDoc.PriceBucket bucket;
            if (max.compareTo(new BigDecimal("150000")) < 0)        bucket = RestaurantDoc.PriceBucket.UNDER_150K;
            else if (max.compareTo(new BigDecimal("250000")) < 0)   bucket = RestaurantDoc.PriceBucket.FROM_150K_TO_250K;
            else if (max.compareTo(new BigDecimal("350000")) < 0)   bucket = RestaurantDoc.PriceBucket.FROM_250K_TO_350K;
            else if (max.compareTo(new BigDecimal("500000")) < 0)   bucket = RestaurantDoc.PriceBucket.FROM_350K_TO_500K;
            else                                                    bucket = RestaurantDoc.PriceBucket.ABOVE_500K;
            doc.setPriceBucket(bucket);
        }

        if (doc.getPriceLevel() == null) {
            RestaurantDoc.PriceLevel level;
            if (max.compareTo(new BigDecimal("150000")) < 0)        level = RestaurantDoc.PriceLevel.CHEAP;
            else if (max.compareTo(new BigDecimal("350000")) < 0)   level = RestaurantDoc.PriceLevel.MODERATE;
            else if (max.compareTo(new BigDecimal("500000")) < 0)   level = RestaurantDoc.PriceLevel.EXPENSIVE;
            else                                                    level = RestaurantDoc.PriceLevel.LUXURY;
            doc.setPriceLevel(level);
        }
    }

    private static List<String> normalizeCodes(List<String> codes) {
        if (codes == null)
            return null;
        return codes.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(s -> s.toUpperCase(Locale.ROOT))
                .distinct()
                .toList();
    }

    private void touchPublisher(RestaurantDoc doc) {
        if (doc.getPublisher() == null) {
            doc.setPublisher(RestaurantDoc.PublisherInfo.builder()
                    .partnerId(null)
                    .createdAt(Instant.now())
                    .lastUpdatedAt(Instant.now())
                    .build());
            return;
        }
        doc.getPublisher().setLastUpdatedAt(Instant.now());
    }

    private void assertOwner(RestaurantDoc doc, Long partnerId) {
        String pid = String.valueOf(partnerId);
        if (doc.getPublisher() == null
                || doc.getPublisher().getPartnerId() == null
                || !pid.equals(doc.getPublisher().getPartnerId())) {
            throw new SecurityException("Not owner");
        }
    }

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private String slugify(String input) {
        String nowhitespace = WHITESPACE.matcher(input.trim()).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = slug.replaceAll("-{2,}", "-").replaceAll("^-|-$", "");
        return slug.toLowerCase(Locale.ROOT);
    }

    private void enrichPublisherFromBearerIfPossible(RestaurantDoc.PublisherInfo pub, String bearer) {
        try {
            if (pub == null)
                return;
            if (bearer == null || bearer.isBlank())
                return;

            String pidStr = pub.getPartnerId();
            if (pidStr == null || pidStr.isBlank())
                return;

            Long pid;
            try {
                pid = Long.valueOf(pidStr.trim());
            } catch (NumberFormatException e) {
                return;
            }

            var user = userServiceClient.getUserById(pid, bearer);
            if (user == null)
                return;

            pub.setPartnerName(user.fullname());
            pub.setPartnerEmail(user.email());

        } catch (Exception ignored) {
        }
    }

    private String genUniqueSlugForCreate(String name) {
        String base = slugify(name);
        String slug = base;
        int i = 1;
        while (repo.existsBySlug(slug)) {
            slug = base + "-" + i++;
        }
        return slug;
    }

    private String genUniqueSlugForUpdate(String raw, String currentId) {
        String base = slugify(raw);
        String slug = base;
        int i = 1;
        while (repo.existsBySlugAndIdNot(slug, currentId)) {
            slug = base + "-" + i++;
        }
        return slug;
    }

    // Đưa String (theo locale partner đang nhập) thành Map song ngữ: tự dịch sang locale còn lại.
    private Map<String, String> wrap(String value) {
        return localizedTranslator.resolveFromString(value, null);
    }
}
