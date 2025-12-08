package com.mravel.plan.dto.board;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateListRequest {
    private String title;
    private LocalDate dayDate;
}
