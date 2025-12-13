package com.mravel.plan.dto.board;

import com.mravel.plan.model.PlanActivityType;
import lombok.*;

import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardRequest {

    private String text;
    private String description;

    private LocalTime startTime;
    private LocalTime endTime;

    private Integer durationMinutes;

    private PlanActivityType activityType;
    private String activityDataJson;

    private Integer participantCount;

    private Set<Long> participants;

    private PlanCardCostDto cost;
    private PlanCardSplitConfigDto split;
}
