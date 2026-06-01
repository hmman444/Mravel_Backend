package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Bài viết (plan) bị một user ẩn riêng khỏi feed/search của chính họ.
 * Ẩn ≠ chặn: chỉ gỡ khỏi các bề mặt feed, vẫn xem được qua link trực tiếp.
 */
@Entity
@Table(name = "plan_hidden", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id",
        "plan_id" }), indexes = @Index(name = "idx_plan_hidden_user", columnList = "user_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanHidden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "plan_id", nullable = false)
    private Long planId;

    @Column(name = "hidden_at", nullable = false)
    private Instant hiddenAt;
}
