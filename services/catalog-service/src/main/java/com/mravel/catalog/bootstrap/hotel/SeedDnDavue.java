package com.mravel.catalog.bootstrap.hotel;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.AmenityCatalogDoc;
import com.mravel.catalog.model.enums.AmenityGroup;
import com.mravel.catalog.model.enums.AmenitySection;
import com.mravel.catalog.model.doc.HotelDoc.BedOption;
import com.mravel.catalog.model.doc.HotelDoc.BedType;
import com.mravel.catalog.model.doc.HotelDoc.BoardType;
import com.mravel.catalog.model.doc.HotelDoc.ContentBlock;
import com.mravel.catalog.model.doc.HotelDoc.ContentBlock.ContentSection;
import com.mravel.catalog.model.doc.HotelDoc.FaqItem;
import com.mravel.catalog.model.doc.HotelDoc.GeneralInfo;
import com.mravel.catalog.model.doc.HotelDoc.HotelFilterFacets;
import com.mravel.catalog.model.doc.HotelDoc.HotelPolicy;
import com.mravel.catalog.model.doc.HotelDoc.HotelStatus;
import com.mravel.catalog.model.doc.HotelDoc.HotelType;
import com.mravel.catalog.model.doc.HotelDoc.Image;
import com.mravel.catalog.model.doc.HotelDoc.ModerationInfo;
import com.mravel.catalog.model.doc.HotelDoc.NearbyPlace;
import com.mravel.catalog.model.doc.HotelDoc.PaymentType;
import com.mravel.catalog.model.doc.HotelDoc.PolicyItem;
import com.mravel.catalog.model.doc.HotelDoc.PolicySection;
import com.mravel.catalog.model.doc.HotelDoc.PartnerType;
import com.mravel.catalog.model.doc.HotelDoc.PublisherInfo;
import com.mravel.catalog.model.doc.HotelDoc.RatePlan;
import com.mravel.catalog.model.doc.HotelDoc.RoomType;
import com.mravel.catalog.model.doc.HotelDoc.TaxAndFeeConfig;
import com.mravel.catalog.repository.HotelDocRepository;

import static com.mravel.catalog.utils.hotel.HotelContentBlocks.*;
import static com.mravel.catalog.utils.hotel.HotelImages.*;
import static com.mravel.catalog.utils.hotel.HotelSeedHelpers.*;
import static com.mravel.catalog.utils.hotel.Localized.of;

@Configuration
@Profile("seed")
public class SeedDnDavue {

	@Bean
	CommandLineRunner seedDnDavueRunner(HotelDocRepository repo) {
		return args -> {
			System.out.println(">>> [SeedDnDavue] start");
			HotelDoc doc = hotelDavue();
			repo.findBySlugAndActiveTrue(doc.getSlug())
					.ifPresent(existing -> doc.setId(existing.getId()));
			repo.save(doc);
			System.out.println(">>> [SeedDnDavue] upsert: " + doc.getSlug());
			System.out.println(">>> [SeedDnDavue] done");
		};
	}

