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

import com.mravel.catalog.dto.SearchRequests.HotelSearchRequest;
import com.mravel.catalog.dto.hotel.HotelDtos.HotelSummaryDTO;
import com.mravel.catalog.dto.search.FacetBucket;
import com.mravel.catalog.dto.search.FacetedHotelSearchRequest;
import com.mravel.catalog.dto.search.FacetedSearchResponse;
import com.mravel.catalog.dto.search.HotelFacets;
import com.mravel.catalog.model.index.HotelIndex;
import com.mravel.catalog.search.HotelSearchService;
import com.mravel.catalog.search.dto.HotelSearchResult;
import com.mravel.catalog.service.HotelMapper;
import com.mravel.common.i18n.LocaleConstants;
import com.mravel.common.i18n.LocaleContext;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.AggregationRange;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(name = "catalog.search.engine", havingValue = "elasticsearch")
@RequiredArgsConstructor
public class ElasticsearchHotelSearchService implements HotelSearchService {

    private static final String HOTELS_INDEX = "hotels";

    private final ElasticsearchOperations esOps;
    private final ElasticsearchClient esClient;

    @Override
    public Page<HotelSearchResult> search(HotelSearchRequest request, Pageable pageable) {
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(buildQuery(request))
                .withPageable(pageable)
                .build();

        SearchHits<HotelIndex> hits = esOps.search(nativeQuery, HotelIndex.class);
        List<HotelSearchResult> results = hits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(ElasticsearchHotelSearchService::toSearchResult)
                .toList();
        return new PageImpl<>(results, pageable, hits.getTotalHits());
    }

    private static Query buildQuery(HotelSearchRequest r) {
        List<Query> must = new ArrayList<>();
        must.add(Query.of(q -> q.term(t -> t.field("active").value(true))));
        must.add(Query.of(q -> q.term(t -> t.field("moderationStatus").value("APPROVED"))));

        if (r == null) {
            return boolMust(must);
        }

        if (r.location() != null && !r.location().isBlank()) {
            String loc = r.location().trim();
            if (Boolean.TRUE.equals(r.destOnly())) {
                must.add(Query.of(q -> q.term(t -> t.field("destinationSlug").value(loc))));
            } else {
                must.add(Query.of(q -> q.bool(b -> b
                        .should(Query.of(s -> s.multiMatch(m -> m
                                .fields("nameVi^4", "nameEn^4",
                                        "cityNameVi^2", "cityNameEn^2",
                                        "districtNameVi^1", "districtNameEn^1",
                                        "addressLineVi^1", "addressLineEn^1")
                                .query(loc)
                                .fuzziness("AUTO")
                                .type(TextQueryType.BestFields))))
                        .should(Query.of(s -> s.multiMatch(m -> m
                                .fields("nameVi^4", "nameEn^4",
                                        "cityNameVi^2", "cityNameEn^2",
                                        "districtNameVi^1", "districtNameEn^1",
                                        "addressLineVi^1", "addressLineEn^1")
                                .query(loc)
                                .type(TextQueryType.BoolPrefix))))
                        .should(Query.of(s -> s.term(t -> t.field("destinationSlug").value(loc))))
                        .minimumShouldMatch("1"))));
            }
        }

        Integer minGuestsPerRoom = r.calcMinGuestsPerRoom();
        Integer requiredRooms = r.rooms() != null && r.rooms() > 0 ? r.rooms() : null;
        if (minGuestsPerRoom != null || requiredRooms != null) {
            Integer mg = minGuestsPerRoom;
            Integer rr = requiredRooms;
            List<Query> roomClauses = new ArrayList<>();
            if (mg != null) roomClauses.add(Query.of(q -> q.range(rq -> rq.field("roomTypes.maxGuests").gte(JsonData.of(mg)))));
            if (rr != null) roomClauses.add(Query.of(q -> q.range(rq -> rq.field("roomTypes.totalRooms").gte(JsonData.of(rr)))));
            must.add(Query.of(q -> q.nested(n -> n
                    .path("roomTypes")
                    .query(boolMust(roomClauses)))));
        }

        if (r.minPrice() != null) {
            int mp = r.minPrice();
            must.add(Query.of(q -> q.range(rq -> rq.field("minNightlyPrice").gte(JsonData.of(mp)))));
        }
        if (r.maxPrice() != null) {
            int mx = r.maxPrice();
            must.add(Query.of(q -> q.range(rq -> rq.field("minNightlyPrice").lte(JsonData.of(mx)))));
        }
        if (r.minRating() != null) {
            double mr = r.minRating();
            must.add(Query.of(q -> q.range(rq -> rq.field("avgRating").gte(JsonData.of(mr)))));
        }
        if (r.starRating() != null && !r.starRating().isEmpty()) {
            List<co.elastic.clients.elasticsearch._types.FieldValue> values = r.starRating().stream()
                    .map(v -> co.elastic.clients.elasticsearch._types.FieldValue.of(v.longValue()))
                    .toList();
            must.add(Query.of(q -> q.terms(t -> t.field("starRating").terms(tv -> tv.value(values)))));
        }
        if (r.hotelType() != null && !r.hotelType().isBlank()) {
            String ht = r.hotelType().trim().toUpperCase();
            must.add(Query.of(q -> q.term(t -> t.field("hotelType").value(ht))));
        }
        if (r.amenityCodes() != null && !r.amenityCodes().isEmpty()) {
            for (String code : r.amenityCodes()) {
                must.add(Query.of(q -> q.term(t -> t.field("amenityCodes").value(code))));
            }
        }

        return boolMust(must);
    }

