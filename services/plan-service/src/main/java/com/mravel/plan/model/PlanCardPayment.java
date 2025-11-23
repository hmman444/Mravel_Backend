package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "card_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCardPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private PlanCard card;

    /**
     * Người đã trả tiền cho activity này.
     */
    @Embedded
    private CardPersonRef payer;

    @Column(name = "amount", nullable = false)
    private Long amount;

    private String note;
}
