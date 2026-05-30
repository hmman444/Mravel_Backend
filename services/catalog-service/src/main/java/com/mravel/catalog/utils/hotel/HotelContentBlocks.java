package com.mravel.catalog.utils.hotel;

import com.mravel.catalog.model.doc.HotelDoc.ContentBlock;
import com.mravel.catalog.model.doc.HotelDoc.ContentBlock.BlockType;
import com.mravel.catalog.model.doc.HotelDoc.ContentBlock.ContentSection;

import java.util.Map;

public final class HotelContentBlocks {

    private HotelContentBlocks() {
    }

    // No-section overloads (legacy). Bản String delegate sang bản Map (chỉ vi).

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

    // With-section overloads

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
}
