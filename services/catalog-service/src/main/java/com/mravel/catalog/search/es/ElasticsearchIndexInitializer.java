package com.mravel.catalog.search.es;

import java.util.List;
import java.util.Map;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@ConditionalOnProperty(name = "catalog.search.engine", havingValue = "elasticsearch")
@RequiredArgsConstructor
@Slf4j
public class ElasticsearchIndexInitializer implements ApplicationRunner {

    private final ElasticsearchOperations esOps;

    @Override
    public void run(ApplicationArguments args) {
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
}
