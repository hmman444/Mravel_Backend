package com.mravel.catalog.utils.hotel;

import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.enums.AmenityGroup;
import com.mravel.catalog.model.enums.AmenitySection;
import com.mravel.catalog.model.doc.HotelDoc.FaqItem;
import com.mravel.catalog.model.doc.HotelDoc.NearbyPlace;
import com.mravel.catalog.model.doc.HotelDoc.ReviewKeywordStat;

import java.util.Map;

public final class HotelSeedHelpers {

    private HotelSeedHelpers() {
    }

    // Amenity helpers — name là Map<locale, value> (i18n).
    // Bản String giữ lại để tương thích, delegate sang bản Map (chỉ vi).

    public static AmenityCatalogDoc amenity(String code, String name, AmenityGroup group) {
        return amenity(code, Localized.vi(name), group, null, false);
    }

    public static AmenityCatalogDoc amenity(String code, Map<String, String> name, AmenityGroup group) {
        return amenity(code, name, group, null, false);
    }

    public static AmenityCatalogDoc amenity(
            String code,
            String name,
            AmenityGroup group,
            AmenitySection section,
            boolean highlight) {
        return amenity(code, Localized.vi(name), group, section, highlight);
    }

    public static AmenityCatalogDoc amenity(
            String code,
            Map<String, String> name,
            AmenityGroup group,
            AmenitySection section,
            boolean highlight) {
        return AmenityCatalogDoc.builder()
                .code(code)
                .name(name)
                .group(group)
                .section(section)
                .build();
    }

    public static AmenityCatalogDoc roomAmenity(String code, String name, AmenitySection section) {
        return roomAmenity(code, Localized.vi(name), section);
    }

    public static AmenityCatalogDoc roomAmenity(String code, Map<String, String> name, AmenitySection section) {
        return AmenityCatalogDoc.builder()
                .code(code)
                .name(name)
                .group(AmenityGroup.ROOM)
                .section(section)
                .build();
    }

    // Nearby / keyword / FAQ — String delegate sang Map.

    public static NearbyPlace nearby(String slug, String name, String category, Double distanceMeters) {
        return nearby(slug, Localized.vi(name), Localized.vi(category), distanceMeters);
    }

    public static NearbyPlace nearby(String slug, Map<String, String> name, Map<String, String> category,
            Double distanceMeters) {
        return NearbyPlace.builder()
                .placeSlug(slug)
                .name(name)
                .category(category)
                .distanceMeters(distanceMeters)
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

    public static FaqItem faq(String q, String a) {
        return faq(Localized.vi(q), Localized.vi(a));
    }

    public static FaqItem faq(Map<String, String> q, Map<String, String> a) {
        return FaqItem.builder()
                .question(q)
                .answer(a)
                .build();
    }
}
