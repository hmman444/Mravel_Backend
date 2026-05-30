package com.mravel.catalog.search.es;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@ConditionalOnProperty(name = "catalog.search.engine", havingValue = "elasticsearch")
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ElasticsearchIndexInitializer implements ApplicationRunner {

    private final ElasticsearchOperations esOps;
    private final ElasticsearchClient esClient;
    private final IndexingService indexingService;
    private final HotelDocRepository hotelRepo;
    private final RestaurantDocRepository restaurantRepo;
    private final PlaceDocRepository placeRepo;

    @Value("${catalog.search.reindex-on-startup-if-empty:true}")
    private boolean reindexOnStartupIfEmpty;

    /**
     * T?o index + settings TRU?C khi Spring kh?i t?o các CommandLineRunner (seed).
     * Tránh race: seed runner g?i indexingService.syncX() ? ES auto-create index
     * v?i default settings (thi?u vn_text analyzer) ? khi?n putMapping ? run() fail.
     */
    @PostConstruct
    void initIndexesEarly() {
        ensureIndex(HotelIndex.class);
        ensureIndex(RestaurantIndex.class);
        ensureIndex(PlaceIndex.class);
    }

    @Override
    public void run(ApplicationArguments args) {
        // Idempotent re-check sau khi m?i bean lifecycle ?n d?nh.
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
        try {
            ops.putMapping(ops.createMapping());
            log.info("[ES] Mapping updated: {}", indexName(clazz));
        } catch (Exception ex) {
            // Mapping conflict: index dã có settings/mapping cu không tuong thích.
            // Ch? log c?nh báo — user c?n DELETE index th? công d? recreate.
            log.warn("[ES] putMapping failed for {} ({}). " +
                    "Có kh? nang index dã t?n t?i v?i settings cu thi?u analyzer 'vn_text'. " +
                    "Hãy DELETE index này (curl -X DELETE http://localhost:9200/{}) r?i restart.",
                    indexName(clazz), ex.getMessage(), indexName(clazz));
        }
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