    private static Query boolMust(List<Query> clauses) {
        return Query.of(q -> q.bool(b -> {
            clauses.forEach(b::must);
            return b;
        }));
    }

    static HotelSearchResult toSearchResult(HotelIndex h) {
        boolean en = LocaleConstants.EN.equals(LocaleContext.get());

        HotelSearchResult.GeneralInfoRef generalInfo = h.getGeneralInfo() == null ? null
                : new HotelSearchResult.GeneralInfoRef(
                        preferEn(h.getGeneralInfo().getMainFacilitiesSummaryEn(),
                                h.getGeneralInfo().getMainFacilitiesSummaryVi(), en),
                        h.getGeneralInfo().getDistanceToCityCenterKm());

        List<HotelSearchResult.ImageRef> images = h.getImages() == null ? List.of()
                : h.getImages().stream()
                        .filter(Objects::nonNull)
                        .map(img -> new HotelSearchResult.ImageRef(
                                img.getUrl(),
                                preferEn(img.getCaptionEn(), img.getCaptionVi(), en),
                                img.getCover(),
                                img.getSortOrder()))
                        .toList();

        List<HotelSearchResult.RoomTypeMini> roomTypes = h.getRoomTypes() == null ? List.of()
                : h.getRoomTypes().stream()
                        .filter(Objects::nonNull)
                        .map(rt -> new HotelSearchResult.RoomTypeMini(rt.getId(), rt.getMaxGuests(), rt.getTotalRooms()))
                        .toList();

        double[] location = null;
        if (h.getLocation() != null) {
            location = new double[]{h.getLocation().getLon(), h.getLocation().getLat()};
        }

        return new HotelSearchResult(
                h.getId(),
                preferEn(h.getNameEn(), h.getNameVi(), en),
                h.getSlug(),
                h.getActive(),
                h.getStarRating(),
                h.getHotelType(),
                h.getDestinationSlug(),
                preferEn(h.getCityNameEn(), h.getCityNameVi(), en),
                preferEn(h.getDistrictNameEn(), h.getDistrictNameVi(), en),
                preferEn(h.getWardNameEn(), h.getWardNameVi(), en),
                preferEn(h.getAddressLineEn(), h.getAddressLineVi(), en),
                location,
                h.getAvgRating(), h.getReviewsCount(),
                preferEn(h.getRatingLabelEn(), h.getRatingLabelVi(), en),
                h.getMinNightlyPrice(), h.getCurrencyCode(), h.getReferenceNightlyPrice(), h.getDiscountPercent(),
                generalInfo, images, roomTypes);
    }

