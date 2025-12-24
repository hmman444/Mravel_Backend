package com.mravel.catalog.service.admin;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.repository.HotelDocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AdminHotelModerationService {

    private final HotelDocRepository repo;

    public HotelDoc approve(String id, Long adminId) {
        HotelDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        ensureStatus(doc.getModeration(), HotelDoc.HotelStatus.PENDING_REVIEW, "Only PENDING_REVIEW can be approved");

        if (doc.getModeration() == null)
            doc.setModeration(new HotelDoc.ModerationInfo());
        doc.getModeration().setStatus(HotelDoc.HotelStatus.APPROVED);

        doc.getModeration().setRejectionReason(null);
        doc.getModeration().setBlockedReason(null);

        touchAdmin(doc, adminId);
        return repo.save(doc);
    }

    public HotelDoc reject(String id, Long adminId, String reason) {
        HotelDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        ensureStatus(doc.getModeration(), HotelDoc.HotelStatus.PENDING_REVIEW, "Only PENDING_REVIEW can be rejected");

        if (doc.getModeration() == null)
            doc.setModeration(new HotelDoc.ModerationInfo());
        doc.getModeration().setStatus(HotelDoc.HotelStatus.REJECTED);

        doc.getModeration().setRejectionReason(reason);
        doc.getModeration().setBlockedReason(null);
        touchAdmin(doc, adminId);
        return repo.save(doc);
    }

    public HotelDoc block(String id, Long adminId, String reason) {
        HotelDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        ensureStatus(doc.getModeration(), HotelDoc.HotelStatus.APPROVED, "Only APPROVED can be blocked");

        if (doc.getModeration() == null)
            doc.setModeration(new HotelDoc.ModerationInfo());
        doc.getModeration().setStatus(HotelDoc.HotelStatus.BLOCKED);

        doc.getModeration().setBlockedReason(reason);
        doc.getModeration().setRejectionReason(null);

        touchAdmin(doc, adminId);
        return repo.save(doc);
    }

    public HotelDoc unblock(String id, Long adminId) {
        HotelDoc doc = repo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        ensureStatus(doc.getModeration(), HotelDoc.HotelStatus.BLOCKED, "Only BLOCKED can be unblocked");

        if (doc.getModeration() == null)
            doc.setModeration(new HotelDoc.ModerationInfo());
        doc.getModeration().setStatus(HotelDoc.HotelStatus.APPROVED);

        doc.getModeration().setBlockedReason(null);

        // clear unlock request
        doc.getModeration().setUnlockRequestedAt(null);
        doc.getModeration().setUnlockRequestReason(null);

        touchAdmin(doc, adminId);
        return repo.save(doc);
    }

    private void ensureStatus(HotelDoc.ModerationInfo mod, HotelDoc.HotelStatus expected, String msg) {
        if (mod == null || mod.getStatus() != expected)
            throw new IllegalStateException(msg);
    }

    private void touchAdmin(HotelDoc doc, Long adminId) {
        if (doc.getModeration() == null)
            doc.setModeration(new HotelDoc.ModerationInfo());
        doc.getModeration().setLastActionByAdminId(String.valueOf(adminId));
        doc.getModeration().setLastActionAt(java.time.Instant.now());
    }
}
