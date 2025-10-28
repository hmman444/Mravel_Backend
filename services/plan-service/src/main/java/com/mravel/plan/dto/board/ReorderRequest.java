package com.mravel.plan.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReorderRequest {
    /** "list" | "card" */
    private String type;

    /** vị trí nguồn */
    private Long sourceListId; // bắt buộc khi type == "card"
    private Integer sourceIndex; // index trong list

    /** vị trí đích */
    private Long destListId; // bắt buộc khi type == "card"
    private Integer destIndex;

    /** id thẻ khi kéo thả card */
    private Long cardId; // bắt buộc khi type == "card"
}
