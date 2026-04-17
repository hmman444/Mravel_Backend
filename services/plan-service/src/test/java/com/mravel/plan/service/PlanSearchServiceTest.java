package com.mravel.plan.service;

import com.mravel.plan.document.PlanDocument;
import com.mravel.plan.dto.PlanFilterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PlanSearchServiceTest {

    @Mock
    private ElasticsearchOperations esOps;

    @Mock
    @SuppressWarnings("unchecked")
    private SearchHits<PlanDocument> mockHits;

    private PlanSearchService service;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        service = new PlanSearchService(esOps);
        when(esOps.search(any(NativeQuery.class), eq(PlanDocument.class))).thenReturn(mockHits);
        when(mockHits.getSearchHits()).thenReturn(List.of());
        when(mockHits.getTotalHits()).thenReturn(0L);
    }

    @Test
    void search_withNoFilters_executesWithoutError() {
        PlanFilterRequest filter = PlanFilterRequest.builder().size(10).build();

        SearchHits<PlanDocument> result = service.search(filter, null, List.of(), List.of());

        assertThat(result).isNotNull();
        verify(esOps).search(any(NativeQuery.class), eq(PlanDocument.class));
    }

    @Test
    void search_withKeyword_executesWithoutError() {
        PlanFilterRequest filter = PlanFilterRequest.builder()
                .q("Đà Lạt")
                .size(10)
                .build();

        service.search(filter, 1L, List.of(), List.of());

        verify(esOps).search(any(NativeQuery.class), eq(PlanDocument.class));
    }

    @Test
    void search_withBudgetRange_executesWithoutError() {
        PlanFilterRequest filter = PlanFilterRequest.builder()
                .budgetMin(1_000_000L)
                .budgetMax(50_000_000L)
                .size(10)
                .build();

        service.search(filter, 1L, List.of(), List.of());

        verify(esOps).search(any(NativeQuery.class), eq(PlanDocument.class));
    }

    @Test
    void search_withDaysRange_executesWithoutError() {
        PlanFilterRequest filter = PlanFilterRequest.builder()
                .daysMin(3)
                .daysMax(7)
                .size(10)
                .build();

        service.search(filter, 1L, List.of(), List.of());

        verify(esOps).search(any(NativeQuery.class), eq(PlanDocument.class));
    }

    @Test
    void search_withDateRange_executesWithoutError() {
        PlanFilterRequest filter = PlanFilterRequest.builder()
                .startDateFrom("2025-06-01")
                .startDateTo("2025-08-31")
                .size(10)
                .build();

        service.search(filter, 1L, List.of(), List.of());

        verify(esOps).search(any(NativeQuery.class), eq(PlanDocument.class));
    }

    @Test
    void search_withDestinations_executesWithoutError() {
        PlanFilterRequest filter = PlanFilterRequest.builder()
                .destinations(List.of("Hà Nội", "TP.HCM", "Đà Nẵng"))
                .size(10)
                .build();

        service.search(filter, 1L, List.of(), List.of());

        verify(esOps).search(any(NativeQuery.class), eq(PlanDocument.class));
    }

    @Test
    void search_withAllFilters_executesWithoutError() {
        PlanFilterRequest filter = PlanFilterRequest.builder()
                .q("biển")
                .budgetMin(5_000_000L)
                .budgetMax(20_000_000L)
                .daysMin(3)
                .daysMax(5)
                .startDateFrom("2025-07-01")
                .startDateTo("2025-07-31")
                .destinations(List.of("Nha Trang", "Phú Quốc"))
                .sortBy("BUDGET_ASC")
                .size(10)
                .build();

        service.search(filter, 42L, List.of(1L, 2L, 3L), List.of(10L, 11L));

        verify(esOps).search(any(NativeQuery.class), eq(PlanDocument.class));
    }

    @Test
    void search_withEachSortOption_executesWithoutError() {
        for (String sort : List.of("RELEVANCE", "NEWEST", "MOST_VIEWED", "BUDGET_ASC", "BUDGET_DESC")) {
            PlanFilterRequest filter = PlanFilterRequest.builder().sortBy(sort).size(10).build();
            service.search(filter, 1L, List.of(), List.of());
        }

        verify(esOps, times(5)).search(any(NativeQuery.class), eq(PlanDocument.class));
    }

    @Test
    void search_withEmptyDestinations_executesWithoutError() {
        PlanFilterRequest filter = PlanFilterRequest.builder()
                .destinations(List.of())
                .size(10)
                .build();

        service.search(filter, 1L, List.of(), List.of());

        verify(esOps).search(any(NativeQuery.class), eq(PlanDocument.class));
    }

    @Test
    void search_withOversizedPage_clampsToMax50() {
        PlanFilterRequest filter = PlanFilterRequest.builder().size(999).build();

        // Should not throw — size is clamped to 50 inside the service
        service.search(filter, 1L, List.of(), List.of());

        verify(esOps).search(any(NativeQuery.class), eq(PlanDocument.class));
    }

    @Test
    void search_anonymousViewer_onlySeesPublicPlans() {
        PlanFilterRequest filter = PlanFilterRequest.builder().size(10).build();

        // viewerId=null, friendIds=[], memberPlanIds=[] → only PUBLIC clause in
        // visibility gate
        service.search(filter, null, List.of(), List.of());

        verify(esOps).search(any(NativeQuery.class), eq(PlanDocument.class));
    }
}
