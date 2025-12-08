package com.mravel.plan.dto.board;

import com.mravel.plan.model.PlanActivityType;
import lombok.*;

import java.time.LocalTime;
import java.util.Set;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;
    private Long listId;

    private String text;
    private String description;

    private LocalTime startTime;
    private LocalTime endTime;
    private Integer durationMinutes;

    private boolean done;
    private Integer position;

    private PlanActivityType activityType;
    private String activityDataJson;

    private Integer participantCount;

    private Set<Long> participants;

    // chi phí và chia tiền
    private PlanCardCostDto cost;
    private PlanCardSplitConfigDto split;
}
