package com.mravel.catalog.dto.place;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import com.mravel.catalog.model.enums.PlaceKind;
import com.mravel.catalog.model.enums.PriceLevel;
import com.mravel.catalog.model.enums.VenueType;

public class PlaceDtos {

    // Dùng cho danh sách/search
    public record PlaceSummaryDTO(
            Long id,
            String name,
            String slug,
            PlaceKind kind,          // ← thay cho PlaceType
            VenueType venueType,     // null nếu không phải VENUE
            String addressLine,
            String provinceName,
            Double latitude,
            Double longitude,
            PriceLevel priceLevel,
            Double avgRating,
            Integer reviewsCount,
            String coverImageUrl
    ) {}

    // Ảnh
    public record ImageDTO(Long id, String url, String caption, boolean cover, Integer sortOrder) {}

    // Danh mục / Tag
    public record CategoryDTO(Long id, String name, String slug) {}
    public record TagDTO(Long id, String name, String slug, String type) {}

    // Giờ mở cửa
    public record OpenHourDTO(Long id, DayOfWeek dayOfWeek, LocalTime openTime, LocalTime closeTime,
                              boolean open24h, boolean closed) {}

    // Khối nội dung bài viết (để render trang chi tiết)
    public record ContentBlockDTO(
            BlockType type,
            String text,
            ImageDTO image,
            List<ImageDTO> gallery,
            Double mapLat,
            Double mapLon
    ) {
        public enum BlockType { HEADING, PARAGRAPH, IMAGE, GALLERY, QUOTE, DIVIDER, INFOBOX, MAP }
    }

    // Chi tiết
    public record PlaceDetailDTO(
            Long id,
            String name,
            String slug,
            PlaceKind kind,              // ← thay cho PlaceType
            VenueType venueType,         // null nếu không phải VENUE
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
            String parentSlug,               // phục vụ breadcrumb & filter theo destination
            List<String> ancestors,          // hỗ trợ nhiều cấp
            Integer childrenCount,           // số lượng con trực tiếp (nếu cần hiện)
            List<CategoryDTO> categories,
            List<TagDTO> tags,
            List<ImageDTO> images,
            List<OpenHourDTO> openHours,
            List<ContentBlockDTO> content    // nội dung dạng bài báo
    ) {}
}