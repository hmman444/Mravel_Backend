package com.mravel.plan.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GlobalExceptionHandlerTest {

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(new ThrowingController())
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();

    @RestController
    @RequestMapping("/test/exceptions")
    static class ThrowingController {

        @GetMapping("/not-found")
        String notFound() {
            throw new NotFoundException("Plan not found");
        }

        @GetMapping("/bad-request")
        String badRequest() {
            throw new BadRequestException("Invalid payload");
        }

        @GetMapping("/forbidden")
        String forbidden() {
            throw new ForbiddenException("No permission");
        }

        @GetMapping("/conflict")
        String conflict() {
            throw new ConflictException(5L, 3L);
        }

        @GetMapping("/legacy-runtime")
        String legacyRuntime() {
            throw new RuntimeException("Plan not found");
        }
    }

    @Test
    void shouldReturnNotFoundContract() throws Exception {
        mockMvc.perform(get("/test/exceptions/not-found").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.data.status").value(404))
                .andExpect(jsonPath("$.data.code").value(ErrorCodes.NOT_FOUND))
                .andExpect(jsonPath("$.data.message").value("Plan not found"))
                .andExpect(jsonPath("$.data.path").value("/test/exceptions/not-found"));
    }

    @Test
    void shouldReturnBadRequestContract() throws Exception {
        mockMvc.perform(get("/test/exceptions/bad-request").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.data.status").value(400))
                .andExpect(jsonPath("$.data.code").value(ErrorCodes.BAD_REQUEST))
                .andExpect(jsonPath("$.data.message").value("Invalid payload"));
    }

    @Test
    void shouldReturnForbiddenContract() throws Exception {
        mockMvc.perform(get("/test/exceptions/forbidden").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.data.status").value(403))
                .andExpect(jsonPath("$.data.code").value(ErrorCodes.FORBIDDEN))
                .andExpect(jsonPath("$.data.message").value("No permission"));
    }

    @Test
    void shouldReturnConflictContractWithDetails() throws Exception {
        mockMvc.perform(get("/test/exceptions/conflict").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.data.status").value(409))
                .andExpect(jsonPath("$.data.code").value(ErrorCodes.STALE_VERSION))
                .andExpect(jsonPath("$.data.details.currentVersion").value(5))
                .andExpect(jsonPath("$.data.details.yourVersion").value(3));
    }

    @Test
    void shouldMapLegacyRuntimeToNotFound() throws Exception {
        mockMvc.perform(get("/test/exceptions/legacy-runtime").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.data.code").value(ErrorCodes.NOT_FOUND));
    }
}
