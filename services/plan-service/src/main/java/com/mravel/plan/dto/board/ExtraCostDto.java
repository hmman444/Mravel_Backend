package com.mravel.plan.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtraCostDto {
    private String reason;
    private String type; // SURCHARGE, TAX, SERVICE_FEE, ...
    private Long estimatedAmount;
    private Long actualAmount;
}
