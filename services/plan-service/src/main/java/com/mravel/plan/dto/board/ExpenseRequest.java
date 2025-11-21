package com.mravel.plan.dto.board;

import com.mravel.plan.model.Destination;
import com.mravel.plan.model.ExpenseCategory;

import lombok.*;

@Getter
@Setter
public class ExpenseRequest {
    private String title;
    private Long amount;
    private ExpenseCategory category;
    private String note;
    private Destination destination;
    private Long cardId;
}
