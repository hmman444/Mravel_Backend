package com.mravel.plan.client;

import com.mravel.common.response.ApiResponse;
import com.mravel.common.response.RelationshipType;
import com.mravel.plan.dto.VisibilityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FriendClient {

    // giữ như bạn đang làm (deadline): tự tạo RestTemplate
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${mravel.services.user.base-url}")
    private String userBaseUrl;

    @Value("${mravel.services.user.userProfiles-path}")
    private String userApiPath;

    @Value("${mravel.internal.secret:}")
    private String internalSecret;

    private String userServiceUrl() {
        return Objects.requireNonNull(userBaseUrl, "userBaseUrl must not be null")
                + Objects.requireNonNull(userApiPath, "userApiPath must not be null");
    }

    public RelationshipType getRelationship(String authorizationHeader, Long profileUserId) {
        String url = userServiceUrl() + "/friends/relationship?profileUserId=" + profileUserId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse<?>> res = restTemplate.exchange(
                url,
                Objects.requireNonNull(HttpMethod.GET),
                entity,
                new ParameterizedTypeReference<ApiResponse<?>>() {}
        );

        ApiResponse<?> body = res.getBody();
        Object data = (body == null) ? null : body.getData();

        return (data != null)
                ? RelationshipType.valueOf(String.valueOf(data))
                : RelationshipType.NONE;
    }

    public List<Long> getFriendIds(String authorizationHeader) {
        String url = userServiceUrl() + "/friends/ids";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse<?>> res = restTemplate.exchange(
                url,
                Objects.requireNonNull(HttpMethod.GET),
                entity,
                new ParameterizedTypeReference<ApiResponse<?>>() {}
        );

        ApiResponse<?> body = res.getBody();
        Object data = (body == null) ? null : body.getData();
        if (data == null) return List.of();

        if (!(data instanceof List<?> raw)) return List.of();

        List<Long> ids = new ArrayList<>(raw.size());
        for (Object o : raw) {
            if (o != null) ids.add(Long.valueOf(o.toString()));
        }
        return ids;
    }

    /**
     * Lấy ngữ cảnh hiển thị (friendIds + blockedIds) trong 1 lần gọi — thay cho
     * getFriendIds trên đường feed/search. Gọi endpoint NỘI BỘ bằng viewerId +
     * shared-secret (không cần token), kết quả được cache (TTL ~1m theo cấu hình).
     */
    @Cacheable(value = "visibilityContext", key = "#viewerId", condition = "#viewerId != null")
    public VisibilityContext getVisibilityContext(Long viewerId) {
        if (viewerId == null)
            return new VisibilityContext(List.of(), List.of());

        String url = userServiceUrl() + "/internal/visibility-context/" + viewerId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Internal-Secret", internalSecret);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<ApiResponse<Map<String, Object>>> res = restTemplate.exchange(
                    url,
                    Objects.requireNonNull(HttpMethod.GET),
                    entity,
                    new ParameterizedTypeReference<ApiResponse<Map<String, Object>>>() {});

            ApiResponse<Map<String, Object>> body = res.getBody();
            Map<String, Object> data = (body == null) ? null : body.getData();
            if (data == null)
                return new VisibilityContext(List.of(), List.of());

            return new VisibilityContext(
                    toLongList(data.get("friendIds")),
                    toLongList(data.get("blockedIds")));
        } catch (Exception e) {
            // fail-open cho tầng đọc; tầng ghi (chat/notification) dùng block-check đồng bộ riêng
            return new VisibilityContext(List.of(), List.of());
        }
    }

    private List<Long> toLongList(Object raw) {
        if (!(raw instanceof List<?> list))
            return List.of();
        List<Long> out = new ArrayList<>(list.size());
        for (Object o : list) {
            if (o != null)
                out.add(Long.valueOf(o.toString()));
        }
        return out;
    }

    /** Kiểm tra chặn 2 chiều (cho tầng WRITE: notification). Fail-open. */
    public boolean isBlocked(Long a, Long b) {
        if (a == null || b == null || a.equals(b))
            return false;
        String url = userServiceUrl() + "/internal/block-check?a=" + a + "&b=" + b;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Internal-Secret", internalSecret);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<ApiResponse<Boolean>> res = restTemplate.exchange(
                    url,
                    Objects.requireNonNull(HttpMethod.GET),
                    entity,
                    new ParameterizedTypeReference<ApiResponse<Boolean>>() {});
            ApiResponse<Boolean> body = res.getBody();
            return body != null && Boolean.TRUE.equals(body.getData());
        } catch (Exception e) {
            return false;
        }
    }
}