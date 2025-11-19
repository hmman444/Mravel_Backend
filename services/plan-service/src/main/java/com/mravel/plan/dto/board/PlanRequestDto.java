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

    private String fullname;
    private String email;
    private String avatar;

    private PlanRequestType type;
    private PlanRequestStatus status;
    private Instant createdAt;
}