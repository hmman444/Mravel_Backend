package com.mravel.plan.dto.board;

import lombok.*;
import java.time.LocalDate;

import com.mravel.plan.model.PlanRole;
import com.mravel.plan.model.PlanStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPlanDto {

    private Long id;

    private String title;
    private String description;

    private String owner;
    private int members;

    private Long totalEstimatedCost;
    private Long totalActualCost;

    private String budgetCurrency;
    private Long budgetTotal;
    private LocalDate startDate;
    private LocalDate endDate;

    private String thumbnail;

    private PlanStatus status;
    private PlanRole myRole;
}
