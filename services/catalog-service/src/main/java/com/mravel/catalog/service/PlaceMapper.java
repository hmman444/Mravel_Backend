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

public class PlaceMapper {

  public static PlaceSummaryDTO toSummary(PlaceDoc p) {
    String cover = null;
    if (p.getImages() != null && !p.getImages().isEmpty()) {
      cover = p.getImages().stream()
          .filter(Objects::nonNull)
          // ưu tiên cover=true, rồi sortOrder nhỏ
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
        p.getName(),
        p.getSlug(),
        p.getKind(),
        p.getVenueType(),
        p.getAddressLine(),
        p.getProvinceName(),
        lat,
        lon,
        p.getPriceLevel(),
        p.getAvgRating(),
        p.getReviewsCount(),
        cover);
  }

  public static ImageDTO toImage(PlaceDoc.Image img) {
    if (img == null)
      return null;
    return new ImageDTO(
        img.getUrl(),
        img.getCaption(),
        Boolean.TRUE.equals(img.getCover()),
        img.getSortOrder() == null ? 0 : img.getSortOrder());
  }

  public static TagDTO toTag(PlaceDoc.TagMini t) {
    if (t == null)
      return null;
    return new TagDTO(
        t.getName(),
        t.getSlug(),
        t.getType() // TagType, không .name()
    );
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

    // DTO dùng luôn PlaceDoc.ContentBlock.BlockType nên không cần convert valueOf
    return new ContentBlockDTO(
        b.getType(),
        b.getText(),
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
        p.getName(),
        p.getSlug(),
        p.getKind(),
        p.getVenueType(),
        p.getShortDescription(),
        p.getDescription(),
        p.getPhone(),
        p.getEmail(),
        p.getWebsite(),
        p.getAddressLine(),
        p.getCountryCode(),
        p.getProvinceCode(),
        p.getDistrictCode(),
        p.getWardCode(),
        p.getProvinceName(),
        p.getDistrictName(),
        p.getWardName(),
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
