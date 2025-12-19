package com.mravel.booking.client;

import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CatalogPartnerIdsClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.catalog.base-url}")
    private String catalogBaseUrl;

    public List<String> listMyHotelIds(Long partnerId, String bearer) {
        return listIds("/api/catalog/partners/hotels", partnerId, bearer);
    }

    public List<String> listMyRestaurantIds(Long partnerId, String bearer) {
        return listIds("/api/catalog/partners/restaurants", partnerId, bearer);
    }

    // ===== helpers =====

    private List<String> listIds(String path, Long partnerId, String bearer) {
        String base = Objects.requireNonNull(catalogBaseUrl, "mravel.services.catalog.base-url must not be null");
        String safePath = Objects.requireNonNull(path, "path must not be null");
        Long pid = Objects.requireNonNull(partnerId, "partnerId must not be null");

        URI uri = UriComponentsBuilder.fromHttpUrl(Objects.requireNonNull(base))
                .path(safePath)
                .queryParam("partnerId", pid)
                .queryParam("page", 0)
                .queryParam("size", 1000)
                .build(true)
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, Objects.requireNonNull(bearer, "bearer must not be null"));

        RequestEntity<Void> req = new RequestEntity<>(
                headers,
                Objects.requireNonNull(HttpMethod.GET),
                Objects.requireNonNull(uri)
        );

        ResponseEntity<ApiResponse<?>> resp = restTemplate.exchange(
                req,
                new ParameterizedTypeReference<ApiResponse<?>>() {}
        );

        ApiResponse<?> body = resp.getBody();
        if (body == null) return List.of();

        Object data = body.getData();
        // data là Page<?> dạng map -> lấy content[].id
        if (!(data instanceof Map<?, ?> pageMap)) return List.of();
        Object content = pageMap.get("content");
        if (!(content instanceof List<?> list)) return List.of();

        return list.stream()
                .filter(it -> it instanceof Map<?, ?>)
                .map(it -> String.valueOf(((Map<?, ?>) it).get("id")))
                .toList();
    }
}