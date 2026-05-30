package com.mravel.common.i18n;

public final class LocaleContext {

    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    private LocaleContext() {}

    public static void set(String locale) {
        HOLDER.set(locale);
    }

    public static String get() {
        String locale = HOLDER.get();
        return locale != null ? locale : LocaleConstants.DEFAULT_LOCALE;
    }

    public static void clear() {
        HOLDER.remove();
    }
}
