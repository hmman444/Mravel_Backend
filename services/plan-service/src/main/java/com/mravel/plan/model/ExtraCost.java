package com.mravel.plan.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtraCost {

    private String reason;

    private String type;

    private Long estimatedAmount;

    private Long actualAmount;
}
