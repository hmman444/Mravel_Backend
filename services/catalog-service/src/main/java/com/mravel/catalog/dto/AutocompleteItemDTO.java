package com.mravel.catalog.dto;

public record AutocompleteItemDTO(
        String id,
        String name,
        String slug,
        String cityName,
        String coverImageUrl,
        String kind) {
}
