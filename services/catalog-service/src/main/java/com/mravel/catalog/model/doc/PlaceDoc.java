package com.mravel.catalog.model.doc;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.model.enums.PriceLevel;
import com.mravel.catalog.model.enums.TagType;
import com.mravel.catalog.model.enums.VenueType;

import lombok.*;

@Document(collection = "places")
@CompoundIndexes({
  @CompoundIndex(name = "active_kind_province_idx",  def = "{'active':1,'kind':1,'provinceCode':1}"),
  @CompoundIndex(name = "active_kind_district_idx",  def = "{'active':1,'kind':1,'districtCode':1}"),
  @CompoundIndex(name = "tag_cuisine_idx",           def = "{'tags.type':1,'tags.slug':1}"),
  @CompoundIndex(name = "venue_kind_idx",            def = "{'kind':1,'venueType':1}"),
  @CompoundIndex(name = "venue_kind_parent_idx",     def = "{'kind':1,'venueType':1,'parentSlug':1}"),
  @CompoundIndex(name = "ancestors_idx",             def = "{'ancestors':1}")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlaceDoc {

  @Id
  private String id;

  @Field
  @Indexed
  @Builder.Default
  private Boolean active = true;

  /** Phân cấp: DESTINATION / POI / VENUE */
  @Field
  @Indexed(name = "kind_idx") // đặt tên để KHỚP index hiện có, tránh conflict
  private PlaceKind kind;

  /** Loại dịch vụ khi kind = VENUE (HOTEL / RESTAURANT / OTHER) */
  @Field
  @Indexed
  private VenueType venueType;

  /** Liên kết phân cấp */
  @Indexed private String parentId;       // optional

  @Indexed(name = "parent_slug_idx")
  private String parentSlug;              // destination cha theo slug

  private List<String> ancestors; // hỗ trợ nhiều cấp [slug-cha, ...]
  @Builder.Default private Integer childrenCount = 0;

  // ---- Basic info ----
  @TextIndexed(weight = 10)
  private String name;

  @Indexed(name = "slug_unique_idx", unique = true)
  private String slug;

  private String shortDescription;
  private String description;

  // Contact
  private String phone;
  private String email;
  private String website;

  // Address + codes + names
  private String addressLine;

  @Field
  @Indexed
  @Builder.Default
  private String countryCode = "VN";

  @Field @Indexed
  private String provinceCode;

  @Field @Indexed
  private String districtCode;

  @Field @Indexed
  private String wardCode;

  @TextIndexed(weight = 5)
  private String provinceName;

  @TextIndexed(weight = 5)
  private String districtName;

  @TextIndexed(weight = 5)
  private String wardName;

  // Geo: [lon, lat] để dùng 2dsphere
  @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "loc_2dsphere_idx")
  private double[] location; // new double[]{longitude, latitude}

  // Price
  private PriceLevel priceLevel;
  private BigDecimal minPrice;
  private BigDecimal maxPrice;

  // Rating
  @Builder.Default
  private Double avgRating = 0.0;

  @Builder.Default
  private Integer reviewsCount = 0;

  // Embedded arrays
  private List<Image> images;
  private List<OpenHour> openHours;
  private List<CategoryMini> categories;
  private List<TagMini> tags;

  /** Nội dung dạng bài báo (ảnh + đoạn văn đan xen) */
  private List<ContentBlock> content;

  // --- Subdocuments ---
  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class Image {
    private String url;
    private String caption;
    @Builder.Default private Boolean cover = false;
    @Builder.Default private Integer sortOrder = 0;
  }

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class OpenHour {
    private DayOfWeek dayOfWeek;
    private LocalTime openTime;
    private LocalTime closeTime;
    @Builder.Default private Boolean open24h = false;
    @Builder.Default private Boolean closed = false;
  }

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class CategoryMini {
    private String id;   // optional
    private String name;
    private String slug;
  }

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class TagMini {
    private String name;
    private String slug;
    private TagType type; // AMENITY / FEATURE / CUISINE / SERVICE
  }

  /** Khối nội dung để render trang chi tiết như bài viết */
  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class ContentBlock {
    public enum BlockType { HEADING, PARAGRAPH, IMAGE, GALLERY, QUOTE, DIVIDER, INFOBOX, MAP }
    private BlockType type;
    private String text;           // cho HEADING/PARAGRAPH/QUOTE/INFOBOX
    private Image image;           // cho IMAGE
    private List<Image> gallery;   // cho GALLERY
    private double[] mapLocation;  // cho MAP (nếu muốn chỉ điểm khác location chính)
  }
}