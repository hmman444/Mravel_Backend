// src/main/java/com/mravel/catalog/utils/RestaurantSeedUtils.java
package com.mravel.catalog.utils;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mravel.catalog.model.doc.RestaurantDoc.AmbienceTag;
import com.mravel.catalog.model.doc.RestaurantDoc.BlackoutDateRule;
import com.mravel.catalog.model.doc.RestaurantDoc.BlackoutDateType;
import com.mravel.catalog.model.doc.RestaurantDoc.ContentBlock;
import com.mravel.catalog.model.doc.RestaurantDoc.ContentBlock.BlockType;
import com.mravel.catalog.model.doc.RestaurantDoc.ContentBlock.ContentSection;
import com.mravel.catalog.model.doc.RestaurantDoc.Image;
import com.mravel.catalog.model.doc.RestaurantDoc.OpeningHour;
import com.mravel.catalog.model.doc.RestaurantDoc.ReviewKeywordStat;
import com.mravel.catalog.model.doc.RestaurantDoc.SignatureDish;
import com.mravel.catalog.model.doc.RestaurantDoc.SuitableFor;

public final class RestaurantSeedUtils {

    private RestaurantSeedUtils() {}

    // =====================================================================
    //                          CONTENT BLOCK HELPERS
    // =====================================================================

    // ---- Không có section (dùng cho seed đơn giản) ----
    public static ContentBlock heading(String text) {
        return ContentBlock.builder()
            .type(BlockType.HEADING)
            .text(text)
            .build();
    }

    public static ContentBlock paragraph(String text) {
        return ContentBlock.builder()
            .type(BlockType.PARAGRAPH)
            .text(text)
            .build();
    }

    public static ContentBlock quote(String text) {
        return ContentBlock.builder()
            .type(BlockType.QUOTE)
            .text(text)
            .build();
    }

    public static ContentBlock divider() {
        return ContentBlock.builder()
            .type(BlockType.DIVIDER)
            .build();
    }

    public static ContentBlock mapBlock(double[] lonLat) {
        return ContentBlock.builder()
            .type(BlockType.MAP)
            .mapLocation(lonLat)
            .build();
    }

    // ---- Có section (OVERVIEW / STORY / OTHER) ----
    public static ContentBlock heading(ContentSection section, String text) {
        return ContentBlock.builder()
            .type(BlockType.HEADING)
            .section(section)
            .text(text)
            .build();
    }

    public static ContentBlock paragraph(ContentSection section, String text) {
        return ContentBlock.builder()
            .type(BlockType.PARAGRAPH)
            .section(section)
            .text(text)
            .build();
    }

    public static ContentBlock quote(ContentSection section, String text) {
        return ContentBlock.builder()
            .type(BlockType.QUOTE)
            .section(section)
            .text(text)
            .build();
    }

    public static ContentBlock mapBlock(ContentSection section, double[] lonLat) {
        return ContentBlock.builder()
            .type(BlockType.MAP)
            .section(section)
            .mapLocation(lonLat)
            .build();
    }

    // =====================================================================
    //                            IMAGE HELPERS
    // =====================================================================

    public static Image img(String url, String caption, boolean cover, Integer sortOrder) {
        return Image.builder()
            .url(url)
            .caption(caption)
            .cover(cover)
            .sortOrder(sortOrder == null ? 0 : sortOrder)
            .build();
    }

    public static List<Image> withCover(Image cover, Image... rest) {
        List<Image> list = new ArrayList<>();
        list.add(cover);
        list.addAll(Arrays.asList(rest));
        return list;
    }

    // =====================================================================
    //                            SEED HELPERS
    // =====================================================================

    // ---- Opening hours ----
    public static OpeningHour openingHour(
        DayOfWeek day,
        int openHour,
        int openMinute,
        int closeHour,
        int closeMinute
    ) {
        return OpeningHour.builder()
            .dayOfWeek(day)
            .openTime(LocalTime.of(openHour, openMinute))
            .closeTime(LocalTime.of(closeHour, closeMinute))
            .open24h(false)
            .closed(false)
            .build();
    }

    // ---- SuitableFor ----
    public static SuitableFor suitable(String code, String label) {
        return SuitableFor.builder()
            .code(code)
            .label(label)
            .build();
    }

    // ---- Ambience ----
    public static AmbienceTag ambience(String code, String label) {
        return AmbienceTag.builder()
            .code(code)
            .label(label)
            .build();
    }

    // ---- Signature dishes ----
    public static SignatureDish signatureDish(String name) {
        return SignatureDish.builder()
            .name(name)
            .description(null)
            .estimatedPrice(null)
            .highlight(true)
            .build();
    }

    public static SignatureDish signatureDish(String name, String description, BigDecimal estimatedPrice) {
        return SignatureDish.builder()
            .name(name)
            .description(description)
            .estimatedPrice(estimatedPrice)
            .highlight(true)
            .build();
    }

    // ---- Blackout rule dương lịch ----
    public static BlackoutDateRule blackoutGregorian(int month, int day, String desc) {
        return BlackoutDateRule.builder()
            .dateType(BlackoutDateType.GREGORIAN_DATE)
            .month(month)
            .day(day)
            .description(desc)
            .build();
    }

    // ---- Review keyword ----
    public static ReviewKeywordStat keyword(String code, String label, Integer count) {
        return ReviewKeywordStat.builder()
            .code(code)
            .label(label)
            .count(count)
            .build();
    }

    // RestaurantSeedUtils.java
    public static ContentBlock imageBlock(ContentSection section, Image img) {
        return ContentBlock.builder()
            .type(ContentBlock.BlockType.IMAGE)
            .section(section)
            .image(img)
            .build();
    }

    public static ContentBlock gallery(ContentSection section, List<Image> imgs) {
        return ContentBlock.builder()
            .type(ContentBlock.BlockType.GALLERY)
            .section(section)
            .gallery(imgs)
            .build();
    }
}