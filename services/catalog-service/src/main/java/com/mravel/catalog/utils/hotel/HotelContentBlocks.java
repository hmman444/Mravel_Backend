// src/main/java/com/mravel/catalog/utils/hotel/HotelContentBlocks.java
package com.mravel.catalog.utils.hotel;

import com.mravel.catalog.model.doc.HotelDoc.ContentBlock;
import com.mravel.catalog.model.doc.HotelDoc.ContentBlock.BlockType;
import com.mravel.catalog.model.doc.HotelDoc.ContentBlock.ContentSection;

public final class HotelContentBlocks {

    private HotelContentBlocks() {}

    // ====== HÀM CŨ (không có section) - vẫn giữ để seed cũ chạy bình thường ======

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

    // ====== HÀM MỚI (có ContentSection) ======

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
}