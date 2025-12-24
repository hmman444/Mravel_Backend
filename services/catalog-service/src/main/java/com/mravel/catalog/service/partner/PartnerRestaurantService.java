package com.mravel.catalog.service.partner;

import com.mravel.catalog.dto.partner.PartnerCatalogDtos;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.model.enums.AmenityScope;
import com.mravel.catalog.repository.RestaurantDocRepository;
import com.mravel.catalog.service.AmenityCatalogService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PartnerRestaurantService {

    private final RestaurantDocRepository repo;
    private final AmenityCatalogService amenityCatalogService;

    public Page<RestaurantDoc> listMyRestaurants(Long partnerId, String status, Integer page, Integer size) {
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
                                PartnerCatalogDtos.UpsertRestaurantReq req) {

        RestaurantDoc doc = new RestaurantDoc();
        doc.setActive(true);
        doc.setDeletedAt(null);

        doc.setName(req.name());
        String slugBase = (req.slug()!=null && !req.slug().isBlank()) ? req.slug() : req.name();
        doc.setSlug(genUniqueSlug(slugBase));

        doc.setDestinationSlug(req.destinationSlug());
        doc.setCityName(req.provinceName());
        doc.setDistrictName(req.districtName());
        doc.setWardName(req.wardName());
        doc.setAddressLine(req.addressLine());

        if (req.latitude() != null && req.longitude() != null) {
            doc.setLocation(new double[]{ req.longitude(), req.latitude() });
        }

        doc.setMinPricePerPerson(req.minPrice());
        doc.setMaxPricePerPerson(req.maxPrice());
        doc.setCurrencyCode("VND");

        doc.setShortDescription(req.shortDescription());
        doc.setDescription(req.description());
        doc.setWebsite(req.website());
        doc.setPhone(req.phone());
        doc.setEmail(req.email());

        if (req.images() != null) {
            doc.setImages(mapResImages(req.images()));
        }

        // ✅ NEW: restaurant amenityCodes
        if (req.amenityCodes() != null) {
            amenityCatalogService.validateCodes(AmenityScope.RESTAURANT, req.amenityCodes());
            doc.setAmenityCodes(normalizeCodes(req.amenityCodes()));
        }

        // ✅ NEW: tableTypes
        if (req.tableTypes() != null) {
            doc.setTableTypes(mapTableTypes(req.tableTypes()));
        }

        // ✅ NEW: bookingConfig
        if (req.bookingConfig() != null) {
            var c = req.bookingConfig();
            doc.setBookingConfig(RestaurantDoc.BookingConfig.builder()
                    .slotMinutes(c.slotMinutes() == null ? 30 : c.slotMinutes())
                    .allowedDurationsMinutes(c.allowedDurationsMinutes() == null ? List.of(60,90,120) : c.allowedDurationsMinutes())
                    .defaultDurationMinutes(c.defaultDurationMinutes() == null ? 90 : c.defaultDurationMinutes())
                    .minBookingLeadTimeMinutes(c.minBookingLeadTimeMinutes() == null ? 60 : c.minBookingLeadTimeMinutes())
                    .graceArrivalMinutes(c.graceArrivalMinutes() == null ? 15 : c.graceArrivalMinutes())
                    .freeCancelMinutes(c.freeCancelMinutes() == null ? 30 : c.freeCancelMinutes())
                    .pendingPaymentExpireMinutes(c.pendingPaymentExpireMinutes() == null ? 30 : c.pendingPaymentExpireMinutes())
                    .depositOnly(c.depositOnly() == null ? true : c.depositOnly())
                    .maxTablesPerBooking(c.maxTablesPerBooking() == null ? 5 : c.maxTablesPerBooking())
                    .build());
        }

        RestaurantDoc.PublisherInfo pub = RestaurantDoc.PublisherInfo.builder()
                .partnerId(String.valueOf(partnerId))
                .createdAt(Instant.now())
                .lastUpdatedAt(Instant.now())
                .build();
        doc.setPublisher(pub);

        RestaurantDoc.ModerationInfo mod = RestaurantDoc.ModerationInfo.builder()
                .status(RestaurantDoc.RestaurantStatus.PENDING_REVIEW)
                .pendingReason(pendingReason.name())
                .build();
        doc.setModeration(mod);

        return repo.save(doc);
    }

    public RestaurantDoc update(String id,
                                Long partnerId,
                                PartnerCatalogDtos.PendingReason pendingReason,
                                PartnerCatalogDtos.UpsertRestaurantReq req) {

        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        assertOwner(doc, partnerId);

        doc.setName(req.name());

        if (req.slug() != null && !req.slug().isBlank()) {
            doc.setSlug(genUniqueSlug(req.slug()));
        }

        if (req.destinationSlug() != null) doc.setDestinationSlug(req.destinationSlug());
        if (req.provinceName() != null) doc.setCityName(req.provinceName());
        if (req.districtName() != null) doc.setDistrictName(req.districtName());
        if (req.wardName() != null) doc.setWardName(req.wardName());
        if (req.addressLine() != null) doc.setAddressLine(req.addressLine());

        if (req.latitude() != null && req.longitude() != null) {
            doc.setLocation(new double[]{ req.longitude(), req.latitude() });
        }

        if (req.shortDescription() != null) doc.setShortDescription(req.shortDescription());
        if (req.description() != null) doc.setDescription(req.description());
        if (req.website() != null) doc.setWebsite(req.website());

        if (req.phone() != null) doc.setPhone(req.phone());
        if (req.email() != null) doc.setEmail(req.email());

        if (req.minPrice() != null) doc.setMinPricePerPerson(req.minPrice());
        if (req.maxPrice() != null) doc.setMaxPricePerPerson(req.maxPrice());

        if (req.images() != null) {
            doc.setImages(mapResImages(req.images()));
        }

        // ✅ NEW
        if (req.amenityCodes() != null) {
            amenityCatalogService.validateCodes(AmenityScope.RESTAURANT, req.amenityCodes());
            doc.setAmenityCodes(normalizeCodes(req.amenityCodes()));
        }

        if (req.tableTypes() != null) {
            doc.setTableTypes(mapTableTypes(req.tableTypes()));
        }

        if (req.bookingConfig() != null) {
            var c = req.bookingConfig();
            if (doc.getBookingConfig() == null) doc.setBookingConfig(new RestaurantDoc.BookingConfig());
            doc.getBookingConfig().setSlotMinutes(c.slotMinutes());
            doc.getBookingConfig().setAllowedDurationsMinutes(c.allowedDurationsMinutes());
            doc.getBookingConfig().setDefaultDurationMinutes(c.defaultDurationMinutes());
            doc.getBookingConfig().setMinBookingLeadTimeMinutes(c.minBookingLeadTimeMinutes());
            doc.getBookingConfig().setGraceArrivalMinutes(c.graceArrivalMinutes());
            doc.getBookingConfig().setFreeCancelMinutes(c.freeCancelMinutes());
            doc.getBookingConfig().setPendingPaymentExpireMinutes(c.pendingPaymentExpireMinutes());
            doc.getBookingConfig().setDepositOnly(c.depositOnly());
            doc.getBookingConfig().setMaxTablesPerBooking(c.maxTablesPerBooking());
        }

        if (doc.getModeration() == null) doc.setModeration(new RestaurantDoc.ModerationInfo());
        doc.getModeration().setStatus(RestaurantDoc.RestaurantStatus.PENDING_REVIEW);
        doc.getModeration().setRejectionReason(null);
        doc.getModeration().setPendingReason(pendingReason.name());

        touchPublisher(doc);
        return repo.save(doc);
    }

    public RestaurantDoc softDelete(String id, Long partnerId) {
        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        assertOwner(doc, partnerId);

        doc.setDeletedAt(Instant.now());
        doc.setActive(false);
        touchPublisher(doc);

        return repo.save(doc);
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

        return repo.save(doc);
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

        return repo.save(doc);
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

        return repo.save(doc);
    }

    private List<RestaurantDoc.Image> mapResImages(List<PartnerCatalogDtos.ImageReq> images) {
        return images.stream()
                .map(i -> RestaurantDoc.Image.builder()
                        .url(i.url())
                        .caption(i.caption())
                        .cover(Boolean.TRUE.equals(i.cover()))
                        .sortOrder(i.sortOrder() == null ? 0 : i.sortOrder())
                        .build())
                .toList();
    }

    private List<RestaurantDoc.TableType> mapTableTypes(List<PartnerCatalogDtos.UpsertTableTypeReq> tableTypes) {
        return tableTypes.stream()
                .filter(Objects::nonNull)
                .map(t -> RestaurantDoc.TableType.builder()
                        .id((t.id() == null || t.id().isBlank()) ? UUID.randomUUID().toString() : t.id())
                        .name(t.name())
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
                        .note(t.note())
                        .build())
                .toList();
    }

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

    // slug utils (tối giản)
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private String slugify(String input) {
        String nowhitespace = WHITESPACE.matcher(input.trim()).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = slug.replaceAll("-{2,}", "-").replaceAll("^-|-$", "");
        return slug.toLowerCase(Locale.ROOT);
    }

    private String genUniqueSlug(String name) {
        String base = slugify(name);
        String slug = base;
        int i = 1;
        while (repo.existsBySlug(slug)) {
            slug = base + "-" + i++;
        }
        return slug;
    }
}