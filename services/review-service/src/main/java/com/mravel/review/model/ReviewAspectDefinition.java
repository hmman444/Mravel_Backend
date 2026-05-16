package com.mravel.review.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "review_aspect_definitions",
    uniqueConstraints = @UniqueConstraint(name = "uq_category_code", columnNames = {"category", "code"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewAspectDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TargetType category;

    @Column(name = "label_vi", nullable = false, length = 200)
    private String labelVi;

    @Column(name = "label_en", nullable = false, length = 200)
    private String labelEn;

    @Column(length = 100)
    private String icon;

    @Column(name = "sort_order")
    @Builder.Default
    private Integer sortOrder = 0;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;
}
