// src/main/java/com/mravel/catalog/utils/hotel/HotelSeedHelpers.java
package com.mravel.catalog.utils.hotel;

import com.mravel.catalog.model.doc.HotelDoc.Amenity;
import com.mravel.catalog.model.doc.HotelDoc.AmenityGroup;
import com.mravel.catalog.model.doc.HotelDoc.AmenitySection;
import com.mravel.catalog.model.doc.HotelDoc.FaqItem;
import com.mravel.catalog.model.doc.HotelDoc.NearbyPlace;
import com.mravel.catalog.model.doc.HotelDoc.ReviewKeywordStat;

public final class HotelSeedHelpers {

    private HotelSeedHelpers() {}

    // ====== Amenity helpers ======

    // HÀM CŨ – cho seed đơn giản, không section / highlight
    public static Amenity amenity(String code, String name, AmenityGroup group) {
        return amenity(code, name, group, null, false);
    }

    // HÀM MỚI – đầy đủ group + section + highlight
    public static Amenity amenity(
        String code,
        String name,
        AmenityGroup group,
        AmenitySection section,
        boolean highlight
    ) {
        return Amenity.builder()
            .code(code)
            .name(name)
            .group(group)
            .section(section)
            .highlight(highlight)
            .build();
    }

    // HÀM MỚI – tiện ích cấp phòng
    public static Amenity roomAmenity(String code, String name, AmenitySection section) {
        return Amenity.builder()
            .code(code)
            .name(name)
            .group(AmenityGroup.ROOM)
            .section(section)
            .highlight(section == AmenitySection.HIGHLIGHT_FEATURES)
            .build();
    }

    // ====== Nearby / keyword / FAQ ======

    public static NearbyPlace nearby(String slug, String name, String category, Double distanceMeters) {
        return NearbyPlace.builder()
            .placeSlug(slug)
            .name(name)
            .category(category)
            .distanceMeters(distanceMeters)
            .build();
    }

    public static ReviewKeywordStat keyword(String code, String label, Integer count) {
        return ReviewKeywordStat.builder()
            .code(code)
            .label(label)
            .count(count)
            .build();
    }

    public static FaqItem faq(String q, String a) {
        return FaqItem.builder()
            .question(q)
            .answer(a)
            .build();
    }
}