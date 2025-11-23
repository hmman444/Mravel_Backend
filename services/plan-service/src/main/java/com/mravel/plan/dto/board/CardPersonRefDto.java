package com.mravel.plan.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardPersonRefDto {
    private Long memberId; // null = người ngoài
    private String displayName; // tên để hiển thị
    private Boolean external; // true nếu không phải PlanMember
}
