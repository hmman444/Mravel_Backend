package com.mravel.plan.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RenameListRequest {
    private String title;
}
