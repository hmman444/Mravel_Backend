package com.mravel.plan.dto;

import lombok.Data;

@Data
public class AddCommentRequest {
    private String userId;
    private String userName;
    private String userAvatar;
    private String text;
}
