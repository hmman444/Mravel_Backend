package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

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

    // info basic
    @Column(nullable = false, length = 1000)
    private String text;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalTime startTime;
    private LocalTime endTime;

    @Builder.Default
    @Column(nullable = false)
    private boolean done = false;

    @Column(nullable = false)
    private Integer position;

    // card thuộc về 1 list
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    private PlanList list;

    // activity type
    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private PlanActivityType activityType; // TRANSPORT, FOOD, ...

    @Lob
    @Column(name = "activity_data_json", columnDefinition = "TEXT")
    private String activityDataJson;
    // chứa dữ liệu động như: địa điểm, khoảng cách, phí vé, món ăn đã chọn, phương
    // tiện...

    // chi phí dự trù
    @Column(name = "estimated_cost")
    private Long estimatedCost;

    // chi phí thực tế
    @Column(name = "actual_cost")
    private Long actualCost;

    // ng trả tiền chính
    @Column(name = "payer_id")
    private Long payerId; // UserId

    /**
     * Kiểu chia tiền:
     * NONE = không chia, chỉ ghi người trả
     * EVEN = chia đều cho splitMembers
     * CUSTOM = chia theo % hoặc số tiền từng người
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "split_type")
    private SplitType splitType;

    // ai được chia tiền
    @ElementCollection
    @CollectionTable(name = "card_split_members", joinColumns = @JoinColumn(name = "card_id"))
    @Column(name = "member_id")
    @Builder.Default
    private Set<Long> splitMembers = new HashSet<>();

    /**
     * Kết quả chia tiền:
     * JSON như:
     * {
     * "12": 30000,
     * "15": 70000
     * }
     */
    @Lob
    @Column(name = "split_result_json", columnDefinition = "TEXT")
    private String splitResultJson;

}
