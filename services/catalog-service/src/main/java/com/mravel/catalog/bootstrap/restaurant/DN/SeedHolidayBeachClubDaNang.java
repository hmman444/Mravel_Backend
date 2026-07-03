package com.mravel.catalog.bootstrap.restaurant.DN;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Instant;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.model.doc.RestaurantDoc.AmbienceTag;
import com.mravel.catalog.model.doc.RestaurantDoc.BlackoutDateRule;
import com.mravel.catalog.model.doc.RestaurantDoc.BookingConfig;
import com.mravel.catalog.model.doc.RestaurantDoc.CapacityInfo;
import com.mravel.catalog.model.doc.RestaurantDoc.ContentBlock;
import com.mravel.catalog.model.doc.RestaurantDoc.ContentBlock.ContentSection;
import com.mravel.catalog.model.doc.RestaurantDoc.CuisineTag;
import com.mravel.catalog.model.doc.RestaurantDoc.Image;
import com.mravel.catalog.model.doc.RestaurantDoc.MenuItem;
import com.mravel.catalog.model.doc.RestaurantDoc.MenuSection;
import com.mravel.catalog.model.doc.RestaurantDoc.ModerationInfo;
import com.mravel.catalog.model.doc.RestaurantDoc.OpeningHour;
import com.mravel.catalog.model.doc.RestaurantDoc.ParkingFeeType;
import com.mravel.catalog.model.doc.RestaurantDoc.ParkingInfo;
import com.mravel.catalog.model.doc.RestaurantDoc.PartnerType;
import com.mravel.catalog.model.doc.RestaurantDoc.PriceBucket;
import com.mravel.catalog.model.doc.RestaurantDoc.PriceLevel;
import com.mravel.catalog.model.doc.RestaurantDoc.PublisherInfo;
import com.mravel.catalog.model.doc.RestaurantDoc.RestaurantPolicy;
import com.mravel.catalog.model.doc.RestaurantDoc.RestaurantStatus;
import com.mravel.catalog.model.doc.RestaurantDoc.RestaurantType;
import com.mravel.catalog.model.doc.RestaurantDoc.SignatureDish;
import com.mravel.catalog.model.doc.RestaurantDoc.SuitableFor;
import com.mravel.catalog.model.doc.RestaurantDoc.TableType;
import com.mravel.catalog.repository.RestaurantDocRepository;

import static com.mravel.catalog.utils.RestaurantSeedUtils.*;
import static com.mravel.catalog.utils.restaurant.Localized.of;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("seed")
public class SeedHolidayBeachClubDaNang {

    @Bean
    CommandLineRunner seedHolidayBeachClubDaNangRunner(RestaurantDocRepository repo) {
        return args -> {
            log.info(">>> [SeedHolidayBeachClubDaNang] start");
            RestaurantDoc doc = restaurantHolidayBeachClub();
            repo.findBySlugAndActiveTrue(doc.getSlug())
                .ifPresent(existing -> doc.setId(existing.getId()));
            repo.save(doc);
            log.info(">>> upsert: {}", doc.getSlug());
            log.info(">>> [SeedHolidayBeachClubDaNang] done");
        };
    }

    // =====================================================================
    //   THE HOLIDAY BEACH CLUB & DINING - Vo Nguyen Giap - Da Nang
    //   300 Vo Nguyen Giap, Phuong Ngu Hanh Son, Da Nang
    // =====================================================================

