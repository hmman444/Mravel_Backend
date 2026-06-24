package com.mravel.catalog.controller;

import com.mravel.catalog.service.RestaurantInventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantInventoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class RestaurantInventoryControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean RestaurantInventoryService inventoryService;

    private static final String BASE = "/api/catalog/restaurants/inventory";

    // ── GET /availability ─────────────────────────────────────────────────────

    @Test
    void availability_returnsOk() throws Exception {
        when(inventoryService.getAvailability(any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(null);

        mvc.perform(get(BASE + "/availability")
                        .param("restaurantId", "r-1")
                        .param("tableTypeId", "tt-1")
                        .param("reservationDate", "2025-12-01")
                        .param("reservationTime", "19:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /check ───────────────────────────────────────────────────────────

    @Test
    void check_returnsOk() throws Exception {
        doNothing().when(inventoryService).assertAvailability(any());

        mvc.perform(post(BASE + "/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Còn bàn"));
    }

    // ── POST /hold ────────────────────────────────────────────────────────────

    @Test
    void hold_returnsOk() throws Exception {
        doNothing().when(inventoryService).holdForPending(any());

        mvc.perform(post(BASE + "/hold")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã hold bàn"));
    }

    // ── POST /release ─────────────────────────────────────────────────────────

    @Test
    void release_returnsOk() throws Exception {
        doNothing().when(inventoryService).releaseHold(any());

        mvc.perform(post(BASE + "/release")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã release hold"));
    }

    // ── POST /commit ──────────────────────────────────────────────────────────

    @Test
    void commit_returnsOk() throws Exception {
        doNothing().when(inventoryService).commitAfterPaid(any());

        mvc.perform(post(BASE + "/commit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã commit bàn"));
    }

    // ── POST /rollback ────────────────────────────────────────────────────────

    @Test
    void rollback_returnsOk() throws Exception {
        doNothing().when(inventoryService).rollbackBooked(any());

        mvc.perform(post(BASE + "/rollback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã rollback booked"));
    }
}
