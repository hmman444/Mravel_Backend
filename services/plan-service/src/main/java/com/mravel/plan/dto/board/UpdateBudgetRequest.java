package com.mravel.plan.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBudgetRequest {
    private String budgetCurrency;
    private Long budgetTotal;
    private Long budgetPerPerson;
}
