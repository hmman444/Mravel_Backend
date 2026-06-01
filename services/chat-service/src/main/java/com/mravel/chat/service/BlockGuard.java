package com.mravel.chat.service;

import com.mravel.chat.client.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Kiểm tra quan hệ chặn (2 chiều) qua endpoint nội bộ user-service.
 * Fail-open: nếu user-service lỗi thì không chặn (tránh đứng toàn bộ chat).
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BlockGuard {

    private final UserClient userClient;

    @Value("${mravel.internal.secret:}")
    private String internalSecret;

    public boolean isBlocked(Long a, Long b) {
        if (a == null || b == null || a.equals(b))
            return false;
        try {
            Map<String, Object> res = userClient.blockCheck(internalSecret, a, b);
            return res != null && Boolean.TRUE.equals(res.get("data"));
        } catch (Exception e) {
            log.warn("block-check failed for {}-{}: {}", a, b, e.getMessage());
            return false;
        }
    }
}
