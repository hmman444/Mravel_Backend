package com.mravel.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.booking.dto.HotelBookingDtos.CreateHotelBookingRequest;
import com.mravel.booking.dto.HotelBookingDtos.HotelBookingCreatedDTO;
import com.mravel.booking.service.HotelBookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HotelBookingController.class)
@AutoConfigureMockMvc(addFilters = false)
class HotelBookingControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @MockBean HotelBookingService hotelBookingService;

    private static final String URL = "/api/booking/hotels";

    @Test
    void createBooking_success_returns200WithBookingCode() throws Exception {
        var req = new CreateHotelBookingRequest(
                null, "Nguyen Van A", "0901234567", "a@test.com", null,
                "hotel-1", "test-hotel", "Test Hotel",
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(3),
                "DEPOSIT", "MOMO_WALLET",
                List.of(new com.mravel.booking.dto.HotelBookingDtos.SelectedRoom(
                        "rt1", "Deluxe", "rp1", "Standard", 1, BigDecimal.valueOf(1200000))));

        var dto = new HotelBookingCreatedDTO(
                "BK-001", "Test Hotel", "test-hotel",
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), 2,
                1, "DEPOSIT",
                BigDecimal.valueOf(2400000), BigDecimal.valueOf(720000),
                BigDecimal.valueOf(720000), "VND", "MOMO_WALLET",
                "https://pay.momo.vn/abc");

        when(hotelBookingService.createHotelBooking(any(), any())).thenReturn(dto);

        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Tạo booking khách sạn thành công"))
                .andExpect(jsonPath("$.data.bookingCode").value("BK-001"))
                .andExpect(jsonPath("$.data.paymentUrl").value("https://pay.momo.vn/abc"));
    }

    @Test
    void createBooking_serviceThrowsIllegalArgument_returns400() throws Exception {
        when(hotelBookingService.createHotelBooking(any(), any()))
                .thenThrow(new IllegalArgumentException("Ngày nhận / trả phòng không hợp lệ"));

        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Ngày nhận / trả phòng không hợp lệ"));
    }

    @Test
    void createBooking_serviceThrowsIllegalState_returns409() throws Exception {
        when(hotelBookingService.createHotelBooking(any(), any()))
                .thenThrow(new IllegalStateException("Hết phòng"));

        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Hết phòng"));
    }
}
