package com.mravel.plan.dto.board;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BoardResponse {
    private Long planId;
    private String planTitle;
    private String myRole;

    private List<ListDto> lists;
    private String thumbnail;
    private List<String> images;
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    private String budgetCurrency;
    private Long budgetTotal;
    private Long budgetPerPerson;

    private Long totalEstimatedCost;
    private Long totalActualCost;

    private String status;

    private PlanCostSummaryDto costSummary;

    private PlanMemberCostSummaryDto memberCostSummary;

    /**
     * Phase 1d / Phase 2 — monotonically increasing per-plan revision.
     * Clients store this and use it for gap detection in the v2 event stream.
     * Additive field — existing clients that don't read it are unaffected.
     */
    private Long boardRevision;
}
