package com.mravel.plan.dto.board;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

import com.mravel.plan.model.PlanActivityType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCostSummaryDto {

    private Long planId;

    private String budgetCurrency;
    private Long budgetTotal;

    private Long totalEstimatedCost;
    private Long totalActualCost;

    private List<ActivityCostDto> byActivityType;
    private List<DayCostDto> byDay;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ActivityCostDto {
        private PlanActivityType activityType;
        private Long estimatedCost;
        private Long actualCost;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DayCostDto {
        private LocalDate date;
        private Long estimatedCost;
        private Long actualCost;
    }
}
