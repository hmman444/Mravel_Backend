package com.mravel.common.i18n;

import java.util.Set;

public final class LocaleConstants {

    public static final String VI = "vi";
    public static final String EN = "en";

    public static final String DEFAULT_LOCALE = VI;

    public static final Set<String> SUPPORTED_LOCALES = Set.of(VI, EN);

    private LocaleConstants() {}
}
