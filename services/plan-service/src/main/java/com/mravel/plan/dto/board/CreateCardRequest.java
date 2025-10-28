package com.mravel.plan.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCardRequest {
    private String text; // tiêu đề thẻ
    private String description; // mô tả chi tiết
    private String priority; // "high" | "medium" | "low" | null
    private String start; // "HH:mm" | null
    private String end; // "HH:mm" | null
}
