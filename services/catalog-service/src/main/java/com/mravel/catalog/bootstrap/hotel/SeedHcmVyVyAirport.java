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
public class SeedHcmVyVyAirport {

        @Bean
        CommandLineRunner seedHcmVyVyAirportRunner(HotelDocRepository repo) {
                return args -> {
                        System.out.println(">>> [SeedHcmVyVyAirport] start");
                        HotelDoc doc = hotelVyVyAirport();
                        repo.findBySlugAndActiveTrue(doc.getSlug())
                                        .ifPresent(existing -> doc.setId(existing.getId()));
                        repo.save(doc);
                        System.out.println(">>> [SeedHcmVyVyAirport] upsert: " + doc.getSlug());
                        System.out.println(">>> [SeedHcmVyVyAirport] done");
                };
        }

        // =====================================================================
        // VY VY AIRPORT HOTEL (Tan Binh, Ho Chi Minh City)
        // (source: Traveloka hotelId 10029910; phong duoc dung them)
        // =====================================================================
        private static HotelDoc hotelVyVyAirport() {
                String slug = "vy-vy-airport-hotel";

                List<Image> images = List.of(
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-e00b6deaaa0deb04139a9cf92876c5dc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", true, 0),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-92c249cb4167fc44486b07b1f8632925.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 1),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-7e83bc0a63ad327e85defc8a61dd1406.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 2),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-3e41d49710452e6ec5b084d60f37d665.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 3),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-0b3d7233026f7c86f990cec82b09cd6a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 4),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-982230e4f3a6f5a23ccd06442710ea80.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 5),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-e3f1535ee74d1a3414a8a9de2cb7bfa1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 6),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-436cebb1fe6404e0acec3eabfcadf862.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 7),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-9e11be07e17a788d0445d1df2c7665bf.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 8),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-ac19332d159c9a9f32142eeb2632dba1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 9),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-c54ed60ccd2fbbb8778cef7dc7c3ad16.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 10),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-10939552c53641e6c2d6a0d457d1a0e2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 11),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-cefb415f9b396ead7fa670fe71cd5cdf.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 12),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-5455cfd8cbfb8aeea124748ca8662dc1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 13),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-30083031c30cadc877acc21827ab06d0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 14),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-a6392455056b13fc0eb7aaa6efb97289.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 15),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-349c7af87c6600dc303a3d46eeca3de3.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 16),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-af861cc590353d855c61fea64644d9d5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Sảnh", "Lobby", false, 17),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-ad071c6c71bdbe079faf66a870e8c994.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 18),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-d99c8e41413a2b3ababdc82e9147e017.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 19),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-16d13564aa7b7f4b2014b26c859a11af.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 20),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-525540c52dda4d61a47c3510bdffad9c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 21),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-38ee55783b63e3198e5e772f988e644b.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 22),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-cb614225ff521e8ebd5804bd1559ef09.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 23),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-d7a3736e88071dd541cda297a88d1383.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 24),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-85d9f4f9411b6eb4f18e5cdfdf83c2b5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 25),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-7c70ff9a9c2ddcfa90152e17ed5167b6.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 26),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-20e45da17d54e82a436899236b2d2414.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 27),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-fe96080ac3f14263544612ca19087b2a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 28),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-fa26dc0e12baff90a89b365d3b52bf07.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 29),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-40f06eef07e2d3257bd68789f204bad8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 30),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-ab3f5660cc4c5aa42d8b8deae8b8d2c9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 31),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-5b87200d197c54314c2a819d9791f75a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Phòng ngủ", "Bedroom", false, 32),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-9883cf1c64da073f29a84faa836f00f5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 33),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-90e0f7a2843266f487b10ce82e16d0f1.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 34),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-480237fd37e7364f5a915a0aec93f365.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 35),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-8ca89993a9b1e99f450ff3d69c13a638.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 36),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-262c6b8d9912cf73b80ad071b103a870.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 37),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-b8f955d52cf5f4087f696fd24d15e406.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 38),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-5d86251c97a9a4b8c211cbf825c337b9.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 39),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-3d05e37634be8be9d518a0fdbd7d153f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 40),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-94c53c3100154ff489540f2903d58bbf.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 41),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-087b925930c0984beaf5e8ccf0ceb7e8.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 42),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-29fa0578626399112450a3e4a43331b2.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 43),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-5e62437750bddceaefb468e309a67789.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 44),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-d9f9c50d407bb758f3176c3f92907930.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 45),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-f7f4065c3d0dff1ec278455c990d3c33.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 46),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-daf317f870bfbf8dab181de53293895e.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 47),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-13ca4b6c2ff1aef039cd77300ed04994.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 48),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-cdf4e143b769698de51030ce982b4e1f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 49),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-b3c8145aebf7a1ca7f636ac16bd2be5f.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 50),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-9f5716c8fb4154a4ada486ad74d59206.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Quầy bar/Cafe", "Bar & cafe", false, 51),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-09007281e39e306893d64f74515bab1d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 52),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-417ca67b19ab2c267d652099e0a0db78.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 53),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-27c566c41cf3a6a7f44f2f6584631358.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 54),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-ae42e239bb3dda0905451145091a9432.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 55),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-80884d541443c8758cd7eac1ad47ec84.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 56),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-0978d2f7109104e4713640f3e20c67dd.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Ngoại thất", "Exterior", false, 57),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-ef38e97bf6d7cf91f3926eb694d5702d.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 58),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-e28041990477af4a52f3f99944e9c5a7.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 59),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-94456360c78c643acb9f0e81e30229a0.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 60),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-c70397d8074c259c7e8675ee92693ae5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Không gian chung", "Common space", false, 61),
                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-b6ede8e2c0eeb43dbde94e192b822fce.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit",
                        "Khu vực khách sạn", "Hotel area", false, 62));

                List<ContentBlock> content = List.of(
                mapBlock(ContentSection.OVERVIEW, new double[] { 106.662511, 10.800462 }),

                heading(ContentSection.STORY, of("Giới thiệu chung về Vy Vy Airport Hotel", "About Vy Vy Airport Hotel")),

                paragraph(ContentSection.STORY,
                        of("Vy Vy Airport Hotel là khách sạn 3 sao tọa lạc tại 283 Hoàng Văn Thụ, Phường 2, Quận Tân Bình, ngay trung tâm khu vực sân bay Tân Sơn Nhất. Với 26 phòng nghỉ trải trên 6 tầng, khách sạn mang đến không gian gọn gàng, hiện đại và mức giá hợp lý, là lựa chọn lý tưởng cho khách công tác, khách quá cảnh và du khách cần điểm dừng chân thuận tiện gần sân bay.", "Vy Vy Airport Hotel is a 3-star hotel located at 283 Hoang Van Thu, Ward 2, Tan Binh District, right in the heart of the Tan Son Nhat Airport area. With 26 rooms across 6 floors, the hotel offers tidy, modern spaces at reasonable prices, an ideal choice for business travelers, transit guests and visitors seeking a convenient stopover near the airport.")),

                heading(ContentSection.STORY, of("Các loại phòng nghỉ", "Room types")),

                paragraph(ContentSection.STORY,
                        of("Khách sạn cung cấp các hạng phòng Standard, Superior, Deluxe và Family phù hợp cho cá nhân, cặp đôi và gia đình. Mỗi phòng được trang bị máy lạnh, TV, tủ lạnh nhỏ, WiFi miễn phí, phòng tắm vòi sen với máy sấy tóc và nước nóng, mang lại sự thoải mái cần thiết cho một kỳ nghỉ ngắn ngày.", "The hotel offers Standard, Superior, Deluxe and Family room categories suitable for solo travelers, couples and families. Each room is equipped with air conditioning, a TV, a minibar fridge, free WiFi, and a shower bathroom with a hair dryer and hot water, providing the comfort needed for a short stay.")),

                heading(ContentSection.STORY, of("Ẩm thực", "Dining")),

                paragraph(ContentSection.STORY,
                        of("Khách sạn có nhà hàng phục vụ các món ăn Việt và quốc tế, cùng quầy cà phê và khu vực lounge ấm cúng để thư giãn. Thức uống chào mừng miễn phí được phục vụ khi nhận phòng, và khu vực xung quanh có nhiều quán ăn, quán cà phê phục vụ suốt ngày đêm.", "The hotel features a restaurant serving Vietnamese and international dishes, along with a coffee bar and a cozy lounge area to relax. A complimentary welcome drink is served at check-in, and the surrounding area offers many eateries and cafes open around the clock.")),

                heading(ContentSection.STORY, of("Tiện ích & dịch vụ", "Facilities & services")),

                paragraph(ContentSection.STORY,
                        of("Vy Vy Airport Hotel trang bị thang máy, lễ tân 24 giờ, bảo vệ 24/7, dịch vụ giặt ủi, giữ hành lý và đội ngũ nhân viên đa ngôn ngữ. Khách có thể đổi ngoại tệ, sử dụng máy ATM gần đó và nhờ quầy lễ tân hỗ trợ đặt xe, đặt tour một cách nhanh chóng.", "Vy Vy Airport Hotel is equipped with an elevator, a 24-hour front desk, 24/7 security, laundry service, luggage storage and a multilingual staff team. Guests can exchange currency, use nearby ATMs and ask the front desk for quick assistance with transport and tour bookings.")),

                heading(ContentSection.STORY, of("Vị trí & điểm tham quan lân cận", "Location & nearby attractions")),

                paragraph(ContentSection.STORY,
                        of("Từ khách sạn, du khách dễ dàng di chuyển đến các điểm nổi bật của Sài Gòn như Chợ Bến Thành, Nhà thờ Đức Bà, Dinh Độc Lập và phố đi bộ Nguyễn Huệ. Khu vực Tân Bình cũng gần nhiều trung tâm thương mại, chợ và quán ăn địa phương sôi động.", "From the hotel, travelers can easily reach Saigon highlights such as Ben Thanh Market, Notre-Dame Cathedral, Independence Palace and Nguyen Hue Walking Street. The Tan Binh area is also close to many shopping centers, markets and lively local eateries.")),

                heading(ContentSection.STORY, of("Di chuyển & sân bay", "Transport & airport")),

                paragraph(ContentSection.STORY,
                        of("Lợi thế lớn nhất của Vy Vy Airport Hotel là khoảng cách rất gần sân bay quốc tế Tân Sơn Nhất, chỉ vài phút di chuyển. Khách sạn nằm trên trục đường Hoàng Văn Thụ thuận tiện kết nối vào trung tâm thành phố bằng taxi hoặc xe công nghệ.", "The biggest advantage of Vy Vy Airport Hotel is its very close proximity to Tan Son Nhat International Airport, just a few minutes away. The hotel sits on Hoang Van Thu street, conveniently connecting to the city center by taxi or ride-hailing.")),

                heading(ContentSection.STORY, of("Khu vực xung quanh", "Surrounding area")),

                paragraph(ContentSection.STORY,
                        of("Xung quanh khách sạn là khu dân cư sầm uất của Quận Tân Bình với siêu thị, cửa hàng tiện lợi, ngân hàng và nhiều hàng quán ẩm thực địa phương. Du khách có thể dễ dàng tìm thấy mọi nhu cầu thiết yếu chỉ trong vài bước chân.", "The hotel is surrounded by the bustling residential area of Tan Binh District with supermarkets, convenience stores, banks and many local food spots. Guests can easily find all essentials just a few steps away.")),

                heading(ContentSection.STORY, of("Lý do nên chọn Vy Vy Airport Hotel", "Why choose Vy Vy Airport Hotel")),

                paragraph(ContentSection.STORY,
                        of("Chọn Vy Vy Airport Hotel, du khách có được vị trí gần sân bay không thể thuận tiện hơn, mức giá phải chăng, phòng ốc sạch sẽ và dịch vụ lễ tân 24 giờ tận tâm. Đây là điểm dừng chân lý tưởng cho những chuyến bay sớm, chuyến bay khuya hoặc lịch trình quá cảnh tại TP. Hồ Chí Minh.", "Choosing Vy Vy Airport Hotel gives travelers an unbeatable location near the airport, affordable rates, clean rooms and attentive 24-hour front desk service. It is an ideal stopover for early flights, late flights or transit itineraries in Ho Chi Minh City.")));

                List<AmenityCatalogDoc> hotelAmenities = List.of(
                amenity("wifi_free", of("Wifi (miễn phí)", "WiFi (free)"), AmenityGroup.INTERNET, AmenitySection.INTERNET, true),
                amenity("air_conditioning", of("Máy lạnh", "Air conditioning"), AmenityGroup.ROOM, AmenitySection.BASIC_FACILITIES, true),
                amenity("restaurant", of("Nhà hàng", "Restaurant"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, true),
                amenity("front_desk_24h", of("Lễ tân 24 giờ", "24-hour front desk"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, true),
                amenity("airport_transfer", of("Đưa đón sân bay (thu phí)", "Airport transfer (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, true),
                amenity("coffee_shop", of("Tiệm cà phê", "Coffee shop"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("bar", of("Quầy bar", "Bar"), AmenityGroup.PUBLIC_AREA, AmenitySection.FOOD_AND_DRINK, false),
                amenity("welcome_drink", of("Thức uống chào mừng", "Welcome drink"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("breakfast", of("Bữa sáng (thu phí)", "Breakfast (surcharge)"), AmenityGroup.HOTEL_SERVICE, AmenitySection.FOOD_AND_DRINK, false),
                amenity("concierge", of("Dịch vụ concierge", "Concierge service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("luggage_storage", of("Giữ hành lý", "Luggage storage"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("laundry", of("Dịch vụ giặt ủi", "Laundry service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("room_service_limited", of("Dịch vụ phòng", "Room service"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("multilingual_staff", of("Nhân viên đa ngôn ngữ", "Multilingual staff"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("currency_exchange", of("Thu đổi ngoại tệ", "Currency exchange"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("tour_desk", of("Dịch vụ hỗ trợ đặt tour", "Tour booking assistance"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false),
                amenity("car_rental", of("Cho thuê xe", "Car hire"), AmenityGroup.HOTEL_SERVICE, AmenitySection.TRANSPORT, false),
                amenity("safe", of("Két an toàn", "Safe"), AmenityGroup.HOTEL_SERVICE, AmenitySection.BASIC_FACILITIES, false),
                amenity("atm", of("Máy ATM/Ngân hàng", "ATM/Banking"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("supermarket", of("Siêu thị", "Supermarket"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("shop", of("Cửa hàng thực phẩm", "Grocery"), AmenityGroup.NEARBY, AmenitySection.OTHER, false),
                amenity("parking", of("Bãi đậu xe", "Parking"), AmenityGroup.PUBLIC_AREA, AmenitySection.BASIC_FACILITIES, false),
                amenity("non_smoking_room", of("Phòng không hút thuốc", "Non-smoking room"), AmenityGroup.ROOM, AmenitySection.OTHER, false),
                amenity("doorman", of("Bảo vệ 24/7", "24-hour security"), AmenityGroup.HOTEL_SERVICE, AmenitySection.OTHER, false));

                List<RoomType> roomTypes = List.of(
                RoomType.builder()
                        .id("rt-vyvy-standard-double")
                        .name(of("Phòng Standard", "Standard Room"))
                        .shortDescription(of("Phòng Standard 18 m², 1 giường đôi.", "Standard Room 18 sqm, 1 double bed."))
                        .description(of("Phòng Standard 18 m², 1 giường đôi. Trang bị máy lạnh, TV, WiFi miễn phí và phòng tắm vòi sen riêng.", "Standard Room 18 sqm, 1 double bed. Equipped with air conditioning, a TV, free WiFi and a private shower bathroom."))
                        .areaSqm(18.0)
                        .bedType(BedType.DOUBLE)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường đôi", "1 double bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(8)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-e00b6deaaa0deb04139a9cf92876c5dc.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-92c249cb4167fc44486b07b1f8632925.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-vyvy-standard-double-prepaid-nonref")
                                        .name(of("Phòng Standard - Không gồm bữa sáng", "Standard Room - Room only"))
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
                                        .id("rt-vyvy-standard-double-breakfast-flex")
                                        .name(of("Phòng Standard - Bao gồm bữa sáng", "Standard Room - Breakfast included"))
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
                        .id("rt-vyvy-superior-double")
                        .name(of("Phòng Superior", "Superior Room"))
                        .shortDescription(of("Phòng Superior 22 m², 1 giường Queen.", "Superior Room 22 sqm, 1 queen bed."))
                        .description(of("Phòng Superior 22 m², 1 giường Queen. Trang bị máy lạnh, TV, WiFi miễn phí và phòng tắm vòi sen riêng.", "Superior Room 22 sqm, 1 queen bed. Equipped with air conditioning, a TV, free WiFi and a private shower bathroom."))
                        .areaSqm(22.0)
                        .bedType(BedType.QUEEN)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường Queen", "1 queen bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.QUEEN).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(0)
                        .maxGuests(2)
                        .totalRooms(8)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-7e83bc0a63ad327e85defc8a61dd1406.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-0b3d7233026f7c86f990cec82b09cd6a.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-vyvy-superior-double-prepaid-nonref")
                                        .name(of("Phòng Superior - Không gồm bữa sáng", "Superior Room - Room only"))
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
                                        .id("rt-vyvy-superior-double-breakfast-flex")
                                        .name(of("Phòng Superior - Bao gồm bữa sáng", "Superior Room - Breakfast included"))
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
                        .id("rt-vyvy-deluxe-king")
                        .name(of("Phòng Deluxe", "Deluxe Room"))
                        .shortDescription(of("Phòng Deluxe 26 m², 1 giường King.", "Deluxe Room 26 sqm, 1 king bed."))
                        .description(of("Phòng Deluxe 26 m², 1 giường King. Trang bị máy lạnh, TV, WiFi miễn phí và phòng tắm vòi sen riêng.", "Deluxe Room 26 sqm, 1 king bed. Equipped with air conditioning, a TV, free WiFi and a private shower bathroom."))
                        .areaSqm(26.0)
                        .bedType(BedType.KING)
                        .bedsCount(1)
                        .bedLayoutDescription(of("1 giường King", "1 king bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.KING).count(1).build()))
                        .maxAdults(2)
                        .maxChildren(1)
                        .maxGuests(3)
                        .totalRooms(6)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-525540c52dda4d61a47c3510bdffad9c.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-cb614225ff521e8ebd5804bd1559ef09.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-vyvy-deluxe-king-prepaid-nonref")
                                        .name(of("Phòng Deluxe - Không gồm bữa sáng", "Deluxe Room - Room only"))
                                        .boardType(BoardType.ROOM_ONLY)
                                        .paymentType(PaymentType.PREPAID)
                                        .refundable(false)
                                        .cancellationPolicy(of("Không được hoàn tiền.", "Non-refundable."))
                                        .pricePerNight(new BigDecimal("1100000"))
                                        .referencePricePerNight(new BigDecimal("1571429"))
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
                                        .id("rt-vyvy-deluxe-king-breakfast-flex")
                                        .name(of("Phòng Deluxe - Bao gồm bữa sáng", "Deluxe Room - Breakfast included"))
                                        .boardType(BoardType.BREAKFAST_INCLUDED)
                                        .paymentType(PaymentType.PAY_AT_HOTEL)
                                        .refundable(true)
                                        .cancellationPolicy(of("Áp dụng chính sách hủy phòng.", "Cancellation policy applies."))
                                        .pricePerNight(new BigDecimal("1265000"))
                                        .referencePricePerNight(new BigDecimal("1807143"))
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
                        .id("rt-vyvy-family-twin")
                        .name(of("Phòng Family", "Family Room"))
                        .shortDescription(of("Phòng Family 32 m², 1 giường đôi và 1 giường đơn.", "Family Room 32 sqm, 1 double bed and 1 single bed."))
                        .description(of("Phòng Family 32 m², 1 giường đôi và 1 giường đơn. Trang bị máy lạnh, TV, WiFi miễn phí và phòng tắm vòi sen riêng.", "Family Room 32 sqm, 1 double bed and 1 single bed. Equipped with air conditioning, a TV, free WiFi and a private shower bathroom."))
                        .areaSqm(32.0)
                        .bedType(BedType.MULTIPLE)
                        .bedsCount(2)
                        .bedLayoutDescription(of("1 giường đôi và 1 giường đơn", "1 double bed and 1 single bed"))
                        .bedOptions(List.of(
                                BedOption.builder().type(BedType.DOUBLE).count(1).build(),
                                BedOption.builder().type(BedType.SINGLE).count(1).build()))
                        .maxAdults(3)
                        .maxChildren(1)
                        .maxGuests(4)
                        .totalRooms(4)
                        .images(List.of(
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-d7a3736e88071dd541cda297a88d1383.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", true, 0),
                                img("https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10029910-85d9f4f9411b6eb4f18e5cdfdf83c2b5.jpeg?tr=q-80,c-at_max,w-1280,h-720&_src=imagekit", "Phòng ngủ", "Bedroom", false, 1)))
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
                                        .id("rt-vyvy-family-twin-prepaid-nonref")
                                        .name(of("Phòng Family - Không gồm bữa sáng", "Family Room - Room only"))
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
                                        .id("rt-vyvy-family-twin-breakfast-flex")
                                        .name(of("Phòng Family - Bao gồm bữa sáng", "Family Room - Breakfast included"))
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
                nearby("san-bay-tan-son-nhat", of("Sân bay Tân Sơn Nhất", "Tan Son Nhat Airport"), of("Trung tâm giao thông", "Transport hub"), 1800.0),
                nearby("cho-ben-thanh", of("Chợ Bến Thành", "Ben Thanh Market"), of("Địa điểm lân cận", "Nearby places"), 6500.0),
                nearby("nha-tho-duc-ba", of("Nhà thờ Đức Bà", "Notre-Dame Cathedral"), of("Địa điểm lân cận", "Nearby places"), 6800.0),
                nearby("buu-dien-thanh-pho", of("Bưu điện Thành phố", "Central Post Office"), of("Địa điểm lân cận", "Nearby places"), 6900.0),
                nearby("pho-di-bo-nguyen-hue", of("Phố đi bộ Nguyễn Huệ", "Nguyen Hue Walking Street"), of("Địa điểm lân cận", "Nearby places"), 7200.0),
                nearby("dinh-doc-lap", of("Dinh Độc Lập", "Independence Palace"), of("Địa điểm lân cận", "Nearby places"), 6200.0),
                nearby("bao-tang-chung-tich-chien-tranh", of("Bảo tàng Chứng tích Chiến tranh", "War Remnants Museum"), of("Khác", "Other"), 6000.0),
                nearby("toa-nha-bitexco", of("Tòa nhà Bitexco", "Bitexco Financial Tower"), of("Khác", "Other"), 7500.0),
                nearby("landmark-81", of("Landmark 81", "Landmark 81"), of("Khác", "Other"), 9500.0),
                nearby("pho-tay-bui-vien", of("Phố Tây Bùi Viện", "Bui Vien Street"), of("Trung tâm giải trí", "Entertainment hub"), 7000.0),
                nearby("thao-cam-vien-sai-gon", of("Thảo Cầm Viên Sài Gòn", "Saigon Zoo"), of("Khác", "Other"), 6300.0),
                nearby("chua-ngoc-hoang", of("Chùa Ngọc Hoàng", "Jade Emperor Pagoda"), of("Khác", "Other"), 5000.0),
                nearby("cho-binh-tay", of("Chợ Bình Tây", "Binh Tay Market"), of("Địa điểm lân cận", "Nearby places"), 9000.0),
                nearby("cau-mong", of("Cầu Mống", "Mong Bridge"), of("Khác", "Other"), 7300.0));

                GeneralInfo generalInfo = GeneralInfo.builder()
                                .mainFacilitiesSummary(of("Nhà hàng, lễ tân 24h, máy lạnh, thang máy, WiFi miễn phí", "Restaurant, 24-hour front desk, air conditioning, elevator, free WiFi"))
                                .distanceToCityCenterKm(6.5)
                                .popularAreaSummary(of("Sân bay Tân Sơn Nhất, Quận Tân Bình, Chợ Bến Thành", "Tan Son Nhat Airport, Tan Binh District, Ben Thanh Market"))
                                .totalRooms(26)
                                .totalFloors(6)
                                .otherHighlightFacilities(of("Lễ tân 24h, đưa đón sân bay, giữ hành lý, giặt ủi, đổi ngoại tệ.", "24-hour front desk, airport transfer, luggage storage, laundry, currency exchange."))
                                .interestingPlacesSummary(of("Chợ Bến Thành, Nhà thờ Đức Bà, Dinh Độc Lập", "Ben Thanh Market, Notre-Dame Cathedral, Independence Palace"))
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
                                                                .title(of("Vị trí gần sân bay", "Near the airport"))
                                                                .content(of("Khách sạn nằm gần sân bay Tân Sơn Nhất, phù hợp cho khách có chuyến bay sớm hoặc khuya.", "The hotel is located near Tan Son Nhat Airport, ideal for guests with early or late flights."))
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
                faq(of("Những tiện ích tại Vy Vy Airport Hotel?", "What amenities does Vy Vy Airport Hotel offer?"),
                        of("Các tiện ích bao gồm máy lạnh, nhà hàng, lễ tân 24h, thang máy, WiFi miễn phí, giữ hành lý và dịch vụ giặt ủi (một số dịch vụ có thể thu thêm phí).", "Amenities include air conditioning, a restaurant, a 24-hour front desk, an elevator, free WiFi, luggage storage and laundry service (some services may require an additional fee).")),
                faq(of("Thời gian nhận phòng và trả phòng của Vy Vy Airport Hotel?", "What are the check-in and check-out times at Vy Vy Airport Hotel?"),
                        of("Thời gian nhận phòng từ 14:00 và trả phòng trước 12:00.", "Check-in is from 14:00 and check-out is before 12:00.")),
                faq(of("Khách sạn Vy Vy Airport Hotel đạt tiêu chuẩn mấy sao?", "What star rating does Vy Vy Airport Hotel have?"),
                        of("Vy Vy Airport Hotel là khách sạn đạt tiêu chuẩn 3 sao.", "Vy Vy Airport Hotel is a 3-star hotel.")),
                faq(of("Vy Vy Airport Hotel có bao nhiêu tầng lầu?", "How many floors does Vy Vy Airport Hotel have?"),
                        of("Vy Vy Airport Hotel có tổng cộng 6 tầng.", "Vy Vy Airport Hotel has a total of 6 floors.")),
                faq(of("Tổng số phòng hiện có tại Vy Vy Airport Hotel là bao nhiêu?", "How many rooms does Vy Vy Airport Hotel have?"),
                        of("Tổng số phòng tại Vy Vy Airport Hotel là 26 phòng.", "Vy Vy Airport Hotel has a total of 26 rooms.")),
                faq(of("Vy Vy Airport Hotel có hỗ trợ nhận phòng sớm không?", "Does Vy Vy Airport Hotel support early check-in?"),
                        of("Có, khách sạn hỗ trợ nhận phòng sớm tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn để sắp xếp.", "Yes, the hotel supports early check-in subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("Vy Vy Airport Hotel có cung cấp dịch vụ trả phòng trễ không?", "Does Vy Vy Airport Hotel offer late check-out?"),
                        of("Có, khách sạn hỗ trợ trả phòng trễ tùy theo tình trạng phòng trống. Vui lòng liên hệ trực tiếp với khách sạn để sắp xếp.", "Yes, the hotel supports late check-out subject to availability. Please contact the hotel directly to arrange.")),
                faq(of("Vy Vy Airport Hotel cách sân bay Tân Sơn Nhất bao xa?", "How far is Vy Vy Airport Hotel from Tan Son Nhat Airport?"),
                        of("Khách sạn nằm rất gần sân bay quốc tế Tân Sơn Nhất, chỉ khoảng vài phút di chuyển bằng taxi hoặc xe công nghệ.", "The hotel is very close to Tan Son Nhat International Airport, just a few minutes away by taxi or ride-hailing.")),
                faq(of("Vy Vy Airport Hotel có chỗ đậu xe không?", "Does Vy Vy Airport Hotel have parking?"),
                        of("Có, khách sạn có khu vực đậu xe cho khách lưu trú.", "Yes, the hotel has a parking area for guests.")),
                faq(of("Vy Vy Airport Hotel có phục vụ bữa sáng không?", "Does Vy Vy Airport Hotel serve breakfast?"),
                        of("Có, khách sạn phục vụ bữa sáng. Bạn cần chọn loại giá phòng bao gồm bữa sáng; nếu không có thể phải trả thêm phí.", "Yes, the hotel serves breakfast. You need to select a rate that includes breakfast; otherwise an additional fee may apply.")));

                return HotelDoc.builder()
                                .active(true)
                                .destinationSlug("ho-chi-minh-city")
                                .cityName(of("Hồ Chí Minh", "Ho Chi Minh City"))
                                .districtName(of("Tân Bình", "Tan Binh"))
                                .wardName(of("Phường 2", "Ward 2"))
                                .addressLine(of("283 Hoàng Văn Thụ, Phường 2, Quận Tân Bình, Thành phố Hồ Chí Minh, Việt Nam, 700000", "283 Hoang Van Thu, Ward 2, Tan Binh District, Ho Chi Minh City, Vietnam, 700000"))
                                .location(new double[] { 106.662511, 10.800462 })
                                .name(of("Vy Vy Airport Hotel", "Vy Vy Airport Hotel"))
                                .slug(slug)
                                .hotelType(HotelType.HOTEL)
                                .starRating(3)
                                .shortDescription(of("Khách sạn 3 sao gần sân bay Tân Sơn Nhất, Quận Tân Bình, TP. Hồ Chí Minh.", "3-star hotel near Tan Son Nhat Airport, Tan Binh District, Ho Chi Minh City."))
                                .description(of("Vy Vy Airport Hotel là khách sạn 3 sao tại Quận Tân Bình, gần sân bay Tân Sơn Nhất, với phòng ốc sạch sẽ, lễ tân 24h và dịch vụ thuận tiện cho khách công tác và quá cảnh.", "Vy Vy Airport Hotel is a 3-star hotel in Tan Binh District, near Tan Son Nhat Airport, with clean rooms, a 24-hour front desk and convenient services for business and transit travelers."))
                                .defaultCheckInTime(LocalTime.of(14, 0))
                                .defaultCheckOutTime(LocalTime.of(12, 0))
                                .hasOnlineCheckin(false)
                                .minNightlyPrice(new BigDecimal("750000"))
                                .currencyCode("VND")
                                .referenceNightlyPrice(new BigDecimal("1071429"))
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
