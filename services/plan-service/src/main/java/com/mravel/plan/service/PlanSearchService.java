package com.mravel.plan.service;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.json.JsonData;
import com.mravel.plan.document.PlanDocument;
import com.mravel.plan.dto.PlanFilterRequest;
import com.mravel.plan.util.CursorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Executes advanced Plan searches against Elasticsearch.
 *
 * Visibility contract (enforced in every query):
 * A plan is visible to viewerId when ANY of these hold:
 * 1. visibility = PUBLIC
 * 2. visibility = FRIENDS AND authorId in friendIds
 * 3. authorId = viewerId
 * 4. planId in memberPlanIds
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlanSearchService {

    private final ElasticsearchOperations esOps;

    /**
     * @param filter        filter params from the API (null fields = no filter
     *                      applied)
     * @param viewerId      authenticated user ID (null for anonymous)
     * @param friendIds     IDs of the viewer's friends (empty if none/anonymous)
     * @param memberPlanIds plan IDs where the viewer is a non-owner member
     */
    public SearchHits<PlanDocument> search(
            PlanFilterRequest filter,
            Long viewerId,
            List<Long> friendIds,
            List<Long> memberPlanIds) {

        BoolQuery.Builder root = new BoolQuery.Builder();

        // ── 1. Visibility gate — always applied ─────────────────────────────────
        root.must(visibilityQuery(viewerId, friendIds, memberPlanIds));

        // ── 2. Full-text search ──────────────────────────────────────────────────
        String q = filter.getQ();
        if (q != null && !q.isBlank()) {
            String rawPattern = wildcardPattern(q.trim());
            root.must(Query.of(mq -> mq.bool(b -> b
                    .should(s -> s.multiMatch(mm -> mm
                            .query(q.trim())
                            .fields("title^3", "description^1")
                            .type(TextQueryType.PhrasePrefix)))
                    .should(s -> s.wildcard(w -> w
                            .field("titleRaw")
                            .value(rawPattern)
                            .caseInsensitive(true)
                            .boost(2.5f)))
                    .should(s -> s.wildcard(w -> w
                            .field("descriptionRaw")
                            .value(rawPattern)
                            .caseInsensitive(true)
                            .boost(2.0f)))
                    .should(s -> s.wildcard(w -> w
                            .field("destinationNames")
                            .value(rawPattern)
                            .caseInsensitive(true)
                            .boost(2.0f))))));
        }

        // ── 3. Budget range ──────────────────────────────────────────────────────
        if (filter.getBudgetMin() != null || filter.getBudgetMax() != null) {
            root.filter(Query.of(fq -> fq.range(r -> {
                r.field("budgetTotal");
                if (filter.getBudgetMin() != null)
                    r.gte(JsonData.of(filter.getBudgetMin()));
                if (filter.getBudgetMax() != null)
                    r.lte(JsonData.of(filter.getBudgetMax()));
                return r;
            })));
        }

        // ── 4. Duration range (days) ─────────────────────────────────────────────
        if (filter.getDaysMin() != null || filter.getDaysMax() != null) {
            root.filter(Query.of(fq -> fq.range(r -> {
                r.field("days");
                if (filter.getDaysMin() != null)
                    r.gte(JsonData.of(filter.getDaysMin()));
                if (filter.getDaysMax() != null)
                    r.lte(JsonData.of(filter.getDaysMax()));
                return r;
            })));
        }

        // ── 5. Start-date range ──────────────────────────────────────────────────
        if (filter.getStartDateFrom() != null || filter.getStartDateTo() != null) {
            root.filter(Query.of(fq -> fq.range(r -> {
                r.field("startDate");
                if (filter.getStartDateFrom() != null)
                    r.gte(JsonData.of(LocalDate.parse(filter.getStartDateFrom()).toString()));
                if (filter.getStartDateTo() != null)
                    r.lte(JsonData.of(LocalDate.parse(filter.getStartDateTo()).toString()));
                return r;
            })));
        }

        // ── 6. Destination filter (OR — any matching destination) ────────────────
        // Uses case-insensitive wildcard per destination so that user-entered names
        // like "TP.HCM" match stored values like "tp hcm" or "TP. HCM".
        List<String> dests = filter.getDestinations();
        if (dests != null && !dests.isEmpty()) {
            List<Query> destClauses = dests.stream()
                    .filter(d -> d != null && !d.isBlank())
                    .map(d -> {
                        String pattern = "*" + d.trim().toLowerCase() + "*";
                        return Query.of(dq -> dq.bool(b -> b
                                .should(ex -> ex.term(t -> t
                                        .field("destinationNames").value(d.trim())))
                                .should(wi -> wi.wildcard(w -> w
                                        .field("destinationNames")
                                        .value(pattern)
                                        .caseInsensitive(true)))
                                .minimumShouldMatch("1")));
                    })
                    .toList();
            if (!destClauses.isEmpty()) {
                root.filter(Query.of(fq -> fq.bool(b -> b
                        .should(destClauses)
                        .minimumShouldMatch("1"))));
            }
        }

        // ── 7. Build NativeQuery ─────────────────────────────────────────────────
        int pageSize = Math.max(1, Math.min(50, filter.getSize()));

        NativeQueryBuilder nqBuilder = new NativeQueryBuilder()
                .withQuery(Query.of(fq -> fq.bool(root.build())))
                // from=0 is always correct with search_after; ES ignores `from` when
                // search_after is present, but setting size via Pageable is required.
                .withPageable(PageRequest.of(0, pageSize));

        // ── 8. Sort — ALWAYS includes a stable _id tie-breaker ───────────────────
        // This guarantees deterministic ordering, which is mandatory for search_after.
        String sortBy = filter.getSortBy();
        List<SortOptions> sorts = buildSort(sortBy);
        sorts.forEach(nqBuilder::withSort);

        // ── 9. Apply decoded cursor (search_after) ───────────────────────────────
        List<Object> searchAfterValues = CursorUtils.decode(filter.getCursor());
        if (searchAfterValues != null) {
            nqBuilder.withSearchAfter(searchAfterValues);
        }

        NativeQuery nativeQuery = nqBuilder.build();
        return esOps.search(nativeQuery, PlanDocument.class);
    }

    // ─────────────────────────── helpers ────────────────────────────────────────

    private Query visibilityQuery(Long viewerId, List<Long> friendIds, List<Long> memberPlanIds) {
        List<Query> shouldClauses = new ArrayList<>();

        // 1. PUBLIC plans visible to everyone
        shouldClauses.add(Query.of(q -> q.term(t -> t.field("visibility").value("PUBLIC"))));

        // 2. FRIENDS plans from authors who are friends with the viewer
        if (friendIds != null && !friendIds.isEmpty()) {
            shouldClauses.add(Query.of(q -> q.bool(b -> b
                    .must(Query.of(m -> m.term(t -> t.field("visibility").value("FRIENDS"))))
                    .must(Query.of(m -> m.terms(t -> t
                            .field("authorId")
                            .terms(tv -> tv.value(
                                    friendIds.stream().map(FieldValue::of).toList()))))))));
        }

        // 3. Viewer's own plans (any visibility)
        if (viewerId != null) {
            shouldClauses.add(Query.of(q -> q.term(t -> t
                    .field("authorId").value(viewerId))));
        }

        // 4. Plans where viewer is a non-owner member
        if (memberPlanIds != null && !memberPlanIds.isEmpty()) {
            List<String> validIds = memberPlanIds.stream()
                    .filter(id -> id > 0)
                    .map(String::valueOf)
                    .toList();
            if (!validIds.isEmpty()) {
                shouldClauses.add(Query.of(q -> q.terms(t -> t
                        .field("id")
                        .terms(tv -> tv.value(
                                validIds.stream().map(FieldValue::of).toList())))));
            }
        }

        final List<Query> clauses = shouldClauses;
        return Query.of(q -> q.bool(b -> b
                .should(clauses)
                .minimumShouldMatch("1")));
    }

    /**
     * Builds a deterministic sort specification for {@code search_after}.
     *
     * <p>
     * Every configuration ends with a stable {@code id.keyword ASC} tie-breaker so
     * that
     * equal primary-sort values always resolve in the same order, making
     * {@code search_after} cursors stable across pages.
     *
     * <p>
     * RELEVANCE uses {@code _score DESC → createdAt DESC → id.keyword ASC}.
     */
    private List<SortOptions> buildSort(String sortBy) {
        // Stable id.keyword tie-breaker — appended to every sort configuration
        SortOptions idTieBreaker = SortOptions.of(s -> s.field(f -> f
                .field("id.keyword").order(SortOrder.Asc)));

        if (sortBy == null || sortBy.isBlank() || "RELEVANCE".equalsIgnoreCase(sortBy)) {
            // _score DESC → createdAt DESC → id.keyword ASC
            return List.of(
                    SortOptions.of(s -> s.score(sc -> sc.order(SortOrder.Desc))),
                    SortOptions.of(s -> s.field(f -> f.field("createdAt").order(SortOrder.Desc))),
                    idTieBreaker);
        }

        return switch (sortBy.toUpperCase()) {
            case "NEWEST" -> List.of(
                    SortOptions.of(s -> s.field(f -> f.field("createdAt").order(SortOrder.Desc))),
                    idTieBreaker);
            case "MOST_VIEWED" -> List.of(
                    SortOptions.of(s -> s.field(f -> f.field("views").order(SortOrder.Desc))),
                    SortOptions.of(s -> s.field(f -> f.field("createdAt").order(SortOrder.Desc))),
                    idTieBreaker);
            case "BUDGET_ASC" -> List.of(
                    SortOptions.of(s -> s.field(f -> f.field("budgetTotal").order(SortOrder.Asc))),
                    idTieBreaker);
            case "BUDGET_DESC" -> List.of(
                    SortOptions.of(s -> s.field(f -> f.field("budgetTotal").order(SortOrder.Desc))),
                    idTieBreaker);
            default -> List.of(
                    SortOptions.of(s -> s.field(f -> f.field("createdAt").order(SortOrder.Desc))),
                    idTieBreaker);
        };
    }

    private String wildcardPattern(String input) {
        if (input == null || input.isBlank()) {
            return "*";
        }

        String escaped = input
                .replace("\\", "\\\\")
                .replace("*", "\\*")
                .replace("?", "\\?");
        return "*" + escaped + "*";
    }
}
