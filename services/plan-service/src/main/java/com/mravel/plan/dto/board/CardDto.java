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

    /**
     * Số người tham gia hoạt động này.
     * Nếu null, FE có thể tính từ participants.size().
     */
    private Integer participantCount;

    /**
     * Danh sách thành viên tham gia hoạt động (PlanMember.userId...).
     */
    private Set<CardPersonRef> participants;

    // chi phí và chia tiền
    private PlanCardCostDto cost;
    private PlanCardSplitConfigDto split;
}
