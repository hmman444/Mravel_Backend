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

    @Column(nullable = false, length = 1000)
    private String text;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 20)
    private String priority;

    private LocalTime startTime;
    private LocalTime endTime;

    @Builder.Default
    @Column(nullable = false)
    private boolean done = false;

    @Column(nullable = false)
    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    private PlanList list;

    @ManyToMany
    @JoinTable(name = "plan_card_labels", joinColumns = @JoinColumn(name = "card_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
    @OrderBy("id ASC")
    @Builder.Default
    private Set<PlanLabel> labels = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (labels == null)
            labels = new HashSet<>();
        if (position == null)
            position = 0;
    }

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<PlanExpense> expenses = new HashSet<>();

}
