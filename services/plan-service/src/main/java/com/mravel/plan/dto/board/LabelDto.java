package com.mravel.plan.dto.board;

import lombok.*;

/**
 * DTO biểu diễn một nhãn (label) trong PlanBoard.
 * Dùng để hiển thị trong Modal Label hoặc gắn trên thẻ.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelDto {
    private Long id;
    private String text;
    private String color; // ví dụ: bg-green-600
}
