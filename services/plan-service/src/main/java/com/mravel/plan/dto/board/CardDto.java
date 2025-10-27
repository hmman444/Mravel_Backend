package com.mravel.plan.dto.board;

import lombok.*;

import java.time.LocalTime;
import java.util.Set;

/**
 * DTO biểu diễn một thẻ (card) trong PlanList.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    private Long id;
    private String text;
    private String description;
    private String priority; // high | medium | low
    private LocalTime start;
    private LocalTime end;
    private boolean done;
    private Integer position;
    private Set<LabelDto> labels;
}
