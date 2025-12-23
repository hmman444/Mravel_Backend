package com.mravel.auth.dto;

import com.mravel.auth.model.AccountStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserRow {
    private Long id;
    private String email;
    private String fullname;
    private String role;
    private boolean enabled;
    private AccountStatus status;
}
