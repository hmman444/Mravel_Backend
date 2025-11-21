package com.mravel.plan.dto.general;

import com.mravel.plan.model.PlanStatus;

import lombok.*;

@Getter
@Setter
public class UpdateStatusRequest {
    private PlanStatus status;
}
