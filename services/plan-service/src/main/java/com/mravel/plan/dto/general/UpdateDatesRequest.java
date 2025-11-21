package com.mravel.plan.dto.general;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
public class UpdateDatesRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
