package com.mravel.plan.dto.board;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

import com.mravel.plan.model.PlanActivityType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PlanCostSummaryDto {

    private Long planId;

    private String budgetCurrency;
    private Long budgetTotal;

    private Long totalEstimatedCost;
    private Long totalActualCost;

    private List<ActivityCostDto> byActivityType;
    private List<DayCostDto> byDay;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class ActivityCostDto {
        private PlanActivityType activityType;
        private Long estimatedCost;
        private Long actualCost;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class DayCostDto {
        private LocalDate date;
        private Long estimatedCost;
        private Long actualCost;
    }
}
