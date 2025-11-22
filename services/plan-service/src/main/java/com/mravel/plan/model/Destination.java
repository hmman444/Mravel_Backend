package com.mravel.plan.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Destination {
    private String name;
    private String address;
    private Double lat;
    private Double lng;
}
