package com.mravel.plan.dto;

import com.mravel.plan.model.Destination;
import com.mravel.plan.model.Visibility;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePlanRequest {

    private String title;
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    private List<Destination> destinations;
    private Visibility visibility;

    private String authorName;
    private String authorAvatar;

    private String budgetCurrency; // vd: VND, USD
    private Long budgetTotal; // ngân sách tổng
    private Long budgetPerPerson; // ngân sách / người
}
