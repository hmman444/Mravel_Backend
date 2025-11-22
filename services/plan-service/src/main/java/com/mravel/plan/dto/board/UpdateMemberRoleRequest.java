package com.mravel.plan.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMemberRoleRequest {
    private Long userId;
    private String role; // EDITOR hoặc VIEWER
}
