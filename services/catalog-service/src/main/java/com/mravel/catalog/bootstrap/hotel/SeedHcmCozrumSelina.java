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
public class SeedHcmCozrumSelina {

	@Bean
	CommandLineRunner seedHcmCozrumSelinaRunner(HotelDocRepository repo) {
		return args -> {
			System.out.println(">>> [SeedHcmCozrumSelina] start");
			HotelDoc doc = hotelCozrumSelina();
			repo.findBySlugAndActiveTrue(doc.getSlug())
					.ifPresent(existing -> doc.setId(existing.getId()));
			repo.save(doc);
			System.out.println(">>> [SeedHcmCozrumSelina] upsert: " + doc.getSlug());
			System.out.println(">>> [SeedHcmCozrumSelina] done");
		};
	}

	// =====================================================================
	// COZRUM HOMES - SELINA RESIDENCE (HCMC, Binh Thanh)
	// (source: Traveloka hotelId 9000003257144; rooms fabricated)
	// =====================================================================
	private static HotelDoc hotelCozrumSelina() {
		String slug = "cozrum-homes-selina-residence";

		List<Image> images = List.of(
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-a7447a6618a465100d6c0d8cab3670c9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", true, 0),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20076628-398fd9dd59540fb0c8bfed348ad775e2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Cảnh quan", "View", false, 1),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-3f47dcf5b352d8dbad0ca704c5414a9d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 2),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-a9bcf30e55f8e260f58bf19061b4fec2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 3),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-358cae9642026eb1f4cb3ab12ca65ec7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 4),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-bb763c9722affca3866f39c56a5b68d2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 5),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20076628-a120d41c105dc633593098a94a7beccc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Cảnh quan", "View", false, 6),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20076628-345dc0931db81df595e47c61549e9308.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Cảnh quan", "View", false, 7),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-ef272de4515ec2f73beedf898394b0bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 8),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-7ac381f5cdcb4352ecd106c6155e1296.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 9),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20076628-2c62568a63c41226f2536120f0de4766.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Cảnh quan", "View", false, 10),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-cd77478e78c90ebfe75a741d1ecfd0f7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 11),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-18b328c817f7febf7fbeaa2884cf2a88.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 12),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20076628-d611272b4b471fefdd801c50e8e54e1c.png?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Cảnh quan", "View", false, 13),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-2024c7f5261736b2dba50135cdb283b6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 14),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-28d0c80a24c1c7167aba3c6906f62870.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 15),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-a8d5891e305d6b13cd8be5f693fcb269.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 16),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-38d1ad706a84f71a2a7cad93774a2478.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 17),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-e8fe8871f95daf2d212710642df946dd.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 18),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-a9605b97293ad6495e2beeebb1f5841f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 19),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-a839e742023f7529fa7ceb5ec1afc78f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 20),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-a00f421d6608526710b4073b7c8de3d1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 21),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-267d6a9bb493fad8648e767232ce0b16.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 22),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-0f91c42a76a722b9104c0f6f764190fa.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 23),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-fd0707cb708f738ccc018a623787a736.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 24),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-ceb8b20176d3733d9ca80ff285f30327.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 25),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-fe7ee9b646dc267e59a9494a6ec88715.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 26),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-300daa45e2935258f12f525a6dd37930.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 27),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-4b68cb81bb467dc3d8230b7797c1f882.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Sảnh", "Lobby", false, 28),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-4ddc973a46a553e1e1be1a056cef01f8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Ngoại thất", "Exterior", false, 29),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-f451d7bd56a725da551d1229c67183b6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Ngoại thất", "Exterior", false, 30),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-8370ad766758263df99a066a8c2ebe27.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 31),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-d6477caf3db61b3cde7b378e83e63fd5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Ngoại thất", "Exterior", false, 32),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-988c5f2308c16a55993c0b123df1a801.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Khu vực khách sạn", "Hotel area", false, 33),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-2c225f32a6bee1527d25a5d1c8c0bfb5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng tắm", "Bathroom", false, 34),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20076628-2e1cc2f0c2ce017a1cbf7440f0404822.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Cảnh quan", "View", false, 35),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20076628-7862528aa428a47b3b7fd31205ad7990.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Cảnh quan", "View", false, 36),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20076628-27149277717291cbc61448cadab6f1db.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Cảnh quan", "View", false, 37),
		img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-ec375bd3889efb714c8f021b1b1d1a6b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
			"Phòng ngủ", "Bedroom", false, 38));

		List<ContentBlock> content = List.of(
				mapBlock(ContentSection.OVERVIEW, new double[] { 106.69855892658234, 10.798403326161518 }),

				heading(ContentSection.STORY, of("Giới thiệu chung về Cozrum Homes - Selina Residence", "About Cozrum Homes - Selina Residence")),
				paragraph(ContentSection.STORY,
						of("Cozrum Homes - Selina Residence là tòa căn hộ dịch vụ 2 sao toạ lạc tại 182 Diên Hồng, Phường 1, Quận Bình Thạnh, Thành phố Hồ Chí Minh. Với 20 căn hộ bố trí trên 4 tầng, nơi đây mang đến không gian lưu trú hiện đại, tiện nghi và gần trung tâm, phù hợp cho cả khách công tác lẫn khách du lịch dài ngày.", "Cozrum Homes - Selina Residence is a 2-star serviced-apartment building at 182 Dien Hong, Ward 1, Binh Thanh District, Ho Chi Minh City. With 20 apartments across 4 floors, it offers modern, well-equipped accommodation close to the center, suitable for both business travelers and long-stay tourists.")),

				heading(ContentSection.STORY, of("Các loại phòng căn hộ", "Apartment room types")),
				paragraph(ContentSection.STORY,
						of("Cozrum Homes cung cấp các hạng căn hộ từ Studio đến 1 phòng ngủ và 2 phòng ngủ. Mỗi căn đều được trang bị máy lạnh, bếp mini, tủ lạnh, lò vi sóng, phòng tắm vòi sen và Wi-Fi miễn phí, phù hợp cho cả cá nhân lẫn gia đình nhỏ.", "Cozrum Homes offers apartment categories from Studio to one-bedroom and two-bedroom units. Each unit is equipped with air conditioning, a kitchenette, a refrigerator, a microwave, a shower bathroom and free Wi-Fi, suitable for both individuals and small families.")),

				heading(ContentSection.STORY, of("Ẩm thực & bếp riêng", "Dining & private kitchen")),
				paragraph(ContentSection.STORY,
						of("Mỗi căn hộ đều có bếp mini đầy đủ dụng cụ giúp du khách tự chuẩn bị bữa ăn. Xung quanh khu vực có nhiều quán cà phê, nhà hàng và siêu thị tiện lợi, giúp việc ăn uống trở nên dễ dàng bất kể ngày hay đêm.", "Each apartment has a fully equipped kitchenette so guests can prepare their own meals. The surrounding area has many cafes, restaurants and convenience supermarkets, making dining easy any time of day or night.")),

				heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),
				paragraph(ContentSection.STORY,
						of("Khách sạn có lễ tân 24 giờ, thang máy, bãi đậu xe an ninh, dịch vụ giữ hành lý và nhận/trả phòng cấp tốc. Wi-Fi miễn phí phủ sóng toàn bộ tòa nhà, hỗ trợ tốt cho khách làm việc từ xa.", "The property features a 24-hour front desk, an elevator, secure parking, luggage storage and express check-in/check-out. Free Wi-Fi covers the entire building, supporting guests who work remotely.")),

				heading(ContentSection.STORY, of("Vị trí & điểm tham quan lân cận", "Location & nearby attractions")),
				paragraph(ContentSection.STORY,
						of("Nằm tại Quận Bình Thạnh, khách sạn chỉ cách trung tâm Quận 1 khoảng 4-5 km. Du khách dễ dàng di chuyển đến Landmark 81, Thảo Cầm Viên, chợ Bến Thành và các điểm tham quan nổi tiếng khác của thành phố.", "Located in Binh Thanh District, the property is only about 4-5 km from the District 1 center. Guests can easily reach Landmark 81, the Saigon Zoo, Ben Thanh Market and other famous city attractions.")),

				heading(ContentSection.STORY, of("Di chuyển & sân bay", "Getting around & airport")),
				paragraph(ContentSection.STORY,
						of("Sân bay quốc tế Tân Sơn Nhất cách khách sạn khoảng 7 km, tương đương 20-25 phút lái xe. Khu vực có nhiều tuyến xe buýt và dịch vụ đặt xe công nghệ, thuận tiện cho việc đi lại trong thành phố.", "Tan Son Nhat International Airport is about 7 km from the property, roughly a 20-25 minute drive. The area is served by many bus routes and ride-hailing services, convenient for getting around the city.")),

				heading(ContentSection.STORY, of("Khu vực xung quanh", "The surrounding area")),
				paragraph(ContentSection.STORY,
						of("Bình Thạnh là khu vực sôi động với nhiều quán cà phê, cửa hàng tiện lợi, chợ và trung tâm thương mại. Đây là nơi lý tưởng để trải nghiệm nhịp sống địa phương và ẩm thực đường phố Sài Gòn.", "Binh Thanh is a lively area with many cafes, convenience stores, markets and shopping centers. It is an ideal base to experience local life and Saigon street food.")),

				heading(ContentSection.STORY, of("Vì sao nên chọn Cozrum Homes - Selina Residence", "Why choose Cozrum Homes - Selina Residence")),
				paragraph(ContentSection.STORY,
						of("Chọn Cozrum Homes - Selina Residence, du khách có được không gian căn hộ riêng tư với bếp mini, vị trí gần trung tâm và mức giá hợp lý. Đây là lựa chọn phù hợp cho cả kỳ nghỉ ngắn ngày lẫn lưu trú dài ngày tại Thành phố Hồ Chí Minh.", "Choosing Cozrum Homes - Selina Residence gives guests a private apartment space with a kitchenette, a central location and reasonable prices. It is a great option for both short getaways and long stays in Ho Chi Minh City.")));

		List<AmenityCatalogDoc> hotelAmenities = List.of(
		amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
		amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
		amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
		amenity("parking", of("Bãi đậu xe an ninh", "Secure parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, true),
		amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, true),
		amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, false),
		amenity("express_checkin", of("Nhận phòng cấp tốc", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("express_checkout", of("Trả phòng cấp tốc", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("hair_salon", of("Hiệu làm tóc", "Hair salon"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
		amenity("supermarket", of("Cửa hàng thực phẩm", "Grocery"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
		amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
		amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
		amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("free_motorbike_parking", of("Đậu xe máy miễn phí", "Free motorbike parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.TRANSPORT, false),
		amenity("free_car_parking", of("Đậu xe ô tô miễn phí", "Free car parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.TRANSPORT, false),
		amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
		amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
		amenity("shop", of("Cửa hàng tiện lợi", "Convenience shop"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
		amenity("wifi", of("WiFi tại khu vực chung", "WiFi in public areas"), AmenityGroup.INTERNET, AmenitySection.INTERNET, false),
		amenity("non_smoking", of("Khu vực không hút thuốc", "Non-smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

		List<RoomType> roomTypes = List.of(
		RoomType.builder()
			.id("rt-cozrum-selina-studio")
			.name(of("Studio", "Studio"))
			.shortDescription(of("Căn Studio 25 m², 1 giường đôi, bếp mini.", "25 sqm studio, 1 double bed, kitchenette."))
			.description(of("Căn Studio 25 m², 1 giường đôi, bếp mini. Được trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "25 sqm studio, 1 double bed, kitchenette. Fully equipped with amenities for a comfortable stay."))
			.areaSqm(25.0)
			.bedType(BedType.DOUBLE)
			.bedsCount(1)
			.bedLayoutDescription(of("1 giường đôi", "1 double bed"))
			.bedOptions(List.of(
						BedOption.builder().type(BedType.DOUBLE).count(1).build()))
			.maxAdults(2)
			.maxChildren(0)
			.maxGuests(2)
			.totalRooms(20)
			.images(List.of(
				img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-a7447a6618a465100d6c0d8cab3670c9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
				img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-a9bcf30e55f8e260f58bf19061b4fec2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
								"SEATING_AREA",
								"BLACKOUT_CURTAINS"))
			.ratePlans(List.of(
				RatePlan.builder()
					.id("rt-cozrum-selina-studio-prepaid-nonref")
					.name(of("Studio - Không gồm bữa sáng", "Studio - Room only"))
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
					.id("rt-cozrum-selina-studio-breakfast-flex")
					.name(of("Studio - Bao gồm bữa sáng", "Studio - Breakfast included"))
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
			.id("rt-cozrum-selina-studio-twin")
			.name(of("Studio Twin", "Studio Twin"))
			.shortDescription(of("Căn Studio 28 m², 2 giường đơn, bếp mini.", "28 sqm studio, 2 single beds, kitchenette."))
			.description(of("Căn Studio 28 m², 2 giường đơn, bếp mini. Được trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "28 sqm studio, 2 single beds, kitchenette. Fully equipped with amenities for a comfortable stay."))
			.areaSqm(28.0)
			.bedType(BedType.TWIN)
			.bedsCount(1)
			.bedLayoutDescription(of("2 giường đơn", "2 single beds"))
			.bedOptions(List.of(
						BedOption.builder().type(BedType.SINGLE).count(2).build()))
			.maxAdults(2)
			.maxChildren(0)
			.maxGuests(2)
			.totalRooms(20)
			.images(List.of(
				img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-bb763c9722affca3866f39c56a5b68d2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
				img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-ef272de4515ec2f73beedf898394b0bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
								"SEATING_AREA",
								"BLACKOUT_CURTAINS"))
			.ratePlans(List.of(
				RatePlan.builder()
					.id("rt-cozrum-selina-studio-twin-prepaid-nonref")
					.name(of("Studio Twin - Không gồm bữa sáng", "Studio Twin - Room only"))
					.boardType(BoardType.ROOM_ONLY)
					.paymentType(PaymentType.PREPAID)
					.refundable(false)
					.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
					.pricePerNight(new BigDecimal("680000"))
					.referencePricePerNight(new BigDecimal("971429"))
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
					.id("rt-cozrum-selina-studio-twin-breakfast-flex")
					.name(of("Studio Twin - Bao gồm bữa sáng", "Studio Twin - Breakfast included"))
					.boardType(BoardType.BREAKFAST_INCLUDED)
					.paymentType(PaymentType.PAY_AT_HOTEL)
					.refundable(true)
					.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
					.pricePerNight(new BigDecimal("782000"))
					.referencePricePerNight(new BigDecimal("1117143"))
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
			.id("rt-cozrum-selina-one-bedroom")
			.name(of("Căn hộ 1 Phòng ngủ", "One-Bedroom Apartment"))
			.shortDescription(of("Căn hộ 35 m² có phòng ngủ riêng, 1 giường Queen.", "35 sqm apartment with separate bedroom, 1 queen bed."))
			.description(of("Căn hộ 35 m² có phòng ngủ riêng, 1 giường Queen. Được trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "35 sqm apartment with separate bedroom, 1 queen bed. Fully equipped with amenities for a comfortable stay."))
			.areaSqm(35.0)
			.bedType(BedType.QUEEN)
			.bedsCount(1)
			.bedLayoutDescription(of("1 giường Queen", "1 queen bed"))
			.bedOptions(List.of(
						BedOption.builder().type(BedType.QUEEN).count(1).build()))
			.maxAdults(2)
			.maxChildren(0)
			.maxGuests(3)
			.totalRooms(20)
			.images(List.of(
				img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-2024c7f5261736b2dba50135cdb283b6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
				img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-a8d5891e305d6b13cd8be5f693fcb269.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
								"SEATING_AREA",
								"BLACKOUT_CURTAINS"))
			.ratePlans(List.of(
				RatePlan.builder()
					.id("rt-cozrum-selina-one-bedroom-prepaid-nonref")
					.name(of("Căn hộ 1 Phòng ngủ - Không gồm bữa sáng", "One-Bedroom Apartment - Room only"))
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
					.id("rt-cozrum-selina-one-bedroom-breakfast-flex")
					.name(of("Căn hộ 1 Phòng ngủ - Bao gồm bữa sáng", "One-Bedroom Apartment - Breakfast included"))
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
			.id("rt-cozrum-selina-two-bedroom")
			.name(of("Căn hộ 2 Phòng ngủ", "Two-Bedroom Apartment"))
			.shortDescription(of("Căn hộ 55 m², 2 phòng ngủ, 1 giường Queen + 2 giường đơn, cho 4 khách.", "55 sqm two-bedroom apartment, 1 queen + 2 single beds, for 4 guests."))
			.description(of("Căn hộ 55 m², 2 phòng ngủ, 1 giường Queen + 2 giường đơn, cho 4 khách. Được trang bị đầy đủ tiện nghi cho kỳ nghỉ thoải mái.", "55 sqm two-bedroom apartment, 1 queen + 2 single beds, for 4 guests. Fully equipped with amenities for a comfortable stay."))
			.areaSqm(55.0)
			.bedType(BedType.MULTIPLE)
			.bedsCount(2)
			.bedLayoutDescription(of("1 giường Queen và 2 giường đơn", "1 queen bed and 2 single beds"))
			.bedOptions(List.of(
						BedOption.builder().type(BedType.QUEEN).count(1).build(),
						BedOption.builder().type(BedType.SINGLE).count(2).build()))
			.maxAdults(4)
			.maxChildren(0)
			.maxGuests(4)
			.totalRooms(20)
			.images(List.of(
				img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-a9605b97293ad6495e2beeebb1f5841f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
				img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/680013312-a00f421d6608526710b4073b7c8de3d1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
								"SEATING_AREA",
								"BLACKOUT_CURTAINS"))
			.ratePlans(List.of(
				RatePlan.builder()
					.id("rt-cozrum-selina-two-bedroom-prepaid-nonref")
					.name(of("Căn hộ 2 Phòng ngủ - Không gồm bữa sáng", "Two-Bedroom Apartment - Room only"))
					.boardType(BoardType.ROOM_ONLY)
					.paymentType(PaymentType.PREPAID)
					.refundable(false)
					.cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
					.pricePerNight(new BigDecimal("1200000"))
					.referencePricePerNight(new BigDecimal("1714286"))
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
					.id("rt-cozrum-selina-two-bedroom-breakfast-flex")
					.name(of("Căn hộ 2 Phòng ngủ - Bao gồm bữa sáng", "Two-Bedroom Apartment - Breakfast included"))
					.boardType(BoardType.BREAKFAST_INCLUDED)
					.paymentType(PaymentType.PAY_AT_HOTEL)
					.refundable(true)
					.cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
					.pricePerNight(new BigDecimal("1380000"))
					.referencePricePerNight(new BigDecimal("1971429"))
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
		nearby("landmark-81", of("Landmark 81", "Landmark 81"), of("Địa điểm lân cận", "Nearby places"), 1500.0),
		nearby("thao-cam-vien-sai-gon", of("Thảo Cầm Viên", "Saigon Zoo"), of("Địa điểm lân cận", "Nearby places"), 3000.0),
		nearby("cau-mong", of("Cầu Móng", "Mong Bridge"), of("Địa điểm lân cận", "Nearby places"), 4000.0),
		nearby("chua-ngoc-hoang", of("Chùa Ngọc Hoàng", "Jade Emperor Pagoda"), of("Địa điểm lân cận", "Nearby places"), 3500.0),
		nearby("dinh-doc-lap", of("Dinh Độc Lập", "Independence Palace"), of("Địa điểm lân cận", "Nearby places"), 4500.0),
		nearby("nha-tho-duc-ba", of("Nhà thờ Đức Bà", "Notre-Dame Cathedral"), of("Địa điểm lân cận", "Nearby places"), 5000.0),
		nearby("buu-dien-thanh-pho", of("Bưu điện Thành phố", "Central Post Office"), of("Địa điểm lân cận", "Nearby places"), 5100.0),
		nearby("bao-tang-chung-tich-chien-tranh", of("Bảo tàng Chứng tích Chiến tranh", "War Remnants Museum"), of("Khác", "Other"), 4800.0),
		nearby("pho-di-bo-nguyen-hue", of("Phố đi bộ Nguyễn Huệ", "Nguyen Hue Walking Street"), of("Địa điểm lân cận", "Nearby places"), 5500.0),
		nearby("toa-nha-bitexco", of("Tòa nhà Bitexco", "Bitexco Financial Tower"), of("Địa điểm lân cận", "Nearby places"), 5800.0),
		nearby("cho-ben-thanh", of("Chợ Bến Thành", "Ben Thanh Market"), of("Địa điểm lân cận", "Nearby places"), 5600.0),
		nearby("pho-tay-bui-vien", of("Phố Tây Bùi Viện", "Bui Vien Street"), of("Trung tâm giải trí", "Entertainment hub"), 6000.0),
		nearby("cho-binh-tay", of("Chợ Bình Tây", "Binh Tay Market"), of("Địa điểm lân cận", "Nearby places"), 9000.0),
		nearby("san-bay-tan-son-nhat", of("Sân bay Tân Sơn Nhất", "Tan Son Nhat Airport"), of("Trung tâm giao thông", "Transport hub"), 7000.0));

		GeneralInfo generalInfo = GeneralInfo.builder()
				.mainFacilitiesSummary(of("Máy lạnh, lễ tân 24h, bếp mini, thang máy, chỗ đậu xe, WiFi miễn phí", "Air conditioning, 24-hour front desk, kitchenette, elevator, parking, free WiFi"))
				.distanceToCityCenterKm(4.5)
				.popularAreaSummary(of("Quận Bình Thạnh, Landmark 81, Thảo Cầm Viên", "Binh Thanh District, Landmark 81, Saigon Zoo"))
				.totalRooms(20)
				.totalFloors(4)
				.otherHighlightFacilities(of("Thang máy, giữ hành lý, nhận/trả phòng cấp tốc, giặt ủi.", "Elevator, luggage storage, express check-in/out, laundry."))
				.interestingPlacesSummary(of("Landmark 81, Chợ Bến Thành, Dinh Độc Lập", "Landmark 81, Ben Thanh Market, Independence Palace"))
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
								.title(of("Bếp & lưu trú dài ngày", "Kitchen & long stay"))
								.content(of("Căn hộ có bếp mini, phù hợp cho lưu trú dài ngày và khách công tác.", "Apartments have a kitchenette, suitable for long stays and business travelers."))
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
		faq(of("Những tiện ích tại Cozrum Homes - Selina Residence?", "What amenities does Cozrum Homes - Selina Residence offer?"),
			of("Các tiện ích bao gồm máy lạnh, lễ tân 24h, chỗ đậu xe, thang máy và WiFi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, a 24-hour front desk, parking, an elevator and WiFi (some services may require an additional fee).")),
		faq(of("Thời gian nhận phòng và trả phòng?", "What are the check-in and check-out times?"),
			of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
		faq(of("Cozrum Homes - Selina Residence đạt tiêu chuẩn mấy sao?", "What star rating does Cozrum Homes - Selina Residence have?"),
			of("Cozrum Homes - Selina Residence là căn hộ dịch vụ đạt tiêu chuẩn 2 sao.", "Cozrum Homes - Selina Residence is a 2-star serviced apartment.")),
		faq(of("Cozrum Homes - Selina Residence có bao nhiêu tầng lầu?", "How many floors does Cozrum Homes - Selina Residence have?"),
			of("Cozrum Homes - Selina Residence có tổng cộng 4 tầng.", "Cozrum Homes - Selina Residence has a total of 4 floors.")),
		faq(of("Tổng số phòng hiện có tại Cozrum Homes - Selina Residence?", "How many rooms does Cozrum Homes - Selina Residence have in total?"),
			of("Tổng số phòng tại Cozrum Homes - Selina Residence là 20 phòng.", "Cozrum Homes - Selina Residence has a total of 20 rooms.")),
		faq(of("Cozrum Homes - Selina Residence có hỗ trợ nhận phòng sớm không?", "Does Cozrum Homes - Selina Residence support early check-in?"),
			of("Có, Cozrum Homes hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn.", "Yes, Cozrum Homes supports early check-in subject to availability. Please contact the property directly.")),
		faq(of("Cozrum Homes - Selina Residence có dịch vụ trả phòng trễ không?", "Does Cozrum Homes - Selina Residence offer late check-out?"),
			of("Có, Cozrum Homes hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn.", "Yes, Cozrum Homes supports late check-out subject to availability. Please contact the property directly.")),
		faq(of("Cozrum Homes - Selina Residence có mức giá là bao nhiêu?", "How much does Cozrum Homes - Selina Residence cost?"),
			of("Phòng có giá từ khoảng 600.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms start from around 600,000 VND per night. Prices may vary depending on the date and promotions.")),
		faq(of("Căn hộ có bếp riêng không?", "Do the apartments have a private kitchen?"),
			of("Có, mỗi căn hộ đều có bếp mini với tủ lạnh và lò vi sóng để du khách tự chuẩn bị bữa ăn.", "Yes, each apartment has a kitchenette with a refrigerator and microwave so guests can prepare their own meals.")),
		faq(of("Cozrum Homes - Selina Residence cách sân bay bao xa?", "How far is Cozrum Homes - Selina Residence from the airport?"),
			of("Khách sạn cách sân bay quốc tế Tân Sơn Nhất khoảng 7 km, tương đương 20-25 phút lái xe.", "The property is about 7 km from Tan Son Nhat International Airport, roughly a 20-25 minute drive.")));

		return HotelDoc.builder()
				.active(true)
				.destinationSlug("ho-chi-minh-city")
				.cityName(of("Hồ Chí Minh", "Ho Chi Minh City"))
				.districtName(of("Bình Thạnh", "Binh Thanh"))
				.wardName(of("Phường 1", "Ward 1"))
				.addressLine(of("182 Diên Hồng, P.1, Quận Bình Thạnh, Thành phố Hồ Chí Minh, Việt Nam, 0840001", "182 Dien Hong, Ward 1, Binh Thanh District, Ho Chi Minh City, Vietnam, 0840001"))
				.location(new double[] { 106.69855892658234, 10.798403326161518 })
				.name(of("Cozrum Homes - Selina Residence", "Cozrum Homes - Selina Residence"))
				.slug(slug)
				.hotelType(HotelType.APARTMENT)
				.starRating(2)
				.shortDescription(of("Căn hộ dịch vụ 2 sao tại Quận Bình Thạnh, gần trung tâm TP.HCM.", "2-star serviced apartment in Binh Thanh District, near the HCMC center."))
				.description(of("Cozrum Homes - Selina Residence là tòa căn hộ dịch vụ 2 sao tại Quận Bình Thạnh, cung cấp các căn Studio và căn hộ 1-2 phòng ngủ với bếp mini, máy lạnh, lễ tân 24h và WiFi miễn phí, phù hợp cho kỳ nghỉ ngắn ngày lẫn lưu trú dài ngày.", "Cozrum Homes - Selina Residence is a 2-star serviced-apartment building in Binh Thanh District offering studios and 1-2 bedroom apartments with a kitchenette, air conditioning, 24-hour front desk and free WiFi, suitable for both short getaways and long stays."))
				.defaultCheckInTime(LocalTime.of(14, 0))
				.defaultCheckOutTime(LocalTime.of(12, 0))
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
