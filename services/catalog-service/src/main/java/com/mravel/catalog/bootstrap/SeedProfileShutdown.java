package com.mravel.catalog.bootstrap;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.mravel.catalog.search.es.IndexingService;

import lombok.RequiredArgsConstructor;

/**
 * Khi chạy với profile "seed": sau khi tất cả Seed*Runner (CommandLineRunner) hoàn tất,
 * ApplicationReadyEvent kích hoạt → tự đẩy dữ liệu sang Elasticsearch (reindexAll) rồi tắt tiến trình.
 * Biến seed thành 1 job chạy-rồi-thoát: vừa ghi Mongo vừa đồng bộ ES trong 1 lần chạy,
 * không cần restart catalog-service đang chạy và không cần gọi reindex thủ công.
 */
@Component
@Profile("seed")
@RequiredArgsConstructor
public class SeedProfileShutdown implements ApplicationListener<ApplicationReadyEvent> {

    private final IndexingService indexingService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            System.out.println(">>> [seed] reindexing Elasticsearch...");
            Map<String, Integer> result = indexingService.reindexAll();
            System.out.println(">>> [seed] reindex done: " + result);
        } catch (Exception e) {
            System.out.println(">>> [seed] reindex SKIPPED (ES not reachable?): " + e.getMessage());
        }
        System.out.println(">>> [seed] all seeders done — shutting down seed process");
        int code = SpringApplication.exit(event.getApplicationContext(), () -> 0);
        System.exit(code);
    }
}
