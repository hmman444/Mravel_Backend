package com.mravel.booking.controller;

import com.mravel.booking.client.AuthValidateClient;
import com.mravel.booking.client.CatalogPartnerIdsClient;
import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.HotelBooking;
import com.mravel.booking.model.RestaurantBooking;
import com.mravel.booking.repository.HotelBookingRepository;
import com.mravel.booking.repository.RestaurantBookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PartnerBookingController.class)
@AutoConfigureMockMvc(addFilters = false)
class PartnerBookingControllerTest {

    @Autowired MockMvc mvc;
    @MockBean AuthValidateClient authValidateClient;
    @MockBean CatalogPartnerIdsClient catalogPartnerIdsClient;
    @MockBean HotelBookingRepository hotelRepo;
    @MockBean RestaurantBookingRepository restaurantRepo;

    private static final String BEARER = "Bearer partner-jwt";
    private static final Long PARTNER_ID = 5L;
    private static final String HOTEL_ID = "hotel-1";
    private static final String RESTAURANT_ID = "rest-1";

    private HotelBooking buildHotel(String code) {
        return HotelBooking.builder()
                .code(code)
                .hotelId(HOTEL_ID)
                .hotelName("Test Hotel")
                .hotelSlug("test-hotel")
                .contactName("Guest")
                .contactPhone("0901234567")
                .status(BookingBase.BookingStatus.PAID)
                .paymentStatus(BookingBase.PaymentStatus.SUCCESS)
                .totalAmount(BigDecimal.valueOf(2400000))
                .depositAmount(BigDecimal.valueOf(720000))
                .amountPayable(BigDecimal.valueOf(720000))
                .amountPaid(BigDecimal.valueOf(720000))
                .checkInDate(LocalDate.now().plusDays(1))
                .checkOutDate(LocalDate.now().plusDays(3))
                .nights(2).roomsCount(1)
                .build();
    }

    private RestaurantBooking buildRestaurant(String code) {
        return RestaurantBooking.builder()
                .code(code)
                .restaurantId(RESTAURANT_ID)
                .restaurantName("Test Restaurant")
                .restaurantSlug("test-restaurant")
                .contactName("Guest")
                .contactPhone("0907654321")
                .status(BookingBase.BookingStatus.PAID)
                .paymentStatus(BookingBase.PaymentStatus.SUCCESS)
                .totalAmount(BigDecimal.valueOf(400000))
                .depositAmount(BigDecimal.valueOf(200000))
                .amountPayable(BigDecimal.valueOf(200000))
                .amountPaid(BigDecimal.valueOf(200000))
                .reservationDate(LocalDate.now().plusDays(1))
                .people(4)
                .build();
    }

    // ── GET /api/booking/partners/hotels ─────────────────────────────────────

    @Test
    void listHotel_returnsPagedList() throws Exception {
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyHotelIds(PARTNER_ID, BEARER)).thenReturn(List.of(HOTEL_ID));
        when(hotelRepo.findByHotelIdInOrderByCreatedAtDesc(List.of(HOTEL_ID)))
                .thenReturn(List.of(buildHotel("BK-001")));

        mvc.perform(get("/api/booking/partners/hotels")
                        .header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.totalElements").value(1));
    }

    @Test
    void listHotel_noHotels_returnsEmpty() throws Exception {
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyHotelIds(PARTNER_ID, BEARER)).thenReturn(List.of());

        mvc.perform(get("/api/booking/partners/hotels")
                        .header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalElements").value(0));
    }

    // ── GET /api/booking/partners/hotels/{code} ───────────────────────────────

    @Test
    void hotelDetail_ownerCanSee_returns200() throws Exception {
        HotelBooking h = buildHotel("BK-001");
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyHotelIds(PARTNER_ID, BEARER)).thenReturn(List.of(HOTEL_ID));
        when(hotelRepo.findWithRoomsByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(get("/api/booking/partners/hotels/BK-001")
                        .header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.code").value("BK-001"));
    }

    @Test
    void hotelDetail_notOwner_returns401() throws Exception {
        HotelBooking h = buildHotel("BK-001");
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyHotelIds(PARTNER_ID, BEARER)).thenReturn(List.of("other-hotel"));
        when(hotelRepo.findWithRoomsByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(get("/api/booking/partners/hotels/BK-001")
                        .header("Authorization", BEARER))
                .andExpect(status().isUnauthorized());
    }

    // ── POST /api/booking/partners/hotels/{code}/cancel ───────────────────────

    @Test
    void cancelHotel_success_returnsCancelledByPartner() throws Exception {
        HotelBooking h = buildHotel("BK-001");
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyHotelIds(PARTNER_ID, BEARER)).thenReturn(List.of(HOTEL_ID));
        when(hotelRepo.findByCode("BK-001")).thenReturn(Optional.of(h));
        when(hotelRepo.save(any())).thenReturn(h);

        mvc.perform(post("/api/booking/partners/hotels/BK-001/cancel")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"Hết phòng\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void cancelHotel_alreadyCancelled_returns409() throws Exception {
        HotelBooking h = buildHotel("BK-001");
        h.setStatus(BookingBase.BookingStatus.CANCELLED);
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyHotelIds(PARTNER_ID, BEARER)).thenReturn(List.of(HOTEL_ID));
        when(hotelRepo.findByCode("BK-001")).thenReturn(Optional.of(h));

        mvc.perform(post("/api/booking/partners/hotels/BK-001/cancel")
                        .header("Authorization", BEARER))
                .andExpect(status().isConflict());
    }

