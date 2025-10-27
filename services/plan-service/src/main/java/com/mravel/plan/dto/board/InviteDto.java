package com.mravel.plan.dto.board;

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
    private String role; // viewer | editor
}
