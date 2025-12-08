package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plan_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String text;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalTime startTime;
    private LocalTime endTime;

    private Integer durationMinutes;

    @Builder.Default
    @Column(nullable = false)
    private boolean done = false;

    @Column(nullable = false)
    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private PlanList list;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private PlanActivityType activityType;

    @Lob
    @Column(name = "activity_data_json", columnDefinition = "TEXT")
    private String activityDataJson;

    private Integer participantCount;

    @ElementCollection
    @CollectionTable(name = "card_participants", joinColumns = @JoinColumn(name = "card_id"))
    @Column(name = "user_id")
    @Builder.Default
    private Set<Long> participants = new HashSet<>();

    @Column(name = "currency_code", length = 10)
    @Builder.Default
    private String currencyCode = "VND";

    @Column(name = "estimated_cost")
    private Long estimatedCost;

    @Column(name = "budget_amount")
    private Long budgetAmount;

    @Column(name = "actual_cost")
    private Long actualCost;

    @ElementCollection
    @CollectionTable(name = "card_extra_costs", joinColumns = @JoinColumn(name = "card_id"))
    @Builder.Default
    private Set<ExtraCost> extraCosts = new HashSet<>();

    @Column(name = "payer_id")
    private Long payerId;

    @Builder.Default
    @Column(name = "include_payer_in_split", nullable = false)
    private boolean includePayerInSplit = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "split_type")
    private SplitType splitType;

    @ElementCollection
    @CollectionTable(name = "card_split_members", joinColumns = @JoinColumn(name = "card_id"))
    @Column(name = "user_id")
    @Builder.Default
    private Set<Long> splitMembers = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "card_split_details", joinColumns = @JoinColumn(name = "card_id"))
    @Builder.Default
    private Set<CardSplitDetail> splitDetails = new HashSet<>();

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<PlanCardPayment> payments = new HashSet<>();

    @Column(name = "actual_manual")
    private Boolean actualManual; // true = user nhập, false = auto

}
