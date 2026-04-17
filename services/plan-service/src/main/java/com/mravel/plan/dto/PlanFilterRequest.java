package com.mravel.plan.dto;

import lombok.*;

import java.util.List;

/**
 * Encapsulates all advanced search / filter parameters received from the frontend.
 * All fields are optional — null means "no filter applied".
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanFilterRequest {

    /** Free-text query — matched against title and description. */
    private String q;

    /** Budget range (in plan's currency, typically VND). */
    private Long budgetMin;
    private Long budgetMax;

    /** Trip duration range (days). */
    private Integer daysMin;
    private Integer daysMax;

    /** Start-date range (ISO-8601, e.g. "2025-06-01"). */
    private String startDateFrom;
    private String startDateTo;

    /**
     * Destination names to include (OR semantics — plan must contain at least one).
     * e.g. ["Đà Nẵng", "Hội An"]
     */
    private List<String> destinations;

    /**
     * Sort strategy.
     * Accepted values: RELEVANCE (default), NEWEST, MOST_VIEWED, BUDGET_ASC, BUDGET_DESC.
     */
    private String sortBy;

    /**
     * Opaque pagination cursor returned by the previous response as {@code nextCursor}.
     * Null means "start from the first page".
     */
    private String cursor;

    /** Page size. */
    @Builder.Default
    private int size = 10;
}
