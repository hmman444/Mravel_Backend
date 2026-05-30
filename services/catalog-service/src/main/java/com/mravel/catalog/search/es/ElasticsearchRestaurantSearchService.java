package com.mravel.catalog.search.es;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import com.mravel.catalog.dto.SearchRequests.RestaurantSearchRequest;
import com.mravel.catalog.model.index.RestaurantIndex;
import com.mravel.catalog.search.RestaurantSearchService;
import com.mravel.catalog.search.dto.RestaurantSearchResult;
import com.mravel.common.i18n.LocaleConstants;
import com.mravel.common.i18n.LocaleContext;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import co.elastic.clients.json.JsonData;
import lombok.RequiredArgsConstructor;

@Service
@ConditionalOnProperty(name = "catalog.search.engine", havingValue = "elasticsearch")
@RequiredArgsConstructor
public class ElasticsearchRestaurantSearchService implements RestaurantSearchService {

    private final ElasticsearchOperations esOps;

    @Override
    public Page<RestaurantSearchResult> search(RestaurantSearchRequest request, Pageable pageable) {
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(buildQuery(request))
                .withPageable(pageable)
                .build();

        SearchHits<RestaurantIndex> hits = esOps.search(nativeQuery, RestaurantIndex.class);
        List<RestaurantSearchResult> results = hits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(ElasticsearchRestaurantSearchService::toSearchResult)
                .toList();
        return new PageImpl<>(results, pageable, hits.getTotalHits());
    }

    private static Query buildQuery(RestaurantSearchRequest r) {
        List<Query> must = new ArrayList<>();
        must.add(Query.of(q -> q.term(t -> t.field("active").value(true))));
        must.add(Query.of(q -> q.term(t -> t.field("moderationStatus").value("APPROVED"))));

        if (r == null) {
            return boolMust(must);
        }

        if (r.location() != null && !r.location().isBlank()) {
            String loc = r.location().trim();
            if (Boolean.TRUE.equals(r.destOnly())) {
                must.add(Query.of(q -> q.bool(b -> b
                        .should(Query.of(s -> s.term(t -> t.field("destinationSlug").value(loc))))
                        .should(Query.of(s -> s.term(t -> t.field("parentPlaceSlug").value(loc))))
                        .minimumShouldMatch("1"))));
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
                        .should(Query.of(s -> s.term(t -> t.field("parentPlaceSlug").value(loc))))
                        .minimumShouldMatch("1"))));
            }
        }

        if (r.people() != null && r.people() > 0) {
            int p = r.people();
            must.add(Query.of(q -> q.bool(b -> b
                    .should(Query.of(s -> s.range(rq -> rq.field("totalCapacity").gte(JsonData.of(p)))))
                    .should(Query.of(s -> s.range(rq -> rq.field("maxGroupSize").gte(JsonData.of(p)))))
                    .minimumShouldMatch("1"))));
        }

        if (r.cuisineSlugs() != null && !r.cuisineSlugs().isEmpty()) {
            List<co.elastic.clients.elasticsearch._types.FieldValue> values = r.cuisineSlugs().stream()
                    .map(co.elastic.clients.elasticsearch._types.FieldValue::of)
                    .toList();
            must.add(Query.of(q -> q.terms(t -> t.field("cuisineCodes").terms(tv -> tv.value(values)))));
        }

        if (r.priceLevel() != null && !r.priceLevel().isBlank()) {
            String pl = r.priceLevel().trim().toUpperCase();
            must.add(Query.of(q -> q.term(t -> t.field("priceLevel").value(pl))));
        }

        if (r.minRating() != null) {
            double mr = r.minRating();
            must.add(Query.of(q -> q.range(rq -> rq.field("avgRating").gte(JsonData.of(mr)))));
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

    static RestaurantSearchResult toSearchResult(RestaurantIndex r) {
        boolean en = LocaleConstants.EN.equals(LocaleContext.get());

        List<RestaurantSearchResult.CuisineRef> cuisines = r.getCuisines() == null ? List.of()
                : r.getCuisines().stream().filter(Objects::nonNull)
                        .map(c -> new RestaurantSearchResult.CuisineRef(
                                c.getCode(),
                                preferEn(c.getNameEn(), c.getNameVi(), en)))
                        .toList();

        List<RestaurantSearchResult.AmbienceRef> ambience = r.getAmbience() == null ? List.of()
                : r.getAmbience().stream().filter(Objects::nonNull)
                        .map(a -> new RestaurantSearchResult.AmbienceRef(
                                a.getCode(),
                                preferEn(a.getLabelEn(), a.getLabelVi(), en)))
                        .toList();

        RestaurantSearchResult.CapacityRef capacity = (r.getTotalCapacity() == null && r.getMaxGroupSize() == null)
                ? null
                : new RestaurantSearchResult.CapacityRef(r.getTotalCapacity());

        List<RestaurantSearchResult.ImageRef> images = r.getImages() == null ? List.of()
                : r.getImages().stream().filter(Objects::nonNull)
                        .map(img -> new RestaurantSearchResult.ImageRef(
                                img.getUrl(),
                                preferEn(img.getCaptionEn(), img.getCaptionVi(), en),
                                img.getCover(),
                                img.getSortOrder()))
                        .toList();

        List<RestaurantSearchResult.OpeningHourRef> openingHours = r.getOpeningHours() == null ? List.of()
                : r.getOpeningHours().stream().filter(Objects::nonNull)
                        .map(oh -> new RestaurantSearchResult.OpeningHourRef(
                                oh.getDayOfWeek() == null ? null : DayOfWeek.valueOf(oh.getDayOfWeek()),
                                oh.getOpenTime() == null ? null : LocalTime.parse(oh.getOpenTime()),
                                oh.getCloseTime() == null ? null : LocalTime.parse(oh.getCloseTime()),
                                oh.getOpen24h(),
                                oh.getClosed()))
                        .toList();

        double[] location = null;
        if (r.getLocation() != null) {
            location = new double[]{r.getLocation().getLon(), r.getLocation().getLat()};
        }

        return new RestaurantSearchResult(
                r.getId(),
                preferEn(r.getNameEn(), r.getNameVi(), en),
                r.getSlug(),
                r.getActive(),
                r.getRestaurantType(),
                r.getDestinationSlug(),
                preferEn(r.getCityNameEn(), r.getCityNameVi(), en),
                preferEn(r.getDistrictNameEn(), r.getDistrictNameVi(), en),
                preferEn(r.getWardNameEn(), r.getWardNameVi(), en),
                preferEn(r.getAddressLineEn(), r.getAddressLineVi(), en),
                location,
                cuisines, ambience, capacity,
                r.getAvgRating(), r.getReviewsCount(),
                preferEn(r.getRatingLabelEn(), r.getRatingLabelVi(), en),
                r.getMinPricePerPerson(), r.getMaxPricePerPerson(), r.getCurrencyCode(),
                r.getPriceLevel(), r.getPriceBucket(),
                images, openingHours);
    }

    private static String preferEn(String enValue, String viValue, boolean en) {
        if (en) {
            if (enValue != null && !enValue.isBlank()) return enValue;
            return viValue;
        }
        if (viValue != null && !viValue.isBlank()) return viValue;
        return enValue;
    }
}
