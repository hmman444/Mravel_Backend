package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "plan_members", uniqueConstraints = @UniqueConstraint(columnNames = { "plan_id", "user_id" }))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // id ngdung lấy từ jwt
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // quyền
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private PlanRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
}
