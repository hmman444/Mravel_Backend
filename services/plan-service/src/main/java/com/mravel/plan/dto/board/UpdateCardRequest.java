package com.mravel.plan.dto.board;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCardRequest {
    private String text;
    private String description;
    private String priority; // "high" | "medium" | "low" | null
    private String start; // "HH:mm" | null
    private String end; // "HH:mm" | null
    private Boolean done; // true/false
    private Set<Long> labelIds; // các label gán cho card
}
