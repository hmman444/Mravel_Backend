package com.mravel.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.booking.dto.RestaurantBookingDtos.CreateRestaurantBookingRequest;
import com.mravel.booking.dto.RestaurantBookingDtos.RestaurantBookingCreatedDTO;
import com.mravel.booking.dto.RestaurantBookingDtos.SelectedTable;
import com.mravel.booking.service.RestaurantBookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantBookingController.class)
@AutoConfigureMockMvc(addFilters = false)
class RestaurantBookingControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;
    @MockBean RestaurantBookingService service;

    private static final String URL = "/api/booking/restaurants";

    @Test
    void create_success_returns200WithBookingCode() throws Exception {
        var req = new CreateRestaurantBookingRequest(
                null, "Tran B", "0907654321", "b@test.com", null,
                "rest-1", "nha-hang-xyz", "Nhà hàng XYZ",
                LocalDate.now().plusDays(3), LocalTime.of(19, 30), 120, 4,
                "VNPAY",
                List.of(new SelectedTable("tt1", "Bàn 4 người", 4, 1, BigDecimal.valueOf(200000))));

        var dto = new RestaurantBookingCreatedDTO(
                "RB-001", "Nhà hàng XYZ", "nha-hang-xyz",
                LocalDate.now().plusDays(3), LocalTime.of(19, 30), 120, 4, 1,
                "DEPOSIT", BigDecimal.valueOf(200000), BigDecimal.valueOf(200000),
                "VND", "VNPAY", "https://pay.vnpay.vn/abc");

        when(service.create(any(), any())).thenReturn(dto);

        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Tạo booking nhà hàng thành công"))
                .andExpect(jsonPath("$.data.bookingCode").value("RB-001"));
    }

    @Test
    void create_serviceThrowsIllegalArgument_returns400() throws Exception {
        when(service.create(any(), any()))
                .thenThrow(new IllegalArgumentException("Chưa chọn bàn hợp lệ"));

        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Chưa chọn bàn hợp lệ"));
    }
}
