package com.mravel.plan.dto.board;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCardCostDto {
    private String currencyCode;

    private Long budgetAmount;

    private Long estimatedCost;
    private Long actualCost;

    private Integer participantCount;
    private List<CardPersonRefDto> participants;
    private List<ExtraCostDto> extraCosts;
}