package com.mravel.plan.model;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plan_expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Tên chi phí (Vé tham quan, Taxi...)

    @Column(nullable = false)
    @Builder.Default
    private Long amount = 0L;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ExpenseCategory category = ExpenseCategory.OTHER;

    private String note; // ghi chú thêm

    @Embedded
    private Destination destination; // địa điểm (nếu có)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private PlanCard card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

}
