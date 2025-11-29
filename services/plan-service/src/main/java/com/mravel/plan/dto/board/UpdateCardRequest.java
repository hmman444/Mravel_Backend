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
public class UpdateCardRequest {

    private String text;
    private String description;

    // Đồng bộ với PlanCard.startTime / endTime
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer durationMinutes;

    private Boolean done;

    private PlanActivityType activityType;
    private String activityDataJson;

    // ============ THAM GIA HOẠT ĐỘNG ============
    /**
     * FE có thể không gửi -> BE tự tính từ participants.size()
     */
    private Integer participantCount;

    /**
     * Danh sách thành viên tham gia hoạt động.
     */
    private Set<CardPersonRef> participants;

    // ============ CHI PHÍ & CHIA TIỀN ============
    private PlanCardCostDto cost;
    private PlanCardSplitConfigDto split;
}
