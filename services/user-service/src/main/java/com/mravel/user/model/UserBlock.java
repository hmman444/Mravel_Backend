package com.mravel.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Quan hệ chặn (block) một chiều: blockerId đã chặn blockedId.
 * Việc thực thi "vô hình hai chiều" (mutual) được suy ra ở tầng đọc
 * bằng cách hợp nhất cả hai chiều (xem UserBlockRepository.findAllRelatedBlockedIds).
 */
@Entity
@Table(name = "user_blocks", uniqueConstraints = @UniqueConstraint(columnNames = { "blocker_id",
        "blocked_id" }), indexes = {
                @Index(name = "idx_user_block_blocked", columnList = "blocked_id"),
                @Index(name = "idx_user_block_blocker", columnList = "blocker_id")
        })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blocker_id", nullable = false)
    private Long blockerId;

    @Column(name = "blocked_id", nullable = false)
    private Long blockedId;

    private LocalDateTime createdAt;
}
