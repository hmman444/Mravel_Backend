package com.mravel.plan.dto.board;

import com.mravel.plan.model.PlanRequestType;

import lombok.Data;

@Data
public class PlanRequestCreate {
    private PlanRequestType type; // VIEW or EDIT
}
