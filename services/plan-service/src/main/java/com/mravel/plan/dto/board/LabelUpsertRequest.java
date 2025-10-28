package com.mravel.plan.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelUpsertRequest {
    private String text; // ví dụ: "Ăn uống"
    private String color; // ví dụ: "bg-green-600"
}
