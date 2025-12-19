package com.mravel.catalog.dto.partner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PartnerCatalogDtos {

    public enum PendingReason { CREATE, UPDATE }

    // wrapper JSON partner-service đang gửi: { partnerId, pendingReason, payload }
    public record UpsertWrapper<T>(
            @NotNull Long partnerId,
            @NotNull PendingReason pendingReason,
            @NotNull T payload
    ) {}

    public record UnlockRequestWrapper(
            @NotNull Long partnerId,
            @NotBlank String reason
    ) {}

    // ===== payload tối thiểu (đúng như PartnerDtos của partner-service) =====
    public record UpsertHotelReq(
            @NotBlank String name,
            String destinationSlug,
            String cityName,
            String districtName,
            String wardName,
            String addressLine,
            Double lat,
            Double lng,
            String phone,
            String email,
            String thumbnailUrl
    ) {}

    public record UpsertRestaurantReq(
            @NotBlank String name,
            String destinationSlug,
            String cityName,
            String districtName,
            String wardName,
            String addressLine,
            Double lat,
            Double lng,
            String phone,
            String email,
            String thumbnailUrl
    ) {}
}