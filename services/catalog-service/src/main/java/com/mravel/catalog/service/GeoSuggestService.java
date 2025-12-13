package com.mravel.catalog.service;

import com.mravel.catalog.dto.geo.LocationSuggestDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeoSuggestService {

    // Nếu sau này muốn cấu hình qua @Bean thì thay bằng constructor-injected
    // WebClient
    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://nominatim.openstreetmap.org")
            // Nominatim yêu cầu UA có tên app + email thật
            .defaultHeader(
                    HttpHeaders.USER_AGENT,
                    "MravelTravelPlanner/1.0 (contact: luandophu8114@gmail.com)")
            .build();

    public List<LocationSuggestDTO> suggest(String q, int limit) {
        if (q == null || q.trim().length() < 3) {
            return Collections.emptyList();
        }

        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search")
                            .queryParam("format", "jsonv2")
                            .queryParam("q", q)
                            .queryParam("limit", limit)
                            // thêm email theo khuyến nghị của Nominatim
                            .queryParam("email", "luandophu8114@gmail.com")
                            .build())
                    .retrieve()
                    .bodyToFlux(NominatimResult.class)
                    .map(this::mapFromNominatim)
                    .collectList()
                    .block();
        } catch (WebClientResponseException ex) {
            int status = ex.getStatusCode().value();
            String reason = ex.getStatusText();

            var req = ex.getRequest();
            String uri = (req != null && req.getURI() != null)
                    ? req.getURI().toString()
                    : "unknown";

            log.warn("GeoSuggestService.suggest error with query '{}': {} {} from {}",
                    q, status, reason, uri);
        } catch (Exception ex) {
            log.warn("GeoSuggestService.suggest unexpected error with query '{}': {}",
                    q, ex.toString());
        }

        // Lỗi gì thì vẫn trả list rỗng để FE không văng
        return Collections.emptyList();
    }

    private LocationSuggestDTO mapFromNominatim(NominatimResult n) {
        String displayName = n.getDisplay_name();
        Double lat = parseDoubleSafe(n.getLat());
        Double lon = parseDoubleSafe(n.getLon());

        return LocationSuggestDTO.builder()
                .name(displayName)
                .label(displayName)
                .fullAddress(displayName)
                .addressLine(displayName)
                .latitude(lat)
                .longitude(lon)
                .placeId(n.getPlace_id())
                // cityName/districtName/provinceName có thể parse thêm sau từ n.address
                .build();
    }

    private Double parseDoubleSafe(String s) {
        try {
            return (s != null) ? Double.parseDouble(s) : null;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    @Data
    public static class NominatimResult {
        private String place_id;
        private String display_name;
        private String lat;
        private String lon;
        // nếu cần, có thể thêm Map<String, Object> address;
    }
}
