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

public class PlaceDtos {

        public record PlaceSummaryDTO(
                        String id,
                        String name,
                        String slug,
                        PlaceKind kind,
                        VenueType venueType,
                        String addressLine,
                        String provinceName,
                        Double latitude,
                        Double longitude,
                        PriceLevel priceLevel,
                        Double avgRating,
                        Integer reviewsCount,
                        String coverImageUrl,
                        Boolean active) {
        }

        public record ImageDTO(String url, String caption, boolean cover, Integer sortOrder) {
        }

        public record CategoryDTO(String name, String slug) {
        }

        public record TagDTO(String name, String slug, TagType type) {
        }

        public record OpenHourDTO(
                        DayOfWeek dayOfWeek,
                        LocalTime openTime,
                        LocalTime closeTime,
                        boolean open24h,
                        boolean closed) {
        }

        public record ContentBlockDTO(
                        PlaceDoc.ContentBlock.BlockType type, // dùng chung enum luôn (mục 3)
                        String text,
                        ImageDTO image,
                        List<ImageDTO> gallery,
                        Double mapLat,
                        Double mapLon) {
        }

        public record PlaceDetailDTO(
                        String id,
                        String name,
                        String slug,
                        PlaceKind kind,
                        VenueType venueType,
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
                        Double avgRating,
                        Integer reviewsCount,
                        boolean active,
                        String parentSlug,
                        List<String> ancestors,
                        Integer childrenCount,
                        List<CategoryDTO> categories,
                        List<TagDTO> tags,
                        List<ImageDTO> images,
                        List<OpenHourDTO> openHours,
                        List<ContentBlockDTO> content) {
        }
}
