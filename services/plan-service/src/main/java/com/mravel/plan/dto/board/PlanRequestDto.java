package com.mravel.plan.dto.board;

import com.mravel.plan.model.PlanRequestStatus;
import com.mravel.plan.model.PlanRequestType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanRequestDto {

    private Long id;
    private Long userId;
    private PlanRequestType type; // VIEW or EDIT
    private PlanRequestStatus status; // PENDING / APPROVED / REJECTED
    private Instant createdAt;

    public PlanRequestDto(PlanRequest r) {
        this.id = r.getId();
        this.userId = r.getUserId();
        this.type = r.getType();
        this.status = r.getStatus();
        this.createdAt = r.getCreatedAt();
    }
}
