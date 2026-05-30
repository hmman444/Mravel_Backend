package com.mravel.common.i18n;

import java.util.Map;

public final class LocaleUtil {

    private LocaleUtil() {}

    public static String pick(Map<String, String> localized, String locale) {
        return pick(localized, locale, LocaleConstants.DEFAULT_LOCALE);
    }

    public static String pick(Map<String, String> localized, String locale, String fallbackLocale) {
        if (localized == null || localized.isEmpty()) {
            return null;
        }

        String value = localized.get(locale);
        if (isNotBlank(value)) {
            return value;
        }

        value = localized.get(fallbackLocale);
        if (isNotBlank(value)) {
            return value;
        }

        for (String v : localized.values()) {
            if (isNotBlank(v)) {
                return v;
            }
        }
        return null;
    }

    public static String parseAcceptLanguage(String header) {
        if (header == null || header.isBlank()) {
            return LocaleConstants.DEFAULT_LOCALE;
        }

        String first = header.split(",")[0].trim();
        String tag = first.split(";")[0].trim();
        String lang = tag.split("-")[0].toLowerCase();

        return LocaleConstants.SUPPORTED_LOCALES.contains(lang)
                ? lang
                : LocaleConstants.DEFAULT_LOCALE;
    }

    public static String normalize(String locale) {
        if (locale == null || locale.isBlank()) {
            return LocaleConstants.DEFAULT_LOCALE;
        }
        String lang = locale.split("-")[0].toLowerCase();
        return LocaleConstants.SUPPORTED_LOCALES.contains(lang)
                ? lang
                : LocaleConstants.DEFAULT_LOCALE;
    }

    private static boolean isNotBlank(String s) {
        return s != null && !s.isBlank();
    }
}
