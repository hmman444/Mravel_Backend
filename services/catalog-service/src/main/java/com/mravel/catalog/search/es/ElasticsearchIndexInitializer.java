package com.mravel.catalog.search.es;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Component;

import com.mravel.catalog.model.index.HotelIndex;
import com.mravel.catalog.model.index.PlaceIndex;
import com.mravel.catalog.model.index.RestaurantIndex;
import com.mravel.catalog.repository.HotelDocRepository;
import com.mravel.catalog.repository.PlaceDocRepository;
import com.mravel.catalog.repository.RestaurantDocRepository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@ConditionalOnProperty(name = "catalog.search.engine", havingValue = "elasticsearch")
@RequiredArgsConstructor
@Slf4j
public class ElasticsearchIndexInitializer implements ApplicationRunner {

    private final ElasticsearchOperations esOps;
    private final ElasticsearchClient esClient;
    private final IndexingService indexingService;
    private final HotelDocRepository hotelRepo;
    private final RestaurantDocRepository restaurantRepo;
    private final PlaceDocRepository placeRepo;

    @Value("${catalog.search.reindex-on-startup-if-empty:true}")
    private boolean reindexOnStartupIfEmpty;

    @Override
    public void run(ApplicationArguments args) {
        ensureIndex(HotelIndex.class);
        ensureIndex(RestaurantIndex.class);
        ensureIndex(PlaceIndex.class);

        if (reindexOnStartupIfEmpty) {
            reindexIfEmpty("hotels", hotelRepo.count(), indexingService::reindexHotels);
            reindexIfEmpty("restaurants", restaurantRepo.count(), indexingService::reindexRestaurants);
            reindexIfEmpty("places", placeRepo.count(), indexingService::reindexPlaces);
        }
    }

    private <T> void ensureIndex(Class<T> clazz) {
        IndexOperations ops = esOps.indexOps(clazz);
        if (!ops.exists()) {
            ops.create(buildSettings());
            log.info("[ES] Created index with vn_text analyzer: {}", indexName(clazz));
        }
        ops.putMapping(ops.createMapping());
        log.info("[ES] Mapping updated: {}", indexName(clazz));
    }

    private static Document buildSettings() {
        return Document.from(Map.of(
            "analysis", Map.of(
                "analyzer", Map.of(
                    "vn_text", Map.of(
                        "type", "custom",
                        "tokenizer", "standard",
                        "filter", List.of("lowercase", "asciifolding")
                    )
                )
            )
        ));
    }

    private static String indexName(Class<?> clazz) {
        org.springframework.data.elasticsearch.annotations.Document doc =
                clazz.getAnnotation(org.springframework.data.elasticsearch.annotations.Document.class);
        return doc != null ? doc.indexName() : clazz.getSimpleName();
    }

    private void reindexIfEmpty(String indexName, long sourceCount, ReindexOperation reindexOperation) {
        if (sourceCount <= 0) {
            log.info("[ES] Skip startup reindex for {} because source collection is empty", indexName);
            return;
        }

        try {
            long indexedCount = esClient.count(c -> c.index(indexName)).count();
            if (indexedCount > 0) {
                log.info("[ES] Skip startup reindex for {} because index already has {} documents", indexName, indexedCount);
                return;
            }
        } catch (IOException e) {
            log.warn("[ES] Failed to count index {} on startup: {}", indexName, e.getMessage(), e);
            return;
        }

        int indexed = reindexOperation.run();
        log.info("[ES] Startup backfill completed for {}: {} documents indexed", indexName, indexed);
    }

    @FunctionalInterface
    private interface ReindexOperation {
        int run();
    }
}
