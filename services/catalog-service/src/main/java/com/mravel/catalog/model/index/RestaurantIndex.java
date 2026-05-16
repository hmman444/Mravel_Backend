package com.mravel.catalog.model.index;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "restaurants", createIndex = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantIndex {

    @Id
    private String id;

    @MultiField(
        mainField = @Field(type = FieldType.Text, analyzer = "vn_text"),
        otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
    )
    private String name;

    @Field(type = FieldType.Keyword)
    private String slug;

    @Field(type = FieldType.Boolean)
    private Boolean active;

    @Field(type = FieldType.Keyword)
    private String moderationStatus;

    @Field(type = FieldType.Keyword)
    private String restaurantType;

    @Field(type = FieldType.Keyword)
    private String destinationSlug;

    @Field(type = FieldType.Keyword)
    private String parentPlaceSlug;

    @MultiField(
        mainField = @Field(type = FieldType.Text, analyzer = "vn_text"),
        otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
    )
    private String cityName;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String districtName;

    @Field(type = FieldType.Keyword)
    private String wardName;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String addressLine;

    @GeoPointField
    private GeoPoint location;

    /** Denormalized từ cuisines[].code — dùng cho filter nhanh */
    @Field(type = FieldType.Keyword)
    private List<String> cuisineCodes;

    /** Store only — dùng cho response */
    @Field(type = FieldType.Object, enabled = false)
    private List<CuisineData> cuisines;

    @Field(type = FieldType.Object, enabled = false)
    private List<AmbienceData> ambience;

    @Field(type = FieldType.Integer)
    private Integer totalCapacity;

    @Field(type = FieldType.Integer)
    private Integer maxGroupSize;

    @Field(type = FieldType.Double)
    private Double avgRating;

    @Field(type = FieldType.Integer)
    private Integer reviewsCount;

    @Field(type = FieldType.Keyword)
    private String ratingLabel;

    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal minPricePerPerson;

    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal maxPricePerPerson;

    @Field(type = FieldType.Keyword)
    private String currencyCode;

    @Field(type = FieldType.Keyword)
    private String priceLevel;

    @Field(type = FieldType.Keyword)
    private String priceBucket;

    @Field(type = FieldType.Keyword)
    private List<String> amenityCodes;

    @Field(type = FieldType.Object, enabled = false)
    private List<ImageData> images;

    /** Store only — filter giờ mở cửa xử lý ở application layer */
    @Field(type = FieldType.Object, enabled = false)
    private List<OpeningHourData> openingHours;

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class CuisineData {
        private String code;
        private String name;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class AmbienceData {
        private String code;
        private String label;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class ImageData {
        private String url;
        private String caption;
        private Boolean cover;
        private Integer sortOrder;
    }

    /** dayOfWeek: enum name (e.g. "MONDAY"), openTime/closeTime: "HH:mm" string */
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class OpeningHourData {
        private String dayOfWeek;
        private String openTime;
        private String closeTime;
        private Boolean open24h;
        private Boolean closed;
    }
}
