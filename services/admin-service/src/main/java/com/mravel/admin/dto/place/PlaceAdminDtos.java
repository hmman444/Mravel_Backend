package com.mravel.admin.dto.place;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.mravel.admin.enums.place.PlaceKind;
import com.mravel.admin.enums.place.PriceLevel;
import com.mravel.admin.enums.place.TagType;
import com.mravel.admin.enums.place.VenueType;

public class PlaceAdminDtos {

        public record UpsertPlaceRequest(
                        Boolean active,
                        PlaceKind kind,
                        VenueType venueType,
                        String parentSlug,

                        Map<String, String> name,
                        String slug,
                        Map<String, String> shortDescription,
                        Map<String, String> description,

                        String phone,
                        String email,
                        String website,

                        Map<String, String> addressLine,
                        String countryCode,
                        String provinceCode,
                        String districtCode,
                        String wardCode,
                        Map<String, String> provinceName,
                        Map<String, String> districtName,
                        Map<String, String> wardName,

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

        public record ImageReq(String url, Map<String, String> caption, Boolean cover, Integer sortOrder) {
        }

        public record CategoryReq(Map<String, String> name, String slug) {
        }

        public record TagReq(Map<String, String> name, String slug, TagType type) {
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
                        Map<String, String> text,
                        ImageReq image,
                        List<ImageReq> gallery,
                        Double mapLat,
                        Double mapLon) {
        }

        public enum BlockType {
                HEADING, PARAGRAPH, IMAGE, GALLERY, QUOTE, DIVIDER, INFOBOX, MAP
        }

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
