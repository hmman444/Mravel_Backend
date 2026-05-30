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
        mainField = @Field(type = FieldType.Text, analyzer = "vn_text"),
        otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
    )
    private String nameVi;

    @MultiField(
        mainField = @Field(type = FieldType.Text, analyzer = "english"),
        otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
    )
    private String nameEn;

    @Field(type = FieldType.Keyword)
    private String slug;

    @Field(type = FieldType.Boolean)
    private Boolean active;

    @Field(type = FieldType.Keyword)
    private String kind;

    @Field(type = FieldType.Keyword)
    private String venueType;

    @Field(type = FieldType.Text, analyzer = "vn_text")
    private String addressLineVi;

    @Field(type = FieldType.Text, analyzer = "english")
    private String addressLineEn;

    @MultiField(
        mainField = @Field(type = FieldType.Text, analyzer = "vn_text"),
        otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
    )
    private String provinceNameVi;

    @MultiField(
        mainField = @Field(type = FieldType.Text, analyzer = "english"),
        otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
    )
    private String provinceNameEn;

    @Field(type = FieldType.Text, analyzer = "vn_text")
    private String districtNameVi;

    @Field(type = FieldType.Text, analyzer = "english")
    private String districtNameEn;

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

    /** Encoded as "slug::nameVi::nameEn" — picked per locale at extract time. */
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
        private String captionVi;
        private String captionEn;
        private Boolean cover;
        private Integer sortOrder;
    }
}
