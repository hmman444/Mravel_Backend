package com.mravel.auth.service.social;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.auth.dto.SocialUserProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class GoogleAuthService {
    private static final String GOOGLE_USERINFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo";

    public SocialUserProfile verifyGoogleToken(String accessToken) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    GOOGLE_USERINFO_URL, HttpMethod.GET, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.getBody());

            if (json.has("email")) {
                return new SocialUserProfile(
                        json.get("email").asText(),
                        json.has("name") ? json.get("name").asText() : "",
                        json.has("picture") ? json.get("picture").asText() : "",
                        json.has("sub") ? json.get("sub").asText() : null);
            } else {
                throw new RuntimeException("Không tìm thấy email trong Google response");
            }
        } catch (Exception e) {
            throw new RuntimeException("Xác thực Google thất bại: " + e.getMessage());
        }
    }
}
