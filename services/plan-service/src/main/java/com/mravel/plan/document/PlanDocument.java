package com.mravel.plan.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

// Document Elasticsearch cho các trường tìm kiếm của Plan; đồng bộ từ MySQL qua Kafka.
@Document(indexName = "plans")
@Setting(settingPath = "/es/plans-settings.json")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDocument {

    @Id
    private String id; // plan.getId().toString()

    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;

    @Field(type = FieldType.Keyword)
    private String titleRaw;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;

    @Field(type = FieldType.Keyword)
    private String descriptionRaw;

    /** PUBLIC | FRIENDS | PRIVATE */
    @Field(type = FieldType.Keyword)
    private String visibility;

    @Field(type = FieldType.Long)
    private Long authorId;

    @Field(type = FieldType.Long)
    private Long views;

    /** Tổng số lượt react của plan — dùng cho sắp xếp MOST_REACTED. */
    @Field(type = FieldType.Long)
    private Long reactionCount;

    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDate startDate;

    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDate endDate;

    @Field(type = FieldType.Integer)
    private Integer days;

    @Field(type = FieldType.Long)
    private Long budgetTotal;

    @Field(type = FieldType.Keyword)
    private String budgetCurrency;

    @Field(type = FieldType.Date, format = DateFormat.epoch_millis)
    private Instant createdAt;

    /** Flat list of destination names for keyword filtering. */
    @Field(type = FieldType.Keyword)
    private List<String> destinationNames;

    /** Thumbnail URL — not analyzed. */
    @Field(type = FieldType.Keyword, index = false)
    private String thumbnail;
}
