package com.mravel.catalog.translation;

/**
 * Dịch văn bản giữa 2 locale. Có thể swap provider (gtx free / Google Cloud...) qua cấu hình.
 */
public interface TranslationService {

    /**
     * Dịch {@code text} từ {@code sourceLocale} sang {@code targetLocale}.
     * Trả về chính {@code text} nếu input rỗng, source==target, hoặc khi dịch lỗi (fail-safe).
     */
    String translate(String text, String sourceLocale, String targetLocale);
}
