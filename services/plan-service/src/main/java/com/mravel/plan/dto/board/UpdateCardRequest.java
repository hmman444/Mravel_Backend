package com.mravel.plan.dto.board;

import com.mravel.plan.model.PlanActivityType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardRequest {

    private String text;
    private String description;

    private String start; // HH:mm
    private String end; // HH:mm
    private Integer durationMinutes;

    private Boolean done;

    private PlanActivityType activityType;
    private String activityDataJson;

    private PlanCardCostDto cost;
    private PlanCardSplitConfigDto split;
}
