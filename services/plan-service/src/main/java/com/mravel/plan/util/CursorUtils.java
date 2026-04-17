package com.mravel.plan.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.List;

/**
 * Utility for encoding/decoding Elasticsearch {@code search_after} sort values
 * as opaque URL-safe Base64 cursor strings.
 *
 * <p>Encoding: sortValues → JSON array → Base64-URL (no-padding)
 * <p>Decoding: Base64-URL → JSON array → {@code List<Object>}
 *
 * <p>Integer values are always decoded as {@code Long} to match ES field types.
 */
@Slf4j
public final class CursorUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .enable(DeserializationFeature.USE_LONG_FOR_INTS);

    private CursorUtils() {}

    /**
     * Encodes the sort values from the last {@code SearchHit} into a cursor string.
     *
     * @param sortValues {@code SearchHit.getSortValues()} of the last hit
     * @return Base64-URL cursor, or {@code null} if sortValues is null/empty
     */
    public static String encode(List<Object> sortValues) {
        if (sortValues == null || sortValues.isEmpty()) return null;
        try {
            byte[] json = MAPPER.writeValueAsBytes(sortValues);
            return Base64.getUrlEncoder().withoutPadding().encodeToString(json);
        } catch (Exception e) {
            log.warn("CursorUtils.encode failed: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Decodes a cursor string back to a list of ES sort values suitable for
     * {@code NativeQueryBuilder.withSearchAfter(...)}.
     *
     * @param cursor opaque cursor from a previous response
     * @return decoded sort values, or {@code null} if cursor is null/blank/invalid
     */
    public static List<Object> decode(String cursor) {
        if (cursor == null || cursor.isBlank()) return null;
        try {
            byte[] json = Base64.getUrlDecoder().decode(cursor);
            return MAPPER.readValue(json, new TypeReference<>() {});
        } catch (Exception e) {
            log.warn("CursorUtils.decode failed for cursor '{}': {}", cursor, e.getMessage());
            return null;
        }
    }
}
