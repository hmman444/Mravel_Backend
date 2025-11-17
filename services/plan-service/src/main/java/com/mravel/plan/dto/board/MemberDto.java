package com.mravel.plan.dto.board;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {
    private Long userId;
    private String email;
    private String fullname;
    private String avatar;
    private String role; // OWNER / EDITOR / VIEWER
}
