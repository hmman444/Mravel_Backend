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

  // =====================================================================
  // NESTED ENUMS
  // =====================================================================

  public enum CrowdLevel  { LOW, MEDIUM, HIGH }
  public enum NoiseLevel  { QUIET, MODERATE, LOUD }
  public enum BestVisitTime { MORNING, AFTERNOON, EVENING, NIGHT }
  public enum BestSeason  { DRY_SEASON, WET_SEASON, YEAR_ROUND }
  public enum VenueContext { INDOOR, OUTDOOR, MIXED }

  // =====================================================================
  // CORE IDENTITY
  // =====================================================================

  @Id
  private String id;

  @Builder.Default
  private Integer favoriteCount = 0;

  @Field
  @Indexed
  @Builder.Default
  private Boolean active = true;

  @Field
  @Indexed(name = "kind_idx")
  private PlaceKind kind;

  @Field
  @Indexed
  private VenueType venueType;

  @Indexed private String parentId;

  @Indexed(name = "parent_slug_idx")
  private String parentSlug;

  private List<String> ancestors;
  @Builder.Default private Integer childrenCount = 0;

  // =====================================================================
  // BASIC INFO
  // =====================================================================

  @TextIndexed(weight = 10)
  private String name;

  @Indexed(name = "slug_unique_idx", unique = true)
  private String slug;

  private String shortDescription;
  private String description;

  private String phone;
  private String email;
  private String website;

  // =====================================================================
  // ADDRESS
  // =====================================================================

  private String addressLine;

  @Field
  @Indexed
  @Builder.Default
  private String countryCode = "VN";

  @Field @Indexed private String provinceCode;
  @Field @Indexed private String districtCode;
  @Field @Indexed private String wardCode;

  @TextIndexed(weight = 5) private String provinceName;
  @TextIndexed(weight = 5) private String districtName;
  @TextIndexed(weight = 5) private String wardName;

  @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "loc_2dsphere_idx")
  private double[] location;

  // =====================================================================
  // PRICE & RATING
  // =====================================================================

  private PriceLevel priceLevel;
  private BigDecimal minPrice;
  private BigDecimal maxPrice;

  @Builder.Default private Double avgRating    = 0.0;
  @Builder.Default private Integer reviewsCount = 0;

  private List<ReviewKeywordStat> reviewKeywords;

  // =====================================================================
  // EMBEDDED ARRAYS
  // =====================================================================

  private List<Image>        images;
  private List<OpenHour>     openHours;
  private List<CategoryMini> categories;
  private List<TagMini>      tags;
  private List<ContentBlock> content;

  // =====================================================================
  // EXPERIENCE LAYER
  // =====================================================================

  private List<String> vibes;
  private String       atmosphere;
  private List<String> travelStyle;

  // =====================================================================
  // USER INTENT FILTERS
  // =====================================================================

  private List<String> suitableFor;
  private List<String> visitPurpose;

  // =====================================================================
  // CONTEXT FILTERS
  // =====================================================================

  private CrowdLevel   crowdLevel;
  private NoiseLevel   noiseLevel;
  private VenueContext venueContext;
  private Boolean      isFamilyFriendly;

  // =====================================================================
  // TIME-BASED DISCOVERY
  // =====================================================================

  private List<BestVisitTime> bestVisitTime;
  private BestSeason          bestSeason;
  private Integer             visitDurationMinutes;

  // =====================================================================
  // UX / RANKING SIGNALS
  // =====================================================================

  private Integer photographyScore;
  private Integer romanticScore;
  private Integer localExperienceScore;

  // =====================================================================
  // SUBDOCUMENTS
  // =====================================================================

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class ReviewKeywordStat {
    private String  code;
    private String  label;
    private Integer count;
  }

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class Image {
    private String  url;
    private String  caption;
    @Builder.Default private Boolean cover      = false;
    @Builder.Default private Integer sortOrder  = 0;
  }

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class OpenHour {
    private DayOfWeek dayOfWeek;
    private LocalTime openTime;
    private LocalTime closeTime;
    @Builder.Default private Boolean open24h = false;
    @Builder.Default private Boolean closed  = false;
  }

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class CategoryMini {
    private String id;
    private String name;
    private String slug;
  }

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class TagMini {
    private String  name;
    private String  slug;
    private TagType type;
  }

  @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
  public static class ContentBlock {
    public enum BlockType { HEADING, PARAGRAPH, IMAGE, GALLERY, QUOTE, DIVIDER, INFOBOX, MAP }
    private BlockType   type;
    private String      text;
    private Image       image;
    private List<Image> gallery;
    private double[]    mapLocation;
  }
}

/*
CHANGELOG:
- added: vibes, suitableFor, visitPurpose
- converted: crowdLevel (String → CrowdLevel enum: LOW/MEDIUM/HIGH)
- converted: noiseLevel (String → NoiseLevel enum: QUIET/MODERATE/LOUD)
- converted: bestVisitTime (String → List<BestVisitTime> enum: MORNING/AFTERNOON/EVENING/NIGHT)
- converted: bestSeason (String → BestSeason enum: DRY_SEASON/WET_SEASON/YEAR_ROUND)
- merged: isIndoor + isOutdoor → venueContext (VenueContext enum: INDOOR/OUTDOOR/MIXED)
- kept: atmosphere, travelStyle, isFamilyFriendly, visitDurationMinutes, photographyScore, romanticScore, localExperienceScore
*/
