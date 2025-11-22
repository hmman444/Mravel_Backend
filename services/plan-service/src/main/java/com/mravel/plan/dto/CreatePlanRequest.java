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

    private LocalDate startDate; // yyyy-MM-dd
    private LocalDate endDate; // yyyy-MM-dd

    private List<String> images;
    private List<Destination> destinations;

    // quyền hiển thị feed/board: PRIVATE, FRIENDS, PUBLIC
    private Visibility visibility;

    // tóm tắt thông tin tác giả
    private String authorName;
    private String authorAvatar;
}
