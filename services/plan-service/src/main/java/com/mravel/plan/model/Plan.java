package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Plan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 4000)
    private String description;

    // UI hiển thị dạng "dd/MM/yyyy" — backend lưu ISO hoặc yyyy-MM-dd
    private String startDate;
    private String endDate;
    private Integer days;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    private Long views;

    @Embedded
    private AuthorSummary author;

    private Instant createdAt;

    // Album ảnh
    @ElementCollection
    @CollectionTable(name = "plan_images", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "url")
    private List<String> images = new ArrayList<>();

    // Điểm đến
    @ElementCollection
    @CollectionTable(name = "plan_destinations", joinColumns = @JoinColumn(name = "plan_id"))
    private List<Destination> destinations = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanReaction> reactions = new ArrayList<>();

    // Comments
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt ASC")
    private List<PlanComment> comments = new ArrayList<>();
}
