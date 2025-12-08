package com.mravel.plan.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCardPaymentDto {
    private Long payerUserId;
    private Long amount;
    private String note;
}