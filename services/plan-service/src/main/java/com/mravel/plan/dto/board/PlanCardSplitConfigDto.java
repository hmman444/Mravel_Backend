package com.mravel.plan.dto.board;

import java.util.List;

import com.mravel.plan.model.SplitType;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCardSplitConfigDto {
    private SplitType splitType;

    private Long payerId;
    private boolean includePayerInSplit;

    private List<Long> splitMembers;
    private List<CardSplitDetailDto> splitDetails;
    private List<PlanCardPaymentDto> payments;
}
