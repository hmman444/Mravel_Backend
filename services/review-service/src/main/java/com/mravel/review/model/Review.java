package com.mravel.review.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviews", indexes = {
    @Index(name = "idx_target", columnList = "targetType, targetId"),
    @Index(name = "idx_user_target", columnList = "userId, targetType, targetId", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String targetId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TargetType targetType;

    @Column(nullable = false)
    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReviewAspect> aspects = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
