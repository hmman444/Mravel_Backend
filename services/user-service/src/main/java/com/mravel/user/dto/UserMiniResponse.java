package com.mravel.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMiniResponse {
    private Long id;
    private String fullname;
    private String avatar;
}