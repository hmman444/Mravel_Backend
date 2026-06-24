package com.mravel.admin.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AdminGlobalExceptionHandlerTest {

    private MockMvc mvc;

    @RestController
    static class ThrowingController {
        @GetMapping("/test/illegal-state")
        void illegalState() { throw new IllegalStateException("conflict msg"); }

        @GetMapping("/test/illegal-arg")
        void illegalArg() { throw new IllegalArgumentException("bad arg"); }

        @PostMapping("/test/msg-not-readable")
        void msgNotReadable(@RequestBody Object body) {}

        @GetMapping("/test/resource-access")
        void resourceAccess() { throw new ResourceAccessException("timeout"); }

        @GetMapping("/test/security")
        void security() { throw new SecurityException("forbidden"); }

        @GetMapping("/test/generic")
        void generic() { throw new RuntimeException("unexpected"); }
    }

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(new ThrowingController())
                .setControllerAdvice(new AdminGlobalExceptionHandler())
                .build();
    }

    @Test
    void illegalState_returns409() throws Exception {
        mvc.perform(get("/test/illegal-state"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("conflict msg"));
    }

    @Test
    void illegalArgument_returns400() throws Exception {
        mvc.perform(get("/test/illegal-arg"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("bad arg"));
    }

    @Test
    void invalidJson_returns400() throws Exception {
        mvc.perform(post("/test/msg-not-readable")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{bad json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Dữ liệu gửi lên không hợp lệ"));
    }

    @Test
    void resourceAccess_returns503() throws Exception {
        mvc.perform(get("/test/resource-access"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Không kết nối được tới dịch vụ liên quan. Vui lòng thử lại sau."));
    }

    @Test
    void securityException_returns401() throws Exception {
        mvc.perform(get("/test/security"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("forbidden"));
    }

    @Test
    void genericException_returns500() throws Exception {
        mvc.perform(get("/test/generic"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Đã xảy ra lỗi hệ thống"));
    }
}
