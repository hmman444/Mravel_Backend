package com.mravel.catalog.service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mravel.catalog.dto.place.PlaceAdminDtos.PlaceAdminResponse;
import com.mravel.catalog.dto.place.PlaceAdminDtos.UpsertPlaceRequest;
import com.mravel.catalog.dto.place.PlaceDtos.CategoryDTO;
import com.mravel.catalog.dto.place.PlaceDtos.ImageDTO;
import com.mravel.catalog.dto.place.PlaceDtos.OpenHourDTO;
import com.mravel.catalog.dto.place.PlaceDtos.PlaceDetailDTO;
import com.mravel.catalog.dto.place.PlaceDtos.PlaceSummaryDTO;
import com.mravel.catalog.dto.place.PlaceDtos.TagDTO;
import com.mravel.catalog.dto.search.FacetedPlaceSearchRequest;
import com.mravel.catalog.dto.search.FacetedSearchResponse;
import com.mravel.catalog.dto.search.PlaceFacets;
import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.repository.PlaceDocRepository;
import com.mravel.catalog.search.PlaceSearchService;
import com.mravel.catalog.search.es.IndexingService;
import com.mravel.catalog.translation.LocalizedTranslator;
import com.mravel.common.i18n.LocaleConstants;
import com.mravel.common.i18n.LocaleContext;
import com.mravel.common.i18n.LocaleUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {

  private final PlaceDocRepository repo;
  private final PlaceSearchService placeSearchService;
  private final IndexingService indexingService;
  private final LocalizedTranslator localizedTranslator;

  public Page<PlaceSummaryDTO> searchPlaces(String q, PlaceKind kind, Pageable pageable) {
    return placeSearchService.search(q, kind, pageable).map(PlaceMapper::toSummary);
  }

  public FacetedSearchResponse<PlaceSummaryDTO, PlaceFacets> searchPlacesFaceted(
      FacetedPlaceSearchRequest request, Pageable pageable) {
    return placeSearchService.searchFaceted(request, pageable);
  }

  public PlaceDetailDTO getBySlug(String slug) {
    PlaceDoc p = repo.findBySlug(slug).orElseThrow(() -> new IllegalArgumentException("Place not found"));

    var images = p.getImages() == null ? List.<ImageDTO>of()
        : p.getImages().stream().map(PlaceMapper::toImage).toList();

    var hours = p.getOpenHours() == null ? List.<OpenHourDTO>of()
        : p.getOpenHours().stream().map(PlaceMapper::toOpenHour).toList();

    var categories = p.getCategories() == null ? List.<CategoryDTO>of()
        : p.getCategories().stream().map(PlaceMapper::toCategory).toList();

    var tags = p.getTags() == null ? List.<TagDTO>of()
        : p.getTags().stream().map(PlaceMapper::toTag).toList();

    return PlaceMapper.toDetail(p, images, hours, categories, tags);
  }

  public Page<PlaceSummaryDTO> findChildrenByParentSlug(String slug, PlaceKind kind, Pageable pageable) {
    return repo.findChildrenByParentSlug(slug, kind, pageable)
        .map(PlaceMapper::toSummary);
  }

  public Page<PlaceSummaryDTO> findChildrenByParentSlugAll(String slug, PlaceKind kind, Pageable pageable) {
    return repo.findChildrenByParentSlugIncludeInactive(slug, kind, pageable)
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

        .name(localizedTranslator.resolveCreate(req.name()))
        .slug(slug)
        .shortDescription(localizedTranslator.resolveCreate(req.shortDescription()))
        .description(localizedTranslator.resolveCreate(req.description()))

        .phone(req.phone())
        .email(req.email())
        .website(req.website())

        .addressLine(localizedTranslator.resolveCreate(req.addressLine()))
        .countryCode(req.countryCode() == null ? "VN" : req.countryCode())
        .provinceCode(req.provinceCode())
        .districtCode(req.districtCode())
        .wardCode(req.wardCode())
        .provinceName(localizedTranslator.resolveCreate(req.provinceName()))
        .districtName(localizedTranslator.resolveCreate(req.districtName()))
        .wardName(localizedTranslator.resolveCreate(req.wardName()))

        .location(buildLocation(req.longitude(), req.latitude()))

        .priceLevel(req.priceLevel())
        .minPrice(req.minPrice())
        .maxPrice(req.maxPrice())

        .images(mapImages(req.images(), null))
        .openHours(mapOpenHours(req.openHours()))
        .categories(mapCategories(req.categories(), null))
        .tags(mapTags(req.tags(), null))
        .content(mapContent(req.content(), null))
        .build();

    PlaceDoc saved = repo.save(doc);

    if (parent != null) {
      refreshChildrenCount(parent.getSlug());
    }

    indexingService.syncPlace(saved);
    return toAdminResponse(saved);
  }

  @Transactional
  public PlaceAdminResponse update(String id, UpsertPlaceRequest req) {
    PlaceDoc existing = repo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Place not found: " + id));

    validateUpsert(req, id);

    String oldParentSlug = existing.getParentSlug();

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

    existing.setName(localizedTranslator.resolve(req.name(), existing.getName()));
    existing.setShortDescription(localizedTranslator.resolve(req.shortDescription(), existing.getShortDescription()));
    existing.setDescription(localizedTranslator.resolve(req.description(), existing.getDescription()));

    existing.setPhone(req.phone());
    existing.setEmail(req.email());
    existing.setWebsite(req.website());

    existing.setAddressLine(localizedTranslator.resolve(req.addressLine(), existing.getAddressLine()));
    existing.setCountryCode(req.countryCode() == null ? existing.getCountryCode() : req.countryCode());
    existing.setProvinceCode(req.provinceCode());
    existing.setDistrictCode(req.districtCode());
    existing.setWardCode(req.wardCode());
    existing.setProvinceName(localizedTranslator.resolve(req.provinceName(), existing.getProvinceName()));
    existing.setDistrictName(localizedTranslator.resolve(req.districtName(), existing.getDistrictName()));
    existing.setWardName(localizedTranslator.resolve(req.wardName(), existing.getWardName()));

    existing.setLocation(buildLocation(req.longitude(), req.latitude()));

    existing.setPriceLevel(req.priceLevel());
    existing.setMinPrice(req.minPrice());
    existing.setMaxPrice(req.maxPrice());

    existing.setImages(mapImages(req.images(), existing.getImages()));
    existing.setOpenHours(mapOpenHours(req.openHours()));
    existing.setCategories(mapCategories(req.categories(), existing.getCategories()));
    existing.setTags(mapTags(req.tags(), existing.getTags()));
    existing.setContent(mapContent(req.content(), existing.getContent()));

    PlaceDoc saved = repo.save(existing);

    if (!Objects.equals(oldParentSlug, saved.getParentSlug())) {
      if (oldParentSlug != null)
        refreshChildrenCount(oldParentSlug);
      if (saved.getParentSlug() != null)
        refreshChildrenCount(saved.getParentSlug());
    }

    indexingService.syncPlace(saved);
    return toAdminResponse(saved);
  }

  @Transactional
  public void lock(String id) {
    PlaceDoc existing = repo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Place not found: " + id));

    if (Boolean.FALSE.equals(existing.getActive()))
      return;

    existing.setActive(false);
    PlaceDoc saved = repo.save(existing);
    indexingService.syncPlace(saved);

    if (existing.getParentSlug() != null) {
      refreshChildrenCount(existing.getParentSlug());
    }
  }

  public Page<PlaceAdminResponse> findAllByKind(PlaceKind kind, Pageable pageable) {
    return repo.findAllByKind(kind, pageable).map(this::toAdminResponse);
  }

  @Transactional
  public void unlock(String id) {
    PlaceDoc existing = repo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Place not found: " + id));

    if (Boolean.TRUE.equals(existing.getActive()))
      return;

    existing.setActive(true);
    PlaceDoc saved = repo.save(existing);
    indexingService.syncPlace(saved);

    if (existing.getParentSlug() != null) {
      refreshChildrenCount(existing.getParentSlug());
    }
  }

  @Transactional
  public void hardDelete(String id) {
    PlaceDoc existing = repo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy địa điểm: " + id));

    long children = repo.countByParentSlug(existing.getSlug());
    if (children > 0) {
      throw new IllegalArgumentException("Không thể xóa, còn địa điểm con trong điểm đến: " + children);
    }

    String parentSlug = existing.getParentSlug();

    repo.delete(existing);
    indexingService.deletePlace(existing.getId());

    if (parentSlug != null) {
      refreshChildrenCount(parentSlug);
    }
  }

  private void validateUpsert(UpsertPlaceRequest req, String idForUpdate) {
    if (req == null)
      throw new IllegalArgumentException("Request is required");
    if (req.kind() == null)
      throw new IllegalArgumentException("kind is required");
    if (!hasAnyLocaleValue(req.name()))
      throw new IllegalArgumentException("name is required (at least one locale)");

    if (req.kind() == PlaceKind.DESTINATION) {
      if (req.parentSlug() != null && !req.parentSlug().isBlank())
        throw new IllegalArgumentException("Địa điểm phải bảo gồm slug điểm đến cha");
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

    if (parent.getKind() != PlaceKind.DESTINATION) {
      throw new IllegalArgumentException(
          "Điểm đến cha phải là một điểm đến (DESTINATION): hiện tại: " + parent.getKind());
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
    return new double[] { lon, lat };
  }

  private void refreshChildrenCount(String parentSlug) {
    long count = repo.countByParentSlugAndActiveTrue(parentSlug);
    repo.findBySlug(parentSlug).ifPresent(p -> {
      p.setChildrenCount((int) count);
      PlaceDoc savedParent = repo.save(p);
      indexingService.syncPlace(savedParent);
    });
  }

  private PlaceAdminResponse toAdminResponse(PlaceDoc p) {
    String locale = LocaleContext.get();
    return new PlaceAdminResponse(
        p.getId(),
        LocaleUtil.pick(p.getName(), locale),
        p.getSlug(),
        p.getKind(),
        p.getVenueType(),
        Boolean.TRUE.equals(p.getActive()),
        p.getParentSlug(),
        p.getAncestors(),
        p.getChildrenCount());
  }

  private List<PlaceDoc.Image> mapImages(List<com.mravel.catalog.dto.place.PlaceAdminDtos.ImageReq> imgs,
      List<PlaceDoc.Image> old) {
    if (imgs == null)
      return List.of();
    Map<String, Map<String, String>> oldByUrl = captionsByUrl(old);
    return imgs.stream()
        .filter(Objects::nonNull)
        .map(i -> PlaceDoc.Image.builder()
            .url(i.url())
            .caption(localizedTranslator.resolve(i.caption(), oldByUrl.get(i.url())))
            .cover(i.cover() != null && i.cover())
            .sortOrder(i.sortOrder() == null ? 0 : i.sortOrder())
            .build())
        .toList();
  }

  // Lookup caption (Map locale->value) cũ theo url ảnh — để giữ bản dịch khi caption không đổi.
  private static Map<String, Map<String, String>> captionsByUrl(List<PlaceDoc.Image> old) {
    Map<String, Map<String, String>> m = new java.util.HashMap<>();
    if (old != null) {
      for (PlaceDoc.Image o : old) {
        if (o != null && o.getUrl() != null) {
          m.put(o.getUrl(), o.getCaption());
        }
      }
    }
    return m;
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
      List<com.mravel.catalog.dto.place.PlaceAdminDtos.CategoryReq> categories,
      List<PlaceDoc.CategoryMini> old) {
    if (categories == null)
      return List.of();
    Map<String, Map<String, String>> oldBySlug = new java.util.HashMap<>();
    if (old != null) {
      for (PlaceDoc.CategoryMini o : old) {
        if (o != null && o.getSlug() != null)
          oldBySlug.put(o.getSlug(), o.getName());
      }
    }
    return categories.stream()
        .filter(Objects::nonNull)
        .map(c -> PlaceDoc.CategoryMini.builder()
            .name(localizedTranslator.resolve(c.name(), oldBySlug.get(c.slug())))
            .slug(c.slug())
            .build())
        .toList();
  }

  private List<PlaceDoc.TagMini> mapTags(List<com.mravel.catalog.dto.place.PlaceAdminDtos.TagReq> tags,
      List<PlaceDoc.TagMini> old) {
    if (tags == null)
      return List.of();
    Map<String, Map<String, String>> oldBySlug = new java.util.HashMap<>();
    if (old != null) {
      for (PlaceDoc.TagMini o : old) {
        if (o != null && o.getSlug() != null)
          oldBySlug.put(o.getSlug(), o.getName());
      }
    }
    return tags.stream()
        .filter(Objects::nonNull)
        .map(t -> PlaceDoc.TagMini.builder()
            .name(localizedTranslator.resolve(t.name(), oldBySlug.get(t.slug())))
            .slug(t.slug())
            .type(t.type())
            .build())
        .toList();
  }

  private List<PlaceDoc.ContentBlock> mapContent(
      List<com.mravel.catalog.dto.place.PlaceAdminDtos.ContentBlockReq> content,
      List<PlaceDoc.ContentBlock> old) {
    if (content == null)
      return List.of();
    List<PlaceDoc.ContentBlock> result = new ArrayList<>();
    for (int idx = 0; idx < content.size(); idx++) {
      var b = content.get(idx);
      if (b == null)
        continue;
      // Khớp block cũ theo INDEX để giữ bản dịch khi text không đổi.
      PlaceDoc.ContentBlock oldBlock = (old != null && idx < old.size()) ? old.get(idx) : null;
      Map<String, String> oldText = oldBlock != null ? oldBlock.getText() : null;
      Map<String, String> oldImgCaption = (oldBlock != null && oldBlock.getImage() != null)
          ? oldBlock.getImage().getCaption()
          : null;
      Map<String, Map<String, String>> oldGalleryByUrl = (oldBlock != null) ? captionsByUrl(oldBlock.getGallery())
          : java.util.Map.of();

      result.add(PlaceDoc.ContentBlock.builder()
          .type(b.type())
          .text(localizedTranslator.resolve(b.text(), oldText))
          .image(b.image() == null ? null
              : PlaceDoc.Image.builder()
                  .url(b.image().url())
                  .caption(localizedTranslator.resolve(b.image().caption(), oldImgCaption))
                  .cover(b.image().cover() != null && b.image().cover())
                  .sortOrder(b.image().sortOrder() == null ? 0 : b.image().sortOrder())
                  .build())
          .gallery(b.gallery() == null ? List.of()
              : b.gallery().stream()
                  .filter(Objects::nonNull)
                  .map(i -> PlaceDoc.Image.builder()
                      .url(i.url())
                      .caption(localizedTranslator.resolve(i.caption(), oldGalleryByUrl.get(i.url())))
                      .cover(i.cover() != null && i.cover())
                      .sortOrder(i.sortOrder() == null ? 0 : i.sortOrder())
                      .build())
                  .toList())
          .mapLocation((b.mapLon() != null && b.mapLat() != null) ? new double[] { b.mapLon(), b.mapLat() } : null)
          .build());
    }
    return result;
  }

  private String normalizeSlug(String slug, Map<String, String> name) {
    String base = (slug != null && !slug.isBlank()) ? slug : pickForSlug(name);
    if (base == null)
      throw new IllegalArgumentException("slug or name is required");

    String s = base.trim().toLowerCase(Locale.ROOT);
    s = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    s = s.replaceAll("[^a-z0-9\\s-]", "");
    s = s.replaceAll("\\s+", "-");
    s = s.replaceAll("-{2,}", "-");
    s = s.replaceAll("^-|-$", "");
    if (s.isBlank())
      throw new IllegalArgumentException("Invalid slug after normalize");
    return s;
  }

  /** Slug derivation: prefer VI (more likely to be original/authoritative), then EN, then any. */
  private static String pickForSlug(Map<String, String> name) {
    if (name == null || name.isEmpty()) return null;
    String vi = name.get(LocaleConstants.VI);
    if (vi != null && !vi.isBlank()) return vi;
    String en = name.get(LocaleConstants.EN);
    if (en != null && !en.isBlank()) return en;
    return name.values().stream().filter(v -> v != null && !v.isBlank()).findFirst().orElse(null);
  }

  private static boolean hasAnyLocaleValue(Map<String, String> map) {
    if (map == null || map.isEmpty()) return false;
    return map.values().stream().anyMatch(v -> v != null && !v.isBlank());
  }
}
