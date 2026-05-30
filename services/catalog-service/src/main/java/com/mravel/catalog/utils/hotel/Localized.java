package com.mravel.catalog.utils.hotel;

import com.mravel.common.i18n.LocaleConstants;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Localized {
  private Localized() {}

  public static Map<String, String> vi(String value) {
    if (value == null || value.isBlank()) return null;
    return Map.of(LocaleConstants.VI, value);
  }

  public static Map<String, String> of(String viValue, String enValue) {
    if (viValue == null && enValue == null) return null;
    Map<String, String> m = new LinkedHashMap<>();
    if (viValue != null && !viValue.isBlank()) m.put(LocaleConstants.VI, viValue);
    if (enValue != null && !enValue.isBlank()) m.put(LocaleConstants.EN, enValue);
    return m.isEmpty() ? null : m;
  }
}
