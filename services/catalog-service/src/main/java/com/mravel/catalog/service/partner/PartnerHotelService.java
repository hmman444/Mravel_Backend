package com.mravel.catalog.service.partner;

import com.mravel.catalog.client.UserServiceClient;
import com.mravel.catalog.dto.partner.PartnerCatalogDtos;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.repository.HotelDocRepository;
import com.mravel.catalog.service.AmenityCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.Instant;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PartnerHotelService {

    private final HotelDocRepository repo;
    private final AmenityCatalogService amenityCatalogService;
    private final UserServiceClient userServiceClient;

    // ================= LIST giữ nguyên như bạn đang làm =================

    // ================= CREATE (FULL) =================
    public HotelDoc create(Long partnerId,
                           PartnerCatalogDtos.PendingReason pendingReason,
                           PartnerCatalogDtos.UpsertHotelReq req,
                           String bearer) {

        HotelDoc doc = new HotelDoc();
        doc.setActive(true);
        doc.setDeletedAt(null);
        doc.setCurrencyCode("VND");

        // --- location ---
        doc.setDestinationSlug(req.destinationSlug());
        doc.setCityName(req.cityName());
        doc.setDistrictName(req.districtName());
        doc.setWardName(req.wardName());
        doc.setAddressLine(req.addressLine());
        if (req.longitude() != null && req.latitude() != null) {
            doc.setLocation(new double[]{req.longitude(), req.latitude()});
        } else {
            doc.setLocation(null);
        }

        // --- basic ---
        doc.setName(req.name());
        String slugBase = (req.slug() != null && !req.slug().isBlank()) ? req.slug() : req.name();
        doc.setSlug(genUniqueSlugForCreate(slugBase));

        doc.setHotelType(parseEnum(req.hotelType(), HotelDoc.HotelType.class, "hotelType"));
        doc.setStarRating(req.starRating());
        doc.setShortDescription(req.shortDescription());
        doc.setDescription(req.description());
        doc.setPhone(req.phone());
        doc.setEmail(req.email());
        doc.setWebsite(req.website());

        // --- check-in/out ---
        doc.setDefaultCheckInTime(parseTimeOrNull(req.defaultCheckInTime()));
        doc.setDefaultCheckOutTime(parseTimeOrNull(req.defaultCheckOutTime()));
        doc.setHasOnlineCheckin(req.hasOnlineCheckin() != null && req.hasOnlineCheckin());

        // --- media/content ---
        doc.setImages(req.images() == null ? List.of() : mapImages(req.images()));
        doc.setContent(req.content() == null ? List.of() : mapContentBlocks(req.content()));

        // --- amenities (hotel) ---
        if (req.amenityCodes() != null) {
            amenityCatalogService.validateCodes(AmenityScope.HOTEL, req.amenityCodes());
            doc.setAmenityCodes(normalizeCodes(req.amenityCodes()));
        } else {
            doc.setAmenityCodes(List.of());
        }

        // --- nearby/policy/info/faq ---
        doc.setNearbyPlaces(req.nearbyPlaces() == null ? List.of() : mapNearbyPlaces(req.nearbyPlaces()));
        doc.setPolicy(mapPolicy(req.policy()));
        doc.setGeneralInfo(mapGeneralInfo(req.generalInfo()));
        doc.setFaqs(req.faqs() == null ? List.of() : mapFaqs(req.faqs()));

        // --- taxConfig ---
        doc.setTaxConfig(req.taxConfig() != null
        ? mapTaxConfig(req.taxConfig())
        : HotelDoc.TaxAndFeeConfig.builder()
            .defaultVatPercent(new BigDecimal("8"))
            .defaultServiceFeePercent(new BigDecimal("7"))
            .showPricePreTax(true)
            .build());

        doc.setBookingConfig(req.bookingConfig() != null
                ? mapBookingConfig(req.bookingConfig())
                : HotelDoc.BookingConfig.builder()
                    .allowFullPayment(true)
                    .allowDeposit(true)
                    .depositPercent(null)
                    .freeCancelMinutes(null)
                    .build());

        // --- roomTypes ---
        if (req.roomTypes() != null) {
            // validate ROOM codes
            List<String> roomCodes = req.roomTypes().stream()
                    .filter(Objects::nonNull)
                    .flatMap(rt -> (rt.amenityCodes() == null ? List.<String>of() : rt.amenityCodes()).stream())
                    .toList();
            amenityCatalogService.validateCodes(AmenityScope.ROOM, roomCodes);

            doc.setRoomTypes(mapRoomTypes(req.roomTypes()));
        } else {
            doc.setRoomTypes(List.of());
        }

        // recompute denormalized fields
        recomputeHotelAggregates(doc);

        // --- publisher: enrich from user-service ---
        var user = userServiceClient.getUserById(partnerId, bearer);
        HotelDoc.PublisherInfo pub = HotelDoc.PublisherInfo.builder()
                .partnerId(String.valueOf(partnerId))
                .partnerName(user != null ? user.fullname() : null)
                .partnerEmail(user != null ? user.email() : null)
                .partnerType(HotelDoc.PartnerType.HOTEL_OWNER) // hoặc map theo role
                .createdAt(Instant.now())
                .lastUpdatedAt(Instant.now())
                .build();
        doc.setPublisher(pub);

        String pr = PartnerCatalogDtos.PendingReason.CREATE.name();
        // moderation
        HotelDoc.ModerationInfo mod = HotelDoc.ModerationInfo.builder()
                .status(HotelDoc.HotelStatus.PENDING_REVIEW)
                .pendingReason(pr)
                .build();
        doc.setModeration(mod);

        // currency default nếu null
        if (doc.getCurrencyCode() == null) doc.setCurrencyCode("VND");

        return repo.save(doc);
    }

    public Page<HotelDoc> listMyHotels(Long partnerId, String status, Integer page, Integer size) {
        String pid = String.valueOf(partnerId);

        Pageable pageable = PageRequest.of(
                page == null ? 0 : page,
                size == null ? 20 : size,
                Sort.by(Sort.Direction.DESC, "publisher.lastUpdatedAt")
        );

        if (status == null || status.isBlank()) {
            return repo.findByPublisher_PartnerIdAndDeletedAtIsNull(pid, pageable);
        }

        String st = status.trim().toUpperCase(Locale.ROOT);

        return switch (st) {
            case "PENDING" ->
                    repo.findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_Status(
                            pid, HotelDoc.HotelStatus.PENDING_REVIEW, pageable);

            case "REJECTED" ->
                    repo.findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_Status(
                            pid, HotelDoc.HotelStatus.REJECTED, pageable);

            case "ADMIN_BLOCKED" ->
                    repo.findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_Status(
                            pid, HotelDoc.HotelStatus.BLOCKED, pageable);

            case "ACTIVE" ->
                    repo.findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_StatusAndActive(
                            pid, HotelDoc.HotelStatus.APPROVED, true, pageable);

            case "PARTNER_PAUSED" ->
                    repo.findByPublisher_PartnerIdAndDeletedAtIsNullAndModeration_StatusAndActive(
                            pid, HotelDoc.HotelStatus.APPROVED, false, pageable);

            default -> throw new IllegalArgumentException("Invalid status: " + status);
        };
    }

     public HotelDoc softDelete(String id, Long partnerId) {
        HotelDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        assertOwner(doc, partnerId);

        doc.setDeletedAt(Instant.now());
        doc.setActive(false);
        touchPublisher(doc);

        return repo.save(doc);
    }

    public HotelDoc pause(String id, Long partnerId) {
        HotelDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        assertOwner(doc, partnerId);

        if (doc.getModeration() == null || doc.getModeration().getStatus() != HotelDoc.HotelStatus.APPROVED) {
            throw new IllegalStateException("Only APPROVED hotel can be paused");
        }

        doc.setActive(false);
        touchPublisher(doc);
        return repo.save(doc);
    }

    public HotelDoc resume(String id, Long partnerId) {
        HotelDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        assertOwner(doc, partnerId);

        if (doc.getModeration() == null || doc.getModeration().getStatus() != HotelDoc.HotelStatus.APPROVED) {
            throw new IllegalStateException("Only APPROVED hotel can be resumed");
        }

        doc.setActive(true);
        touchPublisher(doc);
        return repo.save(doc);
    }

    public HotelDoc unlockRequest(String id, Long partnerId, String reason) {
        HotelDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        assertOwner(doc, partnerId);

        if (doc.getModeration() == null || doc.getModeration().getStatus() != HotelDoc.HotelStatus.BLOCKED) {
            throw new IllegalStateException("Only BLOCKED hotel can request unlock");
        }

        doc.getModeration().setUnlockRequestedAt(Instant.now());
        doc.getModeration().setUnlockRequestReason(reason);
        touchPublisher(doc);

        return repo.save(doc);
    }

    public HotelDoc getByIdForPartner(String id, Long partnerId) {
        HotelDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        assertOwner(doc, partnerId);
        return doc;
    }

    // ================= UPDATE (FULL) =================
    public HotelDoc update(String id,
                           Long partnerId,
                           PartnerCatalogDtos.PendingReason pendingReason,
                           PartnerCatalogDtos.UpsertHotelReq req,
                           String bearer) {

        HotelDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        assertOwner(doc, partnerId);

        // --- basic ---
        if (req.name() != null) doc.setName(req.name());

        // slug: chỉ đổi nếu user gửi slug khác slug hiện tại
        if (req.slug() != null && !req.slug().isBlank()) {
            String nextSlug = slugify(req.slug());
            if (doc.getSlug() == null || !nextSlug.equals(doc.getSlug())) {
                doc.setSlug(genUniqueSlugForUpdate(nextSlug, doc.getId()));
            }
        }

        if (req.hotelType() != null && !req.hotelType().isBlank()) {
            doc.setHotelType(parseEnum(req.hotelType(), HotelDoc.HotelType.class, "hotelType"));
        }
        if (req.starRating() != null) doc.setStarRating(req.starRating());

        if (req.shortDescription() != null) doc.setShortDescription(req.shortDescription());
        if (req.description() != null) doc.setDescription(req.description());
        if (req.phone() != null) doc.setPhone(req.phone());
        if (req.email() != null) doc.setEmail(req.email());
        if (req.website() != null) doc.setWebsite(req.website());

        // --- location ---
        if (req.destinationSlug() != null) doc.setDestinationSlug(req.destinationSlug());
        if (req.cityName() != null) doc.setCityName(req.cityName());
        if (req.districtName() != null) doc.setDistrictName(req.districtName());
        if (req.wardName() != null) doc.setWardName(req.wardName());
        if (req.addressLine() != null) doc.setAddressLine(req.addressLine());

        if (req.longitude() != null && req.latitude() != null) {
            doc.setLocation(new double[]{req.longitude(), req.latitude()});
        } else if (req.longitude() == null && req.latitude() == null) {
            // giữ nguyên nếu FE không gửi (tuỳ rule); nếu muốn clear thì FE gửi explicit flag
        }

        // --- check-in/out ---
        if (req.defaultCheckInTime() != null) doc.setDefaultCheckInTime(parseTimeOrNull(req.defaultCheckInTime()));
        if (req.defaultCheckOutTime() != null) doc.setDefaultCheckOutTime(parseTimeOrNull(req.defaultCheckOutTime()));
        if (req.hasOnlineCheckin() != null) doc.setHasOnlineCheckin(req.hasOnlineCheckin());

        // --- media/content ---
        if (req.images() != null) doc.setImages(mapImages(req.images()));
        if (req.content() != null) doc.setContent(mapContentBlocks(req.content()));

        // --- amenities ---
        if (req.amenityCodes() != null) {
            amenityCatalogService.validateCodes(AmenityScope.HOTEL, req.amenityCodes());
            doc.setAmenityCodes(req.amenityCodes() == null ? List.of() : normalizeCodes(req.amenityCodes()));
        }

        // --- nearby/policy/info/faq ---
        if (req.nearbyPlaces() != null) doc.setNearbyPlaces(mapNearbyPlaces(req.nearbyPlaces()));
        if (req.policy() != null) doc.setPolicy(mapPolicy(req.policy()));
        if (req.generalInfo() != null) doc.setGeneralInfo(mapGeneralInfo(req.generalInfo()));
        if (req.faqs() != null) doc.setFaqs(mapFaqs(req.faqs()));

        // --- taxConfig ---
        if (req.taxConfig() != null) doc.setTaxConfig(mapTaxConfig(req.taxConfig()));

        // --- bookingConfig ---
        if (req.bookingConfig() != null) {
            doc.setBookingConfig(mapBookingConfig(req.bookingConfig()));
        }

        // --- roomTypes ---
        if (req.roomTypes() != null) {
            List<String> roomCodes = req.roomTypes().stream()
                    .filter(Objects::nonNull)
                    .flatMap(rt -> (rt.amenityCodes() == null ? List.<String>of() : rt.amenityCodes()).stream())
                    .toList();
            amenityCatalogService.validateCodes(AmenityScope.ROOM, roomCodes);

            doc.setRoomTypes(mapRoomTypes(req.roomTypes()));
        }

        // recompute denormalized
        recomputeHotelAggregates(doc);

        String pr = PartnerCatalogDtos.PendingReason.UPDATE.name();

        // update moderation
        if (doc.getModeration() == null) doc.setModeration(new HotelDoc.ModerationInfo());
        doc.getModeration().setStatus(HotelDoc.HotelStatus.PENDING_REVIEW);
        doc.getModeration().setPendingReason(pr);
        doc.getModeration().setRejectionReason(null);

        // touch publisher + refresh publisher name/email nếu muốn luôn mới
        touchPublisher(doc);
        var user = userServiceClient.getUserById(partnerId, bearer);
        if (doc.getPublisher() != null && user != null) {
            doc.getPublisher().setPartnerName(user.fullname());
            doc.getPublisher().setPartnerEmail(user.email());
        }

        return repo.save(doc);
    }

    // ================= MAPPERS =================

    private static LocalTime parseTimeOrNull(String s) {
        if (s == null || s.isBlank()) return null;
        return LocalTime.parse(s.trim());
    }

    private List<HotelDoc.Image> mapImages(List<PartnerCatalogDtos.ImageReq> images) {
        if (images == null) return null;
        List<HotelDoc.Image> list = images.stream()
                .filter(Objects::nonNull)
                .map((i) -> HotelDoc.Image.builder()
                        .url(i.url())
                        .caption(i.caption())
                        .cover(Boolean.TRUE.equals(i.cover()))
                        .sortOrder(i.sortOrder() == null ? 0 : i.sortOrder())
                        .build())
                .toList();

        // ensure cover
        if (!list.isEmpty() && list.stream().noneMatch(im -> Boolean.TRUE.equals(im.getCover()))) {
            list.get(0).setCover(true);
        }
        return list;
    }

    private List<HotelDoc.ContentBlock> mapContentBlocks(List<PartnerCatalogDtos.UpsertContentBlockReq> blocks) {
        if (blocks == null) return null;

        return blocks.stream()
                .filter(Objects::nonNull)
                .map(b -> {
                    HotelDoc.ContentBlock.ContentBlockBuilder cb = HotelDoc.ContentBlock.builder()
                            .type(parseEnum(b.type(), HotelDoc.ContentBlock.BlockType.class))
                            .section(parseEnum(b.section(), HotelDoc.ContentBlock.ContentSection.class))
                            .text(b.text());

                    // image
                    if (b.image() != null) {
                        cb.image(HotelDoc.Image.builder()
                                .url(b.image().url())
                                .caption(b.image().caption())
                                .cover(Boolean.TRUE.equals(b.image().cover()))
                                .sortOrder(b.image().sortOrder() == null ? 0 : b.image().sortOrder())
                                .build());
                    }

                    // gallery
                    if (b.gallery() != null) {
                        cb.gallery(mapImages(b.gallery()));
                    }

                    // map
                    if (b.mapLon() != null && b.mapLat() != null) {
                        cb.mapLocation(new double[]{b.mapLon(), b.mapLat()});
                    }

                    return cb.build();
                })
                .toList();
    }

    private List<HotelDoc.NearbyPlace> mapNearbyPlaces(List<PartnerCatalogDtos.UpsertNearbyPlaceReq> nearby) {
        if (nearby == null) return null;
        return nearby.stream()
                .filter(Objects::nonNull)
                .map(n -> HotelDoc.NearbyPlace.builder()
                        .placeSlug(n.placeSlug())
                        .name(n.name())
                        .category(n.category())
                        .distanceMeters(n.distanceMeters())
                        .build())
                .toList();
    }

    private HotelDoc.HotelPolicy mapPolicy(PartnerCatalogDtos.UpsertHotelPolicyReq p) {
        if (p == null) return null;

        List<HotelDoc.PolicyItem> items = null;
        if (p.items() != null) {
            items = p.items().stream()
                    .filter(Objects::nonNull)
                    .map(it -> HotelDoc.PolicyItem.builder()
                            .section(parseEnum(it.section(), HotelDoc.PolicySection.class))
                            .title(it.title())
                            .content(it.content())
                            .build())
                    .toList();
        }

        return HotelDoc.HotelPolicy.builder()
                .checkInFrom(parseTimeOrNull(p.checkInFrom()))
                .checkOutUntil(parseTimeOrNull(p.checkOutUntil()))
                .items(items)
                .build();
    }

    private HotelDoc.GeneralInfo mapGeneralInfo(PartnerCatalogDtos.UpsertGeneralInfoReq gi) {
        if (gi == null) return null;
        return HotelDoc.GeneralInfo.builder()
                .mainFacilitiesSummary(gi.mainFacilitiesSummary())
                .distanceToCityCenterKm(gi.distanceToCityCenterKm())
                .popularAreaSummary(gi.popularAreaSummary())
                .totalRooms(gi.totalRooms())
                .totalFloors(gi.totalFloors())
                .otherHighlightFacilities(gi.otherHighlightFacilities())
                .interestingPlacesSummary(gi.interestingPlacesSummary())
                .build();
    }

    private List<HotelDoc.FaqItem> mapFaqs(List<PartnerCatalogDtos.UpsertFaqReq> faqs) {
        if (faqs == null) return null;
        return faqs.stream()
                .filter(Objects::nonNull)
                .map(f -> HotelDoc.FaqItem.builder()
                        .question(f.question())
                        .answer(f.answer())
                        .build())
                .toList();
    }

    private HotelDoc.TaxAndFeeConfig mapTaxConfig(PartnerCatalogDtos.UpsertTaxConfigReq tc) {
        if (tc == null) return null;
        return HotelDoc.TaxAndFeeConfig.builder()
                .defaultVatPercent(tc.defaultVatPercent())
                .defaultServiceFeePercent(tc.defaultServiceFeePercent())
                .showPricePreTax(tc.showPricePreTax() == null ? true : tc.showPricePreTax())
                .build();
    }

    private HotelDoc.BookingConfig mapBookingConfig(PartnerCatalogDtos.UpsertBookingConfigReq bc) {
        if (bc == null) return null;
        return HotelDoc.BookingConfig.builder()
                .allowFullPayment(bc.allowFullPayment() == null ? true : bc.allowFullPayment())
                .allowDeposit(bc.allowDeposit() == null ? true : bc.allowDeposit())
                .depositPercent(bc.depositPercent())
                .freeCancelMinutes(bc.freeCancelMinutes())
                .build();
    }

    private List<HotelDoc.RoomType> mapRoomTypes(List<PartnerCatalogDtos.UpsertRoomTypeReq> roomTypes) {
        return roomTypes.stream()
                .filter(Objects::nonNull)
                .map(rt -> {
                    String rtId = (rt.id() == null || rt.id().isBlank())
                            ? UUID.randomUUID().toString()
                            : rt.id().trim();

                    List<HotelDoc.BedOption> bedOptions = null;
                    if (rt.bedOptions() != null) {
                        bedOptions = rt.bedOptions().stream()
                                .filter(Objects::nonNull)
                                .map(b -> HotelDoc.BedOption.builder()
                                        .type(parseEnum(b.type(), HotelDoc.BedType.class))
                                        .count(b.count())
                                        .build())
                                .toList();
                    }

                    List<HotelDoc.RatePlan> rps = (rt.ratePlans() == null) ? List.of()
                            : rt.ratePlans().stream()
                            .filter(Objects::nonNull)
                            .map(this::mapRatePlan)
                            .toList();

                    return HotelDoc.RoomType.builder()
                            .id(rtId)
                            .name(rt.name())
                            .shortDescription(rt.shortDescription())
                            .description(rt.description())
                            .areaSqm(rt.areaSqm())
                            .bedType(parseEnum(rt.bedType(), HotelDoc.BedType.class))
                            .bedsCount(rt.bedsCount())
                            .bedLayoutDescription(rt.bedLayoutDescription())
                            .bedOptions(bedOptions)

                            .maxAdults(rt.maxAdults())
                            .maxChildren(rt.maxChildren())
                            .maxGuests(rt.maxGuests())
                            .totalRooms(rt.totalRooms())

                            .images(mapImages(rt.images()))
                            .amenityCodes(normalizeCodes(rt.amenityCodes()))
                            .ratePlans(rps)
                            .build();
                })
                .toList();
    }

    private HotelDoc.RatePlan mapRatePlan(PartnerCatalogDtos.UpsertRatePlanReq rp) {
        String rpId = (rp.id() == null || rp.id().isBlank())
                ? UUID.randomUUID().toString()
                : rp.id().trim();

        List<HotelDoc.LengthOfStayDiscount> los = null;
        if (rp.lengthOfStayDiscounts() != null) {
            los = rp.lengthOfStayDiscounts().stream()
                    .filter(Objects::nonNull)
                    .map(d -> HotelDoc.LengthOfStayDiscount.builder()
                            .minNights(d.minNights())
                            .maxNights(d.maxNights())
                            .discountPercent(d.discountPercent())
                            .build())
                    .toList();
        }

        return HotelDoc.RatePlan.builder()
                .id(rpId)
                .name(rp.name())

                .boardType(parseEnum(rp.boardType(), HotelDoc.BoardType.class))
                .paymentType(parseEnum(rp.paymentType(), HotelDoc.PaymentType.class))
                .refundable(rp.refundable())
                .cancellationPolicy(rp.cancellationPolicy())

                .pricePerNight(rp.pricePerNight())
                .referencePricePerNight(rp.referencePricePerNight())
                .discountPercent(rp.discountPercent())

                .taxPercent(rp.taxPercent())
                .serviceFeePercent(rp.serviceFeePercent())
                .priceIncludesTax(Boolean.TRUE.equals(rp.priceIncludesTax()))
                .priceIncludesServiceFee(Boolean.TRUE.equals(rp.priceIncludesServiceFee()))

                .lengthOfStayDiscounts(los)

                .promoLabel(rp.promoLabel())
                .showLowAvailability(rp.showLowAvailability())
                .build();
    }

    // ===== recompute denormalized (minNightlyPrice/referenceNightlyPrice/filterFacets) =====
    private void recomputeHotelAggregates(HotelDoc doc) {
        BigDecimal min = null;
        BigDecimal refMin = null;

        boolean hasFreeCancel = false;
        boolean hasPayAtHotel = false;
        boolean hasBreakfast = false;

        if (doc.getRoomTypes() != null) {
            for (var rt : doc.getRoomTypes()) {
                if (rt == null || rt.getRatePlans() == null) continue;
                for (var rp : rt.getRatePlans()) {
                    if (rp == null) continue;

                    BigDecimal p = rp.getPricePerNight();
                    if (p != null) min = (min == null) ? p : min.min(p);

                    BigDecimal r = rp.getReferencePricePerNight();
                    if (r != null) refMin = (refMin == null) ? r : refMin.min(r);

                    if (Boolean.TRUE.equals(rp.getRefundable())) hasFreeCancel = true;
                    if (rp.getPaymentType() == HotelDoc.PaymentType.PAY_AT_HOTEL) hasPayAtHotel = true;
                    if (rp.getBoardType() != null && rp.getBoardType() != HotelDoc.BoardType.ROOM_ONLY) hasBreakfast = true;
                }
            }
        }

        if (min != null) doc.setMinNightlyPrice(min);
        if (refMin != null) doc.setReferenceNightlyPrice(refMin);

        doc.setFilterFacets(HotelDoc.HotelFilterFacets.builder()
                .hasFreeCancellation(hasFreeCancel)
                .hasPayAtHotel(hasPayAtHotel)
                .hasBreakfastIncluded(hasBreakfast)
                .build());
    }

    // ================= UTIL =================
    private static List<String> normalizeCodes(List<String> codes) {
        if (codes == null) return null;
        return codes.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(s -> s.toUpperCase(Locale.ROOT))
                .distinct()
                .toList();
    }

    private static <E extends Enum<E>> E parseEnum(String s, Class<E> cls) {
        return parseEnum(s, cls, cls.getSimpleName());
    }

    private static <E extends Enum<E>> E parseEnum(String s, Class<E> cls, String fieldName) {
        if (s == null || s.isBlank()) return null;
        try {
            return Enum.valueOf(cls, s.trim().toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid " + fieldName + ": " + s);
        }
    }

    private void touchPublisher(HotelDoc doc) {
        if (doc.getPublisher() == null) {
            doc.setPublisher(HotelDoc.PublisherInfo.builder()
                    .partnerId(null)
                    .createdAt(Instant.now())
                    .lastUpdatedAt(Instant.now())
                    .build());
            return;
        }
        doc.getPublisher().setLastUpdatedAt(Instant.now());
    }

    private void assertOwner(HotelDoc doc, Long partnerId) {
        String pid = String.valueOf(partnerId);
        if (doc.getPublisher() == null
                || doc.getPublisher().getPartnerId() == null
                || !pid.equals(doc.getPublisher().getPartnerId())) {
            throw new SecurityException("Not owner");
        }
    }

    // ===== slug utils =====
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private String slugify(String input) {
        String nowhitespace = WHITESPACE.matcher(input.trim()).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = slug.replaceAll("-{2,}", "-").replaceAll("^-|-$", "");
        return slug.toLowerCase(Locale.ROOT);
    }

    private String genUniqueSlugForCreate(String raw) {
        String base = slugify(raw);
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

    private static void requireNotBlank(String v, String field) {
        if (v == null || v.isBlank()) throw new IllegalArgumentException(field + " is required");
    }
    private static void rejectBlankIfPresent(String v, String field) {
        if (v != null && v.isBlank()) throw new IllegalArgumentException(field + " must not be blank");
    }

    private void validateForCreate(PartnerCatalogDtos.UpsertHotelReq req) {
        requireNotBlank(req.destinationSlug(), "destinationSlug");
        requireNotBlank(req.name(), "name");
        if (req.latitude() == null || req.longitude() == null)
            throw new IllegalArgumentException("latitude/longitude is required");
    }

    private void validateForUpdate(PartnerCatalogDtos.UpsertHotelReq req) {
        rejectBlankIfPresent(req.destinationSlug(), "destinationSlug");
        rejectBlankIfPresent(req.name(), "name");
        rejectBlankIfPresent(req.slug(), "slug");
    }
}