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
public class SeedSpGate {

	@Bean
	CommandLineRunner seedSpGateRunner(HotelDocRepository repo) {
		return args -> {
			System.out.println(">>> [SeedSpGate] start");
			HotelDoc doc = hotelSpGate();
			repo.findBySlugAndActiveTrue(doc.getSlug())
					.ifPresent(existing -> doc.setId(existing.getId()));
			repo.save(doc);
			System.out.println(">>> [SeedSpGate] upsert: " + doc.getSlug());
			System.out.println(">>> [SeedSpGate] done");
		};
	}

	// =====================================================================
	// THE GATE BOUTIQUE HOTEL SAPA (source: Traveloka hotelId 9000002809293)
	// =====================================================================
	private static HotelDoc hotelSpGate() {
		String slug = "the-gate-boutique-hotel-sapa";

		List<Image> images = List.of(
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-dea9952c4e21779b940e8372af9a9fe0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Sảnh", "Lobby", true, 0),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-89d83b086da911fdb173bb52a5c646c7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 1),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-2d5cdb63eea8671d894679879a57adec.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 2),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680008890-0cb5065a3dbb4de9a2d365261e3b7cc2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 3),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680008890-74333d92ccab967e23bdc80d85685b47.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Cảnh quan núi", "Mountain view", false, 4),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680008890-cbd9a0e1e40e03a5162961918adb68d5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 5),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680008890-966e6e759ebd139069f3578bfad2fbe3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 6),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680008890-9f9ab6e0915fe60204ccabb078c8dfad.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Bên ngoài / Tòa nhà", "Exterior / Building", false, 7),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680008890-fa93a4ef76a21683b203531e8ce0505c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Sảnh", "Lobby", false, 8),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680008890-915463ee1b3d183268971ccdda918bf6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Bên ngoài / Tòa nhà", "Exterior / Building", false, 9),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-6829ff5bb443d8d3a68c0ecd6b171162.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 10),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-ad52725c7be3ad5ab7de20ce454464a7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 11),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-8c63bb26806cc604aa7069f14efa165e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 12),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-9896bdd335f6b74611c3c255161a2755.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 13),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-4ee807a06bff7065ac5ad38ce47a8ab5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 14),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-d3a276c862632e6056f077dbf746d8ff.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 15),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-788edd81f499c1a272b6fb4df89a0239.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 16),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-c936ad0bb610bcd545b0dc3ca23b8b34.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng tắm", "Bathroom", false, 17),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-6eabb2bedacc157435f5dc3eb0299fe6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 18),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-5a321e86d1b479705df5fed9025e3229.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 19),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-d5bd0b1a1969f46ae2de482a5994f892.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 20),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-f81d4c88898a69a05e7da1ddc1655f3a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 21),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-b9651b07851c56b9d2ab0a6eaa9c42a1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 22),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-7b4b81e71f0f1d70a82b435045fa75e9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Cảnh quan núi", "Mountain view", false, 23),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-d8c74f955528511394047ce14090d79a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng tắm", "Bathroom", false, 24),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-ed63c9ce478a1f032fe196e18ab9bdcf.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 25),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-22a1b982d112462c70619ee51cfc924c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 26),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-f0e1fb51cc869993b4f95d290fa830b9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 27),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-569c542d4e17e88937a86f7b77d90f39.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 28),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-f04b217c7b29330b69013922d9908a20.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 29));

		List<ContentBlock> content = List.of(
				mapBlock(ContentSection.OVERVIEW, new double[] { 103.8426508, 22.3356561 }),

				heading(ContentSection.STORY, of("Giới thiệu chung về The Gate Boutique Hotel Sapa", "About The Gate Boutique Hotel Sapa")),

				paragraph(ContentSection.STORY,
						of("The Gate Boutique Hotel Sapa là khách sạn boutique 3 sao nằm tại số 02 Thạch Sơn, ngay trung tâm thị xã Sa Pa, tỉnh Lào Cai. Với phong cách thiết kế ấm cúng và 20 phòng nghỉ trải trên 3 tầng, khách sạn mang đến không gian nghỉ dưỡng yên tĩnh giữa phố núi Tây Bắc, khí hậu mát mẻ quanh năm và vị trí thuận tiện để khám phá Sa Pa.", "The Gate Boutique Hotel Sapa is a 3-star boutique hotel located at 02 Thach Son, right in the center of Sa Pa town, Lao Cai province. With a cozy design and 20 rooms across 3 floors, the hotel offers a peaceful retreat amid the northwestern mountains, a cool climate year-round and a convenient location for exploring Sa Pa.")),

				heading(ContentSection.STORY, of("Các loại phòng nghỉ", "Room types")),

				paragraph(ContentSection.STORY,
						of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe hướng núi đến phòng Gia đình. Mỗi phòng được trang bị máy lạnh, minibar, máy sấy tóc, nước suối miễn phí, đồ dùng phòng tắm và Wi-Fi miễn phí, đảm bảo sự thoải mái sau một ngày khám phá vùng cao.", "The hotel offers a range of room categories from Standard, Superior and Deluxe Mountain View to Family rooms. Each room is equipped with air conditioning, a minibar, a hair dryer, complimentary bottled water, bath amenities and free Wi-Fi, ensuring comfort after a day of exploring the highlands.")),

				heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

				paragraph(ContentSection.STORY,
						of("Nhà hàng của khách sạn phục vụ bữa sáng cùng các món ăn đặc trưng của vùng núi Tây Bắc. Du khách có thể thưởng thức cà phê và trà ấm tại sảnh trong không gian nhìn ra phố núi Sa Pa se lạnh.", "The hotel restaurant serves breakfast along with specialties of the northwestern highlands. Guests can enjoy coffee and warm tea in the lobby overlooking the cool Sa Pa mountain town.")),

				heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

				paragraph(ContentSection.STORY,
						of("Khách sạn có lễ tân 24 giờ, dịch vụ phòng, giặt ủi, giữ hành lý, thu đổi ngoại tệ, hỗ trợ đặt tour và đội ngũ nhân viên đa ngôn ngữ. Bảo vệ 24 giờ và dịch vụ concierge mang đến sự tiện lợi và an tâm cho du khách trong suốt kỳ nghỉ.", "The hotel features a 24-hour front desk, room service, laundry, luggage storage, currency exchange, tour booking assistance and multilingual staff. 24-hour security and concierge service provide convenience and peace of mind throughout the stay.")),

				heading(ContentSection.STORY, of("Vị trí & cảnh quan núi", "Location & mountain scenery")),

				paragraph(ContentSection.STORY,
						of("Nằm trên đường Thạch Sơn giữa trung tâm Sa Pa, khách sạn chỉ cách Nhà thờ đá, Quảng trường và Chợ Sa Pa vài phút đi bộ. Từ đây, du khách dễ dàng chiêm ngưỡng khung cảnh núi non trùng điệp, ruộng bậc thang Mường Hoa và đỉnh Fansipan hùng vĩ.", "Located on Thach Son Street in the heart of Sa Pa, the hotel is just a few minutes' walk from the Stone Church, the Square and Sa Pa Market. From here, guests can easily admire the rolling mountains, the Muong Hoa terraced fields and the majestic Fansipan peak.")),

				heading(ContentSection.STORY, of("Di chuyển", "Getting around")),

				paragraph(ContentSection.STORY,
						of("Khách sạn hỗ trợ dịch vụ đưa đón sân bay và cho thuê xe theo yêu cầu (thu phí). Ga cáp treo Fansipan, các bản làng vùng cao và đèo Ô Quy Hồ đều nằm trong tầm di chuyển thuận tiện bằng xe.", "The hotel offers airport transfer and car hire on request (surcharge). The Fansipan cable car station, highland villages and the O Quy Ho pass are all within convenient driving distance.")),

				heading(ContentSection.STORY, of("Khu vực xung quanh & bản làng", "Surroundings & villages")),

				paragraph(ContentSection.STORY,
						of("Xung quanh khách sạn là những bản làng dân tộc đặc sắc như Cát Cát, Tả Van và Tả Phìn, nơi du khách có thể tìm hiểu văn hóa người H'Mông, Dao đỏ và mua các sản phẩm thổ cẩm thủ công truyền thống.", "Around the hotel are distinctive ethnic villages such as Cat Cat, Ta Van and Ta Phin, where guests can learn about the culture of the H'Mong and Red Dao people and buy traditional handmade brocade products.")),

				heading(ContentSection.STORY, of("Vì sao nên chọn The Gate Boutique Hotel Sapa", "Why choose The Gate Boutique Hotel Sapa")),

				paragraph(ContentSection.STORY,
						of("Chọn The Gate Boutique Hotel Sapa, du khách có được vị trí trung tâm thuận tiện, phòng nghỉ boutique ấm cúng, dịch vụ chu đáo và mức giá hợp lý cho kỳ nghỉ tại Sa Pa. Đây là điểm lưu trú lý tưởng để khám phá phố núi, ruộng bậc thang và chinh phục đỉnh Fansipan.", "Choosing The Gate Boutique Hotel Sapa gives travelers a convenient central location, cozy boutique rooms, attentive service and reasonable rates for a Sa Pa holiday. It is an ideal base to explore the mountain town, terraced fields and conquer the Fansipan peak.")));

		List<AmenityCatalogDoc> hotelAmenities = List.of(
		amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
		amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
		amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
		amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
		amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
		amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
		amenity("coffee_shop", of("Cà phê tại sảnh", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
		amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
		amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
		amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
		amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("doorman", of("Người gác cửa", "Doorman"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
		amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
		amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
		amenity("shop", of("Cửa hàng quà tặng", "Gift shop"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
		amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
		amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
		amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
		amenity("express_checkin", of("Nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false));

		List<RoomType> roomTypes = List.of(
				RoomType.builder()
					.id("rt-spgate-standard-double")
					.name(of("Standard Double", "Standard Double"))
					.shortDescription(of("Phòng Standard 18 m² ấm cúng, 1 giường đôi.", "Cozy 18 sqm standard room, 1 double bed."))
					.description(of("Phòng Standard 18 m² ấm cúng, 1 giường đôi. Trang bị đầy đủ tiện nghi cho kỳ nghỉ tại Sa Pa.", "Cozy 18 sqm standard room, 1 double bed. Fully equipped for a comfortable stay in Sa Pa."))
					.areaSqm(18.0)
					.bedType(BedType.DOUBLE)
					.bedsCount(1)
					.bedLayoutDescription(of("1 giường đôi", "1 double bed"))
					.bedOptions(List.of(
								BedOption.builder().type(BedType.DOUBLE).count(1).build()))
					.maxAdults(2)
					.maxChildren(0)
					.maxGuests(2)
					.totalRooms(6)
					.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-89d83b086da911fdb173bb52a5c646c7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-2d5cdb63eea8671d894679879a57adec.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
				"COFFEE_TEA_MAKER"))
					.ratePlans(List.of(
							RatePlan.builder()
								.id("rt-spgate-standard-double-prepaid-nonref")
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
								.id("rt-spgate-standard-double-breakfast-flex")
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
					.id("rt-spgate-superior-twin")
					.name(of("Superior Twin", "Superior Twin"))
					.shortDescription(of("Phòng Superior 22 m², 2 giường đơn.", "22 sqm superior room, 2 single beds."))
					.description(of("Phòng Superior 22 m², 2 giường đơn. Trang bị đầy đủ tiện nghi cho kỳ nghỉ tại Sa Pa.", "22 sqm superior room, 2 single beds. Fully equipped for a comfortable stay in Sa Pa."))
					.areaSqm(22.0)
					.bedType(BedType.TWIN)
					.bedsCount(2)
					.bedLayoutDescription(of("2 giường đơn", "2 single beds"))
					.bedOptions(List.of(
								BedOption.builder().type(BedType.SINGLE).count(2).build()))
					.maxAdults(2)
					.maxChildren(0)
					.maxGuests(2)
					.totalRooms(5)
					.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680008890-0cb5065a3dbb4de9a2d365261e3b7cc2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680008890-cbd9a0e1e40e03a5162961918adb68d5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
				"COFFEE_TEA_MAKER"))
					.ratePlans(List.of(
							RatePlan.builder()
								.id("rt-spgate-superior-twin-prepaid-nonref")
								.name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
								.boardType(BoardType.ROOM_ONLY)
								.paymentType(PaymentType.PREPAID)
								.refundable(false)
								.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
								.pricePerNight(new BigDecimal("820000"))
								.referencePricePerNight(new BigDecimal("1171429"))
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
								.id("rt-spgate-superior-twin-breakfast-flex")
								.name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
								.boardType(BoardType.BREAKFAST_INCLUDED)
								.paymentType(PaymentType.PAY_AT_HOTEL)
								.refundable(true)
								.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
								.pricePerNight(new BigDecimal("943000"))
								.referencePricePerNight(new BigDecimal("1347143"))
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
					.id("rt-spgate-deluxe-double")
					.name(of("Deluxe Double", "Deluxe Double"))
					.shortDescription(of("Phòng Deluxe 26 m², 1 giường Queen.", "26 sqm deluxe room, 1 queen bed."))
					.description(of("Phòng Deluxe 26 m², 1 giường Queen. Trang bị đầy đủ tiện nghi cho kỳ nghỉ tại Sa Pa.", "26 sqm deluxe room, 1 queen bed. Fully equipped for a comfortable stay in Sa Pa."))
					.areaSqm(26.0)
					.bedType(BedType.QUEEN)
					.bedsCount(1)
					.bedLayoutDescription(of("1 giường Queen", "1 queen bed"))
					.bedOptions(List.of(
								BedOption.builder().type(BedType.QUEEN).count(1).build()))
					.maxAdults(2)
					.maxChildren(0)
					.maxGuests(2)
					.totalRooms(4)
					.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680008890-966e6e759ebd139069f3578bfad2fbe3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-6829ff5bb443d8d3a68c0ecd6b171162.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
				"SEATING_AREA"))
					.ratePlans(List.of(
							RatePlan.builder()
								.id("rt-spgate-deluxe-double-prepaid-nonref")
								.name(of("Deluxe Double - Không gồm bữa sáng", "Deluxe Double - Room only"))
								.boardType(BoardType.ROOM_ONLY)
								.paymentType(PaymentType.PREPAID)
								.refundable(false)
								.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
								.pricePerNight(new BigDecimal("980000"))
								.referencePricePerNight(new BigDecimal("1400000"))
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
								.id("rt-spgate-deluxe-double-breakfast-flex")
								.name(of("Deluxe Double - Bao gồm bữa sáng", "Deluxe Double - Breakfast included"))
								.boardType(BoardType.BREAKFAST_INCLUDED)
								.paymentType(PaymentType.PAY_AT_HOTEL)
								.refundable(true)
								.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
								.pricePerNight(new BigDecimal("1127000"))
								.referencePricePerNight(new BigDecimal("1610000"))
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
					.id("rt-spgate-deluxe-mountain-view")
					.name(of("Deluxe Mountain View", "Deluxe Mountain View"))
					.shortDescription(of("Phòng Deluxe 28 m² hướng núi, có ban công.", "28 sqm deluxe room with mountain view and balcony."))
					.description(of("Phòng Deluxe 28 m² hướng núi, có ban công. Trang bị đầy đủ tiện nghi cho kỳ nghỉ tại Sa Pa.", "28 sqm deluxe room with mountain view and balcony. Fully equipped for a comfortable stay in Sa Pa."))
					.areaSqm(28.0)
					.bedType(BedType.QUEEN)
					.bedsCount(1)
					.bedLayoutDescription(of("1 giường Queen", "1 queen bed"))
					.bedOptions(List.of(
								BedOption.builder().type(BedType.QUEEN).count(1).build()))
					.maxAdults(2)
					.maxChildren(0)
					.maxGuests(2)
					.totalRooms(3)
					.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-ad52725c7be3ad5ab7de20ce454464a7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-8c63bb26806cc604aa7069f14efa165e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
				"BALCONY",
				"SEATING_AREA"))
					.ratePlans(List.of(
							RatePlan.builder()
								.id("rt-spgate-deluxe-mountain-view-prepaid-nonref")
								.name(of("Deluxe Mountain View - Không gồm bữa sáng", "Deluxe Mountain View - Room only"))
								.boardType(BoardType.ROOM_ONLY)
								.paymentType(PaymentType.PREPAID)
								.refundable(false)
								.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
								.pricePerNight(new BigDecimal("1150000"))
								.referencePricePerNight(new BigDecimal("1642857"))
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
								.id("rt-spgate-deluxe-mountain-view-breakfast-flex")
								.name(of("Deluxe Mountain View - Bao gồm bữa sáng", "Deluxe Mountain View - Breakfast included"))
								.boardType(BoardType.BREAKFAST_INCLUDED)
								.paymentType(PaymentType.PAY_AT_HOTEL)
								.refundable(true)
								.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
								.pricePerNight(new BigDecimal("1322500"))
								.referencePricePerNight(new BigDecimal("1889286"))
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
					.id("rt-spgate-family-room")
					.name(of("Family Room", "Family Room"))
					.shortDescription(of("Phòng Gia đình 34 m², 1 giường Queen + 1 giường đơn, cho 3 khách.", "34 sqm family room, 1 queen + 1 single bed, for 3 guests."))
					.description(of("Phòng Gia đình 34 m², 1 giường Queen + 1 giường đơn, cho 3 khách. Trang bị đầy đủ tiện nghi cho kỳ nghỉ tại Sa Pa.", "34 sqm family room, 1 queen + 1 single bed, for 3 guests. Fully equipped for a comfortable stay in Sa Pa."))
					.areaSqm(34.0)
					.bedType(BedType.MULTIPLE)
					.bedsCount(2)
					.bedLayoutDescription(of("1 giường Queen và 1 giường đơn", "1 queen bed and 1 single bed"))
					.bedOptions(List.of(
								BedOption.builder().type(BedType.QUEEN).count(1).build(),
								BedOption.builder().type(BedType.SINGLE).count(1).build()))
					.maxAdults(3)
					.maxChildren(0)
					.maxGuests(3)
					.totalRooms(2)
					.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-9896bdd335f6b74611c3c255161a2755.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20075398-4ee807a06bff7065ac5ad38ce47a8ab5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
				"SEATING_AREA"))
					.ratePlans(List.of(
							RatePlan.builder()
								.id("rt-spgate-family-room-prepaid-nonref")
								.name(of("Family Room - Không gồm bữa sáng", "Family Room - Room only"))
								.boardType(BoardType.ROOM_ONLY)
								.paymentType(PaymentType.PREPAID)
								.refundable(false)
								.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
								.pricePerNight(new BigDecimal("1380000"))
								.referencePricePerNight(new BigDecimal("1971429"))
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
								.id("rt-spgate-family-room-breakfast-flex")
								.name(of("Family Room - Bao gồm bữa sáng", "Family Room - Breakfast included"))
								.boardType(BoardType.BREAKFAST_INCLUDED)
								.paymentType(PaymentType.PAY_AT_HOTEL)
								.refundable(true)
								.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
								.pricePerNight(new BigDecimal("1587000"))
								.referencePricePerNight(new BigDecimal("2267143"))
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
		nearby("nha-tho-da-sa-pa", of("Nhà thờ đá Sa Pa", "Sapa Stone Church"), of("Địa điểm lân cận", "Nearby places"), 300.0),
		nearby("quang-truong-sa-pa", of("Quảng trường Sa Pa", "Sapa Square"), of("Địa điểm lân cận", "Nearby places"), 350.0),
		nearby("ho-sa-pa", of("Hồ Sa Pa", "Sapa Lake"), of("Địa điểm lân cận", "Nearby places"), 500.0),
		nearby("nui-ham-rong", of("Núi Hàm Rồng", "Ham Rong Mountain"), of("Địa điểm lân cận", "Nearby places"), 800.0),
		nearby("cho-sa-pa", of("Chợ Sa Pa", "Sapa Market"), of("Địa điểm lân cận", "Nearby places"), 550.0),
		nearby("ban-cat-cat", of("Bản Cát Cát", "Cat Cat Village"), of("Địa điểm lân cận", "Nearby places"), 2500.0),
		nearby("thung-lung-muong-hoa", of("Thung lũng Mường Hoa", "Muong Hoa Valley"), of("Địa điểm lân cận", "Nearby places"), 6000.0),
		nearby("ban-ta-van", of("Bản Tả Van", "Ta Van Village"), of("Địa điểm lân cận", "Nearby places"), 8000.0),
		nearby("ban-ta-phin", of("Bản Tả Phìn", "Ta Phin Village"), of("Địa điểm lân cận", "Nearby places"), 11000.0),
		nearby("thac-bac", of("Thác Bạc", "Silver Waterfall"), of("Địa điểm lân cận", "Nearby places"), 12000.0),
		nearby("deo-o-quy-ho", of("Đèo Ô Quy Hồ", "O Quy Ho Pass"), of("Khác", "Other"), 12500.0),
		nearby("cap-treo-fansipan", of("Cáp treo Fansipan", "Fansipan Cable Car"), of("Trung tâm giải trí", "Entertainment hub"), 2200.0),
		nearby("dinh-fansipan", of("Đỉnh Fansipan", "Fansipan Peak"), of("Địa điểm lân cận", "Nearby places"), 9000.0),
		nearby("sun-world-fansipan-legend", of("Sun World Fansipan Legend", "Sun World Fansipan Legend"), of("Trung tâm giải trí", "Entertainment hub"), 2300.0),
		nearby("cong-troi-o-quy-ho", of("Cổng trời Ô Quy Hồ", "Heaven Gate"), of("Khác", "Other"), 12800.0));

		GeneralInfo generalInfo = GeneralInfo.builder()
				.mainFacilitiesSummary(of("Nhà hàng, máy lạnh, lễ tân 24h, chỗ đậu xe, WiFi miễn phí", "Restaurant, air conditioning, 24-hour front desk, parking, free WiFi"))
				.distanceToCityCenterKm(0.3)
				.popularAreaSummary(of("Trung tâm Sa Pa, Nhà thờ đá, Chợ Sa Pa", "Sa Pa center, Stone Church, Sa Pa Market"))
				.totalRooms(20)
				.totalFloors(3)
				.otherHighlightFacilities(of("Đưa đón sân bay, hỗ trợ đặt tour, thu đổi ngoại tệ, dịch vụ concierge.", "Airport transfer, tour booking assistance, currency exchange, concierge service."))
				.interestingPlacesSummary(of("Bản Cát Cát, Thung lũng Mường Hoa, Cáp treo Fansipan", "Cat Cat Village, Muong Hoa Valley, Fansipan Cable Car"))
				.build();

		HotelPolicy policy = HotelPolicy.builder()
				.checkInFrom(LocalTime.of(14, 0))
				.checkOutUntil(LocalTime.of(12, 0))
				.items(List.of(
						PolicyItem.builder().section(PolicySection.CHECKIN_CHECKOUT)
								.title(of("Thời gian nhận phòng/trả phòng", "Check-in/check-out time"))
								.content(of("Giờ nhận phòng: từ 14:00. Giờ trả phòng: trước 12:00.", "Check-in: from 14:00. Check-out: before 12:00."))
								.build(),
						PolicyItem.builder().section(PolicySection.REQUIRED_DOCUMENTS)
								.title(of("Giấy tờ yêu cầu", "Required documents"))
								.content(of("Vui lòng xuất trình CCCD/hộ chiếu hợp lệ khi nhận phòng.", "Please present a valid ID card/passport at check-in."))
								.build(),
						PolicyItem.builder().section(PolicySection.EARLY_CHECKIN)
								.title(of("Nhận phòng sớm", "Early check-in"))
								.content(of("Hỗ trợ nhận phòng sớm tùy tình trạng phòng trống, vui lòng liên hệ trước.", "Early check-in is subject to availability; please contact in advance."))
								.build(),
						PolicyItem.builder().section(PolicySection.LATE_CHECKOUT)
								.title(of("Trả phòng trễ", "Late check-out"))
								.content(of("Hỗ trợ trả phòng trễ tùy tình trạng phòng trống, vui lòng liên hệ trước.", "Late check-out is subject to availability; please contact in advance."))
								.build(),
						PolicyItem.builder().section(PolicySection.SMOKING)
								.title(of("Chính sách hút thuốc", "Smoking policy"))
								.content(of("Phòng không hút thuốc; có khu vực hút thuốc riêng.", "Non-smoking rooms; a designated smoking area is available."))
								.build(),
						PolicyItem.builder().section(PolicySection.AIRPORT_TRANSFER)
								.title(of("Đưa đón sân bay", "Airport transfer"))
								.content(of("Khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu (thu phí).", "The hotel offers airport transfer on request (surcharge)."))
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
		faq(of("Những tiện ích tại The Gate Boutique Hotel Sapa?", "What amenities does The Gate Boutique Hotel Sapa offer?"),
			of("Các tiện ích bao gồm nhà hàng, lễ tân 24h, chỗ đậu xe, máy lạnh và WiFi miễn phí (một số dịch vụ có thể thu thêm phí).", "Amenities include a restaurant, 24-hour front desk, parking, air conditioning and free WiFi (some services may require an additional fee).")),
		faq(of("Thời gian nhận phòng và trả phòng của The Gate Boutique Hotel Sapa?", "What are the check-in and check-out times at The Gate Boutique Hotel Sapa?"),
			of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
		faq(of("Khách sạn The Gate Boutique Hotel Sapa đạt tiêu chuẩn mấy sao?", "What star rating does The Gate Boutique Hotel Sapa have?"),
			of("The Gate Boutique Hotel Sapa là khách sạn đạt tiêu chuẩn 3 sao.", "The Gate Boutique Hotel Sapa is a 3-star hotel.")),
		faq(of("The Gate Boutique Hotel Sapa có bao nhiêu tầng và bao nhiêu phòng?", "How many floors and rooms does The Gate Boutique Hotel Sapa have?"),
			of("Khách sạn có tổng cộng 3 tầng với 20 phòng.", "The hotel has a total of 3 floors with 20 rooms.")),
		faq(of("The Gate Boutique Hotel Sapa có chỗ đậu xe không?", "Does The Gate Boutique Hotel Sapa have parking?"),
			of("Có, khách sạn có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với khách sạn để xác nhận thông tin chi tiết.", "Yes, the hotel provides parking. Please contact the hotel directly to confirm details.")),
		faq(of("The Gate Boutique Hotel Sapa có hỗ trợ nhận phòng sớm không?", "Does The Gate Boutique Hotel Sapa support early check-in?"),
			of("Có, khách sạn hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp để sắp xếp.", "Yes, the hotel supports early check-in subject to availability. Please contact the hotel directly to arrange.")),
		faq(of("The Gate Boutique Hotel Sapa có cung cấp dịch vụ trả phòng trễ không?", "Does The Gate Boutique Hotel Sapa offer late check-out?"),
			of("Có, khách sạn hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp để sắp xếp.", "Yes, the hotel supports late check-out subject to availability. Please contact the hotel directly to arrange.")),
		faq(of("The Gate Boutique Hotel Sapa có phục vụ ăn sáng không?", "Does The Gate Boutique Hotel Sapa serve breakfast?"),
			of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
		faq(of("The Gate Boutique Hotel Sapa có mức giá là bao nhiêu?", "How much does The Gate Boutique Hotel Sapa cost?"),
			of("Phòng tại khách sạn có giá từ khoảng 700.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 700,000 VND per night. Prices may vary depending on the date and promotions.")),
		faq(of("Khách sạn có gần trung tâm Sa Pa không?", "Is the hotel close to Sa Pa center?"),
			of("Có, khách sạn nằm trên đường Thạch Sơn ngay trung tâm thị xã, chỉ vài phút đi bộ đến Nhà thờ đá, Quảng trường và Chợ Sa Pa.", "Yes, the hotel is on Thach Son Street right in the town center, just a few minutes' walk from the Stone Church, the Square and Sa Pa Market.")));

		return HotelDoc.builder()
				.active(true)
				.destinationSlug("sa-pa")
				.cityName(of("Sa Pa", "Sa Pa"))
				.districtName(of("Sa Pa", "Sa Pa"))
				.wardName(of("Trung tâm Sa Pa", "Sa Pa Township"))
				.addressLine(of("02 Thạch Sơn, Trung tâm Sa Pa, Sa Pa, Tỉnh Lào Cai, Việt Nam, 330000", "2 Thach Son, Sa Pa Township, Sa Pa District, Lao Cai Province, Vietnam, 330000"))
				.location(new double[] { 103.8426508, 22.3356561 })
				.name(of("The Gate Boutique Hotel Sapa", "The Gate Boutique Hotel Sapa"))
				.slug(slug)
				.hotelType(HotelType.HOTEL)
				.starRating(3)
				.shortDescription(of("Khách sạn boutique 3 sao tại trung tâm thị xã Sa Pa, gần Nhà thờ đá và Chợ Sa Pa.", "3-star boutique hotel in the center of Sa Pa town, near the Stone Church and Sa Pa Market."))
				.description(of("The Gate Boutique Hotel Sapa là khách sạn boutique 3 sao ấm cúng trên đường Thạch Sơn, trung tâm Sa Pa, mang đến phòng nghỉ tiện nghi, nhà hàng và dịch vụ chu đáo cho kỳ nghỉ giữa vùng núi Tây Bắc.", "The Gate Boutique Hotel Sapa is a cozy 3-star boutique hotel on Thach Son Street in central Sa Pa, offering comfortable rooms, a restaurant and attentive service for a holiday in the northwestern mountains."))
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
