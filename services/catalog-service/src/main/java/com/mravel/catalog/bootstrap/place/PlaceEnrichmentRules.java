package com.mravel.catalog.bootstrap.place;

import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.doc.PlaceDoc.*;

import java.util.Arrays;
import java.util.List;

public final class PlaceEnrichmentRules {

    private static final List<String> MAJOR_HUBS = Arrays.asList(
        "ha-noi", "ho-chi-minh", "da-nang", "nha-trang", "khanh-hoa", 
        "da-lat", "lam-dong", "phu-quoc", "kien-giang", "hoi-an", "quang-nam", "ha-long", "quang-ninh", "vung-tau", "sapa", "lao-cai"
    );

    private PlaceEnrichmentRules() {}

    // =====================================================================
    // CONTROLLED VOCABULARY
    // =====================================================================

    public static final class Vibe {
        public static final String RELAXING = "relaxing";
        public static final String VIBRANT = "vibrant";
        public static final String DIVERSE = "diverse";
        public static final String CULTURAL = "cultural";
        public static final String URBAN = "urban";
        public static final String EDUCATIONAL = "educational";
        public static final String NATURE = "nature";
        public static final String COZY = "cozy";
        public static final String ROMANTIC = "romantic";
        public static final String LIVELY = "lively";
        public static final String LUXURY = "luxury";
        public static final String COMFORTABLE = "comfortable";
        public static final String QUIET = "quiet";
        public static final String CHILL = "chill";
        public static final String AESTHETIC = "aesthetic";
        public static final String SOCIAL = "social";
        public static final String PEACEFUL = "peaceful";
        public static final String GENERAL = "general";
    }

    public static final class TravelStyle {
        public static final String BUDGET = "budget";
        public static final String LUXURY = "luxury";
        public static final String FAMILY = "family";
        public static final String BACKPACKER = "backpacker";
        public static final String SLOW_TRAVEL = "slow-travel";
        public static final String CASUAL = "casual";
        public static final String PHOTOGRAPHY = "photography";
        public static final String EXPLORATION = "exploration";
        public static final String SOLO = "solo";
        public static final String COUPLE = "couple";
        public static final String BUSINESS = "business";
    }

    public static final class VisitPurpose {
        public static final String EXPLORE = "explore";
        public static final String CULTURE = "culture";
        public static final String FOOD = "food";
        public static final String RELAX = "relax";
        public static final String PHOTOGRAPHY = "photography";
        public static final String STAY = "stay";
        public static final String WORK = "work";
        public static final String SOCIAL = "social";
    }

    // =====================================================================
    // CORE ENRICHMENT ENGINE
    // =====================================================================

    public static void enrich(PlaceDoc doc) {
        if (doc == null || doc.getKind() == null) {
            applyDefaultRules(doc);
            return;
        }

        String contextHint = extractContextHint(doc);

        switch (doc.getKind()) {
            case DESTINATION:
                enrichDestination(doc, contextHint);
                break;
            case POI:
                enrichPoi(doc, contextHint);
                break;
            case VENUE:
                enrichVenue(doc);
                break;
            default:
                applyDefaultRules(doc);
                break;
        }
    }

    // =====================================================================
    // SPECIFIC RULES
    // =====================================================================

    private static void enrichDestination(PlaceDoc doc, String context) {
        // Vibes & Seasons
        if ("coastal".equals(context)) {
            doc.setVibes(Arrays.asList(Vibe.RELAXING, Vibe.DIVERSE));
            doc.setBestSeason(BestSeason.DRY_SEASON);
            doc.setNoiseLevel(NoiseLevel.QUIET);
        } else if ("urban".equals(context)) {
            doc.setVibes(Arrays.asList(Vibe.URBAN, Vibe.VIBRANT));
            doc.setBestSeason(BestSeason.YEAR_ROUND);
            doc.setNoiseLevel(NoiseLevel.MODERATE);
        } else if ("cultural".equals(context)) {
            doc.setVibes(List.of(Vibe.CULTURAL));
            doc.setBestSeason(BestSeason.YEAR_ROUND);
            doc.setNoiseLevel(NoiseLevel.MODERATE);
        } else {
            doc.setVibes(List.of(Vibe.DIVERSE));
            doc.setBestSeason(BestSeason.YEAR_ROUND);
            doc.setNoiseLevel(NoiseLevel.MODERATE);
        }

        doc.setTravelStyle(Arrays.asList(TravelStyle.BUDGET, TravelStyle.LUXURY, TravelStyle.FAMILY, TravelStyle.BACKPACKER, TravelStyle.SLOW_TRAVEL));
        doc.setVisitPurpose(Arrays.asList(VisitPurpose.EXPLORE, VisitPurpose.CULTURE, VisitPurpose.FOOD, VisitPurpose.RELAX, VisitPurpose.PHOTOGRAPHY));
        
        doc.setVenueContext(VenueContext.MIXED);
        doc.setBestVisitTime(Arrays.asList(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING));
        
        // Crowd Level & Duration (deterministic by known major tourist hubs)
        boolean isMajorHub = doc.getSlug() != null && MAJOR_HUBS.stream().anyMatch(doc.getSlug()::contains);
        
        if (isMajorHub) {
            doc.setCrowdLevel(CrowdLevel.HIGH);
            doc.setVisitDurationMinutes(4320); // 3 days
        } else {
            doc.setCrowdLevel(CrowdLevel.MEDIUM);
            doc.setVisitDurationMinutes(2880); // 2 days
        }
    }

