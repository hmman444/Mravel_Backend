package com.mravel.plan.dto.board;

import com.mravel.plan.model.PlanRole;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
public class InviteRequest {
    private List<String> emails;

    @Enumerated(EnumType.STRING)
    private PlanRole role;
}
