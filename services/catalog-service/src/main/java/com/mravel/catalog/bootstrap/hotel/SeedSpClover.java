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
public class SeedSpClover {

	@Bean
	CommandLineRunner seedSpCloverRunner(HotelDocRepository repo) {
		return args -> {
			System.out.println(">>> [SeedSpClover] start");
			HotelDoc doc = hotelSpClover();
			repo.findBySlugAndActiveTrue(doc.getSlug())
					.ifPresent(existing -> doc.setId(existing.getId()));
			repo.save(doc);
			System.out.println(">>> [SeedSpClover] upsert: " + doc.getSlug());
			System.out.println(">>> [SeedSpClover] done");
		};
	}

	// =====================================================================
	// SAPA CLOVER HOTEL (source: Traveloka hotelId 20006744; phòng được dựng thêm)
	// =====================================================================
	private static HotelDoc hotelSpClover() {
		String slug = "sapa-clover-hotel";

		List<Image> images = List.of(
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-3630ef5cc29ad5bca0e4b84989b3b66e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", true, 0),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-c37d1892b350fa2bcbd96069c3db9cb1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Khu vực khách sạn", "Hotel area", false, 1),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-7a357e24926dcfc19e36cbf49beb6caa.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 2),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-ad7abdfeb9ca678e02b972e9c4f5d030.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Nhà hàng", "Restaurant", false, 3),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-8a5ebbc3e3dd749057643306e8c15375.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 4),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-9d82be7eda489fb32b1b6b506cb58201.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Nhà hàng", "Restaurant", false, 5),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-88eafb188d1253575ecff335af89e35b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Nhà hàng", "Restaurant", false, 6),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-b59c95f0b310baf6e758252e4997316d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Sảnh", "Lobby", false, 7),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-efeaf9bb1fec5c541eca419890604a83.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Nhà hàng", "Restaurant", false, 8),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-849e4554a25f485b343fcabedd1ffdad.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Quầy bar/Cafe", "Bar & cafe", false, 9),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-c4bfa87e8b9f239ada3c95569159e41f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 10),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-f63fb3b163c48b4e28706ceeb7f9cd5b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 11),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-7d0c9c0e285b5628c388b50b5ce07186.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Khu vực khách sạn", "Hotel area", false, 12),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-96e0ff4c2ba3c1130cc3dd285d0b0c93.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 13),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-bff76e5d673b2948758d37edf9a45b99.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 14),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-fd57fc32468c0169b8f2519aedf23c95.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 15),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-adaa867bbefc2d20845cc06e9becfb95.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng tắm", "Bathroom", false, 16),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-51fdf064b4eb621699e32bf21506dd3d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 17),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-226dde0169461d9a7957d1e12a8f3e58.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 18),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-31e9f464c8f18e2bed2f0b38aea73c86.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 19),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-a6f3e0c76626b52e93770895d5edc83a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 20),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-f6c9f9d9d09bef647154e373fdab7158.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 21),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-f38dc73577ed663687c55a2b8efcb4c4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 22),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-3c02eae5f62b395c4e730e8ebb5b91bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 23),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-d092795bf9e5920bd27711ae08aa998b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 24),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-a03470651eeaf72271c6d634b2d05290.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 25),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-a345fb531f8d3e43cacb1fe6b847d6d4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 26),
		img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-9118108627776255dd5ca5ac06df306a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 27));

		List<ContentBlock> content = List.of(
				mapBlock(ContentSection.OVERVIEW, new double[] { 103.84934220000002, 22.3280155 }),

				heading(ContentSection.STORY, of("Giới thiệu chung về Sapa Clover Hotel", "About Sapa Clover Hotel")),
				paragraph(ContentSection.STORY,
						of("Sapa Clover Hotel là khách sạn 3 sao tọa lạc trên đường Mường Hoa, ngay trung tâm thị xã Sa Pa, Lào Cai. Với 13 phòng ấm cúng trải trên 6 tầng, khách sạn mang đến không gian nghỉ ngơi yên tĩnh giữa vùng núi Tây Bắc, khí hậu mát mẻ quanh năm và rất gần các điểm tham quan nổi tiếng của Sa Pa.", "Sapa Clover Hotel is a 3-star hotel located on Muong Hoa Street, right in the center of Sa Pa town, Lao Cai. With 13 cozy rooms across 6 floors, the hotel offers a peaceful retreat amid the northwestern mountains, a cool climate year-round and easy access to Sa Pa's most famous attractions.")),

				heading(ContentSection.STORY, of("Các loại phòng nghỉ", "Room types")),
				paragraph(ContentSection.STORY,
						of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến phòng Gia đình và phòng hướng núi. Mỗi phòng được trang bị máy lạnh, minibar, máy sấy tóc, két an toàn, nước suối miễn phí và Wi-Fi miễn phí, đảm bảo sự thoải mái cho du khách sau một ngày khám phá vùng cao.", "The hotel offers a range of room categories from Standard, Superior and Deluxe to Family and Mountain View rooms. Each room is equipped with air conditioning, a minibar, a hair dryer, a safe, complimentary bottled water and free Wi-Fi, ensuring comfort after a day exploring the highlands.")),

				heading(ContentSection.STORY, of("Ẩm thực", "Dining")),
				paragraph(ContentSection.STORY,
						of("Nhà hàng của khách sạn phục vụ bữa sáng cùng các món ăn đặc trưng của vùng núi Tây Bắc. Du khách có thể thưởng thức cà phê và đồ uống nhẹ tại quầy bar và khu vực cà phê tại sảnh trong không gian ấm áp nhìn ra phố núi.", "The hotel restaurant serves breakfast along with specialties of the northwestern highlands. Guests can enjoy coffee and light drinks at the bar and the lobby cafe area in a warm setting overlooking the mountain town.")),

				heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),
				paragraph(ContentSection.STORY,
						of("Khách sạn có lễ tân 24 giờ, dịch vụ phòng, dịch vụ giặt ủi, giữ hành lý, thu đổi ngoại tệ, hỗ trợ đặt tour và nhân viên đa ngôn ngữ. Thang máy và bảo vệ 24 giờ mang đến sự tiện lợi và an tâm cho du khách trong suốt kỳ nghỉ.", "The hotel features a 24-hour front desk, room service, laundry, luggage storage, currency exchange, tour booking assistance and multilingual staff. An elevator and 24-hour security provide convenience and peace of mind throughout the stay.")),

				heading(ContentSection.STORY, of("Vị trí & cảnh quan núi", "Location & mountain scenery")),
				paragraph(ContentSection.STORY,
						of("Nằm trên đường Mường Hoa giữa trung tâm Sa Pa, khách sạn chỉ cách Nhà thờ đá, Quảng trường và Chợ Sa Pa vài phút đi bộ. Từ đây, du khách dễ dàng chiêm ngưỡng khung cảnh núi non trùng điệp, ruộng bậc thang Mường Hoa và đỉnh Fansipan hùng vĩ.", "Located on Muong Hoa Street in the heart of Sa Pa, the hotel is just a few minutes' walk from the Stone Church, the Square and Sa Pa Market. From here, guests can easily admire the rolling mountains, the Muong Hoa terraced fields and the majestic Fansipan peak.")),

				heading(ContentSection.STORY, of("Di chuyển", "Getting around")),
				paragraph(ContentSection.STORY,
						of("Khách sạn hỗ trợ dịch vụ đưa đón sân bay và đón khách tại ga tàu (thu phí), cho thuê xe và đưa đón hạng sang. Ga cáp treo Fansipan và các bản làng vùng cao đều nằm trong tầm di chuyển thuận tiện bằng xe.", "The hotel offers airport transfer and train station pickup (surcharge), car hire and limousine service. The Fansipan cable car station and highland villages are all within convenient driving distance.")),

				heading(ContentSection.STORY, of("Khu vực xung quanh & bản làng", "Surroundings & villages")),
				paragraph(ContentSection.STORY,
						of("Xung quanh khách sạn là những bản làng dân tộc đặc sắc như Cát Cát, Tả Van và Tả Phìn, nơi du khách có thể tìm hiểu văn hóa người H'Mông, Dao đỏ và mua các sản phẩm thổ cẩm thủ công truyền thống.", "Around the hotel are distinctive ethnic villages such as Cat Cat, Ta Van and Ta Phin, where guests can learn about the culture of the H'Mong and Red Dao people and buy traditional handmade brocade products.")),

				heading(ContentSection.STORY, of("Vì sao nên chọn Sapa Clover Hotel", "Why choose Sapa Clover Hotel")),
				paragraph(ContentSection.STORY,
						of("Chọn Sapa Clover Hotel, du khách có được vị trí trung tâm thuận tiện, phòng nghỉ ấm cúng, dịch vụ chu đáo và mức giá hợp lý cho kỳ nghỉ tại Sa Pa. Đây là điểm lưu trú lý tưởng để khám phá phố núi, ruộng bậc thang và chinh phục đỉnh Fansipan.", "Choosing Sapa Clover Hotel gives travelers a convenient central location, cozy rooms, attentive service and reasonable rates for a Sa Pa holiday. It is an ideal base to explore the mountain town, terraced fields and conquer the Fansipan peak.")));

		List<AmenityCatalogDoc> hotelAmenities = List.of(
		amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
		amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
		amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
		amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
		amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
		amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
		amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
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
		amenity("shop", of("Cửa hàng", "Shop"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
		amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
		amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
		amenity("smoking_area", of("Khu vực hút thuốc", "Smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
		amenity("express_checkin", of("Nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("express_checkout", of("Trả phòng cấp tốc", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false));

		List<RoomType> roomTypes = List.of(
				RoomType.builder()
					.id("rt-spclover-standard-double")
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
					.totalRooms(3)
					.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-3630ef5cc29ad5bca0e4b84989b3b66e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-7a357e24926dcfc19e36cbf49beb6caa.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
								.id("rt-spclover-standard-double-prepaid-nonref")
								.name(of("Standard Double - Không gồm bữa sáng", "Standard Double - Room only"))
								.boardType(BoardType.ROOM_ONLY)
								.paymentType(PaymentType.PREPAID)
								.refundable(false)
								.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
								.pricePerNight(new BigDecimal("600000"))
								.referencePricePerNight(new BigDecimal("857143"))
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
								.id("rt-spclover-standard-double-breakfast-flex")
								.name(of("Standard Double - Bao gồm bữa sáng", "Standard Double - Breakfast included"))
								.boardType(BoardType.BREAKFAST_INCLUDED)
								.paymentType(PaymentType.PAY_AT_HOTEL)
								.refundable(true)
								.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
								.pricePerNight(new BigDecimal("690000"))
								.referencePricePerNight(new BigDecimal("985714"))
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
					.id("rt-spclover-superior-twin")
					.name(of("Superior Twin", "Superior Twin"))
					.shortDescription(of("Phòng Superior 22 m², 2 giường đơn.", "22 sqm superior room, 2 single beds."))
					.description(of("Phòng Superior 22 m², 2 giường đơn. Trang bị đầy đủ tiện nghi cho kỳ nghỉ tại Sa Pa.", "22 sqm superior room, 2 single beds. Fully equipped for a comfortable stay in Sa Pa."))
					.areaSqm(22.0)
					.bedType(BedType.TWIN)
					.bedsCount(1)
					.bedLayoutDescription(of("1 giường đơn", "1 single beds"))
					.bedOptions(List.of(
							BedOption.builder().type(BedType.TWIN).count(1).build()))
					.maxAdults(2)
					.maxChildren(0)
					.maxGuests(2)
					.totalRooms(3)
					.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-8a5ebbc3e3dd749057643306e8c15375.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-c4bfa87e8b9f239ada3c95569159e41f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
								.id("rt-spclover-superior-twin-prepaid-nonref")
								.name(of("Superior Twin - Không gồm bữa sáng", "Superior Twin - Room only"))
								.boardType(BoardType.ROOM_ONLY)
								.paymentType(PaymentType.PREPAID)
								.refundable(false)
								.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
								.pricePerNight(new BigDecimal("750000"))
								.referencePricePerNight(new BigDecimal("1071429"))
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
								.id("rt-spclover-superior-twin-breakfast-flex")
								.name(of("Superior Twin - Bao gồm bữa sáng", "Superior Twin - Breakfast included"))
								.boardType(BoardType.BREAKFAST_INCLUDED)
								.paymentType(PaymentType.PAY_AT_HOTEL)
								.refundable(true)
								.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
								.pricePerNight(new BigDecimal("862500"))
								.referencePricePerNight(new BigDecimal("1232143"))
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
					.id("rt-spclover-deluxe-double")
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
					.totalRooms(3)
					.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-f63fb3b163c48b4e28706ceeb7f9cd5b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-96e0ff4c2ba3c1130cc3dd285d0b0c93.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
				"SEATING_AREA"))
					.ratePlans(List.of(
							RatePlan.builder()
								.id("rt-spclover-deluxe-double-prepaid-nonref")
								.name(of("Deluxe Double - Không gồm bữa sáng", "Deluxe Double - Room only"))
								.boardType(BoardType.ROOM_ONLY)
								.paymentType(PaymentType.PREPAID)
								.refundable(false)
								.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
								.pricePerNight(new BigDecimal("900000"))
								.referencePricePerNight(new BigDecimal("1285714"))
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
								.id("rt-spclover-deluxe-double-breakfast-flex")
								.name(of("Deluxe Double - Bao gồm bữa sáng", "Deluxe Double - Breakfast included"))
								.boardType(BoardType.BREAKFAST_INCLUDED)
								.paymentType(PaymentType.PAY_AT_HOTEL)
								.refundable(true)
								.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
								.pricePerNight(new BigDecimal("1035000"))
								.referencePricePerNight(new BigDecimal("1478571"))
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
					.id("rt-spclover-deluxe-mountain-view")
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
					.totalRooms(2)
					.images(List.of(
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-bff76e5d673b2948758d37edf9a45b99.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-fd57fc32468c0169b8f2519aedf23c95.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
								.id("rt-spclover-deluxe-mountain-view-prepaid-nonref")
								.name(of("Deluxe Mountain View - Không gồm bữa sáng", "Deluxe Mountain View - Room only"))
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
								.id("rt-spclover-deluxe-mountain-view-breakfast-flex")
								.name(of("Deluxe Mountain View - Bao gồm bữa sáng", "Deluxe Mountain View - Breakfast included"))
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
					.id("rt-spclover-family-room")
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
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-51fdf064b4eb621699e32bf21506dd3d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
						img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20006744-226dde0169461d9a7957d1e12a8f3e58.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
				"SEATING_AREA"))
					.ratePlans(List.of(
							RatePlan.builder()
								.id("rt-spclover-family-room-prepaid-nonref")
								.name(of("Family Room - Không gồm bữa sáng", "Family Room - Room only"))
								.boardType(BoardType.ROOM_ONLY)
								.paymentType(PaymentType.PREPAID)
								.refundable(false)
								.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
								.pricePerNight(new BigDecimal("1250000"))
								.referencePricePerNight(new BigDecimal("1785714"))
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
								.id("rt-spclover-family-room-breakfast-flex")
								.name(of("Family Room - Bao gồm bữa sáng", "Family Room - Breakfast included"))
								.boardType(BoardType.BREAKFAST_INCLUDED)
								.paymentType(PaymentType.PAY_AT_HOTEL)
								.refundable(true)
								.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
								.pricePerNight(new BigDecimal("1437500"))
								.referencePricePerNight(new BigDecimal("2053571"))
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
		nearby("cho-sa-pa", of("Chợ Sa Pa", "Sapa Market"), of("Địa điểm lân cận", "Nearby places"), 600.0),
		nearby("nui-ham-rong", of("Núi Hàm Rồng", "Ham Rong Mountain"), of("Địa điểm lân cận", "Nearby places"), 900.0),
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
				.mainFacilitiesSummary(of("Nhà hàng, quầy bar, máy lạnh, lễ tân 24h, chỗ đậu xe, WiFi miễn phí", "Restaurant, bar, air conditioning, 24-hour front desk, parking, free WiFi"))
				.distanceToCityCenterKm(0.3)
				.popularAreaSummary(of("Trung tâm Sa Pa, Nhà thờ đá, Chợ Sa Pa", "Sa Pa center, Stone Church, Sa Pa Market"))
				.totalRooms(13)
				.totalFloors(6)
				.otherHighlightFacilities(of("Đưa đón sân bay, đón khách tại ga tàu, hỗ trợ đặt tour, thu đổi ngoại tệ.", "Airport transfer, train station pickup, tour booking assistance, currency exchange."))
				.interestingPlacesSummary(of("Bản Cát Cát, Thung lũng Mường Hoa, Cáp treo Fansipan", "Cat Cat Village, Muong Hoa Valley, Fansipan Cable Car"))
				.build();

		HotelPolicy policy = HotelPolicy.builder()
				.checkInFrom(LocalTime.of(12, 0))
				.checkOutUntil(LocalTime.of(11, 0))
				.items(List.of(
						PolicyItem.builder().section(PolicySection.CHECKIN_CHECKOUT)
								.title(of("Thời gian nhận phòng/trả phòng", "Check-in/check-out time"))
								.content(of("Giờ nhận phòng: từ 12:00. Giờ trả phòng: trước 11:00.", "Check-in: from 12:00. Check-out: before 11:00."))
								.build(),
						PolicyItem.builder().section(PolicySection.REQUIRED_DOCUMENTS)
								.title(of("Giấy tờ yêu cầu", "Required documents"))
								.content(of("Vui lòng xuất trình CCCD/hộ chiếu hợp lệ khi nhận phòng.", "Please present a valid ID card/passport at check-in."))
								.build(),
						PolicyItem.builder().section(PolicySection.EARLY_CHECKIN)
								.title(of("Nhận phòng sớm", "Early check-in"))
								.content(of("Hỗ trợ nhận phòng sớm tùy tình trạng phòng trống, vui lòng liên hệ trước.", "Early check-in is subject to availability; please contact in advance."))
								.build(),
						PolicyItem.builder().section(PolicySection.SMOKING)
								.title(of("Chính sách hút thuốc", "Smoking policy"))
								.content(of("Phòng không hút thuốc; có khu vực hút thuốc riêng.", "Non-smoking rooms; a designated smoking area is available."))
								.build(),
						PolicyItem.builder().section(PolicySection.AIRPORT_TRANSFER)
								.title(of("Đưa đón sân bay", "Airport transfer"))
								.content(of("Khách sạn cung cấp dịch vụ đưa đón sân bay và đón khách tại ga tàu theo yêu cầu (thu phí).", "The hotel offers airport transfer and train station pickup on request (surcharge)."))
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
		faq(of("Những tiện ích tại Sapa Clover Hotel?", "What amenities does Sapa Clover Hotel offer?"),
			of("Các tiện ích bao gồm máy lạnh, nhà hàng, lễ tân 24h, chỗ đậu xe, thang máy và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, restaurant, 24-hour front desk, parking, elevator and WiFi (some services may require an additional fee).")),
		faq(of("Thời gian nhận phòng và trả phòng của Sapa Clover Hotel?", "What are the check-in and check-out times at Sapa Clover Hotel?"),
			of("Thời gian nhận phòng từ 12:00 và trả phòng trước 11:00.", "Check-in is from 12:00 and check-out is before 11:00.")),
		faq(of("Sapa Clover Hotel có phục vụ ăn sáng không?", "Does Sapa Clover Hotel serve breakfast?"),
			of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
		faq(of("Khách sạn Sapa Clover Hotel đạt tiêu chuẩn mấy sao?", "What star rating does Sapa Clover Hotel have?"),
			of("Sapa Clover Hotel là khách sạn đạt tiêu chuẩn 3 sao.", "Sapa Clover Hotel is a 3-star hotel.")),
		faq(of("Sapa Clover Hotel có bao nhiêu tầng và bao nhiêu phòng?", "How many floors and rooms does Sapa Clover Hotel have?"),
			of("Khách sạn có tổng cộng 6 tầng với 13 phòng.", "The hotel has a total of 6 floors with 13 rooms.")),
		faq(of("Sapa Clover Hotel có chỗ đậu xe không?", "Does Sapa Clover Hotel have parking?"),
			of("Có, khách sạn có cung cấp chỗ đậu xe. Vui lòng liên hệ trực tiếp với khách sạn để xác nhận thông tin chi tiết.", "Yes, the hotel provides parking. Please contact the hotel directly to confirm details.")),
		faq(of("Sapa Clover Hotel có dịch vụ đưa đón sân bay không?", "Does Sapa Clover Hotel offer airport transfer?"),
			of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu (thu phí). Vui lòng liên hệ trực tiếp để biết thêm chi tiết.", "Yes, the hotel offers airport transfer on request (surcharge). Please contact the hotel directly for more details.")),
		faq(of("Sapa Clover Hotel có mức giá là bao nhiêu?", "How much does Sapa Clover Hotel cost?"),
			of("Phòng tại khách sạn có giá từ khoảng 600.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 600,000 VND per night. Prices may vary depending on the date and promotions.")),
		faq(of("Khách sạn có gần trung tâm Sa Pa không?", "Is the hotel close to Sa Pa center?"),
			of("Có, khách sạn nằm trên đường Mường Hoa ngay trung tâm thị xã, chỉ vài phút đi bộ đến Nhà thờ đá, Quảng trường và Chợ Sa Pa.", "Yes, the hotel is on Muong Hoa Street right in the town center, just a few minutes' walk from the Stone Church, the Square and Sa Pa Market.")),
		faq(of("Từ khách sạn đến ga cáp treo Fansipan có xa không?", "How far is the hotel from the Fansipan cable car station?"),
			of("Ga cáp treo Fansipan cách khách sạn khoảng 2 km, có thể di chuyển bằng xe trong vài phút.", "The Fansipan cable car station is about 2 km from the hotel, a few minutes away by car.")));

		return HotelDoc.builder()
				.active(true)
				.destinationSlug("sa-pa")
				.cityName(of("Sa Pa", "Sa Pa"))
				.districtName(of("Sa Pa", "Sa Pa"))
				.wardName(of("Trung tâm Sa Pa", "Sa Pa Township"))
				.addressLine(of("25 Mường Hoa, Trung tâm Sa Pa, Sa Pa, Tỉnh Lào Cai, Việt Nam, 330000", "25 Muong Hoa Street, Sa Pa Township, Sa Pa District, Lao Cai Province, Vietnam, 330000"))
				.location(new double[] { 103.84934220000002, 22.3280155 })
				.name(of("Sapa Clover Hotel", "Sapa Clover Hotel"))
				.slug(slug)
				.hotelType(HotelType.HOTEL)
				.starRating(3)
				.shortDescription(of("Khách sạn 3 sao tại trung tâm thị xã Sa Pa, gần Nhà thờ đá và Chợ Sa Pa.", "3-star hotel in the center of Sa Pa town, near the Stone Church and Sa Pa Market."))
				.description(of("Sapa Clover Hotel là khách sạn 3 sao ấm cúng trên đường Mường Hoa, trung tâm Sa Pa, mang đến phòng nghỉ tiện nghi, nhà hàng, quầy bar và dịch vụ chu đáo cho kỳ nghỉ giữa vùng núi Tây Bắc.", "Sapa Clover Hotel is a cozy 3-star hotel on Muong Hoa Street in central Sa Pa, offering comfortable rooms, a restaurant, a bar and attentive service for a holiday in the northwestern mountains."))
				.defaultCheckInTime(LocalTime.of(12, 0))
				.defaultCheckOutTime(LocalTime.of(11, 0))
				.hasOnlineCheckin(false)
				.minNightlyPrice(new BigDecimal("600000"))
				.currencyCode("VND")
				.referenceNightlyPrice(new BigDecimal("857143"))
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