    private static void enrichPoi(PlaceDoc doc, String context) {
        doc.setTravelStyle(Arrays.asList(TravelStyle.CASUAL, TravelStyle.PHOTOGRAPHY, TravelStyle.EXPLORATION));
        doc.setVenueContext(determineVenueContext(context));

        if ("church".equals(context)) {
            doc.setVibes(Arrays.asList(Vibe.CULTURAL, Vibe.PEACEFUL));
            doc.setVisitPurpose(Arrays.asList(VisitPurpose.CULTURE, VisitPurpose.PHOTOGRAPHY));
            doc.setNoiseLevel(NoiseLevel.QUIET);
            doc.setBestVisitTime(Arrays.asList(BestVisitTime.MORNING, BestVisitTime.AFTERNOON));
            doc.setVisitDurationMinutes(60);
        } else if ("beach".equals(context) || "coastal".equals(context)) {
            doc.setVibes(Arrays.asList(Vibe.RELAXING, Vibe.NATURE));
            doc.setVisitPurpose(Arrays.asList(VisitPurpose.RELAX, VisitPurpose.PHOTOGRAPHY));
            doc.setNoiseLevel(NoiseLevel.MODERATE);
            doc.setBestVisitTime(Arrays.asList(BestVisitTime.MORNING, BestVisitTime.AFTERNOON));
            doc.setBestSeason(BestSeason.DRY_SEASON);
            doc.setVisitDurationMinutes(180);
        } else if ("museum".equals(context)) {
            doc.setVibes(Arrays.asList(Vibe.EDUCATIONAL, Vibe.CULTURAL));
            doc.setVisitPurpose(Arrays.asList(VisitPurpose.CULTURE));
            doc.setNoiseLevel(NoiseLevel.QUIET);
            doc.setBestVisitTime(Arrays.asList(BestVisitTime.MORNING, BestVisitTime.AFTERNOON));
            doc.setVisitDurationMinutes(120);
        } else if ("landmark".equals(context)) {
            doc.setVibes(Arrays.asList(Vibe.VIBRANT, Vibe.URBAN));
            doc.setVisitPurpose(Arrays.asList(VisitPurpose.PHOTOGRAPHY, VisitPurpose.CULTURE));
            doc.setNoiseLevel(NoiseLevel.MODERATE);
            doc.setBestVisitTime(Arrays.asList(BestVisitTime.MORNING, BestVisitTime.EVENING));
            doc.setVisitDurationMinutes(45);
        } else {
            // General POI
            doc.setVibes(List.of(Vibe.GENERAL));
            doc.setVisitPurpose(List.of(VisitPurpose.EXPLORE));
            doc.setNoiseLevel(NoiseLevel.MODERATE);
            doc.setBestVisitTime(Arrays.asList(BestVisitTime.MORNING, BestVisitTime.AFTERNOON));
            doc.setVisitDurationMinutes(60);
        }

        // Safe defaults
        if (doc.getCrowdLevel() == null) doc.setCrowdLevel(CrowdLevel.MEDIUM);
        if (doc.getBestSeason() == null) doc.setBestSeason(BestSeason.YEAR_ROUND);
    }

