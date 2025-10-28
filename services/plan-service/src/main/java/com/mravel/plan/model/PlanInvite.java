package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Đại diện cho lời mời tham gia kế hoạch (Plan).
 * Ví dụ: { email: "lan@example.com", role: "editor" }
 */
@Entity
@Table(name = "plan_invites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Email người được mời */
    @Column(nullable = false)
    private String email;

    /** Quyền hạn trong plan: viewer | editor */
    @Column(nullable = false, length = 20)
    private String role;

    /** Kế hoạch mà lời mời thuộc về */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
}
