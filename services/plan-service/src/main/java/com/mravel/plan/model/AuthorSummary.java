package com.mravel.plan.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorSummary {

    @Column(name = "author_id")
    private Long id;

    @Column(name = "author_name")
    private String name;

    @Column(name = "author_avatar")
    private String avatar;
}
