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
public class SeedHcmMeraki {

        @Bean
        CommandLineRunner seedHcmMerakiRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHcmMeraki] start");
                        HotelDoc doc = hotelMeraki();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedHcmMeraki] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedHcmMeraki] done");
                };
        }

        // =====================================================================
        // MERAKI BOUTIQUE HOTEL (Quan 1, HCMC)
        // (source: Traveloka hotelId 3000010027042; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelMeraki() {
                String slug = "meraki-boutique-hotel";

        List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-ed4c9a4557b1c17cee079d74a70731dc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-9abad41af31683431dc93ee51311f267.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-881b5e196f21d8ba0d8417a95b76dc73.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-316fc987628ff0f70df81a420f3c2698.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-8515aea36df02e1b3d48b5df4c9fb0e1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-6e740c161e42f5317d4e98389ea99128.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-974d219c1eef17b01f7582d9479027a4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-1c4719ccdfcce0a7dd0fcd5bd10bb8bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-b5cb7350602d5f22a841b153d30c5089.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-18ec2fcd4c922e639058dddafec6b48e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-013332c7bd84db040158e1699a7adad7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-dfcbee9ee9e0b2e55327b838b57ec9a5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-55ccb284afe80bcd47a47da65505b826.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-898166a58901784fb574722be220cb4d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-f23a8dc9c43d868aecf663a3b49f556c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-1c36045a55b7fc7a89f1ddcff931749b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-e38fa4c941b89d365a2f187ad4f95485.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-97998c381d53f53269ab9cbc22e8b2e7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-1100x733-FIT_AND_TRIM-ad09701ea8cf9430d8ae12461e7da049.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Sảnh", "Lobby", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-1100x733-FIT_AND_TRIM-d0b74b254ca2adabcb082fded1e7f75b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Không gian chung", "Common space", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-1100x733-FIT_AND_TRIM-3534dee403db95dcb30e96d585fcb267.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Nhà hàng", "Restaurant", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-e26218c957c9e092c3cf30c3d98dae52.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng tắm", "Bathroom", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-5661be70e2de57aea770f99a6fd4cc8f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-2547acd512cb4d7b245455d0a654d1df.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-283a408e64d7d34de066edf54409e24e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-90cfe37ad7af28ebcc01e95726765e13.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10027042-800x600-FIT_AND_TRIM-a273a59c38e3d77bde215c2a0da6e7cc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng tắm", "Bathroom", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-92af7dc8e6398906f896abb335b35630.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 27),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-cb5edb610242f1eef2342791e837f40f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 28),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-19fee7d490d686a59c5a4386ae4635af.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 29),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-9ba7a5d145d5dd0462d7dec782b1adad.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 30),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-6c7c5467b60519853f3833ba33377190.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 31),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-8818776bde5929768ff0f235f2a2fe3e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 32),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-0fa748aff221ddadf18b11fad65b4947.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 33),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-d8da3d4842e72b9f2e51c010a7e6eb58.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 34),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-3cc07c6489e85fc493f67fef7a52f09d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 35),
                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-ccc585bba882ca1028391c80bed60198.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Khu vực khách sạn", "Hotel area", false, 36));

        List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 106.691978, 10.766674 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về Meraki Boutique Hotel", "About Meraki Boutique Hotel")),

                paragraph(ContentSection.STORY,
                                of("Meraki Boutique Hotel là khách sạn boutique 3 sao tọa lạc tại 178 Bùi Viện, phường Phạm Ngũ Lão, Quận 1 - ngay trung tâm phố Tây sôi động của Thành phố Hồ Chí Minh. Với 19 phòng nghỉ ấm cúng trải trên 7 tầng, khách sạn mang đến không gian lưu trú gần gũi, hiện đại và vị trí lý tưởng để khám phá Sài Gòn về đêm.", "Meraki Boutique Hotel is a 3-star boutique hotel located at 178 Bui Vien Street, Pham Ngu Lao Ward, District 1 - right in the lively backpacker quarter of Ho Chi Minh City. With 19 cozy rooms across 7 floors, the hotel offers an intimate, modern stay and an ideal base to explore Saigon by night.")),

                heading(ContentSection.STORY, of("Các hạng phòng", "Room types")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cung cấp đa dạng hạng phòng từ Standard, Superior, Deluxe đến phòng Family, phù hợp cho cả khách lẻ, cặp đôi và gia đình nhỏ. Mỗi phòng được trang bị máy lạnh, WiFi miễn phí, phòng tắm riêng với nước nóng, minibar và bàn làm việc, đảm bảo sự tiện nghi trong suốt kỳ nghỉ.", "The hotel offers a range of room types from Standard, Superior and Deluxe to Family rooms, suitable for solo travelers, couples and small families. Each room is equipped with air conditioning, free WiFi, a private bathroom with hot water, a minibar and a work desk to ensure comfort throughout your stay.")),

                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

                paragraph(ContentSection.STORY,
                                of("Nhà hàng của khách sạn phục vụ bữa sáng cùng các món Á - Âu trong không gian ấm cúng. Ngay bên ngoài cửa khách sạn là vô số quán ăn đường phố, quán bar và cà phê đặc trưng của phố Bùi Viện, mang đến trải nghiệm ẩm thực phong phú suốt ngày đêm.", "The hotel restaurant serves breakfast along with Asian and European dishes in a cozy setting. Just outside the door, Bui Vien Street offers countless street-food stalls, bars and cafes, delivering a rich culinary experience day and night.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn có lễ tân 24 giờ, dịch vụ dọn phòng, giặt ủi, giữ hành lý và thang máy thuận tiện. Đội ngũ nhân viên thân thiện sẵn sàng hỗ trợ đặt tour, thuê xe và đưa đón sân bay theo yêu cầu, giúp chuyến đi của bạn thêm trọn vẹn.", "The hotel features a 24-hour front desk, housekeeping, laundry, luggage storage and a convenient elevator. The friendly staff are ready to assist with tour bookings, car rental and airport transfer on request to make your trip complete.")),

                heading(ContentSection.STORY, of("Vị trí & điểm tham quan lân cận", "Location & nearby attractions")),

                paragraph(ContentSection.STORY,
                                of("Từ khách sạn, du khách chỉ mất vài phút đi bộ đến chợ Bến Thành, phố đi bộ Nguyễn Huệ và Bùi Viện. Các điểm tham quan nổi tiếng như Nhà thờ Đức Bà, Dinh Độc Lập, Bưu điện Thành phố và Bảo tàng Chứng tích Chiến tranh đều nằm trong bán kính ngắn, rất thuận tiện cho việc khám phá.", "From the hotel, it takes just a few minutes on foot to reach Ben Thanh Market, Nguyen Hue Walking Street and Bui Vien. Famous attractions such as Notre-Dame Cathedral, Independence Palace, the Central Post Office and the War Remnants Museum are all within a short radius, very convenient for sightseeing.")),

                heading(ContentSection.STORY, of("Di chuyển & sân bay", "Getting around & airport")),

                paragraph(ContentSection.STORY,
                                of("Khách sạn cách Sân bay Quốc tế Tân Sơn Nhất khoảng 8 km, tương đương 20-25 phút di chuyển bằng ô tô. Dịch vụ đưa đón sân bay được cung cấp theo yêu cầu, đồng thời taxi và xe công nghệ luôn sẵn sàng ngay trước cửa khách sạn.", "The hotel is about 8 km from Tan Son Nhat International Airport, roughly a 20-25 minute drive. Airport transfer is available on request, while taxis and ride-hailing cars are readily available right at the door.")),

                heading(ContentSection.STORY, of("Khu vực xung quanh", "The surrounding area")),

                paragraph(ContentSection.STORY,
                                of("Nằm giữa lòng phố Tây Bùi Viện, khách sạn được bao quanh bởi các quán bar, nhà hàng, cửa hàng tiện lợi và ATM, đáp ứng mọi nhu cầu của du khách. Đây là khu vực nhộn nhịp bậc nhất Sài Gòn, lý tưởng cho những ai yêu thích trải nghiệm về đêm.", "Set in the heart of the Bui Vien backpacker area, the hotel is surrounded by bars, restaurants, convenience stores and ATMs that cater to every need. This is one of the most vibrant areas of Saigon, ideal for those who love nightlife.")),

                heading(ContentSection.STORY, of("Vì sao nên chọn Meraki Boutique Hotel", "Why choose Meraki Boutique Hotel")),

                paragraph(ContentSection.STORY,
                                of("Chọn Meraki Boutique Hotel, du khách có được vị trí trung tâm tuyệt vời ngay tại phố Tây, phòng nghỉ tiện nghi với mức giá hợp lý và dịch vụ thân thiện. Đây là lựa chọn lý tưởng cho những chuyến công tác, du lịch khám phá hay trải nghiệm cuộc sống về đêm sôi động của Sài Gòn.", "Choosing Meraki Boutique Hotel gives travelers a superb central location right in the backpacker quarter, comfortable rooms at a reasonable price and friendly service. It is an ideal choice for business trips, sightseeing tours or experiencing Saigon's vibrant nightlife.")));

        List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, true),
                amenity("breakfast", of("Bữa sáng", "Breakfast"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, true),
                amenity("room_service_limited", of("Dịch vụ dọn phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("car_rental", of("Cho thuê xe hơi", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkin", of("Nhận phòng nhanh", "Express check-in"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("express_checkout", of("Trả phòng nhanh", "Express check-out"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("non_smoking", of("Khu vực không hút thuốc", "Non-smoking area"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false),
                amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("atm", of("Máy ATM", "ATM"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Cửa hàng tiện lợi", "Convenience store"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng", "Shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.OTHER, false));

        List<RoomType> roomTypes = List.of(
        RoomType.builder()
                .id("rt-meraki-standard-double")
                .name(of("Standard Double", "Standard Double"))
                .shortDescription(of("Phòng Standard Double 18 m², 1 giường đôi.", "Standard Double room 18 sqm, 1 double bed."))
                .description(of("Phòng Standard Double 18 m², 1 giường đôi. Trang bị đầy đủ tiện nghi hiện đại cho kỳ nghỉ thoải mái.", "Standard Double room 18 sqm, 1 double bed. Fully equipped with modern amenities for a comfortable stay."))
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
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-ed4c9a4557b1c17cee079d74a70731dc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-9abad41af31683431dc93ee51311f267.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "FAN"))
                .ratePlans(List.of(
                        RatePlan.builder()
                                .id("rt-meraki-standard-double-prepaid-nonref")
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
                                .id("rt-meraki-standard-double-breakfast-flex")
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
                .id("rt-meraki-superior-double")
                .name(of("Superior Double", "Superior Double"))
                .shortDescription(of("Phòng Superior Double 22 m², 1 giường đôi.", "Superior Double room 22 sqm, 1 double bed."))
                .description(of("Phòng Superior Double 22 m², 1 giường đôi. Trang bị đầy đủ tiện nghi hiện đại cho kỳ nghỉ thoải mái.", "Superior Double room 22 sqm, 1 double bed. Fully equipped with modern amenities for a comfortable stay."))
                .areaSqm(22.0)
                .bedType(BedType.DOUBLE)
                .bedsCount(1)
                .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                .maxAdults(2)
                .maxChildren(1)
                .maxGuests(2)
                .totalRooms(5)
                .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-881b5e196f21d8ba0d8417a95b76dc73.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-316fc987628ff0f70df81a420f3c2698.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "FAN"))
                .ratePlans(List.of(
                        RatePlan.builder()
                                .id("rt-meraki-superior-double-prepaid-nonref")
                                .name(of("Superior Double - Không gồm bữa sáng", "Superior Double - Room only"))
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
                                .id("rt-meraki-superior-double-breakfast-flex")
                                .name(of("Superior Double - Bao gồm bữa sáng", "Superior Double - Breakfast included"))
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
                .id("rt-meraki-deluxe-double")
                .name(of("Deluxe Double", "Deluxe Double"))
                .shortDescription(of("Phòng Deluxe Double 26 m², 1 giường cỡ Queen.", "Deluxe Double room 26 sqm, 1 queen bed."))
                .description(of("Phòng Deluxe Double 26 m², 1 giường cỡ Queen. Trang bị đầy đủ tiện nghi hiện đại cho kỳ nghỉ thoải mái.", "Deluxe Double room 26 sqm, 1 queen bed. Fully equipped with modern amenities for a comfortable stay."))
                .areaSqm(26.0)
                .bedType(BedType.QUEEN)
                .bedsCount(1)
                .bedLayoutDescription(of("1 giường cỡ Queen", "1 queen bed"))
                .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                .maxAdults(2)
                .maxChildren(1)
                .maxGuests(2)
                .totalRooms(5)
                .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-8515aea36df02e1b3d48b5df4c9fb0e1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-6e740c161e42f5317d4e98389ea99128.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "FAN"))
                .ratePlans(List.of(
                        RatePlan.builder()
                                .id("rt-meraki-deluxe-double-prepaid-nonref")
                                .name(of("Deluxe Double - Không gồm bữa sáng", "Deluxe Double - Room only"))
                                .boardType(BoardType.ROOM_ONLY)
                                .paymentType(PaymentType.PREPAID)
                                .refundable(false)
                                .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                .pricePerNight(new BigDecimal("1000000"))
                                .referencePricePerNight(new BigDecimal("1428571"))
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
                                .id("rt-meraki-deluxe-double-breakfast-flex")
                                .name(of("Deluxe Double - Bao gồm bữa sáng", "Deluxe Double - Breakfast included"))
                                .boardType(BoardType.BREAKFAST_INCLUDED)
                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                .refundable(true)
                                .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                .pricePerNight(new BigDecimal("1150000"))
                                .referencePricePerNight(new BigDecimal("1642857"))
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
                .id("rt-meraki-family-room")
                .name(of("Family Room", "Family Room"))
                .shortDescription(of("Phòng Family Room 32 m², 1 giường đôi và 1 giường đơn.", "Family Room room 32 sqm, 1 double bed and 1 single bed."))
                .description(of("Phòng Family Room 32 m², 1 giường đôi và 1 giường đơn. Trang bị đầy đủ tiện nghi hiện đại cho kỳ nghỉ thoải mái.", "Family Room room 32 sqm, 1 double bed and 1 single bed. Fully equipped with modern amenities for a comfortable stay."))
                .areaSqm(32.0)
                .bedType(BedType.MULTIPLE)
                .bedsCount(2)
                .bedLayoutDescription(of("1 giường đôi và 1 giường đơn", "1 double bed and 1 single bed"))
                .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                .maxAdults(3)
                .maxChildren(1)
                .maxGuests(3)
                .totalRooms(3)
                .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-974d219c1eef17b01f7582d9479027a4.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/10027042-1c4719ccdfcce0a7dd0fcd5bd10bb8bb.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                "FAN"))
                .ratePlans(List.of(
                        RatePlan.builder()
                                .id("rt-meraki-family-room-prepaid-nonref")
                                .name(of("Family Room - Không gồm bữa sáng", "Family Room - Room only"))
                                .boardType(BoardType.ROOM_ONLY)
                                .paymentType(PaymentType.PREPAID)
                                .refundable(false)
                                .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                .pricePerNight(new BigDecimal("1300000"))
                                .referencePricePerNight(new BigDecimal("1857143"))
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
                                .id("rt-meraki-family-room-breakfast-flex")
                                .name(of("Family Room - Bao gồm bữa sáng", "Family Room - Breakfast included"))
                                .boardType(BoardType.BREAKFAST_INCLUDED)
                                .paymentType(PaymentType.PAY_AT_HOTEL)
                                .refundable(true)
                                .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                .pricePerNight(new BigDecimal("1495000"))
                                .referencePricePerNight(new BigDecimal("2135714"))
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
                nearby("pho-tay-bui-vien", of("Phố Tây Bùi Viện", "Bui Vien Street"), of("Địa điểm lân cận", "Nearby places"), 50.0),
                nearby("pho-di-bo-nguyen-hue", of("Phố đi bộ Nguyễn Huệ", "Nguyen Hue Walking Street"), of("Địa điểm lân cận", "Nearby places"), 900.0),
                nearby("cho-ben-thanh", of("Chợ Bến Thành", "Ben Thanh Market"), of("Địa điểm lân cận", "Nearby places"), 600.0),
                nearby("dinh-doc-lap", of("Dinh Độc Lập", "Independence Palace"), of("Địa điểm lân cận", "Nearby places"), 1300.0),
                nearby("bao-tang-chung-tich-chien-tranh", of("Bảo tàng Chứng tích Chiến tranh", "War Remnants Museum"), of("Khác", "Other"), 1500.0),
                nearby("nha-tho-duc-ba", of("Nhà thờ Đức Bà", "Notre-Dame Cathedral"), of("Địa điểm lân cận", "Nearby places"), 1400.0),
                nearby("buu-dien-thanh-pho", of("Bưu điện Thành phố", "Central Post Office"), of("Địa điểm lân cận", "Nearby places"), 1450.0),
                nearby("toa-nha-bitexco", of("Tòa nhà Bitexco", "Bitexco Financial Tower"), of("Địa điểm lân cận", "Nearby places"), 1200.0),
                nearby("cau-mong", of("Cầu Mống", "Mong Bridge"), of("Khác", "Other"), 1600.0),
                nearby("chua-ngoc-hoang", of("Chùa Ngọc Hoàng", "Jade Emperor Pagoda"), of("Khác", "Other"), 3500.0),
                nearby("thao-cam-vien-sai-gon", of("Thảo Cầm Viên", "Saigon Zoo"), of("Trung tâm giải trí", "Entertainment hub"), 2800.0),
                nearby("landmark-81", of("Landmark 81", "Landmark 81"), of("Địa điểm lân cận", "Nearby places"), 6500.0),
                nearby("cho-binh-tay", of("Chợ Bình Tây", "Binh Tay Market"), of("Địa điểm lân cận", "Nearby places"), 6000.0),
                nearby("san-bay-tan-son-nhat", of("Sân bay Tân Sơn Nhất", "Tan Son Nhat Airport"), of("Trung tâm giao thông", "Transport hub"), 8000.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Nhà hàng, lễ tân 24h, WiFi miễn phí, thang máy, đưa đón sân bay", "Restaurant, 24-hour front desk, free WiFi, elevator, airport transfer"))
                                .distanceToCityCenterKm(0.6)
                                .popularAreaSummary(of("Phố Bùi Viện, Chợ Bến Thành, Phố đi bộ Nguyễn Huệ", "Bui Vien Street, Ben Thanh Market, Nguyen Hue Walking Street"))
                                .totalRooms(19)
                                .totalFloors(7)
                                .otherHighlightFacilities(of("Dịch vụ giặt ủi, giữ hành lý, hỗ trợ đặt tour, cho thuê xe.", "Laundry, luggage storage, tour assistance, car hire."))
                                .interestingPlacesSummary(of("Chợ Bến Thành, Dinh Độc Lập, Bảo tàng Chứng tích Chiến tranh", "Ben Thanh Market, Independence Palace, War Remnants Museum"))
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
                                                                .title(of("Đưa đón sân bay", "Airport transfer"))
                                                                .content(of("Khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu (thu phí); vui lòng liên hệ trước.", "The hotel offers airport transfer on request (surcharge); please contact in advance."))
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
                faq(of("Những tiện ích tại Meraki Boutique Hotel?", "What amenities does Meraki Boutique Hotel offer?"),
                        of("Các tiện ích bao gồm nhà hàng, lễ tân 24h, WiFi miễn phí, thang máy, dịch vụ dọn phòng, giặt ủi và đưa đón sân bay (một số dịch vụ có thể thu thêm phí).", "Amenities include a restaurant, 24-hour front desk, free WiFi, an elevator, housekeeping, laundry and airport transfer (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Meraki Boutique Hotel?", "What are the check-in and check-out times at Meraki Boutique Hotel?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Khách sạn Meraki Boutique Hotel có bao nhiêu tầng?", "How many floors does Meraki Boutique Hotel have?"),
                        of("Khách sạn có tổng cộng 7 tầng.", "The hotel has a total of 7 floors.")),
                faq(of("Tổng số phòng hiện có tại Meraki Boutique Hotel là bao nhiêu?", "How many rooms does Meraki Boutique Hotel have?"),
                        of("Tổng số phòng tại khách sạn là 19 phòng.", "The hotel has a total of 19 rooms.")),
                faq(of("Meraki Boutique Hotel có dịch vụ đưa đón sân bay không?", "Does Meraki Boutique Hotel offer airport transfer?"),
                        of("Có, khách sạn cung cấp dịch vụ đưa đón sân bay theo yêu cầu. Vui lòng liên hệ trực tiếp với khách sạn để biết thêm chi tiết.", "Yes, the hotel provides airport transfer on request. Please contact the hotel directly for more details.")),
                faq(of("Meraki Boutique Hotel có mức giá là bao nhiêu?", "How much does Meraki Boutique Hotel cost?"),
                        of("Phòng tại khách sạn có giá từ khoảng 700.000 VND mỗi đêm. Giá có thể thay đổi tùy thời điểm và chương trình khuyến mãi.", "Rooms at the hotel start from around 700,000 VND per night. Prices may vary depending on the date and promotions.")),
                faq(of("Khách sạn có phục vụ bữa sáng không?", "Does the hotel serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")),
                faq(of("Khách sạn có nằm gần chợ Bến Thành không?", "Is the hotel near Ben Thanh Market?"),
                        of("Có, khách sạn nằm tại phố Bùi Viện, chỉ cách chợ Bến Thành khoảng 600 m và rất gần phố đi bộ Nguyễn Huệ.", "Yes, the hotel is on Bui Vien Street, only about 600 m from Ben Thanh Market and very close to Nguyen Hue Walking Street.")),
                faq(of("Khách sạn có chỗ đậu xe không?", "Does the hotel have parking?"),
                        of("Khách sạn nằm trong khu phố Tây nên chỗ đậu xe ô tô hạn chế; vui lòng liên hệ lễ tân để được hỗ trợ sắp xếp.", "As the hotel is in the backpacker quarter, car parking is limited; please contact the front desk for assistance.")),
                faq(of("Khách sạn có thang máy không?", "Does the hotel have an elevator?"),
                        of("Có, khách sạn được trang bị thang máy phục vụ cho cả 7 tầng.", "Yes, the hotel is equipped with an elevator serving all 7 floors.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("ho-chi-minh-city")
                                .cityName(of("Hồ Chí Minh", "Ho Chi Minh City"))
                                .districtName(of("Quận 1", "District 1"))
                                .wardName(of("Phạm Ngũ Lão", "Pham Ngu Lao Ward"))
                                .addressLine(of("178 Bùi Viện, Phạm Ngũ Lão, Quận 1, Thành phố Hồ Chí Minh, Việt Nam, 711248", "178 Bui Vien , Pham Ngu Lao ward, District 1, Ho Chi Minh City, Vietnam, 711248"))
                                .location(new double[] { 106.691978, 10.766674 })
                                .name(of("Meraki Boutique Hotel", "Meraki Boutique Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khách sạn boutique 3 sao ngay phố Tây Bùi Viện, Quận 1, trung tâm TP.HCM.", "3-star boutique hotel on Bui Vien backpacker street, District 1, central HCMC."))
                                .description(of("Meraki Boutique Hotel là khách sạn boutique 3 sao với 19 phòng nghỉ tiện nghi ngay phố Tây Bùi Viện, Quận 1, mang đến vị trí trung tâm lý tưởng để khám phá Thành phố Hồ Chí Minh.", "Meraki Boutique Hotel is a 3-star boutique hotel with 19 comfortable rooms right on Bui Vien backpacker street in District 1, offering an ideal central location to explore Ho Chi Minh City."))
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
