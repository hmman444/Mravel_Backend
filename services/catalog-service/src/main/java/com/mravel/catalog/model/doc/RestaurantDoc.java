// package com.mravel.catalog.model.doc;

// import java.math.BigDecimal;
// import java.time.DayOfWeek;
// import java.time.LocalTime;
// import java.util.List;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.index.CompoundIndex;
// import org.springframework.data.mongodb.core.index.CompoundIndexes;
// import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
// import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
// import org.springframework.data.mongodb.core.index.Indexed;
// import org.springframework.data.mongodb.core.index.TextIndexed;
// import org.springframework.data.mongodb.core.mapping.Document;

// import com.mravel.catalog.model.enums.PriceLevel;
// import com.mravel.catalog.model.enums.TagType;

// import lombok.*;

// @Document(collection = "restaurants")
// @CompoundIndexes({
//     @CompoundIndex(name = "restaurant_parent_slug_idx", def = "{'parentSlug':1}"),
//     @CompoundIndex(name = "restaurant_tag_cuisine_idx", def = "{'tags.type':1,'tags.slug':1}")
// })
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class RestaurantDoc {

//   @Id
//   private String id;

//   @Indexed
//   @Builder.Default
//   private Boolean active = true;

//   @TextIndexed(weight = 10)
//   private String name;

//   @Indexed(name = "restaurant_slug_unique_idx", unique = true)
//   private String slug;

//   /** Liên kết DESTINATION/POI cha */
//   @Indexed
//   private String parentSlug;
//   private List<String> ancestors;

//   private String shortDescription;
//   private String description;

//   private String phone;
//   private String email;
//   private String website;

//   private String addressLine;

//   @Indexed
//   private String countryCode;

//   @Indexed
//   private String provinceCode;

//   @Indexed
//   private String districtCode;

//   @Indexed
//   private String wardCode;

//   @TextIndexed(weight = 5)
//   private String provinceName;

//   @TextIndexed(weight = 5)
//   private String districtName;

//   @TextIndexed(weight = 5)
//   private String wardName;

//   @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "restaurant_loc_2dsphere_idx")
//   private double[] location;

//   private PriceLevel priceLevel;
//   private BigDecimal minPrice;
//   private BigDecimal maxPrice;

//   @Builder.Default
//   private Double avgRating = 0.0;

//   @Builder.Default
//   private Integer reviewsCount = 0;

//   private List<Image> images;
//   private List<OpenHour> openHours;
//   private List<TagMini> tags;
//   private List<ContentBlock> content;

//   // ====== Embedded subdocs ======

//   @Getter
//   @Setter
//   @NoArgsConstructor
//   @AllArgsConstructor
//   @Builder
//   public static class Image {
//     private String url;
//     private String caption;
//     @Builder.Default
//     private Boolean cover = false;
//     @Builder.Default
//     private Integer sortOrder = 0;
//   }

//   @Getter
//   @Setter
//   @NoArgsConstructor
//   @AllArgsConstructor
//   @Builder
//   public static class OpenHour {
//     private DayOfWeek dayOfWeek;
//     private LocalTime openTime;
//     private LocalTime closeTime;
//     @Builder.Default
//     private Boolean open24h = false;
//     @Builder.Default
//     private Boolean closed = false;
//   }

//   @Getter
//   @Setter
//   @NoArgsConstructor
//   @AllArgsConstructor
//   @Builder
//   public static class TagMini {
//     private String name;
//     private String slug;
//     private TagType type; // CỤ THỂ: CUISINE, AMENITY, FEATURE...
//   }

//   @Getter
//   @Setter
//   @NoArgsConstructor
//   @AllArgsConstructor
//   @Builder
//   public static class ContentBlock {
//     public enum BlockType { HEADING, PARAGRAPH, IMAGE, GALLERY, QUOTE, DIVIDER, INFOBOX, MAP }
//     private BlockType type;
//     private String text;
//     private Image image;
//     private List<Image> gallery;
//     private double[] mapLocation;
//   }
// }