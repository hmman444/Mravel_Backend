package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 4000)
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer days;

    @Enumerated(EnumType.STRING)
    private Visibility visibility; // PUBLIC, PRIVATE, FRIENDS

    private Long views;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    private Instant createdAt;

    @ElementCollection
    @CollectionTable(name = "plan_images", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "url")
    @Builder.Default
    private List<String> images = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "plan_videos", joinColumns = @JoinColumn(name = "plan_id"))
    @Column(name = "url")
    @Builder.Default
    private List<String> videos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "plan_destinations", joinColumns = @JoinColumn(name = "plan_id"))
    @Builder.Default
    private List<Destination> destinations = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PlanReaction> reactions = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt ASC")
    @Builder.Default
    private List<PlanComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position ASC")
    @Builder.Default
    private List<PlanList> lists = new ArrayList<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PlanMember> members = new ArrayList<>();

    @Builder.Default
    private String budgetCurrency = "VND"; // default "VND"

    private Long budgetTotal; // tổng ngân sách

    private Long budgetPerPerson; // tổng ngân sách/ người

    private Long totalEstimatedCost;

    private Long totalActualCost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private PlanStatus status = PlanStatus.DRAFT;

    @Column(name = "thumbnail_url")
    private String thumbnail;

    @Column(name = "board_revision", nullable = false)
    @Builder.Default
    private Long boardRevision = 0L;

    // ===== Kiểm duyệt (admin takedown) =====
    // adminLocked = true: bài đã bị admin GỠ -> ép PRIVATE và KHÓA VĨNH VIỄN.
    // Không có endpoint mở khóa (bất khả hồi theo yêu cầu).
    @Column(name = "admin_locked", nullable = false)
    @ColumnDefault("false")
    @Builder.Default
    private Boolean adminLocked = false;

    @Column(name = "takedown_by")
    private Long takedownBy;

    @Column(name = "takedown_at")
    private Instant takedownAt;

    @Column(name = "takedown_reason", length = 500)
    private String takedownReason;

}
