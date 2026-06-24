package com.mravel.admin.controller;

import com.mravel.admin.client.ReviewAdminClient;
import com.mravel.common.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminReviewController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminReviewControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean ReviewAdminClient reviewAdminClient;

    private static final String AUTH = "Bearer test-token";

    @Test
    void listNegative_delegatesWithDefaultParams() throws Exception {
        when(reviewAdminClient.listNegative(anyString(), any(), anyInt(), anyInt(), anyInt(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/reviews/negative")
                        .header("Authorization", AUTH)
                        .param("targetType", "HOTEL"))
                .andExpect(status().isOk());

        verify(reviewAdminClient).listNegative(eq("HOTEL"), isNull(), eq(2), eq(0), eq(10), eq("test-token"));
    }

    @Test
    void listNegative_withAllParams() throws Exception {
        when(reviewAdminClient.listNegative(anyString(), anyString(), anyInt(), anyInt(), anyInt(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(get("/api/admin/reviews/negative")
                        .header("Authorization", AUTH)
                        .param("targetType", "RESTAURANT")
                        .param("targetId", "RES123")
                        .param("maxRating", "1")
                        .param("page", "2")
                        .param("size", "5"))
                .andExpect(status().isOk());

        verify(reviewAdminClient).listNegative(
                eq("RESTAURANT"), eq("RES123"), eq(1), eq(2), eq(5), eq("test-token"));
    }

    @Test
    void negativeCount_delegatesWithDefaultParams() throws Exception {
        when(reviewAdminClient.negativeCount(anyString(), anyInt(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", 42)));

        mvc.perform(get("/api/admin/reviews/negative/count")
                        .header("Authorization", AUTH)
                        .param("targetType", "HOTEL"))
                .andExpect(status().isOk());

        verify(reviewAdminClient).negativeCount(eq("HOTEL"), eq(2), eq("test-token"));
    }

    @Test
    void negativeCount_customMaxRating() throws Exception {
        when(reviewAdminClient.negativeCount(anyString(), anyInt(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", 5)));

        mvc.perform(get("/api/admin/reviews/negative/count")
                        .header("Authorization", AUTH)
                        .param("targetType", "RESTAURANT")
                        .param("maxRating", "3"))
                .andExpect(status().isOk());

        verify(reviewAdminClient).negativeCount(eq("RESTAURANT"), eq(3), eq("test-token"));
    }

    @Test
    void deleteReview_delegatesToClient() throws Exception {
        when(reviewAdminClient.delete(anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("deleted", null)));

        mvc.perform(delete("/api/admin/reviews/15")
                        .header("Authorization", AUTH))
                .andExpect(status().isOk());

        verify(reviewAdminClient).delete(eq(15L), eq("test-token"));
    }

    @Test
    void deleteReview_bearerPrefixStripped() throws Exception {
        when(reviewAdminClient.delete(anyLong(), anyString()))
                .thenReturn(ResponseEntity.ok(ApiResponse.success("ok", null)));

        mvc.perform(delete("/api/admin/reviews/7")
                        .header("Authorization", "Bearer my-jwt"))
                .andExpect(status().isOk());

        verify(reviewAdminClient).delete(eq(7L), eq("my-jwt"));
    }
}
