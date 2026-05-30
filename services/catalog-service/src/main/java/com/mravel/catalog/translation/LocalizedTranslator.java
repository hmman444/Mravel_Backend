package com.mravel.catalog.translation;

import com.mravel.common.i18n.LocaleConstants;
import com.mravel.common.i18n.LocaleContext;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Điền locale còn thiếu cho 1 field localized (Map locale->value) bằng cách dịch tự động.
 *
 * Quy tắc (hướng "admin nhập 1 ngôn ngữ"):
 * - source = locale admin đang nhập (LocaleContext, từ Accept-Language).
 * - CREATE: dịch source -> locale kia để có cả 2.
 * - UPDATE: chỉ dịch lại khi giá trị locale-nguồn THAY ĐỔI so với bản cũ; không đổi -> giữ nguyên cả 2
 *   (tránh ghi đè bản dịch tay/seed tốt). Nếu admin nhập sẵn cả 2 locale thì tôn trọng, không dịch.
 */
@Component
@RequiredArgsConstructor
public class LocalizedTranslator {

    private final TranslationService translator;

    /** Dùng cho CREATE (không có bản cũ). */
    public Map<String, String> resolveCreate(Map<String, String> incoming) {
        return resolve(incoming, null);
    }

    /**
     * Khi input là String 1 ngôn ngữ (theo locale hiện tại) — vd DTO admin amenity dùng String.
     * Bọc thành Map { localeHiệnTại: value } rồi resolve (giữ field không đổi, dịch field đổi).
     */
    public Map<String, String> resolveFromString(String value, Map<String, String> old) {
        if (value == null || value.isBlank()) {
            return old; // không nhập -> giữ nguyên bản cũ
        }
        return resolve(Map.of(LocaleContext.get(), value), old);
    }

    public Map<String, String> resolve(Map<String, String> incoming, Map<String, String> old) {
        if (incoming == null || incoming.isEmpty()) {
            return old; // không gửi gì -> giữ nguyên bản cũ (update) hoặc null (create)
        }

        // Xác định locale nguồn: ưu tiên locale hiện tại; nếu incoming không có thì lấy key đầu tiên có giá trị.
        String src = LocaleContext.get();
        if (isBlank(incoming.get(src))) {
            src = incoming.entrySet().stream()
                    .filter(e -> !isBlank(e.getValue()))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(src);
        }
        String srcVal = incoming.get(src);
        if (isBlank(srcVal)) {
            return old != null ? old : incoming;
        }

        String other = LocaleConstants.VI.equals(src) ? LocaleConstants.EN : LocaleConstants.VI;

        // UPDATE & locale nguồn không đổi -> giữ nguyên bản cũ.
        if (old != null && Objects.equals(old.get(src), srcVal)) {
            return old;
        }

        Map<String, String> result = new LinkedHashMap<>();
        result.put(src, srcVal);

        // Admin nhập sẵn locale kia -> tôn trọng; ngược lại dịch tự động.
        String otherProvided = incoming.get(other);
        if (!isBlank(otherProvided)) {
            result.put(other, otherProvided);
        } else {
            result.put(other, translator.translate(srcVal, src, other));
        }
        return result;
    }

    private static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}