    private static RestaurantDoc restaurantHolidayBeachClub() {
        String slug = "holiday-beach-club-dining-vo-nguyen-giap-da-nang";

        // ---------- Gallery ----------
        List<Image> images = List.of(
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-1-normal-4237973673347.webp",
                of("Khong gian beach club ngoai troi The Holiday Da Nang", "The Holiday Beach Club outdoor space Da Nang"), true, 0),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-2-normal-4237973773348.webp",
                of("Khu vuc bar cocktail The Holiday Beach Club", "Cocktail bar area at The Holiday Beach Club"), false, 1),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-3-normal-4237973873349.webp",
                of("View bien My Khe tu The Holiday Beach Club", "My Khe beach view from The Holiday Beach Club"), false, 2),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-4-normal-4237973973350.webp",
                of("Khong gian ngoi ngoai troi thoang mat", "Open-air outdoor seating area"), false, 3),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-5-normal-4237974073351.webp",
                of("Ban nhom ban be tai The Holiday Beach Club", "Friend group table at The Holiday Beach Club"), false, 4),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-6-normal-4237974173352.webp",
                of("Cocktail dac biet The Holiday Beach Da Nang", "Signature cocktails at The Holiday Beach Da Nang"), false, 5),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-7-normal-4237974273353.webp",
                of("Khong gian song dong buoi toi tai Holiday Beach", "Vibrant evening atmosphere at Holiday Beach"), false, 6),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-8-normal-4237974373354.webp",
                of("Khu vuc san khau am nhac ngoai troi", "Outdoor music stage area"), false, 7),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-9-normal-4237974473355.webp",
                of("Combo an nhe va do uong Holiday Beach", "Light food and drink combos at Holiday Beach"), false, 8),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-10-normal-4237974573356.webp",
                of("Khong gian VIP booth Holiday Beach Club", "VIP booth space at Holiday Beach Club"), false, 9),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-11-normal-4237974673357.webp",
                of("Hoang hon bien dep tu Holiday Beach Vo Nguyen Giap", "Beautiful beach sunset from Holiday Beach Vo Nguyen Giap"), false, 10),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-12-normal-4237974773358.webp",
                of("Nhom ban be chill tai Holiday Beach Da Nang", "Friend group chilling at Holiday Beach Da Nang"), false, 11),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-13-normal-4237974873359.webp",
                of("Pizza va bia tai bai bien Holiday Beach", "Pizza and beer at Holiday Beach"), false, 12),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-14-normal-4237974973360.webp",
                of("Tiec sinh nhat ngoai troi tai Holiday Beach Club", "Outdoor birthday party at Holiday Beach Club"), false, 13),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-15-normal-4237975073361.webp",
                of("Khong gian buoi sang yean binh Holiday Beach", "Peaceful morning atmosphere at Holiday Beach"), false, 14),
            img("https://pasgo.vn/Upload/anh-chi-tiet/the-holiday-beach-club-dining-16-normal-4237975173362.webp",
                of("Toan canh The Holiday Beach Club & Dining Da Nang", "Full view of The Holiday Beach Club & Dining Da Nang"), false, 15)
        );

        // ---------- Menu images ----------
        List<Image> menuImages = List.of(
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-1.png",
                of("", ""), false, 0),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-2.png",
                of("", ""), false, 1),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-3.png",
                of("", ""), false, 2),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-4.png",
                of("", ""), false, 3),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-5.png",
                of("", ""), false, 4),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-6.png",
                of("", ""), false, 5),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-7.png",
                of("", ""), false, 6),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-8.png",
                of("", ""), false, 7),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-9.png",
                of("", ""), false, 8),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-10.png",
                of("", ""), false, 9),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-11.png",
                of("", ""), false, 10),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-12.png",
                of("", ""), false, 11),
            img("https://cdn.pastaxi-manager.onepas.vn/Content/Uploads/Prices/NGOCDIEM/10.%20The/The-Holiday-Beach-Club-Dining-menu-13.png",
                of("", ""), false, 12)
        );

        // ---------- Cuisines ----------
        List<CuisineTag> cuisines = List.of(
            CuisineTag.builder()
                .code("COCKTAIL_BAR")
                .name(of("Bar cocktail & do uong", "Cocktail bar & beverages"))
                .region("GLOBAL")
                .build(),
            CuisineTag.builder()
                .code("WESTERN_SNACKS")
                .name(of("Mon an nhe phong cach Tay", "Western-style light bites"))
                .region("WESTERN")
                .build(),
            CuisineTag.builder()
                .code("BEACH_FOOD")
                .name(of("Am thuc bien — pizza, burger, BBQ", "Beach food — pizza, burgers, BBQ"))
                .region("COASTAL")
                .build()
        );

        // ---------- Opening hours (07:00 - 23:59) ----------
        List<OpeningHour> openingHours = List.of(
            openingHour(DayOfWeek.MONDAY,    7, 0, 23, 59),
            openingHour(DayOfWeek.TUESDAY,   7, 0, 23, 59),
            openingHour(DayOfWeek.WEDNESDAY, 7, 0, 23, 59),
            openingHour(DayOfWeek.THURSDAY,  7, 0, 23, 59),
            openingHour(DayOfWeek.FRIDAY,    7, 0, 23, 59),
            openingHour(DayOfWeek.SATURDAY,  7, 0, 23, 59),
            openingHour(DayOfWeek.SUNDAY,    7, 0, 23, 59)
        );

        // ---------- Suitable for ----------
        List<SuitableFor> suitableFor = List.of(
            suitable("FRIENDS",         of("Tu tap ban be chill bien", "Beach chill with friends")),
            suitable("BIRTHDAY",        of("Tiec sinh nhat ngoai troi", "Outdoor birthday party")),
            suitable("ANNIVERSARY",     of("Ky niem ngay dac biet", "Special anniversary")),
            suitable("GROUP_MEETING",   of("Hop nhom khong chinh thuc", "Informal group gathering")),
            suitable("COMPANY_PARTY",   of("Tiec cong ty ngoai troi", "Outdoor company party")),
            suitable("COUPLE",          of("Hen ho hoang hon bien", "Romantic beach sunset date")),
            suitable("HANGOUT",         of("Chill va thu gian bai bien", "Beach chill and relaxation")),
            suitable("TEAM_OUTING",     of("Team building ngoai troi bai bien", "Outdoor beach team building")),
            suitable("SUNSET_WATCH",    of("Ngam hoang hon bien dep", "Watching beautiful beach sunsets")),
            suitable("CASUAL_DINING",   of("An nhe uong cocktail thoai mai", "Casual dining and cocktails"))
        );

        // ---------- Ambience ----------
        List<AmbienceTag> ambience = List.of(
            ambience("VIBRANT",   of("Soi noi, tre trung, nang dong", "Vibrant, youthful and energetic")),
            ambience("OUTDOOR",   of("Ngoai troi thoang mat view bien", "Open-air with beach view")),
            ambience("TROPICAL",  of("Nhiet doi, mang net bai bien", "Tropical, with a beach vibe")),
            ambience("CASUAL",    of("Thoai mai, than thien", "Relaxed and friendly")),
            ambience("ROMANTIC",  of("Lang man duoi bau troi sao bien", "Romantic under the coastal starry sky"))
        );

        // ---------- Capacity ----------
        CapacityInfo capacity = CapacityInfo.builder()
            .totalCapacity(300)
            .maxGroupSize(100)
            .hasPrivateRooms(false)
            .privateRoomCount(0)
            .maxPrivateRoomCapacity(0)
            .hasOutdoorSeating(true)
            .build();

        // ---------- Signature dishes / drinks ----------
        List<SignatureDish> signatureDishes = List.of(
            signatureDishL(of("Danang Sunrise Ritual", "Danang Sunrise Ritual")),
            signatureDishL(of("Tropic Mirage", "Tropic Mirage")),
            signatureDishL(of("Hoi An Umami", "Hoi An Umami")),
            signatureDishL(of("Holiday Beach Horizon", "Holiday Beach Horizon")),
            signatureDishL(of("Sontra Velvet", "Sontra Velvet")),
            signatureDishL(of("Ga canh & Khoai tay chien combo", "Chicken Wings & French Fries combo")),
            signatureDishL(of("Burger & Khoai tay & Nuoc ngot combo", "Burger & French Fries & Soft Drink combo")),
            signatureDishL(of("Pizza & 2 Lon bia combo", "Pizza & 2 Beers combo")),
            signatureDishL(of("BBQ Platter & 2 Lon bia combo", "BBQ Platter & 2 Beers combo")),
            signatureDishL(of("Sinh to nhiet doi dac biet", "Special tropical fruit smoothie"))
        );

        // ---------- Menu sections ----------
        List<MenuSection> menuSections = List.of(
            MenuSection.builder()
                .code("COCKTAILS")
                .name(of("Cocktails & Do uong", "Cocktails & Beverages"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Danang Sunrise Ritual", "Danang Sunrise Ritual"))
                        .description(of("Cocktail dac trung cua Holiday Beach, ket hop rum, nuoc ep trai cay nhiet doi va huong bien Da Nang.", "Holiday Beach signature cocktail blending rum, tropical fruit juices and Da Nang coastal flavors."))
                        .priceFrom(new BigDecimal("65000"))
                        .priceTo(new BigDecimal("85000"))
                        .unit(of("ly", "glass"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("cocktail", "signature", "tropical"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Mocktail Bien Xanh", "Blue Ocean Mocktail"))
                        .description(of("Mocktail khong con tu cac loai trai cay tuoi, huong vi mat lanh phu hop moi lua tuoi.", "Alcohol-free mocktail made from fresh fruits, refreshing flavor suitable for all ages."))
                        .priceFrom(new BigDecimal("45000"))
                        .priceTo(new BigDecimal("55000"))
                        .unit(of("ly", "glass"))
                        .combo(false)
                        .buffetItem(false)
                        .tags(List.of("mocktail", "refreshing", "non-alcoholic"))
                        .build()
                ))
                .build(),
            MenuSection.builder()
                .code("COMBO_FOOD")
                .name(of("Combo an nhe & Tiec bai bien", "Light Bites & Beach Party Combos"))
                .items(List.of(
                    MenuItem.builder()
                        .name(of("Combo Pizza & 2 Lon bia", "Pizza & 2 Beers combo"))
                        .description(of("1 pizza co vua (26cm) voi cac toping da dang kem 2 lon bia lat.", "One medium pizza (26cm) with various toppings and 2 cans of lager beer."))
                        .priceFrom(new BigDecimal("150000"))
                        .priceTo(new BigDecimal("220000"))
                        .unit(of("combo", "combo"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("combo", "pizza", "beer", "popular"))
                        .build(),
                    MenuItem.builder()
                        .name(of("Combo BBQ Platter & 2 Lon bia", "BBQ Platter & 2 Beers combo"))
                        .description(of("Dia BBQ thu cam bao gom ga canh, suon heo va xuc xich nuong kem 2 lon bia mat lanh.", "Mixed BBQ platter with chicken wings, pork ribs and grilled sausages paired with 2 cold beers."))
                        .priceFrom(new BigDecimal("180000"))
                        .priceTo(new BigDecimal("250000"))
                        .unit(of("combo", "combo"))
                        .combo(true)
                        .buffetItem(false)
                        .tags(List.of("combo", "bbq", "beer", "group"))
                        .build()
                ))
                .build()
        );

        // ---------- Parking ----------
        ParkingInfo parking = ParkingInfo.builder()
            .hasCarParking(true)
            .carParkingLocation(of("Bai do xe o to truoc khu vuc Holiday Beach, co bao ve huong dan", "Car parking in front of Holiday Beach area, supervised by security"))
            .carParkingFeeType(ParkingFeeType.FREE)
            .carParkingFeeAmount(BigDecimal.ZERO)
            .hasMotorbikeParking(true)
            .motorbikeParkingLocation(of("Bai gui xe may tai Holiday Beach, co bao ve", "Motorbike parking at Holiday Beach, supervised"))
            .motorbikeParkingFeeType(ParkingFeeType.FREE)
            .motorbikeParkingFeeAmount(BigDecimal.ZERO)
            .notes(of("Mien phi gui xe may va o to. Bao ve truc trong gio hoat dong.", "Free parking for both motorbikes and cars. Security on duty during operating hours."))
            .build();

        // ---------- Amenities ----------
        List<String> amenityCodes = List.of(
            "FREE_CAR_PARKING",
            "FREE_MOTORBIKE_PARKING",
            "OUTDOOR_TABLE",
            "BEACH_VIEW",
            "WIFI",
            "VISA_MASTER",
            "VAT_INVOICE",
            "LIVE_MUSIC",
            "COCKTAIL_BAR",
            "SMOKING_AREA",
            "SOUND_LIGHT",
            "EVENT_DECOR",
            "KIDS_AREA",
            "PROJECTOR"
        );

        // ---------- Content ----------
        List<ContentBlock> content = List.of(
            heading(ContentSection.STORY, of("Gioi Thieu The Holiday Beach Club & Dining", "About The Holiday Beach Club & Dining")),
            paragraph(ContentSection.STORY,
                of("The Holiday Beach Club & Dining la dia diem giai tri beach club hang dau toa lac ngay mat duong Vo Nguyen Giap, " +
                   "Phuong Ngu Hanh Son, Da Nang — ngay truoc bai bien My Khe thom manh va trong xanh. " +
                   "Khong chi la mot nha hang, The Holiday Beach Club con la khong gian song dong, thu vi " +
                   "de chill, thu gian va tan huong cuoc song ven bien theo phong cach beach club hien dai. " +
                   "Voi suc chua hon 300 khach ngoi ngoai troi thoang mat, noi day la diem den ly tuong " +
                   "cho nhung ai muon nham sip cocktail dac sac, thu gian truoc song bien va dac san am thuc bien nhiet doi.",
                   "The Holiday Beach Club & Dining is a premier beach club entertainment venue located right on Vo Nguyen Giap Street, " +
                   "Ngu Hanh Son Ward, Da Nang — facing the beautiful and clear My Khe Beach. " +
                   "More than just a restaurant, The Holiday Beach Club is a vibrant, fun space " +
                   "to chill, relax and enjoy seaside life in a modern beach club style. " +
                   "With capacity for over 300 guests in an open-air outdoor setting, it is the ideal destination " +
                   "for those wanting to sip signature cocktails, unwind by the waves, and enjoy tropical beach cuisine.")),
            heading(ContentSection.STORY, of("Khong Gian Bai Bien Mo — Chill & Thu Gian", "Open Beach Space — Chill & Relax")),
            paragraph(ContentSection.STORY,
                of("Khong gian ngoai troi mo rong la diem nhan lon nhat cua The Holiday Beach Club. " +
                   "Toan bo khu vuc phuc vu deu dat ngay truoc mat bien, khach hang co the nghe tieng song bien, " +
                   "tan huong gio bien mat lanh va ngam nhin hoang hon tuyet dep tren bai bien Da Nang. " +
                   "Ban ghe thoai mai, den led lung linh ve dem, am nhac nong nhiet tao nen bau khong khi " +
                   "song dong, tre trung va day cam hung. Dac biet vao cuoi tuan va cac dip le, " +
                   "Holiday Beach Club thuong to chuc cac su kien am nhac ngoai troi, " +
                   "thu hut dong dao ban tre Da Nang va du khach quoc te.",
                   "The expansive open-air space is the biggest highlight of The Holiday Beach Club. " +
                   "The entire service area is placed right in front of the beach, allowing guests to hear the waves, " +
                   "enjoy cool sea breezes and admire stunning sunsets over Da Nang's coastline. " +
                   "Comfortable furniture, shimmering LED lights at night, and lively music create an atmosphere " +
                   "that is vibrant, youthful and inspiring. Especially on weekends and holidays, " +
                   "Holiday Beach Club often hosts outdoor music events, " +
                   "attracting many young people from Da Nang and international tourists.")),
            heading(ContentSection.STORY, of("Cocktail Dac Sac & Thuc Don Bien", "Signature Cocktails & Beach Menu")),
            paragraph(ContentSection.STORY,
                of("Thuong hieu cocktail la diem tu hao nhat cua The Holiday Beach Club. " +
                   "Doi barista tai nang sao tao ra nhung cong thuc doc quyen mang dam phong cach Da Nang: " +
                   "Danang Sunrise Ritual goi len mau sac ruc ro cua binh minh tren bien, " +
                   "Tropic Mirage dan nguoi thu vao khong gian nhiet doi, " +
                   "Hoi An Umami ket hop huong vi dac trung mien Trung vao tung giot cocktail. " +
                   "Thuc don an nhe da dang voi pizza, burger, ga canh chien gion, dia BBQ " +
                   "— tat ca duoc thiet ke de thu gian va nham thuong ben tieng song bien. " +
                   "Muc gia phu hop chi tu 30.000 – 80.000d/nguoi, " +
                   "phu hop cho moi doi tuong khach hang.",
                   "The cocktail brand is the greatest pride of The Holiday Beach Club. " +
                   "The talented barista team creates exclusive recipes with a distinct Da Nang style: " +
                   "Danang Sunrise Ritual evokes the radiant colors of a sea sunrise, " +
                   "Tropic Mirage transports guests into a tropical space, " +
                   "Hoi An Umami blends the unique flavors of Central Vietnam into every drop. " +
                   "The diverse light food menu includes pizza, burgers, crispy chicken wings, and BBQ platters " +
                   "— all designed to enjoy while relaxing by the sound of waves. " +
                   "Affordable pricing from just 30,000 – 80,000 VND/person, " +
                   "suitable for all customer segments.")),
            heading(ContentSection.STORY, of("Suc Chua & To Chuc Su Kien", "Capacity & Event Hosting")),
            paragraph(ContentSection.STORY,
                of("The Holiday Beach Club co suc chua tren 300 khach ngoi ngoai troi, " +
                   "la dia diem tuong doi ly tuong de to chuc cac su kien ngoai troi quy mo vua va lon. " +
                   "Nha hang co san khau am nhac, he thong am thanh anh sang chuyen nghiep, " +
                   "phong cach trang tri linh hoat theo yeu cau cua khach. " +
                   "Da tung don tiep thanh cong cac su kien: tiec tat nien cong ty, " +
                   "tiec sinh nhat ngoai troi, tiec dam cuoi bai bien nho, " +
                   "buoi ra mat san pham va cac su kien brand event. " +
                   "Doi ngu su kien chuyen nghiep san sang tu van va ho tro lap ke hoach " +
                   "cho moi loai tiec va su kien theo yeu cau.",
                   "The Holiday Beach Club has capacity for over 300 outdoor guests, " +
                   "making it a relatively ideal venue for medium to large-scale outdoor events. " +
                   "The restaurant features a music stage, professional sound and lighting systems, " +
                   "and flexible decoration styles tailored to guest requirements. " +
                   "It has successfully hosted events including: company year-end parties, " +
                   "outdoor birthday celebrations, small beach weddings, " +
                   "product launches and brand events. " +
                   "A professional events team is ready to advise and assist in planning " +
                   "all types of parties and events on request.")),
            heading(ContentSection.STORY, of("Phu Hop Dip Nao", "Suitable Occasions")),
            paragraph(ContentSection.STORY,
                of("The Holiday Beach Club & Dining la diem den khong the bo qua cho ban tre Da Nang " +
                   "va du khach muon trai nghiem lifestyle beach club dich thuc. " +
                   "Cac nhom ban be se co buoi hop mat vui ve ben cocktail va am nhac song. " +
                   "Cap doi se tim thay khong gian lang man hoang hon hoac dem sao lung linh ben bien. " +
                   "Cac nhom cong ty, team building se co buoi sinh hoat nang dong ngoai troi day cam hung. " +
                   "Day cung la dia diem doc dao de to chuc tiec sinh nhat ngoai troi voi khong gian mo, " +
                   "am nhac va view bien tao nen buoi tiec kho quen.",
                   "The Holiday Beach Club & Dining is a must-visit destination for Da Nang's youth " +
                   "and tourists wanting to experience an authentic beach club lifestyle. " +
                   "Friend groups will enjoy cheerful gatherings with cocktails and live music. " +
                   "Couples will find romantic sunset moments or starlit evenings by the sea. " +
                   "Corporate groups and team-building parties will have dynamic outdoor sessions full of inspiration. " +
                   "It is also a unique venue for outdoor birthday parties with open space, " +
                   "music and sea views creating an unforgettable celebration.")),
            heading(ContentSection.STORY, of("Dich Vu & Tien Ich Noi Bat", "Services & Highlights")),
            paragraph(ContentSection.STORY,
                of("WiFi toc do cao mien phi phuc vu ca khu vuc ngoai troi. He thong am thanh chuyen nghiep " +
                   "voi cac buoi live music, DJ set va performance them phan song dong vao cuoi tuan. " +
                   "Ho tro trang tri bong bong, bang ron, den led theo yeu cau cho tiec sinh nhat va su kien. " +
                   "Chap nhan thanh toan the Visa/Master va xuat hoa don VAT theo yeu cau. " +
                   "Khu vuc hut thuoc rieng biet, khong anh huong den khach khac. " +
                   "Doi barista co kinh nghiem tu van cac loai cocktail phu hop khi vi va do tuoi, " +
                   "dam bao moi khach hang tim duoc do uong ung y nhat.",
                   "Free high-speed WiFi covering the entire outdoor area. Professional sound system " +
                   "with live music, DJ sets and performances adding vibrancy on weekends. " +
                   "Balloon decorations, banners and LED lights on request for birthdays and events. " +
                   "Visa/Master card payments accepted and VAT invoices available on request. " +
                   "Separate smoking area away from other guests. " +
                   "Experienced barista team advises on cocktails suitable for every taste and age, " +
                   "ensuring every guest finds their ideal drink.")),
            heading(ContentSection.STORY, of("Vi Tri & Di Chuyen", "Location & Getting There")),
            paragraph(ContentSection.STORY,
                of("The Holiday Beach Club & Dining toa lac tai 300 duong Vo Nguyen Giap, " +
                   "Phuong Ngu Hanh Son — ngay mat bien Da Nang, " +
                   "tren tuyen duong ven bien dep nhat thanh pho. " +
                   "Cach Cau Rong khoang 7km, cach san bay Da Nang 8km va cach khu nghi duong My Khe 3km. " +
                   "Du khach co the di chuyen bang taxi, xe may hoac ung dung dat xe (Grab, Be) rat thuan tien. " +
                   "Ban dem, anh den beach club sang ruc de nhan biet tu xa, " +
                   "tro thanh diem check-in hot nhat tren tuyen pho bien Vo Nguyen Giap.",
                   "The Holiday Beach Club & Dining is located at 300 Vo Nguyen Giap Street, " +
                   "Ngu Hanh Son Ward — right on Da Nang's beachfront, " +
                   "along the city's most beautiful coastal road. " +
                   "Approximately 7km from Dragon Bridge, 8km from Da Nang Airport and 3km from My Khe resort area. " +
                   "Tourists can travel conveniently by taxi, motorbike, or ride-hailing apps (Grab, Be). " +
                   "At night, the beach club's glowing lights are easily recognizable from a distance, " +
                   "making it the hottest check-in spot along Vo Nguyen Giap coastal boulevard.")),
            heading(ContentSection.STORY, of("Ly Do Nen Chon The Holiday Beach Club", "Why Choose The Holiday Beach Club")),
            paragraph(ContentSection.STORY,
                of("The Holiday Beach Club & Dining la su ket hop hoan hao giua beach bar quoc te " +
                   "va phong cach am thuc nhiet doi dac trung. " +
                   "Voi muc gia phai chang chi tu 30.000–80.000d/nguoi, " +
                   "ban co the tan huong cocktail dac sac, am nhac song va view bien mien phi — " +
                   "mot trai nghiem lifestyle beach club dich thuc giua long Da Nang. " +
                   "Su ket hop giua khong khi song dong, menu do uong phong phu va am thuc an nhe da dang " +
                   "giup noi day tro thanh diem den di-di-lai-lai khong bao gio chan " +
                   "trong chuyen du lich Da Nang cua ban.",
                   "The Holiday Beach Club & Dining is the perfect blend of an international beach bar " +
                   "and distinctive tropical dining style. " +
                   "At an affordable price from just 30,000–80,000 VND/person, " +
                   "you can enjoy signature cocktails, live music and ocean views — " +
                   "an authentic beach club lifestyle experience in the heart of Da Nang. " +
                   "The combination of vibrant atmosphere, rich drink menu and diverse light food " +
                   "makes this a go-to destination you will never tire of " +
                   "during your Da Nang travel adventure.")),
            mapBlock(ContentSection.STORY, new double[]{108.249158, 16.049713})
        );

        // ---------- Policy ----------
        List<BlackoutDateRule> blackoutRules = List.of(
            blackoutGregorian(1,  1,  of("Khong ap dung uu dai ngay 1/1 Tet Duong lich", "No promotions on January 1 (New Year)")),
            blackoutGregorian(2,  14, of("Khong ap dung uu dai ngay Valentine", "No promotions on Valentine's Day")),
            blackoutGregorian(3,  8,  of("Khong ap dung uu dai ngay 8/3", "No promotions on International Women's Day")),
            blackoutGregorian(4,  30, of("Khong ap dung uu dai ngay 30/4", "No promotions on April 30")),
            blackoutGregorian(5,  1,  of("Khong ap dung uu dai ngay 1/5", "No promotions on International Labor Day")),
            blackoutGregorian(9,  2,  of("Khong ap dung uu dai ngay Quoc khanh 2/9", "No promotions on National Day")),
            blackoutGregorian(12, 24, of("Khong ap dung uu dai dem Giang sinh 24/12", "No promotions on Christmas Eve")),
            blackoutGregorian(12, 31, of("Khong ap dung uu dai dem Giao thua 31/12", "No promotions on New Year's Eve"))
        );

        RestaurantPolicy policy = RestaurantPolicy.builder()
            .depositRequired(true)
            .depositMinGuests(15)
            .depositAmount(new BigDecimal("100000"))
            .depositCurrencyCode("VND")
            .depositNotes(of("Ap dung cho doan khach tu 15 nguoi tro len, dat coc 30-50% tuy truong hop.",
                             "Applies to groups of 15+ guests, deposit 30-50% depending on case."))
            .hasPromotion(true)
            .promotionSummary(of("Uu dai cho khach dat ban truc tuyen qua he thong, khong ap dung ngay le.",
                                 "Discount for online table reservations; not applicable on public holidays."))
            .promotionMaxDiscountPercent(10)
            .promotionNote(of("Uu dai co the thay doi theo thoi diem; lien he nha hang de biet them chi tiet.",
                              "Promotions may vary; contact the restaurant for details."))
            .blackoutRules(blackoutRules)
            .minBookingLeadTimeMinutes(30)
            .maxHoldTimeMinutes(15)
            .minGuestsPerBooking(null)
            .vatInvoiceAvailable(true)
            .vatPercent(new BigDecimal("10"))
            .directInvoiceAvailable(true)
            .invoiceNotes(of("Xuat hoa don VAT theo yeu cau truoc khi thanh toan.", "VAT invoices issued on request before payment."))
            .serviceChargePercent(new BigDecimal("5"))
            .serviceChargeNotes(of("Phu thu 5% ap dung cho nhom su kien va tiec.", "5% service charge for event groups and parties."))
            .allowOutsideFood(false)
            .allowOutsideDrink(false)
            .outsideFoodPolicy(of("Khong cho phep mang thuc an tu ngoai vao.", "Outside food is not permitted."))
            .outsideDrinkPolicy(of("Khong cho phep mang do uong tu ngoai vao.", "Outside drinks are not permitted."))
            .outsideDrinkFees(List.of())
            .build();

        // ---------- Publisher & Moderation ----------
        Instant now = Instant.parse("2024-01-01T00:00:00Z");

        PublisherInfo publisher = PublisherInfo.builder()
            .partnerId("11")
            .partnerName("Ông Trùm Bất Động Sản")
            .partnerEmail("22110377@student.hcmute.edu.vn")
            .partnerType(PartnerType.RESTAURANT_OWNER)
            .createdAt(now)
            .lastUpdatedAt(now)
            .build();

        ModerationInfo moderation = ModerationInfo.builder()
            .status(RestaurantStatus.APPROVED)
            .rejectionReason(null)
            .blockedReason(null)
            .reportCount(0)
            .lastActionByAdminId("system-seed")
            .lastActionAt(now)
            .build();

        // ---------- Table types ----------
        List<TableType> tableTypes = List.of(
            TableType.builder()
                .id("tb-2-couple")
                .name(of("Ban doi ngoai troi view bien", "Outdoor couple table with beach view"))
                .seats(2)
                .minPeople(1)
                .maxPeople(2)
                .totalTables(20)
                .depositPrice(new BigDecimal("50000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Ban doi ngoai troi, ly tuong cho cap doi ngam hoang hon bien.", "Outdoor couple table, ideal for couples watching the beach sunset."))
                .build(),
            TableType.builder()
                .id("tb-6-group")
                .name(of("Ban nhom 6 nguoi", "Group table for 6"))
                .seats(6)
                .minPeople(3)
                .maxPeople(6)
                .totalTables(25)
                .depositPrice(new BigDecimal("100000"))
                .currencyCode("VND")
                .vip(false)
                .privateRoom(false)
                .defaultDurationMinutes(90)
                .allowedDurationsMinutes(List.of(60, 90, 120))
                .note(of("Ban nhom ngoai troi, phu hop tu tap ban be va sinh nhat nho.", "Outdoor group table, suitable for friends hangout and small birthday parties."))
                .build(),
            TableType.builder()
                .id("tb-vip-booth")
                .name(of("VIP Booth ngoai troi san khau", "Outdoor VIP booth near stage"))
                .seats(12)
                .minPeople(6)
                .maxPeople(15)
                .totalTables(8)
                .depositPrice(new BigDecimal("300000"))
                .currencyCode("VND")
                .vip(true)
                .privateRoom(false)
                .defaultDurationMinutes(120)
                .allowedDurationsMinutes(List.of(90, 120, 150))
                .note(of("VIP booth gan san khau am nhac, view bien dep nhat, phu hop tiec nhom va su kien nho.", "VIP booth near music stage with the best beach view, suitable for group parties and small events."))
                .build()
        );

        // ---------- Booking config ----------
        BookingConfig bookingConfig = BookingConfig.builder()
            .slotMinutes(30)
            .allowedDurationsMinutes(List.of(60, 90, 120))
            .defaultDurationMinutes(90)
            .minBookingLeadTimeMinutes(120)
            .graceArrivalMinutes(15)
            .freeCancelMinutes(60)
            .pendingPaymentExpireMinutes(30)
            .depositOnly(true)
            .maxTablesPerBooking(5)
            .build();

        // ---------- Build ----------
        return RestaurantDoc.builder()
            .active(true)
            .destinationSlug("da-nang")
            .cityName(of("Đà Nẵng", "Da Nang"))
            .districtName(of("Quận Ngũ Hành Sơn", "Ngu Hanh Son District"))
            .wardName(of("P. Ngũ Hành Sơn", "Ngu Hanh Son Ward"))
            .addressLine(of("300 Đường Võ Nguyên Giáp, Phường Ngũ Hành Sơn, Q. Ngũ Hành Sơn, TP. Đà Nẵng",
                            "300 Vo Nguyen Giap Street, Ngu Hanh Son Ward, Ngu Hanh Son District, Da Nang"))
            .location(new double[]{108.249158, 16.049713})
            .name(of("The Holiday Beach Club & Dining", "The Holiday Beach Club & Dining"))
            .slug(slug)
            .restaurantType(RestaurantType.BAR)
            .cuisines(cuisines)
            .shortDescription(of("Beach bar ngoai troi 300 cho nhin thang ra bien Vo Nguyen Giap, cocktail dac trung Da Nang va am thuc bien nhe nhang.",
                                 "300-seat open-air beach bar facing the sea on Vo Nguyen Giap, featuring Da Nang signature cocktails and light coastal dining."))
            .description(of("The Holiday Beach Club & Dining tai 300 Vo Nguyen Giap, Ngu Hanh Son, Da Nang. " +
                            "Beach club ngoai troi 300 cho, phong cach the mat bang bien, cocktail dac trung: " +
                            "Danang Sunrise Ritual, Tropic Mirage, Hoi An Umami. Gia 30.000-80.000d/nguoi. " +
                            "Am nhac live, su kien cuoi tuan. Bai do xe mien phi.",
                            "The Holiday Beach Club & Dining at 300 Vo Nguyen Giap, Ngu Hanh Son, Da Nang. " +
                            "300-seat outdoor beach club, beach lifestyle vibe, signature cocktails: " +
                            "Danang Sunrise Ritual, Tropic Mirage, Hoi An Umami. Price 30,000-80,000 VND/person. " +
                            "Live music, weekend events. Free parking."))
            .phone("0236383800")
            .website("https://pasgo.vn/nha-hang/the-holiday-beach-club-dining-vo-nguyen-giap-5822")
            .bookingHotline("0236383800")
            .minPricePerPerson(new BigDecimal("30000"))
            .maxPricePerPerson(new BigDecimal("80000"))
            .currencyCode("VND")
            .priceLevel(PriceLevel.CHEAP)
            .priceBucket(PriceBucket.UNDER_150K)
            .openingHours(openingHours)
            .suitableFor(suitableFor)
            .ambience(ambience)
            .capacity(capacity)
            .signatureDishes(signatureDishes)
            .menuImages(menuImages)
            .menuSections(menuSections)
            .parking(parking)
            .amenityCodes(amenityCodes)
            .images(images)
            .content(content)
            .policy(policy)
            .publisher(publisher)
            .moderation(moderation)
            .tableTypes(tableTypes)
            .bookingConfig(bookingConfig)
            .build();
    }
}
