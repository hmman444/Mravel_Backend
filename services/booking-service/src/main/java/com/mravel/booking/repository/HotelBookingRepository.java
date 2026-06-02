// src/main/java/com/mravel/booking/repository/HotelBookingRepository.java
package com.mravel.booking.repository;

import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.HotelBooking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {

    Optional<HotelBooking> findByCode(String code);

    @EntityGraph(attributePaths = "rooms")
    Optional<HotelBooking> findWithRoomsByCode(String code);

    boolean existsByCode(String code);

    List<HotelBooking> findByStatusAndCreatedAtBefore(
            BookingBase.BookingStatus status,
            Instant cutoff
    );

    List<HotelBooking> findByGuestSessionIdOrderByCreatedAtDesc(String guestSessionId);

    List<HotelBooking> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<HotelBooking> findByGuestSessionIdAndUserIdIsNullOrderByCreatedAtDesc(String guestSessionId);

    Optional<HotelBooking> findByPendingPaymentOrderId(String orderId);

    List<HotelBooking> findByHotelIdInOrderByCreatedAtDesc(List<String> hotelIds);

    List<HotelBooking> findByHotelIdInAndStatusOrderByCreatedAtDesc(
        List<String> hotelIds,
        BookingBase.BookingStatus status
    );

    // ===== Admin dashboard aggregates =====

    long countByCreatedAtBetween(Instant start, Instant end);

    long countByStatusInAndCreatedAtBetween(
            Collection<BookingBase.BookingStatus> statuses, Instant start, Instant end);

    long countByPaymentStatusAndCreatedAtBetween(
            BookingBase.PaymentStatus paymentStatus, Instant start, Instant end);

    long countByPaymentStatusInAndCreatedAtBetween(
            Collection<BookingBase.PaymentStatus> paymentStatuses, Instant start, Instant end);

    @Query("select coalesce(sum(b.amountPaid), 0) from HotelBooking b "
            + "where b.paymentStatus = :ps and b.createdAt between :start and :end")
    BigDecimal sumAmountPaid(@Param("ps") BookingBase.PaymentStatus ps,
            @Param("start") Instant start, @Param("end") Instant end);

    @Query("select b.status, count(b) from HotelBooking b "
            + "where b.createdAt between :start and :end group by b.status")
    List<Object[]> countGroupByStatus(@Param("start") Instant start, @Param("end") Instant end);

    @Query("select b.hotelId, b.hotelName, count(b), coalesce(sum(b.amountPaid), 0) from HotelBooking b "
            + "where b.paymentStatus = :ps and b.createdAt between :start and :end "
            + "group by b.hotelId, b.hotelName order by coalesce(sum(b.amountPaid), 0) desc")
    List<Object[]> topServices(@Param("ps") BookingBase.PaymentStatus ps,
            @Param("start") Instant start, @Param("end") Instant end, Pageable pageable);

    @Query("select b.createdAt, b.amountPaid from HotelBooking b "
            + "where b.paymentStatus = :ps and b.createdAt between :start and :end")
    List<Object[]> revenueRows(@Param("ps") BookingBase.PaymentStatus ps,
            @Param("start") Instant start, @Param("end") Instant end);
}