	// =====================================================================
	// DAVUE HOTEL DA NANG
	// (source: Traveloka hotelId 9000000134494; phòng được dựng thêm)
	// =====================================================================
	private static HotelDoc hotelDavue() {
		String slug = "davue-hotel-da-nang";

		List<Image> images = List.of(
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-5ed86dd02e4234d1bb6025804fef7200.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", true, 0),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67757118-3000x2495-FIT_AND_TRIM-42982403ed14c775a205413c940e8dc7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 1),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-c2cc4e72eabeddbd906ad53ec03daf26.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 2),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-0cb7ac27f8b7095291a4ecef1c971ea2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Sảnh", "Lobby", false, 3),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-d29ebbccaa2bf8b80d723a3661b2bfc5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Nhà hàng", "Restaurant", false, 4),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-78d0231f7441b335bafce712f63ef867.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 5),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67757118-3000x1944-FIT_AND_TRIM-250ad28af590029d27e2b7d4edfdc0c3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Không gian chung", "Common space", false, 6),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67757118-3000x2963-FIT_AND_TRIM-e60f197b50032bc72dc8f09f9e70539c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng tắm", "Bathroom", false, 7),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67757118-3000x2392-FIT_AND_TRIM-812a47967e50014d71a4348156e883aa.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 8),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-ad8ff0eb27f6d8a8dd5bc41e4a8a10cb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 9),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67757118-3000x2052-FIT_AND_TRIM-0ee613076980837e9ca0d4c6966cacf9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 10),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-24e3a1a1eeeccaa4ca1892fd1d2c8475.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 11),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67757118-3000x2058-FIT_AND_TRIM-6fe1afb3991b8a18c0df4fe63b836bca.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 12),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67757118-3000x2047-FIT_AND_TRIM-62fdf9adc7aeb0bc0802350081504f24.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 13),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67757118-3000x2695-FIT_AND_TRIM-84db9b6e1ce45e86ffeb015708e7c0f3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng tắm", "Bathroom", false, 14),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-e303620eb2522a07f4950021a40315a0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng tắm", "Bathroom", false, 15),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-bade329f74d1b85cc4eccd12b541dcf5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 16),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-5220aab7f5be48ae3ca18707e66f92f9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 17),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-4e5f2c7f430b099c3ee9cb0e310cd02b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 18),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-6a519a6ef452c4c37dd0f9f204a24e55.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 19),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-fb7fd957b6071286b10ae438d5e5b95d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 20),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-2852e2a4d5197d6ae0e9948051e3440e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 21),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-d3e76b7bd03864e17dc6abe1b996ce4c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 22),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-0a48e9c0b3c3a2f0926422ae6a6ccd50.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng tắm", "Bathroom", false, 23),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-d50741082cf30d51e41ccf44a57aa3f0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 24),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-c5b6951b891fbf811494a6a4b9372542.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 25),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-c910855c2ebf72851f7654f2ed8f85bd.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng tắm", "Bathroom", false, 26),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-076227915a3309355f40218062d13824.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 27),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-165be630f95719198e13012233bdd0f8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng ngủ", "Bedroom", false, 28),
				img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-8986efdae6626bd0efdadad6db890984.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
					"Phòng tắm", "Bathroom", false, 29));

		List<ContentBlock> content = List.of(
				mapBlock(ContentSection.OVERVIEW, new double[] { 108.24597253763125, 16.053788174658518 }),

				heading(ContentSection.STORY, of("Giới thiệu chung về Davue Hotel Da Nang", "About Davue Hotel Da Nang")),

				paragraph(ContentSection.STORY,
						of("Davue Hotel Da Nang là khách sạn 4 sao tọa lạc tại 57-59 Đỗ Bí, phường Mỹ An, quận Ngũ Hành Sơn, Đà Nẵng. Chỉ cách bãi biển Mỹ Khê và bãi biển Mỹ An vài phút di chuyển, khách sạn là lựa chọn lý tưởng cho du khách muốn tận hưởng kỳ nghỉ biển trong không gian hiện đại, ấm cúng với dịch vụ tận tâm.",
								"Davue Hotel Da Nang is a 4-star hotel located at 57-59 Do Bi Street, My An Ward, Ngu Hanh Son District, Da Nang. Just minutes from My Khe Beach and My An Beach, it is an ideal choice for travelers seeking a beach holiday in a modern, cozy setting with attentive service.")),

				heading(ContentSection.STORY, of("Phòng nghỉ tiện nghi", "Comfortable rooms")),

				paragraph(ContentSection.STORY,
						of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến phòng Gia đình, đáp ứng nhu cầu của cả khách lẻ lẫn gia đình. Mỗi phòng được trang bị máy lạnh, minibar, bàn làm việc, máy sấy tóc, phòng tắm vòi sen riêng cùng Wi-Fi miễn phí, mang đến không gian nghỉ ngơi thoải mái.",
								"The hotel offers a variety of room categories from Standard, Superior and Deluxe to Family rooms, catering to both solo travelers and families. Each room comes with air conditioning, a minibar, a work desk, a hair dryer, a private shower bathroom and free Wi-Fi for a comfortable stay.")),

				heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

				paragraph(ContentSection.STORY,
						of("Nhà hàng của khách sạn phục vụ bữa sáng phong phú cùng các món Á - Âu trong ngày. Quầy cà phê ấm cúng là nơi lý tưởng để du khách thư giãn, nhâm nhi đồ uống sau một ngày khám phá thành phố biển Đà Nẵng.",
								"The hotel restaurant serves a hearty breakfast along with Asian and European dishes throughout the day. The cozy coffee shop is a perfect spot to relax and enjoy a drink after a day exploring the coastal city of Da Nang.")),

				heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

				paragraph(ContentSection.STORY,
						of("Davue Hotel Da Nang trang bị lễ tân 24 giờ, thang máy, dịch vụ giặt ủi, giữ hành lý, hỗ trợ đặt tour và thu đổi ngoại tệ. Khách sạn còn có dịch vụ cho thuê xe đạp, xe hơi và đưa đón sân bay theo yêu cầu, giúp du khách di chuyển và khám phá thuận tiện.",
								"Davue Hotel Da Nang features a 24-hour front desk, an elevator, laundry service, luggage storage, tour booking assistance and currency exchange. The hotel also offers bicycle and car rental and airport transfer on request, making travel and exploration convenient for guests.")),

				heading(ContentSection.STORY, of("Vị trí & bãi biển", "Location & beaches")),

				paragraph(ContentSection.STORY,
						of("Nằm tại khu vực Mỹ An sầm uất của quận Ngũ Hành Sơn, khách sạn chỉ cách bãi biển Mỹ Khê - một trong những bãi biển đẹp nhất hành tinh - khoảng vài phút đi xe. Danh thắng Ngũ Hành Sơn, bán đảo Sơn Trà và trung tâm thành phố đều nằm trong tầm di chuyển ngắn.",
								"Set in the bustling My An area of Ngu Hanh Son District, the hotel is just a few minutes' drive from My Khe Beach, one of the world's most beautiful beaches. The Marble Mountains, Son Tra Peninsula and the city center are all within a short drive.")),

				heading(ContentSection.STORY, of("Di chuyển", "Getting around")),

				paragraph(ContentSection.STORY,
						of("Sân bay quốc tế Đà Nẵng cách khách sạn khoảng 15 phút lái xe, thuận tiện cho việc đến và đi. Từ khách sạn, du khách dễ dàng tiếp cận các cây cầu biểu tượng như Cầu Rồng, Cầu sông Hàn cùng các khu chợ truyền thống và điểm tham quan nổi tiếng của thành phố.",
								"Da Nang International Airport is about a 15-minute drive from the hotel, convenient for arrivals and departures. From the hotel, guests can easily reach iconic bridges such as the Dragon Bridge and Han River Bridge, along with traditional markets and famous attractions of the city.")),

				heading(ContentSection.STORY, of("Khu vực xung quanh", "Surrounding area")),

				paragraph(ContentSection.STORY,
						of("Xung quanh khách sạn là khu phố ẩm thực sôi động với nhiều nhà hàng hải sản, quán cà phê và cửa hàng tiện lợi. Chỉ vài bước chân, du khách có thể tìm thấy siêu thị, máy ATM và các dịch vụ thiết yếu phục vụ cho kỳ nghỉ.",
								"The hotel is surrounded by a lively culinary neighborhood with many seafood restaurants, cafes and convenience stores. Within a few steps, guests can find a supermarket, ATMs and essential services for their stay.")),

				heading(ContentSection.STORY, of("Vì sao nên chọn Davue Hotel Da Nang", "Why choose Davue Hotel Da Nang")),

				paragraph(ContentSection.STORY,
						of("Với vị trí gần biển, phòng nghỉ tiện nghi, dịch vụ chu đáo và mức giá hợp lý, Davue Hotel Da Nang là lựa chọn đáng cân nhắc cho cả chuyến công tác lẫn kỳ nghỉ biển. Sự kết hợp giữa không gian hiện đại và sự thân thiện của đội ngũ nhân viên sẽ mang đến cho du khách kỳ nghỉ trọn vẹn tại Đà Nẵng.",
								"With its near-beach location, comfortable rooms, attentive service and reasonable prices, Davue Hotel Da Nang is a worthy choice for both business trips and beach holidays. The combination of a modern setting and a friendly staff offers guests a complete stay in Da Nang.")));

		List<AmenityCatalogDoc> hotelAmenities = List.of(
		amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
		amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
		amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
		amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
		amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
		amenity("parking", of("Bãi đậu xe (thu phí)", "Parking (surcharge)"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
		amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
		amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, true),
		amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
		amenity("bicycle_rental", of("Cho thuê xe đạp", "Bicycle hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
		amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("front_desk", of("Quầy lễ tân", "Front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
		amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
		amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
		amenity("shop", of("Cửa hàng quà tặng", "Gift shop"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
		amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.ROOM_FACILITIES, false),
		amenity("non_smoking", of("Khu vực không hút thuốc", "Non-smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
		amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false));

		List<RoomType> roomTypes = List.of(
				RoomType.builder()
						.id("rt-davue-standard-double")
						.name(of("Standard Double", "Standard Double"))
						.shortDescription(of("Phòng Standard Double 22 m², 1 giường double.", "22 sqm Standard Double room, 1 double bed."))
						.description(of("Phòng Standard Double 22 m² với đầy đủ tiện nghi hiện đại, mang đến không gian nghỉ ngơi thoải mái tại trung tâm Mỹ An, Đà Nẵng.", "22 sqm Standard Double room with full modern amenities, offering a comfortable stay in central My An, Da Nang."))
						.areaSqm(22.0)
						.bedType(BedType.DOUBLE)
						.bedsCount(1)
						.bedLayoutDescription(of("1 giường double", "1 double bed"))
						.bedOptions(List.of(
						BedOption.builder().type(BedType.DOUBLE).count(1).build()))
						.maxAdults(2)
						.maxChildren(0)
						.maxGuests(2)
						.totalRooms(8)
						.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-5ed86dd02e4234d1bb6025804fef7200.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
							"Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67757118-3000x2495-FIT_AND_TRIM-42982403ed14c775a205413c940e8dc7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
							"Phòng ngủ", "Bedroom", false, 1)))
						.amenityCodes(List.of(
								"SHOWER",
								"AIR_CONDITIONING",
								"WIFI_FREE",
								"HOT_WATER",
								"MINIBAR",
								"DESK",
								"HAIR_DRYER",
								"PRIVATE_BATHROOM",
								"BATH_AMENITIES",
								"SLIPPERS",
								"BOTTLED_WATER",
								"COFFEE_TEA_MAKER",
								"BLACKOUT_CURTAINS"))
						.ratePlans(List.of(
								RatePlan.builder()
										.id("rt-davue-standard-double-prepaid-nonref")
										.name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
										.boardType(BoardType.ROOM_ONLY)
										.paymentType(PaymentType.PREPAID)
										.refundable(false)
										.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
										.pricePerNight(new BigDecimal("700000"))
										.referencePricePerNight(new BigDecimal("1000000"))
										.discountPercent(30)
										.taxPercent(new BigDecimal("8"))
										.serviceFeePercent(new BigDecimal("5"))
										.priceIncludesTax(false)
										.priceIncludesServiceFee(false)
										.lengthOfStayDiscounts(List.of())
										.promoLabel(of("Giá ưu đãi", "Special price"))
										.showLowAvailability(true)
										.build(),
								RatePlan.builder()
										.id("rt-davue-standard-double-breakfast-flex")
										.name(of("Standard Double - Bao gồm bữa sáng", "Standard Double - Breakfast included"))
										.boardType(BoardType.BREAKFAST_INCLUDED)
										.paymentType(PaymentType.PAY_AT_HOTEL)
										.refundable(true)
										.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
										.pricePerNight(new BigDecimal("805000"))
										.referencePricePerNight(new BigDecimal("1150000"))
										.discountPercent(30)
										.taxPercent(new BigDecimal("8"))
										.serviceFeePercent(new BigDecimal("5"))
										.priceIncludesTax(false)
										.priceIncludesServiceFee(false)
										.lengthOfStayDiscounts(List.of())
										.promoLabel(of("Gồm bữa sáng", "Breakfast included"))
										.showLowAvailability(false)
										.build()))
						.build(),
				RoomType.builder()
						.id("rt-davue-superior-twin")
						.name(of("Superior Twin", "Superior Twin"))
						.shortDescription(of("Phòng Superior Twin 26 m², 1 giường twin.", "26 sqm Superior Twin room, 1 twin bed."))
						.description(of("Phòng Superior Twin 26 m² với đầy đủ tiện nghi hiện đại, mang đến không gian nghỉ ngơi thoải mái tại trung tâm Mỹ An, Đà Nẵng.", "26 sqm Superior Twin room with full modern amenities, offering a comfortable stay in central My An, Da Nang."))
						.areaSqm(26.0)
						.bedType(BedType.TWIN)
						.bedsCount(1)
						.bedLayoutDescription(of("1 giường twin", "1 twin bed"))
						.bedOptions(List.of(
						BedOption.builder().type(BedType.TWIN).count(1).build()))
						.maxAdults(2)
						.maxChildren(0)
						.maxGuests(2)
						.totalRooms(8)
						.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-c2cc4e72eabeddbd906ad53ec03daf26.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
							"Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-78d0231f7441b335bafce712f63ef867.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
							"Phòng ngủ", "Bedroom", false, 1)))
						.amenityCodes(List.of(
								"SHOWER",
								"AIR_CONDITIONING",
								"WIFI_FREE",
								"HOT_WATER",
								"MINIBAR",
								"DESK",
								"HAIR_DRYER",
								"PRIVATE_BATHROOM",
								"BATH_AMENITIES",
								"SLIPPERS",
								"BOTTLED_WATER",
								"COFFEE_TEA_MAKER",
								"BLACKOUT_CURTAINS"))
						.ratePlans(List.of(
								RatePlan.builder()
										.id("rt-davue-superior-twin-prepaid-nonref")
										.name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
										.boardType(BoardType.ROOM_ONLY)
										.paymentType(PaymentType.PREPAID)
										.refundable(false)
										.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
										.pricePerNight(new BigDecimal("850000"))
										.referencePricePerNight(new BigDecimal("1214286"))
										.discountPercent(30)
										.taxPercent(new BigDecimal("8"))
										.serviceFeePercent(new BigDecimal("5"))
										.priceIncludesTax(false)
										.priceIncludesServiceFee(false)
										.lengthOfStayDiscounts(List.of())
										.promoLabel(of("Giá ưu đãi", "Special price"))
										.showLowAvailability(true)
										.build(),
								RatePlan.builder()
										.id("rt-davue-superior-twin-breakfast-flex")
										.name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
										.boardType(BoardType.BREAKFAST_INCLUDED)
										.paymentType(PaymentType.PAY_AT_HOTEL)
										.refundable(true)
										.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
										.pricePerNight(new BigDecimal("977500"))
										.referencePricePerNight(new BigDecimal("1396429"))
										.discountPercent(30)
										.taxPercent(new BigDecimal("8"))
										.serviceFeePercent(new BigDecimal("5"))
										.priceIncludesTax(false)
										.priceIncludesServiceFee(false)
										.lengthOfStayDiscounts(List.of())
										.promoLabel(of("Gồm bữa sáng", "Breakfast included"))
										.showLowAvailability(false)
										.build()))
						.build(),
				RoomType.builder()
						.id("rt-davue-deluxe-double")
						.name(of("Deluxe Double", "Deluxe Double"))
						.shortDescription(of("Phòng Deluxe Double 30 m², 1 giường queen.", "30 sqm Deluxe Double room, 1 queen bed."))
						.description(of("Phòng Deluxe Double 30 m² với đầy đủ tiện nghi hiện đại, mang đến không gian nghỉ ngơi thoải mái tại trung tâm Mỹ An, Đà Nẵng.", "30 sqm Deluxe Double room with full modern amenities, offering a comfortable stay in central My An, Da Nang."))
						.areaSqm(30.0)
						.bedType(BedType.QUEEN)
						.bedsCount(1)
						.bedLayoutDescription(of("1 giường queen", "1 queen bed"))
						.bedOptions(List.of(
						BedOption.builder().type(BedType.QUEEN).count(1).build()))
						.maxAdults(2)
						.maxChildren(1)
						.maxGuests(3)
						.totalRooms(8)
						.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67757118-3000x2392-FIT_AND_TRIM-812a47967e50014d71a4348156e883aa.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
							"Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-ad8ff0eb27f6d8a8dd5bc41e4a8a10cb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
							"Phòng ngủ", "Bedroom", false, 1)))
						.amenityCodes(List.of(
								"SHOWER",
								"AIR_CONDITIONING",
								"WIFI_FREE",
								"HOT_WATER",
								"MINIBAR",
								"DESK",
								"HAIR_DRYER",
								"PRIVATE_BATHROOM",
								"BATH_AMENITIES",
								"SLIPPERS",
								"BOTTLED_WATER",
								"COFFEE_TEA_MAKER",
								"BLACKOUT_CURTAINS",
								"BALCONY",
								"SEATING_AREA"))
						.ratePlans(List.of(
								RatePlan.builder()
										.id("rt-davue-deluxe-double-prepaid-nonref")
										.name(of("Deluxe Double - Không gồm bữa sáng", "Deluxe Double - Room only"))
										.boardType(BoardType.ROOM_ONLY)
										.paymentType(PaymentType.PREPAID)
										.refundable(false)
										.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
										.pricePerNight(new BigDecimal("1050000"))
										.referencePricePerNight(new BigDecimal("1500000"))
										.discountPercent(30)
										.taxPercent(new BigDecimal("8"))
										.serviceFeePercent(new BigDecimal("5"))
										.priceIncludesTax(false)
										.priceIncludesServiceFee(false)
										.lengthOfStayDiscounts(List.of())
										.promoLabel(of("Giá ưu đãi", "Special price"))
										.showLowAvailability(true)
										.build(),
								RatePlan.builder()
										.id("rt-davue-deluxe-double-breakfast-flex")
										.name(of("Deluxe Double - Bao gồm bữa sáng", "Deluxe Double - Breakfast included"))
										.boardType(BoardType.BREAKFAST_INCLUDED)
										.paymentType(PaymentType.PAY_AT_HOTEL)
										.refundable(true)
										.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
										.pricePerNight(new BigDecimal("1207500"))
										.referencePricePerNight(new BigDecimal("1725000"))
										.discountPercent(30)
										.taxPercent(new BigDecimal("8"))
										.serviceFeePercent(new BigDecimal("5"))
										.priceIncludesTax(false)
										.priceIncludesServiceFee(false)
										.lengthOfStayDiscounts(List.of())
										.promoLabel(of("Gồm bữa sáng", "Breakfast included"))
										.showLowAvailability(false)
										.build()))
						.build(),
				RoomType.builder()
						.id("rt-davue-family-room")
						.name(of("Family Room", "Family Room"))
						.shortDescription(of("Phòng Family Room 38 m², 1 giường double và 1 giường single.", "38 sqm Family Room room, 1 double bed and 1 single bed."))
						.description(of("Phòng Family Room 38 m² với đầy đủ tiện nghi hiện đại, mang đến không gian nghỉ ngơi thoải mái tại trung tâm Mỹ An, Đà Nẵng.", "38 sqm Family Room room with full modern amenities, offering a comfortable stay in central My An, Da Nang."))
						.areaSqm(38.0)
						.bedType(BedType.MULTIPLE)
						.bedsCount(2)
						.bedLayoutDescription(of("1 giường double và 1 giường single", "1 double bed and 1 single bed"))
						.bedOptions(List.of(
						BedOption.builder().type(BedType.DOUBLE).count(1).build(),
						BedOption.builder().type(BedType.SINGLE).count(1).build()))
						.maxAdults(3)
						.maxChildren(1)
						.maxGuests(4)
						.totalRooms(6)
						.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/67757118-3000x2052-FIT_AND_TRIM-0ee613076980837e9ca0d4c6966cacf9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
							"Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20023314-24e3a1a1eeeccaa4ca1892fd1d2c8475.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
							"Phòng ngủ", "Bedroom", false, 1)))
						.amenityCodes(List.of(
								"SHOWER",
								"AIR_CONDITIONING",
								"WIFI_FREE",
								"HOT_WATER",
								"MINIBAR",
								"DESK",
								"HAIR_DRYER",
								"PRIVATE_BATHROOM",
								"BATH_AMENITIES",
								"SLIPPERS",
								"BOTTLED_WATER",
								"COFFEE_TEA_MAKER",
								"BLACKOUT_CURTAINS",
								"BALCONY",
								"SEATING_AREA"))
						.ratePlans(List.of(
								RatePlan.builder()
										.id("rt-davue-family-room-prepaid-nonref")
										.name(of("Family Room - Không gồm bữa sáng", "Family Room - Room only"))
										.boardType(BoardType.ROOM_ONLY)
										.paymentType(PaymentType.PREPAID)
										.refundable(false)
										.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
										.pricePerNight(new BigDecimal("1400000"))
										.referencePricePerNight(new BigDecimal("2000000"))
										.discountPercent(30)
										.taxPercent(new BigDecimal("8"))
										.serviceFeePercent(new BigDecimal("5"))
										.priceIncludesTax(false)
										.priceIncludesServiceFee(false)
										.lengthOfStayDiscounts(List.of())
										.promoLabel(of("Giá ưu đãi", "Special price"))
										.showLowAvailability(true)
										.build(),
								RatePlan.builder()
										.id("rt-davue-family-room-breakfast-flex")
										.name(of("Family Room - Bao gồm bữa sáng", "Family Room - Breakfast included"))
										.boardType(BoardType.BREAKFAST_INCLUDED)
										.paymentType(PaymentType.PAY_AT_HOTEL)
										.refundable(true)
										.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
										.pricePerNight(new BigDecimal("1610000"))
										.referencePricePerNight(new BigDecimal("2300000"))
										.discountPercent(30)
										.taxPercent(new BigDecimal("8"))
										.serviceFeePercent(new BigDecimal("5"))
										.priceIncludesTax(false)
										.priceIncludesServiceFee(false)
										.lengthOfStayDiscounts(List.of())
										.promoLabel(of("Gồm bữa sáng", "Breakfast included"))
										.showLowAvailability(false)
										.build()))
						.build());

		List<NearbyPlace> nearby = List.of(
		nearby("bai-bien-my-an", of("Bãi biển Mỹ An", "My An Beach"), of("Bãi biển", "Beach"), 600.0),
		nearby("bai-bien-my-khe", of("Bãi biển Mỹ Khê", "My Khe Beach"), of("Bãi biển", "Beach"), 1200.0),
		nearby("ngu-hanh-son", of("Ngũ Hành Sơn", "Marble Mountains"), of("Khác", "Other"), 3000.0),
		nearby("chua-linh-ung", of("Chùa Linh Ứng", "Linh Ung Pagoda"), of("Khác", "Other"), 9000.0),
		nearby("ban-dao-son-tra", of("Bán đảo Sơn Trà", "Son Tra Peninsula"), of("Khác", "Other"), 11000.0),
		nearby("cau-rong", of("Cầu Rồng", "Dragon Bridge"), of("Khác", "Other"), 5000.0),
		nearby("cau-song-han", of("Cầu sông Hàn", "Han River Bridge"), of("Khác", "Other"), 5500.0),
		nearby("cau-tinh-yeu", of("Cầu Tình Yêu", "Love Bridge"), of("Khác", "Other"), 5200.0),
		nearby("cho-han", of("Chợ Hàn", "Han Market"), of("Khác", "Other"), 5800.0),
		nearby("cho-con", of("Chợ Cồn", "Con Market"), of("Khác", "Other"), 6500.0),
		nearby("bao-tang-dieu-khac-cham", of("Bảo tàng Điêu khắc Chăm", "Cham Sculpture Museum"), of("Khác", "Other"), 4800.0),
		nearby("cong-vien-chau-a", of("Công viên Châu Á (Asia Park)", "Sun World Asia Park"), of("Trung tâm giải trí", "Entertainment hub"), 7000.0),
		nearby("dinh-ban-co", of("Đỉnh Bàn Cờ", "Ban Co Peak"), of("Khác", "Other"), 14000.0),
		nearby("san-bay-da-nang", of("Sân bay quốc tế Đà Nẵng", "Da Nang International Airport"), of("Trung tâm giao thông", "Transport hub"), 6000.0),
		nearby("ba-na-hills", of("Bà Nà Hills", "Ba Na Hills"), of("Trung tâm giải trí", "Entertainment hub"), 28000.0));

		GeneralInfo generalInfo = GeneralInfo.builder()
				.mainFacilitiesSummary(of("Nhà hàng, máy lạnh, lễ tân 24h, chỗ đậu xe, thang máy, WiFi miễn phí", "Restaurant, air conditioning, 24-hour front desk, parking, elevator, free WiFi"))
				.distanceToCityCenterKm(5.0)
				.popularAreaSummary(of("Bãi biển Mỹ An, Bãi biển Mỹ Khê, Ngũ Hành Sơn", "My An Beach, My Khe Beach, Marble Mountains"))
				.totalRooms(30)
				.totalFloors(6)
				.otherHighlightFacilities(of("Đưa đón sân bay, cho thuê xe đạp & xe hơi, hỗ trợ đặt tour, giặt ủi.", "Airport transfer, bicycle & car rental, tour booking, laundry."))
				.interestingPlacesSummary(of("Bãi biển Mỹ Khê, Cầu Rồng, Ngũ Hành Sơn", "My Khe Beach, Dragon Bridge, Marble Mountains"))
				.build();

		HotelPolicy policy = HotelPolicy.builder()
				.checkInFrom(LocalTime.of(14, 0))
				.checkOutUntil(LocalTime.of(12, 0))
				.items(List.of(
						PolicyItem.builder().section(PolicySection.CHECKIN_CHECKOUT)
								.title(of("Thời gian nhận phòng/trả phòng", "Check-in/check-out time"))
								.content(of("Giờ nhận phòng: từ 14:00. Giờ trả phòng: trước 12:00.", "Check-in: from 14:00. Check-out: before 12:00."))
								.build(),
						PolicyItem.builder().section(PolicySection.EARLY_CHECKIN)
								.title(of("Nhận phòng sớm", "Early check-in"))
								.content(of("Hỗ trợ nhận phòng sớm tùy tình trạng phòng trống, vui lòng liên hệ trước.", "Early check-in is subject to availability; please contact in advance."))
								.build(),
						PolicyItem.builder().section(PolicySection.REQUIRED_DOCUMENTS)
								.title(of("Giấy tờ yêu cầu", "Required documents"))
								.content(of("Vui lòng xuất trình CCCD/hộ chiếu hợp lệ khi nhận phòng.", "Please present a valid ID card/passport at check-in."))
								.build(),
						PolicyItem.builder().section(PolicySection.SMOKING)
								.title(of("Chính sách hút thuốc", "Smoking policy"))
								.content(of("Phòng không hút thuốc; có khu vực hút thuốc riêng.", "Non-smoking rooms; a designated smoking area is available."))
								.build(),
						PolicyItem.builder().section(PolicySection.OTHER)
								.title(of("Vật nuôi", "Pets"))
								.content(of("Cho phép vật nuôi nhỏ; vui lòng liên hệ trước để được hỗ trợ.", "Small pets are allowed; please contact in advance for assistance."))
								.build()))
				.build();

		TaxAndFeeConfig taxConfig = TaxAndFeeConfig.builder()
				.defaultVatPercent(new BigDecimal("8"))
				.defaultServiceFeePercent(new BigDecimal("5"))
				.showPricePreTax(true)
				.build();

		HotelFilterFacets filterFacets = HotelFilterFacets.builder()
				.hasFreeCancellation(true)
				.hasPayAtHotel(true)
				.hasBreakfastIncluded(true)
				.build();

		Instant now = Instant.parse("2024-01-01T00:00:00Z");
		PublisherInfo publisher = PublisherInfo.builder()
				.partnerId("11")
				.partnerName("Ông Trùm Bất Động Sản")
				.partnerEmail("22110377@student.hcmute.edu.vn")
				.partnerType(PartnerType.HOTEL_OWNER)
				.createdAt(now)
				.lastUpdatedAt(now)
				.build();
		ModerationInfo moderation = ModerationInfo.builder()
				.status(HotelStatus.APPROVED)
				.reportCount(0)
				.lastActionByAdminId("system-seed")
				.lastActionAt(now)
				.build();

		List<FaqItem> faqs = List.of(
		faq(of("Những tiện ích tại Davue Hotel Da Nang?", "What amenities does Davue Hotel Da Nang offer?"),
			of("Các tiện ích bao gồm máy lạnh, nhà hàng, lễ tân 24h, chỗ đậu xe, thang máy và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, 24-hour front desk, parking, elevator and WiFi (some services may require an additional fee).")),
		faq(of("Thời gian nhận phòng và trả phòng của Davue Hotel Da Nang?", "What are the check-in and check-out times at Davue Hotel Da Nang?"),
			of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
		faq(of("Davue Hotel Da Nang có phục vụ ăn sáng không?", "Does Davue Hotel Da Nang serve breakfast?"),
			of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
		faq(of("Khách sạn Davue Hotel Da Nang đạt tiêu chuẩn mấy sao?", "What star rating does Davue Hotel Da Nang have?"),
			of("Davue Hotel Da Nang là khách sạn đạt tiêu chuẩn 4 sao.", "Davue Hotel Da Nang is a 4-star hotel.")),
		faq(of("Davue Hotel Da Nang có bao nhiêu tầng lầu?", "How many floors does Davue Hotel Da Nang have?"),
			of("Davue Hotel Da Nang có tổng cộng 6 tầng.", "Davue Hotel Da Nang has a total of 6 floors.")),
		faq(of("Tổng số phòng hiện có tại Davue Hotel Da Nang là bao nhiêu?", "How many rooms does Davue Hotel Da Nang have?"),
			of("Tổng số phòng tại Davue Hotel Da Nang là 30 phòng.", "Davue Hotel Da Nang has a total of 30 rooms.")),
		faq(of("Davue Hotel Da Nang có chỗ đậu xe không?", "Does Davue Hotel Da Nang have parking?"),
			of("Có, khách sạn có cung cấp chỗ đậu xe (có thể thu phí). Vui lòng liên hệ trực tiếp với khách sạn để xác nhận chi tiết.", "Yes, the hotel provides parking (a surcharge may apply). Please contact the hotel directly to confirm details.")),
		faq(of("Davue Hotel Da Nang có dịch vụ đưa đón sân bay không?", "Does Davue Hotel Da Nang offer airport transfer?"),
			of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu (thu phí). Vui lòng liên hệ trước để được sắp xếp.", "Yes, the hotel offers airport transfer on request (surcharge). Please contact in advance to arrange.")),
		faq(of("Davue Hotel Da Nang cách bãi biển bao xa?", "How far is Davue Hotel Da Nang from the beach?"),
			of("Khách sạn chỉ cách bãi biển Mỹ An khoảng 600 m và bãi biển Mỹ Khê khoảng 1,2 km, rất thuận tiện cho kỳ nghỉ biển.", "The hotel is only about 600 m from My An Beach and around 1.2 km from My Khe Beach, very convenient for a beach holiday.")),
		faq(of("Davue Hotel Da Nang có mức giá là bao nhiêu?", "How much does Davue Hotel Da Nang cost?"),
			of("Phòng tại khách sạn có giá từ khoảng 700.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 700,000 VND per night. Prices may vary depending on the date and promotions.")));

		return HotelDoc.builder()
				.active(true)
				.destinationSlug("da-nang")
				.cityName(of("Đà Nẵng", "Da Nang"))
				.districtName(of("Ngũ Hành Sơn", "Ngu Hanh Son"))
				.wardName(of("Mỹ An", "My An"))
				.addressLine(of("57-59 Đỗ Bí, Mỹ An, Ngũ Hành Sơn, Đà Nẵng, Việt Nam, 550000", "57-59 Do Bi, My An Ward, Ngu Hanh Son District, Da Nang, Vietnam, 550000"))
				.location(new double[] { 108.24597253763125, 16.053788174658518 })
				.name(of("Davue Hotel Da Nang", "Davue Hotel Da Nang"))
				.slug(slug)
				.hotelType(HotelType.HOTEL)
				.starRating(4)
				.shortDescription(of("Khách sạn 4 sao gần biển Mỹ An, quận Ngũ Hành Sơn, Đà Nẵng.", "4-star hotel near My An Beach, Ngu Hanh Son District, Da Nang."))
				.description(of("Davue Hotel Da Nang là khách sạn 4 sao tại Mỹ An, Ngũ Hành Sơn, Đà Nẵng, gần bãi biển Mỹ Khê với phòng nghỉ tiện nghi, nhà hàng, lễ tân 24h và dịch vụ đưa đón sân bay.", "Davue Hotel Da Nang is a 4-star hotel in My An, Ngu Hanh Son, Da Nang, near My Khe Beach, with comfortable rooms, a restaurant, 24-hour front desk and airport transfer service."))
				.defaultCheckInTime(LocalTime.of(14, 0))
				.defaultCheckOutTime(LocalTime.of(12, 0))
				.hasOnlineCheckin(false)
				.minNightlyPrice(new BigDecimal("700000"))
				.currencyCode("VND")
				.referenceNightlyPrice(new BigDecimal("1000000"))
				.discountPercent(30)
				.taxConfig(taxConfig)
				.filterFacets(filterFacets)
				.images(images)
				.content(content)
				.amenityCodes(hotelAmenities.stream()
						.map(AmenityCatalogDoc::getCode)
						.filter(Objects::nonNull)
						.map(String::trim)
						.map(s -> s.toUpperCase(Locale.ROOT))
						.distinct()
						.toList())
				.roomTypes(roomTypes)
				.nearbyPlaces(nearby)
				.policy(policy)
				.generalInfo(generalInfo)
				.faqs(faqs)
				.publisher(publisher)
				.moderation(moderation)
				.build();
	}
}
