package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Trạng thái throttle lượt xem của 1 user cho 1 plan: lưu thời điểm xem được tính
 * gần nhất (viewedAt). UNIQUE(user_id, plan_id) => mỗi cặp giữ đúng 1 bản ghi.
 * Lượt xem chỉ được tính lại khi lần xem mới cách viewedAt quá cửa sổ (mặc định 4h).
 * Tách khỏi PlanRecentView (danh sách xem gần đây, có thể bị user xoá).
 */
@Entity
@Table(name = "plan_views", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id",
        "plan_id" }), indexes = @Index(name = "idx_plan_view_plan", columnList = "plan_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "plan_id", nullable = false)
    private Long planId;

    @Column(name = "viewed_at", nullable = false)
    private Instant viewedAt;
}
