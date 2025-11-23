package com.mravel.plan.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardPersonRef {
    private Long memberId; // null nếu là người ngoài
    private String displayName; // tên hiển thị
    private Boolean external; // true nếu không phải PlanMember
}