    // ── POST /api/booking/partners/restaurants/{code}/cancel ─────────────────

    @Test
    void cancelRestaurant_success_returnsCancelledByPartner() throws Exception {
        RestaurantBooking r = buildRestaurant("RB-001");
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyRestaurantIds(PARTNER_ID, BEARER)).thenReturn(List.of(RESTAURANT_ID));
        when(restaurantRepo.findByCode("RB-001")).thenReturn(Optional.of(r));
        when(restaurantRepo.save(any())).thenReturn(r);

        mvc.perform(post("/api/booking/partners/restaurants/RB-001/cancel")
                        .header("Authorization", BEARER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reason\":\"Hết bàn\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/booking/partners/restaurants ─────────────────────────────────

    @Test
    void listRestaurant_returnsPagedList() throws Exception {
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyRestaurantIds(PARTNER_ID, BEARER)).thenReturn(List.of(RESTAURANT_ID));
        when(restaurantRepo.findByRestaurantIdInOrderByCreatedAtDesc(List.of(RESTAURANT_ID)))
                .thenReturn(List.of(buildRestaurant("RB-001")));

        mvc.perform(get("/api/booking/partners/restaurants")
                        .header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalElements").value(1));
    }

    // ── GET /api/booking/partners/restaurants/{code} ──────────────────────────

    @Test
    void restaurantDetail_ownerCanSee_returns200() throws Exception {
        RestaurantBooking r = buildRestaurant("RB-001");
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyRestaurantIds(PARTNER_ID, BEARER)).thenReturn(List.of(RESTAURANT_ID));
        when(restaurantRepo.findWithTablesByCode("RB-001")).thenReturn(Optional.of(r));

        mvc.perform(get("/api/booking/partners/restaurants/RB-001")
                        .header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.code").value("RB-001"));
    }

    // ── GET /api/booking/partners/stats/status ────────────────────────────────

    @Test
    void statsByStatus_returns200WithCounts() throws Exception {
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyHotelIds(PARTNER_ID, BEARER)).thenReturn(List.of(HOTEL_ID));
        when(catalogPartnerIdsClient.listMyRestaurantIds(PARTNER_ID, BEARER)).thenReturn(List.of(RESTAURANT_ID));
        when(hotelRepo.findByHotelIdInOrderByCreatedAtDesc(List.of(HOTEL_ID)))
                .thenReturn(List.of(buildHotel("BK-001")));
        when(restaurantRepo.findByRestaurantIdInOrderByCreatedAtDesc(List.of(RESTAURANT_ID)))
                .thenReturn(List.of(buildRestaurant("RB-001")));

        mvc.perform(get("/api/booking/partners/stats/status")
                        .header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    // ── GET /api/booking/partners/stats/revenue ───────────────────────────────

    @Test
    void statsRevenue_noGroup_returnsHotelRestaurantTotal() throws Exception {
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyHotelIds(PARTNER_ID, BEARER)).thenReturn(List.of(HOTEL_ID));
        when(catalogPartnerIdsClient.listMyRestaurantIds(PARTNER_ID, BEARER)).thenReturn(List.of(RESTAURANT_ID));
        when(hotelRepo.findByHotelIdInOrderByCreatedAtDesc(List.of(HOTEL_ID)))
                .thenReturn(List.of(buildHotel("BK-001")));
        when(restaurantRepo.findByRestaurantIdInOrderByCreatedAtDesc(List.of(RESTAURANT_ID)))
                .thenReturn(List.of(buildRestaurant("RB-001")));

        mvc.perform(get("/api/booking/partners/stats/revenue")
                        .header("Authorization", BEARER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.TOTAL").exists());
    }

    @Test
    void statsRevenue_weeklyGroup_returnsSeries() throws Exception {
        when(authValidateClient.requirePartnerId(BEARER)).thenReturn(PARTNER_ID);
        when(catalogPartnerIdsClient.listMyHotelIds(PARTNER_ID, BEARER)).thenReturn(List.of(HOTEL_ID));
        when(catalogPartnerIdsClient.listMyRestaurantIds(PARTNER_ID, BEARER)).thenReturn(List.of(RESTAURANT_ID));
        when(hotelRepo.findByHotelIdInOrderByCreatedAtDesc(List.of(HOTEL_ID))).thenReturn(List.of());
        when(restaurantRepo.findByRestaurantIdInOrderByCreatedAtDesc(List.of(RESTAURANT_ID))).thenReturn(List.of());

        mvc.perform(get("/api/booking/partners/stats/revenue")
                        .header("Authorization", BEARER)
                        .param("group", "weekly"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(7));
    }
}
