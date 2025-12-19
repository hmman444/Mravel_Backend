package com.mravel.catalog.service.partner;

import com.mravel.catalog.dto.partner.PartnerCatalogDtos;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.repository.RestaurantDocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.Instant;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PartnerRestaurantService {

    private final RestaurantDocRepository repo;

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
        doc.setSlug(genUniqueSlug(req.name()));
        doc.setDestinationSlug(req.destinationSlug());
        doc.setCityName(req.cityName());
        doc.setDistrictName(req.districtName());
        doc.setWardName(req.wardName());
        doc.setAddressLine(req.addressLine());

        if (req.lng() != null && req.lat() != null) {
            doc.setLocation(new double[]{req.lng(), req.lat()}); // [lon, lat]
        }

        doc.setPhone(req.phone());
        doc.setEmail(req.email());

        if (req.thumbnailUrl() != null && !req.thumbnailUrl().isBlank()) {
            doc.setImages(java.util.List.of(
                    RestaurantDoc.Image.builder()
                            .url(req.thumbnailUrl())
                            .cover(true)
                            .sortOrder(0)
                            .build()
            ));
        }

        RestaurantDoc.PublisherInfo pub = RestaurantDoc.PublisherInfo.builder()
                .partnerId(String.valueOf(partnerId))
                .createdAt(Instant.now())
                .lastUpdatedAt(Instant.now())
                .build();
        doc.setPublisher(pub);

        RestaurantDoc.ModerationInfo mod = RestaurantDoc.ModerationInfo.builder()
                .status(RestaurantDoc.RestaurantStatus.PENDING_REVIEW)
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
        if (req.destinationSlug() != null) doc.setDestinationSlug(req.destinationSlug());
        if (req.cityName() != null) doc.setCityName(req.cityName());
        if (req.districtName() != null) doc.setDistrictName(req.districtName());
        if (req.wardName() != null) doc.setWardName(req.wardName());
        if (req.addressLine() != null) doc.setAddressLine(req.addressLine());

        if (req.lng() != null && req.lat() != null) {
            doc.setLocation(new double[]{req.lng(), req.lat()});
        }

        if (req.phone() != null) doc.setPhone(req.phone());
        if (req.email() != null) doc.setEmail(req.email());

        if (doc.getModeration() == null) doc.setModeration(new RestaurantDoc.ModerationInfo());
        doc.getModeration().setStatus(RestaurantDoc.RestaurantStatus.PENDING_REVIEW);
        doc.getModeration().setRejectionReason(null);

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