package com.mravel.catalog.search.es;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import com.mravel.catalog.dto.place.PlaceDtos.PlaceSummaryDTO;
import com.mravel.catalog.dto.search.FacetBucket;
import com.mravel.catalog.dto.search.FacetedPlaceSearchRequest;
import com.mravel.catalog.dto.search.FacetedSearchResponse;
import com.mravel.catalog.dto.search.PlaceFacets;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.model.enums.PriceLevel;
import com.mravel.catalog.model.enums.VenueType;
import com.mravel.catalog.model.index.PlaceIndex;
import com.mravel.catalog.search.PlaceSearchService;
import com.mravel.catalog.search.dto.PlaceSearchResult;
import com.mravel.catalog.service.PlaceMapper;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(name = "catalog.search.engine", havingValue = "elasticsearch")
@RequiredArgsConstructor
public class ElasticsearchPlaceSearchService implements PlaceSearchService {

    private static final String PLACES_INDEX = "places";

    private final ElasticsearchOperations esOps;
    private final ElasticsearchClient esClient;

    // =========================================================
    // LEGACY SEARCH (unchanged)
    // =========================================================

    @Override
    public Page<PlaceSearchResult> search(String q, Pageable pageable) {
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(buildQuery(q))
                .withPageable(pageable)
                .build();

        SearchHits<PlaceIndex> hits = esOps.search(nativeQuery, PlaceIndex.class);
        List<PlaceSearchResult> results = hits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(ElasticsearchPlaceSearchService::toSearchResult)
                .toList();
        return new PageImpl<>(results, pageable, hits.getTotalHits());
    }

    private static Query buildQuery(String q) {
        List<Query> must = new ArrayList<>();
        must.add(Query.of(qr -> qr.term(t -> t.field("active").value(true))));
        must.add(Query.of(qr -> qr.terms(t -> t.field("kind").terms(tv -> tv.value(List.of(
                FieldValue.of(PlaceKind.DESTINATION.name()),
                FieldValue.of(PlaceKind.POI.name())))))));

        if (q != null && !q.isBlank()) {
            String qTrimmed = q.trim();
            must.add(Query.of(qr -> qr.bool(b -> b
                    .should(Query.of(s -> s.match(m -> m.field("name").query(qTrimmed))))
                    .should(Query.of(s -> s.match(m -> m.field("slug").query(qTrimmed))))
                    .should(Query.of(s -> s.match(m -> m.field("addressLine").query(qTrimmed))))
                    .should(Query.of(s -> s.match(m -> m.field("provinceName").query(qTrimmed))))
                    .should(Query.of(s -> s.match(m -> m.field("districtName").query(qTrimmed))))
                    .minimumShouldMatch("1"))));
        }

        return Query.of(qr -> qr.bool(b -> {
            must.forEach(b::must);
            return b;
        }));
    }

    static PlaceSearchResult toSearchResult(PlaceIndex p) {
        List<PlaceSearchResult.ImageRef> images = p.getImages() == null ? List.of()
                : p.getImages().stream().filter(Objects::nonNull)
                        .map(img -> new PlaceSearchResult.ImageRef(img.getUrl(), img.getCaption(), img.getCover(),
                                img.getSortOrder()))
                        .toList();

        double[] location = null;
        if (p.getLocation() != null) {
            location = new double[] { p.getLocation().getLon(), p.getLocation().getLat() };
        }

        PlaceKind kind = p.getKind() == null ? null : PlaceKind.valueOf(p.getKind());
        VenueType venueType = p.getVenueType() == null ? null : VenueType.valueOf(p.getVenueType());
        PriceLevel priceLevel = p.getPriceLevel() == null ? null : PriceLevel.valueOf(p.getPriceLevel());

        return new PlaceSearchResult(
                p.getId(), p.getName(), p.getSlug(),
                kind, venueType,
                p.getAddressLine(), p.getProvinceName(),
                location,
                priceLevel, p.getAvgRating(), p.getReviewsCount(),
                images, p.getActive());
    }

    // =========================================================
    // FACETED SEARCH
    // =========================================================

    @Override
    public FacetedSearchResponse<PlaceSummaryDTO, PlaceFacets> searchFaceted(
            FacetedPlaceSearchRequest request, Pageable pageable) {
        try {
            SearchResponse<PlaceIndex> response = esClient.search(s -> s
                    .index(PLACES_INDEX)
                    .query(buildContextQuery(request))
                    .postFilter(buildPostFilter(request))
                    .aggregations("by_category",
                            Aggregation.of(a -> a.terms(t -> t.field("categoryLabels").size(30))))
                    .aggregations("by_venue_type",
                            Aggregation.of(a -> a.terms(t -> t.field("venueType").size(10))))
                    .aggregations("by_price_level",
                            Aggregation.of(a -> a.terms(t -> t.field("priceLevel").size(5))))
                    .from(pageable.getPageNumber() * pageable.getPageSize())
                    .size(pageable.getPageSize()),
                    PlaceIndex.class);

            List<PlaceSummaryDTO> results = response.hits().hits().stream()
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .map(ElasticsearchPlaceSearchService::toSearchResult)
                    .map(PlaceMapper::toSummary)
                    .toList();

            var total = response.hits().total();
            long totalHits = total != null ? total.value() : 0L;
            int size = pageable.getPageSize() > 0 ? pageable.getPageSize() : 10;
            int totalPages = (int) Math.ceil((double) totalHits / size);

            return new FacetedSearchResponse<>(
                    results, totalHits, pageable.getPageNumber(), size, totalPages,
                    extractFacets(response.aggregations()));

        } catch (IOException e) {
            throw new RuntimeException("Faceted place search failed", e);
        }
    }

