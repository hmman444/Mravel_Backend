package com.mravel.catalog.controller;

import com.mravel.catalog.dto.geo.LocationSuggestDTO;
import com.mravel.catalog.service.GeoSuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/geo")
@RequiredArgsConstructor
public class GeoController {

    private final GeoSuggestService geoSuggestService;

    @GetMapping("/suggest")
    public List<LocationSuggestDTO> suggest(
            @RequestParam("q") String q,
            @RequestParam(value = "limit", required = false, defaultValue = "6") int limit) {
        return geoSuggestService.suggest(q, limit);
    }
}
