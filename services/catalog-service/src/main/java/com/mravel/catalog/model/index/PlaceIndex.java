package com.mravel.catalog.model.index;

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
@Document(indexName = "places", createIndex = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceIndex {

    @Id
    private String id;

    @MultiField(
        mainField = @Field(type = FieldType.Text, analyzer = "standard"),
        otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
    )
    private String name;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String slug;

    @Field(type = FieldType.Boolean)
    private Boolean active;

    @Field(type = FieldType.Keyword)
    private String kind;

    @Field(type = FieldType.Keyword)
    private String venueType;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String addressLine;

    @MultiField(
        mainField = @Field(type = FieldType.Text, analyzer = "standard"),
        otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
    )
    private String provinceName;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String districtName;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Keyword)
    private String priceLevel;

    @Field(type = FieldType.Double)
    private Double avgRating;

    @Field(type = FieldType.Integer)
    private Integer reviewsCount;

    @Field(type = FieldType.Keyword)
    private String parentSlug;

    @Field(type = FieldType.Keyword)
    private List<String> categorySlugs;

    /** Encoded as "slug::DisplayName" for facet label resolution without lookup. */
    @Field(type = FieldType.Keyword)
    private List<String> categoryLabels;

    // ---- v2 discovery fields ----

    @Field(type = FieldType.Keyword)
    private String crowdLevel;

    @Field(type = FieldType.Keyword)
    private String noiseLevel;

    @Field(type = FieldType.Keyword)
    private String venueContext;

    @Field(type = FieldType.Keyword)
    private List<String> bestVisitTime;

    @Field(type = FieldType.Keyword)
    private String bestSeason;

    @Field(type = FieldType.Keyword)
    private List<String> vibes;

    @Field(type = FieldType.Keyword)
    private List<String> suitableFor;

    @Field(type = FieldType.Keyword)
    private List<String> visitPurpose;

    @Field(type = FieldType.Boolean)
    private Boolean isFamilyFriendly;

    @Field(type = FieldType.Integer)
    private Integer visitDurationMinutes;

    @Field(type = FieldType.Integer)
    private Integer photographyScore;

    @Field(type = FieldType.Integer)
    private Integer romanticScore;

    @Field(type = FieldType.Integer)
    private Integer localExperienceScore;

    @Field(type = FieldType.Object, enabled = false)
    private List<ImageData> images;

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class ImageData {
        private String url;
        private String caption;
        private Boolean cover;
        private Integer sortOrder;
    }
}
