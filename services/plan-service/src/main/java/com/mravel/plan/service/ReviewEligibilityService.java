package com.mravel.plan.service;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.plan.model.Plan;
import com.mravel.plan.model.PlanCard;
import com.mravel.plan.model.PlanList;
import com.mravel.plan.model.PlanListType;
import com.mravel.plan.repository.PlanMemberRepository;
import com.mravel.plan.repository.PlanRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Kiểm tra user đã TRẢI NGHIỆM một dịch vụ catalog (hotel/restaurant/place) chưa,
 * dựa trên các plan mà user là tác giả hoặc thành viên.
 *
 * Điều kiện "đã trải nghiệm":
 *  - dịch vụ nằm trong 1 card thuộc DAY list có dayDate ĐÃ QUA (dayDate < hôm nay), và
 *  - chống backdate: ngày đó không nằm trước thời điểm tạo plan (createdAt ≤ dayDate).
 *
 * Khớp dịch vụ ↔ card (đọc activityDataJson):
 *  - mạnh: recommendation.id == targetId  (card do AI tạo)
 *  - mạnh: recommendation.slug == slug
 *  - best-effort: tên dịch vụ (chuẩn hoá, bỏ dấu) trùng field tên trong card tay
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewEligibilityService {

    private final PlanRepository planRepository;
    private final PlanMemberRepository planMemberRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /** Bật để chống "backdate" (chỉ tính khi plan tạo trước/đúng ngày đi). Tắt để dễ demo/test. */
    @Value("${review-gate.anti-backdate:false}")
    private boolean antiBackdate;

    /** Các key tên dịch vụ mà card tay trên board hay lưu trong activityDataJson. */
    private static final List<String> NAME_KEYS = List.of(
            "restaurantName", "placeName", "hotelName", "stayName",
            "storeName", "cinemaName", "venue", "locationName", "name");

    public record ExperienceResult(boolean eligible, LocalDate lastExperiencedDate) {}

    @Transactional(readOnly = true)
    public ExperienceResult check(Long userId, String targetId, String slug, String name) {
        if (userId == null || targetId == null || targetId.isBlank()) {
            return new ExperienceResult(false, null);
        }

        // Gom plan: tác giả + thành viên (không trùng).
        Map<Long, Plan> plans = new LinkedHashMap<>();
        for (Plan p : planRepository.findByAuthorId(userId)) {
            plans.put(p.getId(), p);
        }
        List<Long> memberPlanIds = planMemberRepository.findPlanIdsByUserId(userId);
        if (memberPlanIds != null && !memberPlanIds.isEmpty()) {
            for (Plan p : planRepository.findAllById(memberPlanIds)) {
                plans.putIfAbsent(p.getId(), p);
            }
        }

        LocalDate today = LocalDate.now();
        String normName = normalize(name);
        String normSlug = (slug == null || slug.isBlank()) ? null : slug.trim().toLowerCase();

        LocalDate last = null;
        for (Plan plan : plans.values()) {
            LocalDate createdDate = plan.getCreatedAt() == null ? null
                    : plan.getCreatedAt().atZone(ZoneId.systemDefault()).toLocalDate();
            if (plan.getLists() == null) continue;

            for (PlanList list : plan.getLists()) {
                if (list.getType() != PlanListType.DAY) continue;
                LocalDate d = list.getDayDate();
                if (d == null || !d.isBefore(today)) continue;                 // ngày phải đã qua
                if (antiBackdate && createdDate != null && createdDate.isAfter(d)) continue; // chống backdate (tùy chọn)
                if (list.getCards() == null) continue;

                for (PlanCard card : list.getCards()) {
                    if (matches(card, targetId, normSlug, normName)) {
                        if (last == null || d.isAfter(last)) last = d;
                    }
                }
            }
        }
        return new ExperienceResult(last != null, last);
    }

    private boolean matches(PlanCard card, String targetId, String normSlug, String normName) {
        String json = card.getActivityDataJson();
        if (json == null || json.isBlank()) return false;

        // Khớp mạnh nhất: id catalog (ObjectId 24-hex, duy nhất) xuất hiện trong JSON card.
        // Bắt mọi vị trí: recommendation.id (card AI) hoặc hotelLocation/restaurantLocation.placeId (card tay từ board).
        if (targetId != null && !targetId.isBlank() && json.contains(targetId)) return true;

        try {
            JsonNode root = objectMapper.readTree(json);

            JsonNode rec = root.get("recommendation");
            if (rec != null && !rec.isNull()) {
                String id = text(rec, "id");
                if (id != null && id.equals(targetId)) return true;             // mạnh: id
                String s = text(rec, "slug");
                if (normSlug != null && s != null && normSlug.equals(s.trim().toLowerCase())) return true; // mạnh: slug
                if (nameMatch(text(rec, "name"), normName)) return true;
            }

            // best-effort: tên trong card tay
            if (normName != null) {
                for (String k : NAME_KEYS) {
                    if (nameMatch(text(root, k), normName)) return true;
                }
            }
        } catch (Exception ex) {
            log.debug("[experienced] parse activityDataJson failed: {}", ex.getMessage());
        }
        return false;
    }

    private boolean nameMatch(String value, String normName) {
        if (normName == null) return false;
        String n = normalize(value);
        return n != null && n.equals(normName);
    }

    private static String text(JsonNode node, String field) {
        JsonNode v = node.get(field);
        return (v == null || v.isNull()) ? null : v.asText();
    }

    /** lowercase + bỏ dấu tiếng Việt + gộp khoảng trắng. */
    private static String normalize(String s) {
        if (s == null) return null;
        String t = Normalizer.normalize(s.trim().toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replace("đ", "d")
                .replaceAll("\\s+", " ")
                .trim();
        return t.isBlank() ? null : t;
    }
}
