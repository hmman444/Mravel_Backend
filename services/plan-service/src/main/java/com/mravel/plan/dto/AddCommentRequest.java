package com.mravel.plan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddCommentRequest {
    private Long userId;
    private String text;

    @JsonProperty("parentId")
    private Long parentId; // nếu có -> là reply
}
