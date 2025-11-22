package com.mravel.plan.dto.board;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ShareResponse {

    private String visibility;

    private List<MemberDto> members;

    private List<InviteDto> invites;
}
