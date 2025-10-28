package com.mravel.plan.dto.board;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO biểu diễn một danh sách (list / ngày) trong bảng PlanBoard.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListDto {
    private Long id;
    private String title;
    private Integer position;
    private LocalDate dayDate;
    private List<CardDto> cards;
}
