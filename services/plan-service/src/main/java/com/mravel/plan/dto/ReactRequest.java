package com.mravel.plan.dto;

import lombok.Data;

@Data
public class ReactRequest {
    private String key; // like, love, etc.
    private Long userId;
    private String userName;
    private String userAvatar;
}