    // ── (A) Context query: active + kind + optional text ─────────────────────

    private static Query buildContextQuery(FacetedPlaceSearchRequest r) {
        List<Query> must = new ArrayList<>();
        must.add(Query.of(q -> q.term(t -> t.field("active").value(true))));

        if (r != null && r.parentSlug() != null && !r.parentSlug().isBlank()) {
            // Scoped to child POIs of a specific destination
            must.add(Query.of(q -> q.term(t -> t.field("kind").value(PlaceKind.POI.name()))));
            String ps = r.parentSlug();
            must.add(Query.of(q -> q.term(t -> t.field("parentSlug").value(ps))));
        } else {
            // Global search: include both DESTINATION and POI
            must.add(Query.of(q -> q.terms(t -> t.field("kind").terms(tv -> tv.value(List.of(
                    FieldValue.of(PlaceKind.DESTINATION.name()),
                    FieldValue.of(PlaceKind.POI.name())))))));
        }

        if (r != null && r.q() != null && !r.q().isBlank()) {
            String text = r.q().trim();
            must.add(Query.of(q -> q.bool(b -> b
                    .should(Query.of(s -> s.match(m -> m.field("name").query(text))))
                    .should(Query.of(s -> s.match(m -> m.field("provinceName").query(text))))
                    .should(Query.of(s -> s.match(m -> m.field("addressLine").query(text))))
                    .should(Query.of(s -> s.match(m -> m.field("districtName").query(text))))
                    .minimumShouldMatch("1"))));
        }

        return boolMust(must);
    }

    // ── (B) Post-filter: user-selected facets, does NOT affect agg counts ────

    private static Query buildPostFilter(FacetedPlaceSearchRequest r) {
        if (r == null)
            return Query.of(q -> q.matchAll(m -> m));
        List<Query> filters = new ArrayList<>();

        if (r.categorySlugs() != null && !r.categorySlugs().isEmpty()) {
            List<FieldValue> vals = r.categorySlugs().stream().map(FieldValue::of).toList();
            filters.add(Query.of(q -> q.terms(t -> t.field("categorySlugs").terms(tv -> tv.value(vals)))));
        }
        if (r.venueTypes() != null && !r.venueTypes().isEmpty()) {
            List<FieldValue> vals = r.venueTypes().stream().map(FieldValue::of).toList();
            filters.add(Query.of(q -> q.terms(t -> t.field("venueType").terms(tv -> tv.value(vals)))));
        }
        if (r.priceLevel() != null && !r.priceLevel().isBlank()) {
            String pl = r.priceLevel();
            filters.add(Query.of(q -> q.term(t -> t.field("priceLevel").value(pl))));
        }
        if (r.minRating() != null) {
            double mr = r.minRating();
            filters.add(Query.of(q -> q.range(rq -> rq.field("avgRating").gte(JsonData.of(mr)))));
        }

        if (filters.isEmpty())
            return Query.of(q -> q.matchAll(m -> m));
        return Query.of(q -> q.bool(b -> {
            filters.forEach(b::filter);
            return b;
        }));
    }

    // ── (C) Extract aggregations → PlaceFacets ───────────────────────────────

    private static PlaceFacets extractFacets(java.util.Map<String, Aggregate> aggs) {
        if (aggs == null)
            return new PlaceFacets(List.of(), List.of(), List.of());

        java.util.Map<String, String> errors = new java.util.LinkedHashMap<>();

        // by_category aggregates on "categoryLabels" field encoded as
        // "slug::DisplayName"
        List<FacetBucket> categories = safeAgg(aggs, "by_category", errors,
                agg -> agg.sterms().buckets().array().stream()
                        .map(b -> {
                            String encoded = b.key().stringValue(); // "kien-truc::Kiến trúc"
                            int sep = encoded.indexOf("::");
                            String slug = sep >= 0 ? encoded.substring(0, sep) : encoded;
                            String name = sep >= 0 ? encoded.substring(sep + 2) : encoded;
                            return new FacetBucket(slug, name, b.docCount());
                        })
                        .toList());

        List<FacetBucket> venueTypes = safeAgg(aggs, "by_venue_type", errors,
                agg -> agg.sterms().buckets().array().stream()
                        .map(b -> new FacetBucket(b.key().stringValue(), b.key().stringValue(), b.docCount()))
                        .toList());

        List<FacetBucket> priceLevels = safeAgg(aggs, "by_price_level", errors,
                agg -> agg.sterms().buckets().array().stream()
                        .map(b -> new FacetBucket(b.key().stringValue(), b.key().stringValue(), b.docCount()))
                        .toList());

        if (!errors.isEmpty()) {
            log.warn("[ES] Place facet extraction errors: {}", errors);
        }

        return new PlaceFacets(categories, priceLevels, venueTypes);
    }

    private static List<FacetBucket> safeAgg(
            java.util.Map<String, Aggregate> aggs,
            String name,
            java.util.Map<String, String> errors,
            Function<Aggregate, List<FacetBucket>> mapper) {
        Aggregate agg = aggs.get(name);
        if (agg == null)
            return List.of();
        try {
            return mapper.apply(agg);
        } catch (Exception ex) {
            log.warn("[ES] Place aggregation '{}' parse failed", name, ex);
            errors.put(name, "unavailable");
            return List.of();
        }
    }

    private static Query boolMust(List<Query> clauses) {
        return Query.of(q -> q.bool(b -> {
            clauses.forEach(b::must);
            return b;
        }));
    }
}
