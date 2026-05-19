package com.mravel.catalog.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mravel.catalog.dto.AutocompleteItemDTO;
import com.mravel.catalog.search.es.ElasticsearchAutocompleteService;

@RestController
@RequestMapping("/api/catalog")
public class CatalogAutocompleteController {

    @Autowired(required = false)
    private ElasticsearchAutocompleteService autocompleteService;

    @GetMapping("/hotels/autocomplete")
    public List<AutocompleteItemDTO> hotelAutocomplete(
            @RequestParam String q,
            @RequestParam(defaultValue = "5") int limit) {
        if (autocompleteService == null || q == null || q.isBlank() || q.trim().length() < 2)
            return Collections.emptyList();
        return autocompleteService.suggestHotels(q.trim(), Math.min(limit, 8));
    }

    @GetMapping("/restaurants/autocomplete")
    public List<AutocompleteItemDTO> restaurantAutocomplete(
            @RequestParam String q,
            @RequestParam(defaultValue = "5") int limit) {
        if (autocompleteService == null || q == null || q.isBlank() || q.trim().length() < 2)
            return Collections.emptyList();
        return autocompleteService.suggestRestaurants(q.trim(), Math.min(limit, 8));
    }
}
