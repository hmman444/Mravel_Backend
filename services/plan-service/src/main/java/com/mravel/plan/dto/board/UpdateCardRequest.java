package com.mravel.plan.dto.board;

import com.mravel.plan.model.PlanActivityType;
import com.mravel.plan.model.SplitType;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardRequest {

    private String text;
    private String description;

    private String start; // HH:mm
    private String end;

    private Boolean done;

    private PlanActivityType activityType;
    private String activityDataJson;

    private Long estimatedCost;
    private Long actualCost;

    // Payment
    private Long payerId;
    private SplitType splitType;
    private Set<Long> splitMembers;

    private String splitResultJson; // gửi lên khi FE tính lại
}
