package com.mravel.catalog.service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mravel.catalog.dto.place.PlaceAdminDtos.PlaceAdminResponse;
import com.mravel.catalog.dto.place.PlaceAdminDtos.UpsertPlaceRequest;
import com.mravel.catalog.dto.place.PlaceDtos;
import com.mravel.catalog.dto.place.PlaceDtos.*;
import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.repository.PlaceDocRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {

  private final PlaceDocRepository repo;

  public Page<PlaceSummaryDTO> searchPlaces(String q, Pageable pageable) {
    return repo.searchPlaces(q, pageable).map(PlaceMapper::toSummary);
  }

  public PlaceDetailDTO getBySlug(String slug) {
    PlaceDoc p = repo.findBySlug(slug).orElseThrow(() -> new IllegalArgumentException("Place not found"));

    var images = p.getImages() == null ? List.<ImageDTO>of()
        : p.getImages().stream().map(PlaceMapper::toImage).toList();

    var hours = p.getOpenHours() == null ? List.<OpenHourDTO>of()
        : p.getOpenHours().stream().map(PlaceMapper::toOpenHour).toList();

    var categories = p.getCategories() == null ? List.<PlaceDtos.CategoryDTO>of()
        : p.getCategories().stream().map(c -> new PlaceDtos.CategoryDTO(c.getName(), c.getSlug())).toList();

    var tags = p.getTags() == null ? List.<TagDTO>of()
        : p.getTags().stream().map(PlaceMapper::toTag).toList();

    return PlaceMapper.toDetail(p, images, hours, categories, tags);
  }

  public Page<PlaceSummaryDTO> findChildrenByParentSlug(String slug, PlaceKind kind, Pageable pageable) {
    return repo.findChildrenByParentSlug(slug, kind, pageable)
        .map(PlaceMapper::toSummary);
  }

  @Transactional
  public PlaceAdminResponse create(UpsertPlaceRequest req) {
    validateUpsert(req, null);

    String slug = normalizeSlug(req.slug(), req.name());
    if (repo.existsBySlug(slug)) {
      throw new IllegalArgumentException("Slug already exists: " + slug);
    }

    PlaceDoc parent = resolveParentIfNeeded(req.kind(), req.parentSlug());

    PlaceDoc doc = PlaceDoc.builder()
        .active(req.active() == null ? true : req.active())
        .kind(req.kind())
        .venueType(req.kind() == PlaceKind.VENUE ? req.venueType() : null)

        .parentSlug(parent == null ? null : parent.getSlug())
        .ancestors(buildAncestors(parent))
        .childrenCount(0)

        .name(req.name())
        .slug(slug)
        .shortDescription(req.shortDescription())
        .description(req.description())

        .phone(req.phone())
        .email(req.email())
        .website(req.website())

        .addressLine(req.addressLine())
        .countryCode(req.countryCode() == null ? "VN" : req.countryCode())
        .provinceCode(req.provinceCode())
        .districtCode(req.districtCode())
        .wardCode(req.wardCode())
        .provinceName(req.provinceName())
        .districtName(req.districtName())
        .wardName(req.wardName())

        .location(buildLocation(req.longitude(), req.latitude()))

        .priceLevel(req.priceLevel())
        .minPrice(req.minPrice())
        .maxPrice(req.maxPrice())

        .images(mapImages(req.images()))
        .openHours(mapOpenHours(req.openHours()))
        .categories(mapCategories(req.categories()))
        .tags(mapTags(req.tags()))
        .content(mapContent(req.content()))
        .build();

    PlaceDoc saved = repo.save(doc);

    // cập nhật childrenCount cho parent (nếu có)
    if (parent != null) {
      refreshChildrenCount(parent.getSlug());
    }

    return toAdminResponse(saved);
  }

  // ===================== UPDATE =====================
  @Transactional
  public PlaceAdminResponse update(String id, UpsertPlaceRequest req) {
    PlaceDoc existing = repo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Place not found: " + id));

    validateUpsert(req, id);

    String oldParentSlug = existing.getParentSlug();

    // slug: cho phép update nếu bạn muốn (mặc định: cho phép nhưng validate unique)
    String newSlug = normalizeSlug(req.slug(), req.name());
    if (!Objects.equals(existing.getSlug(), newSlug)) {
      if (repo.existsBySlugAndIdNot(newSlug, id)) {
        throw new IllegalArgumentException("Slug already exists: " + newSlug);
      }
      existing.setSlug(newSlug);
    }

    PlaceDoc newParent = resolveParentIfNeeded(req.kind(), req.parentSlug());

    existing.setActive(req.active() == null ? existing.getActive() : req.active());
    existing.setKind(req.kind());
    existing.setVenueType(req.kind() == PlaceKind.VENUE ? req.venueType() : null);

    existing.setParentSlug(newParent == null ? null : newParent.getSlug());
    existing.setAncestors(buildAncestors(newParent));

    existing.setName(req.name());
    existing.setShortDescription(req.shortDescription());
    existing.setDescription(req.description());

    existing.setPhone(req.phone());
    existing.setEmail(req.email());
    existing.setWebsite(req.website());

    existing.setAddressLine(req.addressLine());
    existing.setCountryCode(req.countryCode() == null ? existing.getCountryCode() : req.countryCode());
    existing.setProvinceCode(req.provinceCode());
    existing.setDistrictCode(req.districtCode());
    existing.setWardCode(req.wardCode());
    existing.setProvinceName(req.provinceName());
    existing.setDistrictName(req.districtName());
    existing.setWardName(req.wardName());

    existing.setLocation(buildLocation(req.longitude(), req.latitude()));

    existing.setPriceLevel(req.priceLevel());
    existing.setMinPrice(req.minPrice());
    existing.setMaxPrice(req.maxPrice());

    existing.setImages(mapImages(req.images()));
    existing.setOpenHours(mapOpenHours(req.openHours()));
    existing.setCategories(mapCategories(req.categories()));
    existing.setTags(mapTags(req.tags()));
    existing.setContent(mapContent(req.content()));

    PlaceDoc saved = repo.save(existing);

    // nếu đổi parent => refresh childrenCount cả 2 bên
    if (!Objects.equals(oldParentSlug, saved.getParentSlug())) {
      if (oldParentSlug != null)
        refreshChildrenCount(oldParentSlug);
      if (saved.getParentSlug() != null)
        refreshChildrenCount(saved.getParentSlug());
    }

    return toAdminResponse(saved);
  }

  // ===================== SOFT DELETE =====================
  @Transactional
  public void softDelete(String id) {
    PlaceDoc existing = repo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Place not found: " + id));

    if (Boolean.FALSE.equals(existing.getActive()))
      return;

    existing.setActive(false);
    repo.save(existing);

    if (existing.getParentSlug() != null) {
      refreshChildrenCount(existing.getParentSlug());
    }
  }

  // ===================== HELPERS =====================

  private void validateUpsert(UpsertPlaceRequest req, String idForUpdate) {
    if (req == null)
      throw new IllegalArgumentException("Request is required");
    if (req.kind() == null)
      throw new IllegalArgumentException("kind is required");
    if (req.name() == null || req.name().trim().isEmpty())
      throw new IllegalArgumentException("name is required");

    // kind rules
    if (req.kind() == PlaceKind.DESTINATION) {
      if (req.parentSlug() != null && !req.parentSlug().isBlank())
        throw new IllegalArgumentException("DESTINATION must not have parentSlug");
      if (req.venueType() != null)
        throw new IllegalArgumentException("DESTINATION must not have venueType");
    }

    if (req.kind() == PlaceKind.POI) {
      if (req.parentSlug() == null || req.parentSlug().isBlank())
        throw new IllegalArgumentException("POI requires parentSlug");
      if (req.venueType() != null)
        throw new IllegalArgumentException("POI must not have venueType");
    }

    if (req.kind() == PlaceKind.VENUE) {
      if (req.parentSlug() == null || req.parentSlug().isBlank())
        throw new IllegalArgumentException("VENUE requires parentSlug");
      if (req.venueType() == null)
        throw new IllegalArgumentException("VENUE requires venueType");
    }

    // location: nếu nhập 1 cái mà thiếu cái còn lại => lỗi
    boolean hasLat = req.latitude() != null;
    boolean hasLon = req.longitude() != null;
    if (hasLat ^ hasLon) {
      throw new IllegalArgumentException("Both latitude and longitude must be provided together");
    }
  }

  private PlaceDoc resolveParentIfNeeded(PlaceKind kind, String parentSlug) {
    if (kind == PlaceKind.DESTINATION)
      return null;

    PlaceDoc parent = repo.findBySlug(parentSlug)
        .orElseThrow(() -> new IllegalArgumentException("Parent not found: " + parentSlug));

    // Quy ước theo seed: POI/VENUE thường nằm dưới DESTINATION
    if (parent.getKind() != PlaceKind.DESTINATION) {
      throw new IllegalArgumentException("Parent must be DESTINATION (current: " + parent.getKind() + ")");
    }
    return parent;
  }

  private List<String> buildAncestors(PlaceDoc parent) {
    if (parent == null)
      return List.of();
    List<String> list = new ArrayList<>();
    if (parent.getAncestors() != null)
      list.addAll(parent.getAncestors());
    list.add(parent.getSlug());
    return list;
  }

  private double[] buildLocation(Double lon, Double lat) {
    if (lon == null || lat == null)
      return null;
    return new double[] { lon, lat }; // ✅ [lon, lat]
  }

  private void refreshChildrenCount(String parentSlug) {
    long count = repo.countByParentSlugAndActiveTrue(parentSlug);
    repo.findBySlug(parentSlug).ifPresent(p -> {
      p.setChildrenCount((int) count);
      repo.save(p);
    });
  }

  private PlaceAdminResponse toAdminResponse(PlaceDoc p) {
    return new PlaceAdminResponse(
        p.getId(),
        p.getName(),
        p.getSlug(),
        p.getKind(),
        p.getVenueType(),
        Boolean.TRUE.equals(p.getActive()),
        p.getParentSlug(),
        p.getAncestors(),
        p.getChildrenCount());
  }

  // --- mapping lists to embedded docs ---
  private List<PlaceDoc.Image> mapImages(List<com.mravel.catalog.dto.place.PlaceAdminDtos.ImageReq> imgs) {
    if (imgs == null)
      return List.of();
    return imgs.stream()
        .filter(Objects::nonNull)
        .map(i -> PlaceDoc.Image.builder()
            .url(i.url())
            .caption(i.caption())
            .cover(i.cover() != null && i.cover())
            .sortOrder(i.sortOrder() == null ? 0 : i.sortOrder())
            .build())
        .toList();
  }

  private List<PlaceDoc.OpenHour> mapOpenHours(List<com.mravel.catalog.dto.place.PlaceAdminDtos.OpenHourReq> hours) {
    if (hours == null)
      return List.of();
    return hours.stream()
        .filter(Objects::nonNull)
        .map(h -> PlaceDoc.OpenHour.builder()
            .dayOfWeek(h.dayOfWeek())
            .openTime(h.openTime())
            .closeTime(h.closeTime())
            .open24h(h.open24h() != null && h.open24h())
            .closed(h.closed() != null && h.closed())
            .build())
        .toList();
  }

  private List<PlaceDoc.CategoryMini> mapCategories(
      List<com.mravel.catalog.dto.place.PlaceAdminDtos.CategoryReq> categories) {
    if (categories == null)
      return List.of();
    return categories.stream()
        .filter(Objects::nonNull)
        .map(c -> PlaceDoc.CategoryMini.builder()
            .name(c.name())
            .slug(c.slug())
            .build())
        .toList();
  }

  private List<PlaceDoc.TagMini> mapTags(List<com.mravel.catalog.dto.place.PlaceAdminDtos.TagReq> tags) {
    if (tags == null)
      return List.of();
    return tags.stream()
        .filter(Objects::nonNull)
        .map(t -> PlaceDoc.TagMini.builder()
            .name(t.name())
            .slug(t.slug())
            .type(t.type())
            .build())
        .toList();
  }

  private List<PlaceDoc.ContentBlock> mapContent(
      List<com.mravel.catalog.dto.place.PlaceAdminDtos.ContentBlockReq> content) {
    if (content == null)
      return List.of();
    return content.stream()
        .filter(Objects::nonNull)
        .map(b -> PlaceDoc.ContentBlock.builder()
            .type(b.type())
            .text(b.text())
            .image(b.image() == null ? null
                : PlaceDoc.Image.builder()
                    .url(b.image().url())
                    .caption(b.image().caption())
                    .cover(b.image().cover() != null && b.image().cover())
                    .sortOrder(b.image().sortOrder() == null ? 0 : b.image().sortOrder())
                    .build())
            .gallery(b.gallery() == null ? List.of()
                : b.gallery().stream()
                    .filter(Objects::nonNull)
                    .map(i -> PlaceDoc.Image.builder()
                        .url(i.url())
                        .caption(i.caption())
                        .cover(i.cover() != null && i.cover())
                        .sortOrder(i.sortOrder() == null ? 0 : i.sortOrder())
                        .build())
                    .toList())
            .mapLocation((b.mapLon() != null && b.mapLat() != null) ? new double[] { b.mapLon(), b.mapLat() } : null)
            .build())
        .toList();
  }

  // slug helper
  private String normalizeSlug(String slug, String name) {
    String base = (slug != null && !slug.isBlank()) ? slug : name;
    if (base == null)
      throw new IllegalArgumentException("slug or name is required");

    String s = base.trim().toLowerCase(Locale.ROOT);
    s = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); // bỏ dấu
    s = s.replaceAll("[^a-z0-9\\s-]", "");
    s = s.replaceAll("\\s+", "-");
    s = s.replaceAll("-{2,}", "-");
    s = s.replaceAll("^-|-$", "");
    if (s.isBlank())
      throw new IllegalArgumentException("Invalid slug after normalize");
    return s;
  }
}