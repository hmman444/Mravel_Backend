package com.mravel.plan.dto.board;

import com.mravel.plan.model.PlanActivityType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardRequest {

    private String text;
    private String description;

    private String start;
    private String end;

    private Integer durationMinutes;

    private PlanActivityType activityType;
    private String activityDataJson;

    private PlanCardCostDto cost;
    private PlanCardSplitConfigDto split;
}
