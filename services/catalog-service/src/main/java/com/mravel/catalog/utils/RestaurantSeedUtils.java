// src/main/java/com/mravel/catalog/utils/RestaurantSeedUtils.java
package com.mravel.catalog.utils;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import com.mravel.catalog.utils.restaurant.Localized;

public final class RestaurantSeedUtils {

    private RestaurantSeedUtils() {
    }

    //
    // CONTENT BLOCK HELPERS
    //

    public static ContentBlock heading(String text) {
        return heading(Localized.vi(text));
    }

    public static ContentBlock heading(Map<String, String> text) {
        return ContentBlock.builder()
                .type(BlockType.HEADING)
                .text(text)
                .build();
    }

    public static ContentBlock paragraph(String text) {
        return paragraph(Localized.vi(text));
    }

    public static ContentBlock paragraph(Map<String, String> text) {
        return ContentBlock.builder()
                .type(BlockType.PARAGRAPH)
                .text(text)
                .build();
    }

    public static ContentBlock quote(String text) {
        return quote(Localized.vi(text));
    }

    public static ContentBlock quote(Map<String, String> text) {
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

    public static ContentBlock heading(ContentSection section, String text) {
        return heading(section, Localized.vi(text));
    }

    public static ContentBlock heading(ContentSection section, Map<String, String> text) {
        return ContentBlock.builder()
                .type(BlockType.HEADING)
                .section(section)
                .text(text)
                .build();
    }

    public static ContentBlock paragraph(ContentSection section, String text) {
        return paragraph(section, Localized.vi(text));
    }

    public static ContentBlock paragraph(ContentSection section, Map<String, String> text) {
        return ContentBlock.builder()
                .type(BlockType.PARAGRAPH)
                .section(section)
                .text(text)
                .build();
    }

    public static ContentBlock quote(ContentSection section, String text) {
        return quote(section, Localized.vi(text));
    }

    public static ContentBlock quote(ContentSection section, Map<String, String> text) {
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

    //
    // IMAGE HELPERS
    //

    public static Image img(String url, String caption, boolean cover, Integer sortOrder) {
        return img(url, Localized.vi(caption), cover, sortOrder);
    }

    public static Image img(String url, Map<String, String> caption, boolean cover, Integer sortOrder) {
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

    //
    // SEED HELPERS
    //

    public static OpeningHour openingHour(
            DayOfWeek day,
            int openHour,
            int openMinute,
            int closeHour,
            int closeMinute) {
        return OpeningHour.builder()
                .dayOfWeek(day)
                .openTime(LocalTime.of(openHour, openMinute))
                .closeTime(LocalTime.of(closeHour, closeMinute))
                .open24h(false)
                .closed(false)
                .build();
    }

    public static SuitableFor suitable(String code, String label) {
        return suitable(code, Localized.vi(label));
    }

    public static SuitableFor suitable(String code, Map<String, String> label) {
        return SuitableFor.builder()
                .code(code)
                .label(label)
                .build();
    }

    public static AmbienceTag ambience(String code, String label) {
        return ambience(code, Localized.vi(label));
    }

    public static AmbienceTag ambience(String code, Map<String, String> label) {
        return AmbienceTag.builder()
                .code(code)
                .label(label)
                .build();
    }

    public static SignatureDish signatureDish(String name) {
        return SignatureDish.builder()
                .name(Localized.vi(name))
                .description(null)
                .estimatedPrice(null)
                .highlight(true)
                .build();
    }

    public static SignatureDish signatureDishL(Map<String, String> name) {
        return SignatureDish.builder()
                .name(name)
                .description(null)
                .estimatedPrice(null)
                .highlight(true)
                .build();
    }

    public static SignatureDish signatureDish(String name, String description, BigDecimal estimatedPrice) {
        return signatureDish(Localized.vi(name), Localized.vi(description), estimatedPrice);
    }

    public static SignatureDish signatureDish(Map<String, String> name, Map<String, String> description,
            BigDecimal estimatedPrice) {
        return SignatureDish.builder()
                .name(name)
                .description(description)
                .estimatedPrice(estimatedPrice)
                .highlight(true)
                .build();
    }

    public static BlackoutDateRule blackoutGregorian(int month, int day, String desc) {
        return blackoutGregorian(month, day, Localized.vi(desc));
    }

    public static BlackoutDateRule blackoutGregorian(int month, int day, Map<String, String> desc) {
        return BlackoutDateRule.builder()
                .dateType(BlackoutDateType.GREGORIAN_DATE)
                .month(month)
                .day(day)
                .description(desc)
                .build();
    }

    public static ReviewKeywordStat keyword(String code, String label, Integer count) {
        return keyword(code, Localized.vi(label), count);
    }

    public static ReviewKeywordStat keyword(String code, Map<String, String> label, Integer count) {
        return ReviewKeywordStat.builder()
                .code(code)
                .label(label)
                .count(count)
                .build();
    }

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
