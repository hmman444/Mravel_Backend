package com.mravel.catalog.translation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * Provider dịch FREE qua endpoint gtx không chính thức của Google (không cần API key).
 * Mặc định khi translation.provider=gtx (hoặc không cấu hình). Swap sang Google Cloud sau bằng provider khác.
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "translation.provider", havingValue = "gtx", matchIfMissing = true)
public class GtxTranslationService implements TranslationService {

    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${translation.gtx.url:https://translate.googleapis.com/translate_a/single}")
    private String baseUrl;

    @Override
    public String translate(String text, String sourceLocale, String targetLocale) {
        if (text == null || text.isBlank()
                || sourceLocale == null || targetLocale == null
                || sourceLocale.equals(targetLocale)) {
            return text;
        }
        try {
            String url = baseUrl + "?client=gtx"
                    + "&sl=" + sourceLocale
                    + "&tl=" + targetLocale
                    + "&dt=t&q=" + URLEncoder.encode(text, StandardCharsets.UTF_8);

            HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                    .timeout(Duration.ofSeconds(10))
                    .header("User-Agent", "Mozilla/5.0")
                    .GET()
                    .build();

            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (resp.statusCode() != 200) {
                log.warn("[gtx] HTTP {} khi dịch {}->{}, giữ nguyên text", resp.statusCode(), sourceLocale, targetLocale);
                return text;
            }

            // Response: [[["đã dịch","gốc",...],["...","..."]], ...] — nối các segment[0].
            JsonNode root = mapper.readTree(resp.body());
            JsonNode segments = root.get(0);
            if (segments == null || !segments.isArray()) {
                return text;
            }
            StringBuilder sb = new StringBuilder();
            for (JsonNode seg : segments) {
                if (seg != null && seg.isArray() && seg.get(0) != null && !seg.get(0).isNull()) {
                    sb.append(seg.get(0).asText());
                }
            }
            String out = sb.toString();
            return out.isBlank() ? text : out;
        } catch (Exception e) {
            log.warn("[gtx] dịch {}->{} lỗi: {} — giữ nguyên text", sourceLocale, targetLocale, e.getMessage());
            return text;
        }
    }
}
