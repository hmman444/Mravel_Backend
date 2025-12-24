package com.mravel.catalog.service.admin;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.repository.RestaurantDocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AdminRestaurantModerationService {

    private final RestaurantDocRepository repo;

    public RestaurantDoc approve(String id, Long adminId) {
        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        ensureStatus(doc.getModeration(), RestaurantDoc.RestaurantStatus.PENDING_REVIEW,
                "Only PENDING_REVIEW can be approved");

        if (doc.getModeration() == null)
            doc.setModeration(new RestaurantDoc.ModerationInfo());
        doc.getModeration().setStatus(RestaurantDoc.RestaurantStatus.APPROVED);

        // clear sạch
        doc.getModeration().setRejectionReason(null);
        doc.getModeration().setBlockedReason(null);

        touchAdmin(doc, adminId);
        return repo.save(doc);
    }

    public RestaurantDoc getByIdForAdmin(String id) {
        return repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
    }

    public RestaurantDoc reject(String id, Long adminId, String reason) {
        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        ensureStatus(doc.getModeration(), RestaurantDoc.RestaurantStatus.PENDING_REVIEW,
                "Only PENDING_REVIEW can be rejected");

        if (doc.getModeration() == null)
            doc.setModeration(new RestaurantDoc.ModerationInfo());
        doc.getModeration().setStatus(RestaurantDoc.RestaurantStatus.REJECTED);

        doc.getModeration().setRejectionReason(reason);
        doc.getModeration().setBlockedReason(null);

        touchAdmin(doc, adminId);
        return repo.save(doc);
    }

    public RestaurantDoc block(String id, Long adminId, String reason) {
        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        ensureStatus(doc.getModeration(), RestaurantDoc.RestaurantStatus.APPROVED,
                "Only APPROVED can be blocked");

        if (doc.getModeration() == null)
            doc.setModeration(new RestaurantDoc.ModerationInfo());
        doc.getModeration().setStatus(RestaurantDoc.RestaurantStatus.BLOCKED);

        doc.getModeration().setBlockedReason(reason);
        doc.getModeration().setRejectionReason(null);

        touchAdmin(doc, adminId);
        return repo.save(doc);
    }

    public RestaurantDoc unblock(String id, Long adminId) {
        RestaurantDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        ensureStatus(doc.getModeration(), RestaurantDoc.RestaurantStatus.BLOCKED,
                "Only BLOCKED can be unblocked");

        if (doc.getModeration() == null)
            doc.setModeration(new RestaurantDoc.ModerationInfo());
        doc.getModeration().setStatus(RestaurantDoc.RestaurantStatus.APPROVED);

        doc.getModeration().setBlockedReason(null);

        // clear unlock request
        doc.getModeration().setUnlockRequestedAt(null);
        doc.getModeration().setUnlockRequestReason(null);

        touchAdmin(doc, adminId);
        return repo.save(doc);
    }

    private void ensureStatus(RestaurantDoc.ModerationInfo mod, RestaurantDoc.RestaurantStatus expected, String msg) {
        if (mod == null || mod.getStatus() != expected)
            throw new IllegalStateException(msg);
    }

    private void touchAdmin(RestaurantDoc doc, Long adminId) {
        if (doc.getModeration() == null)
            doc.setModeration(new RestaurantDoc.ModerationInfo());
        doc.getModeration().setLastActionByAdminId(String.valueOf(adminId));
        doc.getModeration().setLastActionAt(Instant.now());
    }
}
