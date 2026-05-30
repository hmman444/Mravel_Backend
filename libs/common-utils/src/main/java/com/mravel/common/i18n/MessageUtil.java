package com.mravel.common.i18n;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * Resolve i18n message theo locale hiện tại của request (LocaleContext).
 * Dùng cho GlobalExceptionHandler và bất kỳ chỗ nào cần message đa ngôn ngữ
 * trong luồng request.
 */
@Component
@RequiredArgsConstructor
public class MessageUtil {

    private final MessageSource messageSource;

    public String get(String code, Object... args) {
        Locale locale = Locale.forLanguageTag(LocaleContext.get());
        return messageSource.getMessage(code, args, code, locale);
    }
}
