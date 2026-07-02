package com.mravel.plan.config;

import com.mravel.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Đồng bộ lại toàn bộ dữ liệu MySQL -> Elasticsearch mỗi khi plan-service khởi động.
 *
 * <p>Mục đích: đảm bảo các trường dùng để SẮP XẾP (views, reactionCount, budget, days, ...)
 * trong ES luôn khớp với dữ liệu thật ở MySQL — nếu không, sort MOST_VIEWED / MOST_REACTED
 * sẽ ra thứ tự "kỳ lạ" vì ES giữ giá trị cũ (thường là 0). Nhờ vậy không cần gọi
 * {@code POST /api/plans/_sync} thủ công sau mỗi lần deploy.
 *
 * <p>Chạy nền (thread riêng) + nuốt lỗi để không chặn/không làm sập quá trình khởi động khi
 * ES tạm thời chưa sẵn sàng. Có thể tắt bằng cấu hình {@code plan.es.sync-on-startup=false}.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticsearchStartupSync {

    private final PlanService planService;

    @Value("${plan.es.sync-on-startup:true}")
    private boolean syncOnStartup;

    @EventListener(ApplicationReadyEvent.class)
    public void resyncOnStartup() {
        if (!syncOnStartup) {
            log.info("ES startup sync bị tắt (plan.es.sync-on-startup=false).");
            return;
        }
        Thread t = new Thread(() -> {
            try {
                planService.syncAllToElasticsearch();
                log.info("ES startup sync: đã đồng bộ toàn bộ plan sang Elasticsearch.");
            } catch (Exception e) {
                log.warn("ES startup sync thất bại (bỏ qua; có thể chạy POST /api/plans/_sync thủ công): {}",
                        e.getMessage());
            }
        }, "es-startup-sync");
        t.setDaemon(true);
        t.start();
    }
}
