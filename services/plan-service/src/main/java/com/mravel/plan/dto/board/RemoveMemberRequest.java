package com.mravel.plan.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveMemberRequest {
    private Long userId; // user cần xóa khỏi plan
}
