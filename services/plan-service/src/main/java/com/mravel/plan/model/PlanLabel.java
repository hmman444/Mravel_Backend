package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plan_labels", uniqueConstraints = @UniqueConstraint(columnNames = { "plan_id", "text" }))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Ví dụ: "Ăn uống" */
    @Column(nullable = false, length = 100)
    private String text;

    /** Ví dụ: "bg-green-600" (lưu class Tailwind theo UI) */
    @Column(nullable = false, length = 50)
    private String color;

    /* Thuộc về plan nào */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
}
