package com.mravel.plan.dto.board;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanMemberCostSummaryDto {

    private Long planId;

    private String budgetCurrency;
    private Long budgetTotal;
    private Long budgetPerPerson;
    private Long totalActualCost;

    private List<MemberCostDto> members;

    @Data
    @Builder
    public static class MemberCostDto {
        private Long userId;
        private String fullname;
        private String email;
        private String avatar;
        private String role; // OWNER / EDITOR / VIEWER

        private Long shareActual; // tổng phần chi phí người này phải trả (sum splitDetails.amount)
        private Integer activityCount; // số activity có mặt trong split
        private Long memberBudget; // ngân sách dự kiến cho người này (plan.budgetPerPerson)
    }
}
