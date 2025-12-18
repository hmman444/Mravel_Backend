package com.mravel.admin.dto.place;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import com.mravel.admin.enums.place.PlaceKind;
import com.mravel.admin.enums.place.PriceLevel;
import com.mravel.admin.enums.place.TagType;
import com.mravel.admin.enums.place.VenueType;

public class PlaceAdminDtos {

    // ====== REQUEST ======
    public record UpsertPlaceRequest(
            Boolean active,
            PlaceKind kind,
            VenueType venueType,
            String parentSlug,

            String name,
            String slug,
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
            BlockType type,
            String text,
            ImageReq image,
            List<ImageReq> gallery,
            Double mapLat,
            Double mapLon) {
    }

    public enum BlockType {
        HEADING, PARAGRAPH, IMAGE, GALLERY, QUOTE, DIVIDER, INFOBOX, MAP
    }

    // ====== RESPONSE (catalog trả về) ======
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
