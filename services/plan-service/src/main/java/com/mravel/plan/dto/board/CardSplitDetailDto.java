package com.mravel.plan.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardSplitDetailDto {
    private CardPersonRefDto person;
    private Long amount;
}
