package com.mravel.auth.service.social;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.auth.dto.SocialUserProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class GoogleAuthService {
    private static final String GOOGLE_USERINFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo";
    private static final String GOOGLE_TOKENINFO_ID_TOKEN_URL = "https://oauth2.googleapis.com/tokeninfo?id_token=";

    public SocialUserProfile verifyGoogleToken(String token) {
        if (token == null || token.isBlank()) {
            throw new RuntimeException("Google token is missing");
        }

        // 1) Try token as OAuth access token via /userinfo.
        // 2) If unauthorized, fallback to token as ID token via /tokeninfo.
        try {
            return verifyWithUserInfo(token);
        } catch (HttpClientErrorException.Unauthorized | HttpClientErrorException.BadRequest e) {
            try {
                return verifyWithIdToken(token);
            } catch (Exception fallbackEx) {
                throw new RuntimeException("Xác thực Google thất bại: " + fallbackEx.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Xác thực Google thất bại: " + e.getMessage());
        }
    }

    private SocialUserProfile verifyWithUserInfo(String accessToken) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                GOOGLE_USERINFO_URL, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response.getBody());
        return toProfile(json);
    }

    private SocialUserProfile verifyWithIdToken(String idToken) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                GOOGLE_TOKENINFO_ID_TOKEN_URL + idToken, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response.getBody());
        return toProfile(json);
    }

    private SocialUserProfile toProfile(JsonNode json) {
        if (!json.has("email") || json.get("email").asText().isBlank()) {
            throw new RuntimeException("Không tìm thấy email trong Google response");
        }

        return new SocialUserProfile(
                json.get("email").asText(),
                json.has("name") ? json.get("name").asText() : "",
                json.has("picture") ? json.get("picture").asText() : "",
                json.has("sub") ? json.get("sub").asText() : null);
    }
}
