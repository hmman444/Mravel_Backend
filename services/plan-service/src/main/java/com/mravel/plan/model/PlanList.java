package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;

@Entity
@Table(name = "plan_lists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Ví dụ: "Ngày 1" */
    @Column(nullable = false)
    private String title;

    /** Dùng để sort list theo thứ tự kéo thả */
    @Column(nullable = false)
    private Integer position;

    /** Ngày thực tế (optional, để hiển thị/ thống kê) */
    private LocalDate dayDate;

    /* Quan hệ */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position ASC")
    private List<PlanCard> cards = new ArrayList<>();
}
