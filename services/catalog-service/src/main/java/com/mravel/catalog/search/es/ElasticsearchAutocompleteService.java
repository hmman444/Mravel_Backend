package com.mravel.catalog.search.es;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import com.mravel.catalog.dto.AutocompleteItemDTO;
import com.mravel.catalog.model.index.HotelIndex;
import com.mravel.catalog.model.index.RestaurantIndex;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@ConditionalOnProperty(name = "catalog.search.engine", havingValue = "elasticsearch")
@RequiredArgsConstructor
@Slf4j
public class ElasticsearchAutocompleteService {

    private final ElasticsearchOperations esOps;

    public List<AutocompleteItemDTO> suggestHotels(String q, int limit) {
        try {
            NativeQuery nq = NativeQuery.builder()
                    .withQuery(buildPrefixQuery(q))
                    .withPageable(PageRequest.of(0, limit))
                    .build();
            return esOps.search(nq, HotelIndex.class)
                    .getSearchHits().stream()
                    .map(SearchHit::getContent)
                    .map(h -> new AutocompleteItemDTO(
                            h.getId(), h.getName(), h.getSlug(), h.getCityName(),
                            coverUrl(h.getImages(), HotelIndex.ImageData::getCover, HotelIndex.ImageData::getUrl),
                            "HOTEL"))
                    .toList();
        } catch (Exception e) {
            log.warn("[autocomplete] hotel error for '{}': {}", q, e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<AutocompleteItemDTO> suggestRestaurants(String q, int limit) {
        try {
            NativeQuery nq = NativeQuery.builder()
                    .withQuery(buildPrefixQuery(q))
                    .withPageable(PageRequest.of(0, limit))
                    .build();
            return esOps.search(nq, RestaurantIndex.class)
                    .getSearchHits().stream()
                    .map(SearchHit::getContent)
                    .map(r -> new AutocompleteItemDTO(
                            r.getId(), r.getName(), r.getSlug(), r.getCityName(),
                            coverUrl(r.getImages(), RestaurantIndex.ImageData::getCover, RestaurantIndex.ImageData::getUrl),
                            "RESTAURANT"))
                    .toList();
        } catch (Exception e) {
            log.warn("[autocomplete] restaurant error for '{}': {}", q, e.getMessage());
            return Collections.emptyList();
        }
    }

    private static Query buildPrefixQuery(String q) {
        return Query.of(qb -> qb.bool(b -> b
                .must(Query.of(s -> s.term(t -> t.field("active").value(true))))
                .must(Query.of(s -> s.term(t -> t.field("moderationStatus").value("APPROVED"))))
                .must(Query.of(s -> s.multiMatch(m -> m
                        .fields("name^3", "cityName^1")
                        .query(q)
                        .type(TextQueryType.BoolPrefix))))));
    }

    private static <T> String coverUrl(List<T> images, Function<T, Boolean> getCover, Function<T, String> getUrl) {
        if (images == null || images.isEmpty()) return null;
        return images.stream()
                .filter(img -> Boolean.TRUE.equals(getCover.apply(img)))
                .map(getUrl)
                .findFirst()
                .orElseGet(() -> getUrl.apply(images.get(0)));
    }
}
