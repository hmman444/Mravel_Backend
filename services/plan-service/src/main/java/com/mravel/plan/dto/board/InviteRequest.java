package com.mravel.plan.dto.board;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InviteRequest {
    private List<String> emails;
    private String role; // "editor" | "viewer"
}
