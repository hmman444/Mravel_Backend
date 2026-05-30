package com.mravel.catalog.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.mravel.catalog.dto.place.PlaceDtos.CategoryDTO;
import com.mravel.catalog.dto.place.PlaceDtos.ContentBlockDTO;
import com.mravel.catalog.dto.place.PlaceDtos.ImageDTO;
import com.mravel.catalog.dto.place.PlaceDtos.OpenHourDTO;
import com.mravel.catalog.dto.place.PlaceDtos.PlaceDetailDTO;
import com.mravel.catalog.dto.place.PlaceDtos.PlaceSummaryDTO;
import com.mravel.catalog.dto.place.PlaceDtos.TagDTO;
import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.search.dto.PlaceSearchResult;
import com.mravel.common.i18n.LocaleContext;
import com.mravel.common.i18n.LocaleUtil;

public class PlaceMapper {

  public static PlaceSummaryDTO toSummary(PlaceSearchResult p) {
    String cover = null;
    if (p.images() != null && !p.images().isEmpty()) {
      cover = p.images().stream()
          .filter(Objects::nonNull)
          .sorted(Comparator
              .comparing((PlaceSearchResult.ImageRef img) -> Boolean.TRUE.equals(img.cover())).reversed()
              .thenComparing(img -> img.sortOrder() == null ? 0 : img.sortOrder()))
          .map(PlaceSearchResult.ImageRef::url)
          .findFirst()
          .orElse(null);
    }

    Double lat = (p.location() != null && p.location().length == 2) ? p.location()[1] : null;
    Double lon = (p.location() != null && p.location().length == 2) ? p.location()[0] : null;

    return new PlaceSummaryDTO(
        p.id(), p.name(), p.slug(),
        p.kind(), p.venueType(),
        p.addressLine(), p.provinceName(),
        lat, lon,
        p.priceLevel(), p.avgRating(), p.reviewsCount(),
        cover, Boolean.TRUE.equals(p.active()));
  }

  public static PlaceSummaryDTO toSummary(PlaceDoc p) {
    String locale = LocaleContext.get();
    String cover = null;
    if (p.getImages() != null && !p.getImages().isEmpty()) {
      cover = p.getImages().stream()
          .filter(Objects::nonNull)
          .sorted(Comparator
              .comparing((PlaceDoc.Image img) -> Boolean.TRUE.equals(img.getCover())).reversed()
              .thenComparing(img -> img.getSortOrder() == null ? 0 : img.getSortOrder()))
          .map(PlaceDoc.Image::getUrl)
          .findFirst()
          .orElse(null);
    }

    Double lat = (p.getLocation() != null && p.getLocation().length == 2) ? p.getLocation()[1] : null;
    Double lon = (p.getLocation() != null && p.getLocation().length == 2) ? p.getLocation()[0] : null;

    return new PlaceSummaryDTO(
        p.getId(),
        LocaleUtil.pick(p.getName(), locale),
        p.getSlug(),
        p.getKind(),
        p.getVenueType(),
        LocaleUtil.pick(p.getAddressLine(), locale),
        LocaleUtil.pick(p.getProvinceName(), locale),
        lat,
        lon,
        p.getPriceLevel(),
        p.getAvgRating(),
        p.getReviewsCount(),
        cover,
        Boolean.TRUE.equals(p.getActive()));
  }

  public static ImageDTO toImage(PlaceDoc.Image img) {
    if (img == null)
      return null;
    String locale = LocaleContext.get();
    return new ImageDTO(
        img.getUrl(),
        LocaleUtil.pick(img.getCaption(), locale),
        Boolean.TRUE.equals(img.getCover()),
        img.getSortOrder() == null ? 0 : img.getSortOrder());
  }

  public static TagDTO toTag(PlaceDoc.TagMini t) {
    if (t == null)
      return null;
    String locale = LocaleContext.get();
    return new TagDTO(
        LocaleUtil.pick(t.getName(), locale),
        t.getSlug(),
        t.getType());
  }

