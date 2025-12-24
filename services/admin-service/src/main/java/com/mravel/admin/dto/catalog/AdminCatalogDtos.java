package com.mravel.admin.dto.catalog;

import jakarta.validation.constraints.NotBlank;

public class AdminCatalogDtos {

    public record ReasonReq(
            @NotBlank String reason) {
    }
}
