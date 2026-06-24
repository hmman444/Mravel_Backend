package com.mravel.catalog.controller;

import com.mravel.catalog.service.HotelInventoryService;
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

@WebMvcTest(HotelInventoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class HotelInventoryControllerTest {

    @Autowired MockMvc mvc;
    @MockBean RestTemplate restTemplate;
    @MockBean HotelInventoryService inventoryService;

    private static final String BASE = "/api/catalog/hotels/inventory";

    // ── GET /availability ─────────────────────────────────────────────────────

    @Test
    void availability_returnsOk() throws Exception {
        when(inventoryService.getAvailability(any(), any(), any(), any(), any(), anyInt()))
                .thenReturn(null);

        mvc.perform(get(BASE + "/availability")
                        .param("hotelId", "h-1")
                        .param("roomTypeId", "rt-1")
                        .param("checkIn", "2025-12-01")
                        .param("checkOut", "2025-12-05"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("OK"));
    }

    // ── POST /check ───────────────────────────────────────────────────────────

    @Test
    void check_returnsOk() throws Exception {
        doNothing().when(inventoryService).assertAvailability(any(), any(), any(), any());

        mvc.perform(post(BASE + "/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"hotelId\":\"h-1\",\"checkInDate\":\"2025-12-01\",\"checkOutDate\":\"2025-12-05\",\"rooms\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Còn phòng"));
    }

    // ── POST /deduct ──────────────────────────────────────────────────────────

    @Test
    void deduct_returnsOk() throws Exception {
        doNothing().when(inventoryService).deductInventoryForBooking(any(), any(), any(), any(), any());

        mvc.perform(post(BASE + "/deduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"hotelId\":\"h-1\",\"checkInDate\":\"2025-12-01\",\"checkOutDate\":\"2025-12-05\",\"rooms\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã trừ tồn kho"));
    }

    // ── POST /hold ────────────────────────────────────────────────────────────

    @Test
    void hold_returnsOk() throws Exception {
        doNothing().when(inventoryService).holdInventoryForPendingBooking(any(), any(), any(), any(), any());

        mvc.perform(post(BASE + "/hold")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"hotelId\":\"h-1\",\"checkInDate\":\"2025-12-01\",\"checkOutDate\":\"2025-12-05\",\"rooms\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã hold tồn kho"));
    }

    // ── POST /rollback ────────────────────────────────────────────────────────

    @Test
    void rollback_returnsOk() throws Exception {
        doNothing().when(inventoryService).rollbackInventoryForBooking(any(), any(), any(), any());

        mvc.perform(post(BASE + "/rollback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"hotelId\":\"h-1\",\"checkInDate\":\"2025-12-01\",\"checkOutDate\":\"2025-12-05\",\"rooms\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã rollback tồn kho"));
    }

    // ── POST /commit ──────────────────────────────────────────────────────────

    @Test
    void commit_returnsOk() throws Exception {
        doNothing().when(inventoryService).commitInventoryAfterPaid(any(), any(), any(), any(), any());

        mvc.perform(post(BASE + "/commit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"hotelId\":\"h-1\",\"checkInDate\":\"2025-12-01\",\"checkOutDate\":\"2025-12-05\",\"rooms\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã commit tồn kho"));
    }

    // ── POST /release ─────────────────────────────────────────────────────────

    @Test
    void release_returnsOk() throws Exception {
        doNothing().when(inventoryService).releaseHoldForCancelledBooking(any(), any(), any(), any());

        mvc.perform(post(BASE + "/release")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"hotelId\":\"h-1\",\"checkInDate\":\"2025-12-01\",\"checkOutDate\":\"2025-12-05\",\"rooms\":[]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Đã release hold"));
    }
}
