package com.mravel.booking.controller;

import com.mravel.booking.model.Payment;
import com.mravel.booking.payment.gateway.PaymentGatewayRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentMethodsController.class)
@AutoConfigureMockMvc(addFilters = false)
class PaymentMethodsControllerTest {

    @Autowired MockMvc mvc;
    @MockBean PaymentGatewayRegistry registry;

    private static final String URL = "/api/payment/methods";

    @Test
    void methods_allEnabled_returns3Items() throws Exception {
        when(registry.availableMethods()).thenReturn(Set.of(
                Payment.PaymentMethod.MOMO_WALLET,
                Payment.PaymentMethod.VNPAY,
                Payment.PaymentMethod.ZALOPAY));

        mvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(3))
                .andExpect(jsonPath("$.data[0].enabled").value(true));
    }

    @Test
    void methods_noneEnabled_allDisabled() throws Exception {
        when(registry.availableMethods()).thenReturn(Set.of());

        mvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(3))
                .andExpect(jsonPath("$.data[0].enabled").value(false))
                .andExpect(jsonPath("$.data[1].enabled").value(false))
                .andExpect(jsonPath("$.data[2].enabled").value(false));
    }

    @Test
    void methods_onlyVnpayEnabled_onlyVnpayTrue() throws Exception {
        when(registry.availableMethods()).thenReturn(Set.of(Payment.PaymentMethod.VNPAY));

        mvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[?(@.method=='VNPAY')].enabled").value(true))
                .andExpect(jsonPath("$.data[?(@.method=='MOMO_WALLET')].enabled").value(false));
    }
}
