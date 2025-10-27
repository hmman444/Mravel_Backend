package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Thực thể đại diện cho một thẻ trong danh sách (card in list)
 * Thuộc về một PlanList (1-nhiều), có thể có nhiều nhãn (many-to-many)
 */
@Entity
@Table(name = "plan_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nội dung chính hiển thị trên thẻ */
    @Column(nullable = false, length = 1000)
    private String text;

    /** Mô tả chi tiết (dùng cho modal EditCard) */
    @Column(columnDefinition = "TEXT")
    private String description;

    /** Ưu tiên: high|medium|low (nullable) */
    @Column(length = 20)
    private String priority;

    /** Thời gian bắt đầu/kết thúc trong ngày (nullable) */
    private LocalTime startTime;
    private LocalTime endTime;

    /** Đã hoàn thành hay chưa */
    @Builder.Default
    @Column(nullable = false)
    private boolean done = false;

    /** Vị trí trong list (đổi khi kéo thả) */
    @Column(nullable = false)
    private Integer position;

    /* Quan hệ tới danh sách chứa card */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    private PlanList list;

    /** Nhãn (multi-select) — chia sẻ theo plan */
    @ManyToMany
    @JoinTable(name = "plan_card_labels", joinColumns = @JoinColumn(name = "card_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
    @OrderBy("id ASC")
    @Builder.Default
    private Set<PlanLabel> labels = new HashSet<>();

    /**
     * 🔒 Đảm bảo không bao giờ bị null trước khi persist
     * Hibernate sẽ gọi hàm này trước khi INSERT
     */
    @PrePersist
    public void prePersist() {
        if (labels == null)
            labels = new HashSet<>();
        if (position == null)
            position = 0;
    }
}
