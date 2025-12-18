package com.mravel.catalog.dto.place;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.model.enums.PriceLevel;
import com.mravel.catalog.model.enums.TagType;
import com.mravel.catalog.model.enums.VenueType;

public class PlaceAdminDtos {

    // ========== UPSERT REQUEST ==========
    public record UpsertPlaceRequest(
            Boolean active, // optional (default true on create)
            PlaceKind kind, // required
            VenueType venueType, // required when kind=VENUE
            String parentSlug, // required when kind=POI/VENUE

            String name, // required
            String slug, // optional: nếu null -> auto generate từ name (service xử lý)
            String shortDescription,
            String description,

            String phone,
            String email,
            String website,

            String addressLine,
            String countryCode,
            String provinceCode,
            String districtCode,
            String wardCode,
            String provinceName,
            String districtName,
            String wardName,

            Double latitude,
            Double longitude,

            PriceLevel priceLevel,
            BigDecimal minPrice,
            BigDecimal maxPrice,

            List<ImageReq> images,
            List<OpenHourReq> openHours,
            List<CategoryReq> categories,
            List<TagReq> tags,
            List<ContentBlockReq> content) {
    }

    public record ImageReq(String url, String caption, Boolean cover, Integer sortOrder) {
    }

    public record CategoryReq(String name, String slug) {
    }

    public record TagReq(String name, String slug, TagType type) {
    }

    public record OpenHourReq(
            DayOfWeek dayOfWeek,
            LocalTime openTime,
            LocalTime closeTime,
            Boolean open24h,
            Boolean closed) {
    }

    public record ContentBlockReq(
            PlaceDoc.ContentBlock.BlockType type,
            String text,
            ImageReq image,
            List<ImageReq> gallery,
            Double mapLat,
            Double mapLon) {
    }

    // ========== SIMPLE RESPONSE ==========
    public record PlaceAdminResponse(
            String id,
            String name,
            String slug,
            PlaceKind kind,
            VenueType venueType,
            boolean active,
            String parentSlug,
            List<String> ancestors,
            Integer childrenCount) {
    }
}
