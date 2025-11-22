package com.mravel.plan.dto.board;

import com.mravel.plan.model.PlanActivityType;
import com.mravel.plan.model.SplitType;
import lombok.*;

import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;
    private String text;
    private String description;

    private LocalTime startTime;
    private LocalTime endTime;

    private boolean done;
    private Integer position;

    // Activity info
    private PlanActivityType activityType;
    private String activityDataJson;

    // Cost
    private Long estimatedCost;
    private Long actualCost;

    // Payment
    private Long payerId;
    private SplitType splitType;
    private Set<Long> splitMembers;

    private String splitResultJson;
}
