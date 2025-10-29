package com.mravel.plan.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReorderRequest {
    // list / card
    private String type;

    // nguồn
    private Long sourceListId; // bắt buộc khi type == "card"
    private Integer sourceIndex; // index trong list

    // đích
    private Long destListId; // bắt buộc khi type == "card"
    private Integer destIndex;

    // id thẻ (card) được di chuyển
    private Long cardId; // bắt buộc khi type == "card"
}