    private static String preferEn(String enValue, String viValue, boolean en) {
        if (en) {
            if (enValue != null && !enValue.isBlank()) return enValue;
            return viValue;
        }
        if (viValue != null && !viValue.isBlank()) return viValue;
        return enValue;
    }

    public FacetedSearchResponse<HotelSummaryDTO, HotelFacets> searchFaceted(
            FacetedHotelSearchRequest request, Pageable pageable) {
        try {
            SearchResponse<HotelIndex> response = esClient.search(s -> s
                    .index(HOTELS_INDEX)
                    .query(buildContextQuery(request))
                    .postFilter(buildPostFilter(request))
                    .aggregations("by_star",
                            Aggregation.of(a -> a.terms(t -> t.field("starRating").size(5))))
                    .aggregations("by_type",
                            Aggregation.of(a -> a.terms(t -> t.field("hotelType").size(15))))
                    .aggregations("by_amenity",
                            Aggregation.of(a -> a.terms(t -> t.field("amenityCodes").size(20))))
                    .aggregations("price_ranges",
                            Aggregation.of(a -> a.range(r -> r
                                    .field("minNightlyPrice")
                                    .ranges(List.of(
                                            AggregationRange.of(rng -> rng.key("UNDER_500K").to("500000")),
                                            AggregationRange.of(rng -> rng.key("500K_1M").from("500000").to("1000000")),
                                            AggregationRange.of(rng -> rng.key("1M_2M").from("1000000").to("2000000")),
                                            AggregationRange.of(rng -> rng.key("OVER_2M").from("2000000")))))))
                    .from(pageable.getPageNumber() * pageable.getPageSize())
                    .size(pageable.getPageSize()),
                    HotelIndex.class);

            List<HotelSummaryDTO> results = response.hits().hits().stream()
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .map(ElasticsearchHotelSearchService::toSearchResult)
                    .map(HotelMapper::toSummary)
                    .toList();

            var total = response.hits().total();
            long totalHits = total != null ? total.value() : 0L;
            int size = pageable.getPageSize() > 0 ? pageable.getPageSize() : 10;
            int totalPages = (int) Math.ceil((double) totalHits / size);

            return new FacetedSearchResponse<>(
                    results, totalHits, pageable.getPageNumber(), size, totalPages,
                    extractFacets(response.aggregations()));

        } catch (IOException e) {
            throw new RuntimeException("Faceted hotel search failed", e);
        }
    }

    private static Query buildContextQuery(FacetedHotelSearchRequest r) {
        List<Query> must = new ArrayList<>();
        must.add(Query.of(q -> q.term(t -> t.field("active").value(true))));
        must.add(Query.of(q -> q.term(t -> t.field("moderationStatus").value("APPROVED"))));

        if (r != null && r.location() != null && !r.location().isBlank()) {
            String loc = r.location().trim();
            if (Boolean.TRUE.equals(r.destOnly())) {
                must.add(Query.of(q -> q.term(t -> t.field("destinationSlug").value(loc))));
            } else {
                must.add(Query.of(q -> q.bool(b -> b
                        .should(Query.of(s -> s.multiMatch(m -> m
                                .fields("nameVi^4", "nameEn^4",
                                        "cityNameVi^2", "cityNameEn^2",
                                        "districtNameVi^1", "districtNameEn^1",
                                        "addressLineVi^1", "addressLineEn^1")
                                .query(loc)
                                .fuzziness("AUTO")
                                .type(TextQueryType.BestFields))))
                        .should(Query.of(s -> s.term(t -> t.field("destinationSlug").value(loc))))
                        .minimumShouldMatch("1"))));
            }
        }
        return boolMust(must);
    }

