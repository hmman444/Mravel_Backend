package com.mravel.plan.dto.board;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponse {
    private Long planId;
    private String planTitle;
    private List<ListDto> lists;
    private List<LabelDto> labels;
    private List<InviteDto> invites;
}
