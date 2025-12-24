package com.mravel.catalog.dto.admin;

import jakarta.validation.constraints.NotBlank;
import java.time.Instant;

public class AdminCatalogDtos {

    public record AdminServiceSummaryDTO(
            String id,
            String name,
            String slug,
            String destinationSlug,
            String cityName,
            Boolean active,

            String partnerId,
            String partnerName,
            Instant partnerLastUpdatedAt,

            String moderationStatus,
            String rejectionReason,
            String blockedReason,

            Instant unlockRequestedAt,
            String unlockRequestReason,

            String lastActionByAdminId,
            Instant lastActionAt) {
    }

    public record ReasonBody(
            @NotBlank String reason) {
    }
}
