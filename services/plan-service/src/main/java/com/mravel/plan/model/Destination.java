package com.mravel.plan.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Destination {
    private String name;
    private String lat;
    private String lng;
}