    private static void enrichVenue(PlaceDoc doc) {
        doc.setTravelStyle(Arrays.asList(TravelStyle.SOLO, TravelStyle.COUPLE, TravelStyle.FAMILY, TravelStyle.BUSINESS));
        doc.setBestSeason(BestSeason.YEAR_ROUND);

        if (doc.getVenueType() != null) {
            switch (doc.getVenueType()) {
                case RESTAURANT:
                    doc.setVibes(Arrays.asList(Vibe.COZY, Vibe.ROMANTIC, Vibe.LIVELY));
                    doc.setVisitPurpose(List.of(VisitPurpose.FOOD));
                    doc.setNoiseLevel(NoiseLevel.MODERATE);
                    doc.setVenueContext(VenueContext.INDOOR);
                    doc.setBestVisitTime(Arrays.asList(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING));
                    doc.setVisitDurationMinutes(90);
                    break;
                case HOTEL:
                    doc.setVibes(Arrays.asList(Vibe.LUXURY, Vibe.COMFORTABLE, Vibe.QUIET));
                    doc.setVisitPurpose(Arrays.asList(VisitPurpose.STAY, VisitPurpose.RELAX));
                    doc.setNoiseLevel(NoiseLevel.QUIET);
                    doc.setVenueContext(VenueContext.INDOOR);
                    doc.setBestVisitTime(Arrays.asList(BestVisitTime.MORNING, BestVisitTime.AFTERNOON, BestVisitTime.EVENING, BestVisitTime.NIGHT));
                    doc.setVisitDurationMinutes(1440); // 1 day baseline
                    break;
                default: // Default Venue (Cafe, etc.)
                    doc.setVibes(Arrays.asList(Vibe.CHILL, Vibe.AESTHETIC, Vibe.SOCIAL));
                    doc.setVisitPurpose(Arrays.asList(VisitPurpose.RELAX, VisitPurpose.WORK, VisitPurpose.SOCIAL));
                    doc.setNoiseLevel(NoiseLevel.MODERATE);
                    doc.setVenueContext(VenueContext.MIXED);
                    doc.setBestVisitTime(Arrays.asList(BestVisitTime.MORNING, BestVisitTime.AFTERNOON));
                    doc.setVisitDurationMinutes(120);
                    break;
            }
        } else {
            applyDefaultRules(doc);
        }

        if (doc.getCrowdLevel() == null) doc.setCrowdLevel(CrowdLevel.MEDIUM);
    }

    private static void applyDefaultRules(PlaceDoc doc) {
        if (doc == null) return;
        doc.setVibes(List.of(Vibe.GENERAL));
        doc.setVisitPurpose(List.of(VisitPurpose.EXPLORE));
        doc.setCrowdLevel(CrowdLevel.MEDIUM);
        doc.setNoiseLevel(NoiseLevel.MODERATE);
        doc.setBestSeason(BestSeason.YEAR_ROUND);
        doc.setVenueContext(VenueContext.MIXED);
    }

    // =====================================================================
    // UTILITIES
    // =====================================================================

    private static String extractContextHint(PlaceDoc doc) {
        String text = String.join(" ", 
            doc.getName() != null ? doc.getName() : "", 
            doc.getShortDescription() != null ? doc.getShortDescription() : "", 
            doc.getDescription() != null ? doc.getDescription() : ""
        ).toLowerCase();

        if (containsAny(text, "beach", "biển", "coastal", "đảo", "island")) return "coastal";
        if (containsAny(text, "church", "nhà thờ", "temple", "đền", "chùa", "pagoda")) return "church";
        if (containsAny(text, "museum", "bảo tàng")) return "museum";
        if (containsAny(text, "mountain", "núi", "rừng", "forest", "nature", "thiên nhiên")) return "nature";
        if (containsAny(text, "city", "thành phố", "urban", "trung tâm")) return "urban";
        if (containsAny(text, "culture", "văn hóa", "heritage", "di sản")) return "cultural";
        if (containsAny(text, "landmark", "di tích", "lịch sử", "tượng đài")) return "landmark";

        return "general";
    }

    private static VenueContext determineVenueContext(String context) {
        if ("church".equals(context) || "museum".equals(context)) return VenueContext.INDOOR;
        if ("beach".equals(context) || "nature".equals(context) || "landmark".equals(context)) return VenueContext.OUTDOOR;
        return VenueContext.MIXED;
    }

    private static boolean containsAny(String text, String... keywords) {
        for (String k : keywords) {
            if (text.contains(k)) return true;
        }
        return false;
    }
}
