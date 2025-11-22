package com.mravel.plan.dto.board;

import com.mravel.plan.model.PlanRole;
import jakarta.persistence.*;
import lombok.*;

/**
 * DTO biểu diễn thành viên được mời vào kế hoạch.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InviteDto {
    private Long id;
    private String email;

    @Enumerated(EnumType.STRING)
    private PlanRole role;
}
