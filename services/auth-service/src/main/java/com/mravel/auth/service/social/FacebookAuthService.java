package com.mravel.auth.service.social;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.auth.dto.SocialUserProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FacebookAuthService {
    private static final String FB_USERINFO_URL = "https://graph.facebook.com/me?fields=id,name,email,picture.width(200)&access_token=";

    public SocialUserProfile verifyFacebookToken(String accessToken) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(FB_USERINFO_URL + accessToken, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response);

            if (json.has("email")) {
                return new SocialUserProfile(
                        json.get("email").asText(),
                        json.get("name").asText(),
                        json.path("picture").path("data").path("url").asText(),
                        json.get("id").asText());
            } else {
                throw new RuntimeException("Token Facebook không hợp lệ");
            }
        } catch (Exception e) {
            throw new RuntimeException("Xác thực Facebook thất bại: " + e.getMessage());
        }
    }
}