    private static Query buildPostFilter(FacetedHotelSearchRequest r) {
        if (r == null) return Query.of(q -> q.matchAll(m -> m));

        List<Query> filters = new ArrayList<>();

        if (r.starRatings() != null && !r.starRatings().isEmpty()) {
            List<FieldValue> vals = r.starRatings().stream()
                    .map(v -> FieldValue.of(v.longValue())).toList();
            filters.add(Query.of(q -> q.terms(t -> t.field("starRating").terms(tv -> tv.value(vals)))));
        }
        if (r.hotelTypes() != null && !r.hotelTypes().isEmpty()) {
            List<FieldValue> vals = r.hotelTypes().stream().map(FieldValue::of).toList();
            filters.add(Query.of(q -> q.terms(t -> t.field("hotelType").terms(tv -> tv.value(vals)))));
        }
        if (r.amenities() != null && !r.amenities().isEmpty()) {
            for (String code : r.amenities()) {
                String c = code;
                filters.add(Query.of(q -> q.term(t -> t.field("amenityCodes").value(c))));
            }
        }
        if (r.minPrice() != null) {
            int mp = r.minPrice();
            filters.add(Query.of(q -> q.range(rq -> rq.field("minNightlyPrice").gte(JsonData.of(mp)))));
        }
        if (r.maxPrice() != null) {
            int mx = r.maxPrice();
            filters.add(Query.of(q -> q.range(rq -> rq.field("minNightlyPrice").lte(JsonData.of(mx)))));
        }
        if (r.minRating() != null) {
            double mr = r.minRating();
            filters.add(Query.of(q -> q.range(rq -> rq.field("avgRating").gte(JsonData.of(mr)))));
        }

        if (filters.isEmpty()) return Query.of(q -> q.matchAll(m -> m));
        return Query.of(q -> q.bool(b -> { filters.forEach(b::filter); return b; }));
    }

    private static HotelFacets extractFacets(java.util.Map<String, Aggregate> aggs) {
        if (aggs == null) return new HotelFacets(List.of(), List.of(), List.of(), List.of(), null);

        java.util.Map<String, String> errors = new java.util.LinkedHashMap<>();

        List<FacetBucket> starRatings = safeAgg(aggs, "by_star", errors,
                agg -> agg.lterms().buckets().array().stream()
                        .map(b -> new FacetBucket(String.valueOf(b.key()), b.key() + "★", b.docCount()))
                        .toList());

        List<FacetBucket> hotelTypes = safeAgg(aggs, "by_type", errors,
                agg -> agg.sterms().buckets().array().stream()
                        .map(b -> new FacetBucket(b.key().stringValue(), b.key().stringValue(), b.docCount()))
                        .toList());

        List<FacetBucket> amenities = safeAgg(aggs, "by_amenity", errors,
                agg -> agg.sterms().buckets().array().stream()
                        .map(b -> new FacetBucket(b.key().stringValue(), b.key().stringValue(), b.docCount()))
                        .toList());

        List<FacetBucket> priceRanges = safeAgg(aggs, "price_ranges", errors,
                agg -> agg.range().buckets().array().stream()
                        .map(b -> new FacetBucket(b.key(), b.key(), b.docCount()))
                        .toList());

        return new HotelFacets(starRatings, hotelTypes, amenities, priceRanges,
                errors.isEmpty() ? null : java.util.Collections.unmodifiableMap(errors));
    }

    private static List<FacetBucket> safeAgg(
            java.util.Map<String, Aggregate> aggs,
            String name,
            java.util.Map<String, String> errors,
            Function<Aggregate, List<FacetBucket>> mapper) {
        Aggregate agg = aggs.get(name);
        if (agg == null) return List.of();
        try { return mapper.apply(agg); }
        catch (Exception ex) {
            log.warn("[ES] Aggregation '{}' parse failed", name, ex);
            errors.put(name, "unavailable");
            return List.of();
        }
    }
}
