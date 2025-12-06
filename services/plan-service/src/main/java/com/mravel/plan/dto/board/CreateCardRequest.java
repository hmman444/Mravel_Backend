package com.mravel.plan.dto.board;

import com.mravel.plan.model.CardPersonRef;
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

    // đồng bộ với PlanCard.startTime / endTime
    private LocalTime startTime;
    private LocalTime endTime;

    private Integer durationMinutes;

    private PlanActivityType activityType;
    private String activityDataJson;

    /**
     * FE có thể gửi kèm, hoặc BE tự tính từ participants.
     */
    private Integer participantCount;

    /**
     * Danh sách thành viên tham gia hoạt động.
     * Có thể map thẳng sang PlanCard.participants.
     */
    private Set<CardPersonRef> participants;

    private PlanCardCostDto cost;
    private PlanCardSplitConfigDto split;
}
