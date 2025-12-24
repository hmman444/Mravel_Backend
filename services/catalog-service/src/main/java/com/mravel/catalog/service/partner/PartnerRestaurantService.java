package com.mravel.catalog.service.partner;

import com.mravel.catalog.client.UserServiceClient;
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
    private final UserServiceClient userServiceClient;

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

    // =========================================================
    // CREATE (default list rỗng + default bookingConfig)
    // =========================================================
    public RestaurantDoc create(Long partnerId,
                                PartnerCatalogDtos.PendingReason pendingReason,
                                PartnerCatalogDtos.UpsertRestaurantReq req,
                                String bearer) {
        pendingReason = PartnerCatalogDtos.PendingReason.CREATE;

        RestaurantDoc doc = new RestaurantDoc();
        doc.setActive(true);
        doc.setDeletedAt(null);

        if (req.name() != null) doc.setName(req.name());
        rejectBlankIfPresent(req.name(), "name");
        String slugBase = (req.slug() != null && !req.slug().isBlank()) ? req.slug() : req.name();
        doc.setSlug(genUniqueSlugForCreate(slugBase));

        doc.setDestinationSlug(req.destinationSlug());
        doc.setCityName(req.cityName());
        doc.setDistrictName(req.districtName());
        doc.setWardName(req.wardName());
        doc.setAddressLine(req.addressLine());

        if (req.latitude() != null && req.longitude() != null) {
            doc.setLocation(new double[]{ req.longitude(), req.latitude() });
        } else {
            doc.setLocation(null);
        }

        doc.setMinPricePerPerson(req.minPrice());
        doc.setMaxPricePerPerson(req.maxPrice());
        doc.setCurrencyCode("VND");

        doc.setShortDescription(req.shortDescription());
        doc.setDescription(req.description());
        doc.setWebsite(req.website());
        doc.setPhone(req.phone());
        doc.setEmail(req.email());

        // ✅ default list rỗng
        doc.setImages(req.images() == null ? List.of() : mapResImages(req.images()));

        // ✅ amenityCodes: validate + default list rỗng
        if (req.amenityCodes() != null) {
            amenityCatalogService.validateCodes(AmenityScope.RESTAURANT, req.amenityCodes());
            doc.setAmenityCodes(normalizeCodes(req.amenityCodes()));
        } else {
            doc.setAmenityCodes(List.of());
        }

        // ✅ tableTypes default list rỗng
        doc.setTableTypes(req.tableTypes() == null ? List.of() : mapTableTypes(req.tableTypes()));

        // ✅ bookingConfig luôn có default (kể cả req.bookingConfig null)
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

        return repo.save(doc);
    }

    // =========================================================
    // UPDATE (bookingConfig merge, không set null đè config cũ)
    // =========================================================
    public RestaurantDoc update(String id,
                                Long partnerId,
                                PartnerCatalogDtos.PendingReason pendingReason,
                                PartnerCatalogDtos.UpsertRestaurantReq req,
                                String bearer) {
        pendingReason = PartnerCatalogDtos.PendingReason.UPDATE;

        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        assertOwner(doc, partnerId);

        // name là @NotBlank => luôn có
        if (req.name() != null) doc.setName(req.name());
        rejectBlankIfPresent(req.name(), "name");

        if (req.slug() != null && !req.slug().isBlank()) {
            String nextSlug = slugify(req.slug());
            if (doc.getSlug() == null || !nextSlug.equals(doc.getSlug())) {
                doc.setSlug(genUniqueSlugForUpdate(nextSlug, doc.getId()));
            }
        }

        if (req.destinationSlug() != null) doc.setDestinationSlug(req.destinationSlug());
        if (req.cityName() != null) doc.setCityName(req.cityName());
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

        if (req.amenityCodes() != null) {
            amenityCatalogService.validateCodes(AmenityScope.RESTAURANT, req.amenityCodes());
            doc.setAmenityCodes(normalizeCodes(req.amenityCodes()));
        }

        if (req.tableTypes() != null) {
            doc.setTableTypes(mapTableTypes(req.tableTypes()));
        }

        // ✅ MERGE bookingConfig: chỉ set field nào != null
        if (req.bookingConfig() != null) {
            var c = req.bookingConfig();

            if (doc.getBookingConfig() == null) {
                // nếu doc chưa có config -> set default trước cho an toàn
                doc.setBookingConfig(buildBookingConfigOrDefault(null));
            }

            if (c.slotMinutes() != null) doc.getBookingConfig().setSlotMinutes(c.slotMinutes());
            if (c.allowedDurationsMinutes() != null) doc.getBookingConfig().setAllowedDurationsMinutes(c.allowedDurationsMinutes());
            if (c.defaultDurationMinutes() != null) doc.getBookingConfig().setDefaultDurationMinutes(c.defaultDurationMinutes());
            if (c.minBookingLeadTimeMinutes() != null) doc.getBookingConfig().setMinBookingLeadTimeMinutes(c.minBookingLeadTimeMinutes());
            if (c.graceArrivalMinutes() != null) doc.getBookingConfig().setGraceArrivalMinutes(c.graceArrivalMinutes());
            if (c.freeCancelMinutes() != null) doc.getBookingConfig().setFreeCancelMinutes(c.freeCancelMinutes());
            if (c.pendingPaymentExpireMinutes() != null) doc.getBookingConfig().setPendingPaymentExpireMinutes(c.pendingPaymentExpireMinutes());
            if (c.depositOnly() != null) doc.getBookingConfig().setDepositOnly(c.depositOnly());
            if (c.maxTablesPerBooking() != null) doc.getBookingConfig().setMaxTablesPerBooking(c.maxTablesPerBooking());
        }

        if (doc.getModeration() == null) doc.setModeration(new RestaurantDoc.ModerationInfo());
        doc.getModeration().setStatus(RestaurantDoc.RestaurantStatus.PENDING_REVIEW);
        doc.getModeration().setRejectionReason(null);
        doc.getModeration().setPendingReason(pendingReason.name());
        if (doc.getPublisher() != null) {
            enrichPublisherFromBearerIfPossible(doc.getPublisher(), bearer);
        }

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

    public RestaurantDoc getByIdForPartner(String id, Long partnerId) {
        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        assertOwner(doc, partnerId);
        return doc;
    }

    // =========================================================
    // HELPERS
    // =========================================================

    private RestaurantDoc.BookingConfig buildBookingConfigOrDefault(PartnerCatalogDtos.UpsertRestaurantBookingConfigReq c) {
        return RestaurantDoc.BookingConfig.builder()
                .slotMinutes(c != null && c.slotMinutes() != null ? c.slotMinutes() : 30)
                .allowedDurationsMinutes(c != null && c.allowedDurationsMinutes() != null ? c.allowedDurationsMinutes() : List.of(60, 90, 120))
                .defaultDurationMinutes(c != null && c.defaultDurationMinutes() != null ? c.defaultDurationMinutes() : 90)
                .minBookingLeadTimeMinutes(c != null && c.minBookingLeadTimeMinutes() != null ? c.minBookingLeadTimeMinutes() : 60)
                .graceArrivalMinutes(c != null && c.graceArrivalMinutes() != null ? c.graceArrivalMinutes() : 15)
                .freeCancelMinutes(c != null && c.freeCancelMinutes() != null ? c.freeCancelMinutes() : 30)
                .pendingPaymentExpireMinutes(c != null && c.pendingPaymentExpireMinutes() != null ? c.pendingPaymentExpireMinutes() : 30)
                .depositOnly(c == null || c.depositOnly() == null ? true : c.depositOnly())
                .maxTablesPerBooking(c != null && c.maxTablesPerBooking() != null ? c.maxTablesPerBooking() : 5)
                .build();
    }

    private List<RestaurantDoc.Image> mapResImages(List<PartnerCatalogDtos.ImageReq> images) {
        if (images == null) return null;

        List<RestaurantDoc.Image> list = images.stream()
                .filter(Objects::nonNull)
                .map(i -> RestaurantDoc.Image.builder()
                        .url(i.url())
                        .caption(i.caption())
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

    private void enrichPublisherFromBearerIfPossible(RestaurantDoc.PublisherInfo pub, String bearer) {
        try {
            if (pub == null) return;
            if (bearer == null || bearer.isBlank()) return;

            String pidStr = pub.getPartnerId();
            if (pidStr == null || pidStr.isBlank()) return;

            Long pid;
            try {
                pid = Long.valueOf(pidStr.trim());
            } catch (NumberFormatException e) {
                return;
            }

            var user = userServiceClient.getUserById(pid, bearer);
            if (user == null) return;

            pub.setPartnerName(user.fullname());
            pub.setPartnerEmail(user.email());

        } catch (Exception ignored) {
        }
    }

    // NOTE: giữ lại vì bạn đang có, không dùng cũng không sao
    private String genUniqueSlug(String name) {
        String base = slugify(name);
        String slug = base;
        int i = 1;
        while (repo.existsBySlug(slug)) {
            slug = base + "-" + i++;
        }
        return slug;
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
}