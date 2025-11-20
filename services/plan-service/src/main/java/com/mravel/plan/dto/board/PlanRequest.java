package com.mravel.plan.dto.board;

import jakarta.persistence.*;
import lombok.*;
import com.mravel.plan.model.PlanRequestStatus;
import com.mravel.plan.model.PlanRequestType;
import java.time.Instant;

@Entity
@Table(name = "plan_requests")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long planId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private PlanRequestType type; // VIEW or EDIT

    @Enumerated(EnumType.STRING)
    private PlanRequestStatus status; // PENDING, APPROVED, REJECTED

    private Instant createdAt;
}
