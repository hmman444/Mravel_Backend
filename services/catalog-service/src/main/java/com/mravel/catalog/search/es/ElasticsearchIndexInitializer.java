package com.mravel.catalog.search.es;

import java.util.List;
import java.util.Map;

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

    /**
     * Tạo index + settings TRƯỚC khi Spring khởi tạo các CommandLineRunner (seed).
     * Tránh race: seed runner gọi indexingService.syncX() → ES auto-create index
     * với default settings (thiếu vn_text analyzer) → khiến putMapping ở run() fail.
     */
    @PostConstruct
    void initIndexesEarly() {
        ensureIndex(HotelIndex.class);
        ensureIndex(RestaurantIndex.class);
        ensureIndex(PlaceIndex.class);
    }

    @Override
    public void run(ApplicationArguments args) {
        // Idempotent re-check sau khi mọi bean lifecycle ổn định.
        ensureIndex(HotelIndex.class);
        ensureIndex(RestaurantIndex.class);
        ensureIndex(PlaceIndex.class);
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
            // Mapping conflict: index đã có settings/mapping cũ không tương thích.
            // Chỉ log cảnh báo — user cần DELETE index thủ công để recreate.
            log.warn("[ES] putMapping failed for {} ({}). " +
                    "Có khả năng index đã tồn tại với settings cũ thiếu analyzer 'vn_text'. " +
                    "Hãy DELETE index này (curl -X DELETE http://localhost:9200/{}) rồi restart.",
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
}