  public static CategoryDTO toCategory(PlaceDoc.CategoryMini c) {
    if (c == null)
      return null;
    String locale = LocaleContext.get();
    return new CategoryDTO(LocaleUtil.pick(c.getName(), locale), c.getSlug());
  }

  public static OpenHourDTO toOpenHour(PlaceDoc.OpenHour oh) {
    if (oh == null)
      return null;
    return new OpenHourDTO(
        oh.getDayOfWeek(),
        oh.getOpenTime(),
        oh.getCloseTime(),
        Boolean.TRUE.equals(oh.getOpen24h()),
        Boolean.TRUE.equals(oh.getClosed()));
  }

  public static ContentBlockDTO toContentBlock(PlaceDoc.ContentBlock b) {
    if (b == null)
      return null;
    String locale = LocaleContext.get();

    ImageDTO image = (b.getImage() == null) ? null : toImage(b.getImage());
    List<ImageDTO> gallery = (b.getGallery() == null)
        ? List.of()
        : b.getGallery().stream()
            .filter(Objects::nonNull)
            .map(PlaceMapper::toImage)
            .filter(Objects::nonNull)
            .toList();

    Double mapLat = null, mapLon = null;
    if (b.getMapLocation() != null && b.getMapLocation().length == 2) {
      mapLon = b.getMapLocation()[0];
      mapLat = b.getMapLocation()[1];
    }

    return new ContentBlockDTO(
        b.getType(),
        LocaleUtil.pick(b.getText(), locale),
        image,
        gallery,
        mapLat,
        mapLon);
  }

  public static PlaceDetailDTO toDetail(
      PlaceDoc p,
      List<ImageDTO> images,
      List<OpenHourDTO> hours,
      List<CategoryDTO> categories,
      List<TagDTO> tags) {
    if (p == null)
      return null;

    String locale = LocaleContext.get();

    images = (images == null ? List.<ImageDTO>of() : images).stream()
        .filter(Objects::nonNull)
        .sorted(Comparator
            .comparing(ImageDTO::cover).reversed()
            .thenComparing(img -> img.sortOrder() == null ? 0 : img.sortOrder()))
        .toList();

    List<ContentBlockDTO> content = (p.getContent() == null)
        ? List.of()
        : p.getContent().stream()
            .map(PlaceMapper::toContentBlock)
            .filter(Objects::nonNull)
            .toList();

    Double lat = (p.getLocation() != null && p.getLocation().length == 2) ? p.getLocation()[1] : null;
    Double lon = (p.getLocation() != null && p.getLocation().length == 2) ? p.getLocation()[0] : null;

    return new PlaceDetailDTO(
        p.getId(),
        LocaleUtil.pick(p.getName(), locale),
        p.getSlug(),
        p.getKind(),
        p.getVenueType(),
        LocaleUtil.pick(p.getShortDescription(), locale),
        LocaleUtil.pick(p.getDescription(), locale),
        p.getPhone(),
        p.getEmail(),
        p.getWebsite(),
        LocaleUtil.pick(p.getAddressLine(), locale),
        p.getCountryCode(),
        p.getProvinceCode(),
        p.getDistrictCode(),
        p.getWardCode(),
        LocaleUtil.pick(p.getProvinceName(), locale),
        LocaleUtil.pick(p.getDistrictName(), locale),
        LocaleUtil.pick(p.getWardName(), locale),
        lat,
        lon,
        p.getPriceLevel(),
        p.getMinPrice(),
        p.getMaxPrice(),
        p.getAvgRating(),
        p.getReviewsCount(),
        Boolean.TRUE.equals(p.getActive()),
        p.getParentSlug(),
        p.getAncestors(),
        p.getChildrenCount(),
        categories == null ? List.of() : categories,
        tags == null ? List.of() : tags,
        images,
        hours == null ? List.of() : hours,
        content);
  }
}
