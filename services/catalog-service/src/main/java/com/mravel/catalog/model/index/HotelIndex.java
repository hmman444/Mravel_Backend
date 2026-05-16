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
@Document(indexName = "hotels", createIndex = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelIndex {

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

    /** Denormalized từ moderation.status */
    @Field(type = FieldType.Keyword)
    private String moderationStatus;

    @Field(type = FieldType.Integer)
    private Integer starRating;

    @Field(type = FieldType.Keyword)
    private String hotelType;

    @Field(type = FieldType.Keyword)
    private String destinationSlug;

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

    @Field(type = FieldType.Double)
    private Double avgRating;

    @Field(type = FieldType.Integer)
    private Integer reviewsCount;

    @Field(type = FieldType.Keyword)
    private String ratingLabel;

    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal minNightlyPrice;

    @Field(type = FieldType.Keyword)
    private String currencyCode;

    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal referenceNightlyPrice;

    @Field(type = FieldType.Integer)
    private Integer discountPercent;

    @Field(type = FieldType.Keyword)
    private List<String> amenityCodes;

    @Field(type = FieldType.Object, enabled = false)
    private GeneralInfoData generalInfo;

    @Field(type = FieldType.Object, enabled = false)
    private List<ImageData> images;

    /** nested để filter kết hợp maxGuests AND totalRooms trên cùng 1 phần tử */
    @Field(type = FieldType.Nested)
    private List<RoomTypeData> roomTypes;

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class GeneralInfoData {
        private String mainFacilitiesSummary;
        private Double distanceToCityCenterKm;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class ImageData {
        private String url;
        private String caption;
        private Boolean cover;
        private Integer sortOrder;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class RoomTypeData {
        private String id;
        private Integer maxGuests;
        private Integer totalRooms;
    }
}
