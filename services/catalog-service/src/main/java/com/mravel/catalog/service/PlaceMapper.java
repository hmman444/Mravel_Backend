package com.mravel.catalog.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.mravel.catalog.dto.place.PlaceDtos.*;
import com.mravel.catalog.model.doc.PlaceDoc;

public class PlaceMapper {

  public static PlaceSummaryDTO toSummary(PlaceDoc p) {
    String cover = null;
    if (p.getImages() != null && !p.getImages().isEmpty()) {
      cover = p.getImages().stream()
        .sorted(Comparator
          .comparing(PlaceDoc.Image::getCover, Comparator.nullsLast(Boolean::compareTo)).reversed()
          .thenComparing(PlaceDoc.Image::getSortOrder, Comparator.nullsLast(Integer::compareTo)))
        .map(PlaceDoc.Image::getUrl)
        .findFirst()
        .orElse(null);
    }
    return new PlaceSummaryDTO(
      null, // id Long: để null vì Mongo id là String
      p.getName(),
      p.getSlug(),
      p.getKind(),             // ← dùng kind
      p.getVenueType(),        // ← VENUE thì có, DESTINATION/POI thì null
      p.getAddressLine(),
      p.getProvinceName(),
      (p.getLocation()!=null && p.getLocation().length==2) ? p.getLocation()[1] : null, // lat
      (p.getLocation()!=null && p.getLocation().length==2) ? p.getLocation()[0] : null, // lon
      p.getPriceLevel(),
      p.getAvgRating(),
      p.getReviewsCount(),
      cover
    );
  }

  public static ImageDTO toImage(PlaceDoc.Image img) {
    return new ImageDTO(
      null,
      img.getUrl(),
      img.getCaption(),
      Boolean.TRUE.equals(img.getCover()),
      img.getSortOrder()
    );
  }

  public static TagDTO toTag(PlaceDoc.TagMini t) {
    return new TagDTO(null, t.getName(), t.getSlug(), t.getType().name());
  }

  public static OpenHourDTO toOpenHour(PlaceDoc.OpenHour oh) {
    return new OpenHourDTO(
      null,
      oh.getDayOfWeek(),
      oh.getOpenTime(),
      oh.getCloseTime(),
      Boolean.TRUE.equals(oh.getOpen24h()),
      Boolean.TRUE.equals(oh.getClosed())
    );
  }

  public static ContentBlockDTO toContentBlock(PlaceDoc.ContentBlock b) {
    if (b == null) return null;

    ImageDTO image = b.getImage() == null ? null : toImage(b.getImage());
    List<ImageDTO> gallery = b.getGallery() == null
      ? List.of()
      : b.getGallery().stream().filter(Objects::nonNull).map(PlaceMapper::toImage).toList();

    Double mapLat = null, mapLon = null;
    if (b.getMapLocation() != null && b.getMapLocation().length == 2) {
      mapLat = b.getMapLocation()[1];
      mapLon = b.getMapLocation()[0];
    }

    // Map enum tên y hệt để client dễ render
    ContentBlockDTO.BlockType type = ContentBlockDTO.BlockType.valueOf(b.getType().name());

    return new ContentBlockDTO(type, b.getText(), image, gallery, mapLat, mapLon);
  }

  public static PlaceDetailDTO toDetail(PlaceDoc p,
                                        List<ImageDTO> images, List<OpenHourDTO> hours,
                                        List<CategoryDTO> categories, List<TagDTO> tags) {

    images = images.stream()
      .sorted(Comparator
        .comparing(ImageDTO::cover).reversed()
        .thenComparing(ImageDTO::sortOrder, Comparator.nullsLast(Integer::compareTo)))
      .toList();

    var content = p.getContent()==null ? List.<ContentBlockDTO>of()
      : p.getContent().stream()
          .map(PlaceMapper::toContentBlock)
          .filter(Objects::nonNull)
          .toList();

    return new PlaceDetailDTO(
      null,
      p.getName(),
      p.getSlug(),
      p.getKind(),                 // ← dùng kind
      p.getVenueType(),            // ← VENUE thì có
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
      (p.getLocation()!=null && p.getLocation().length==2) ? p.getLocation()[1] : null,
      (p.getLocation()!=null && p.getLocation().length==2) ? p.getLocation()[0] : null,
      p.getPriceLevel(),
      p.getMinPrice(),
      p.getMaxPrice(),
      p.getAvgRating(),
      p.getReviewsCount(),
      Boolean.TRUE.equals(p.getActive()),
      p.getParentSlug(),
      p.getAncestors(),
      p.getChildrenCount(),
      categories,
      tags,
      images,
      hours,
      content
    );
  }
}