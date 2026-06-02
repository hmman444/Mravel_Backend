// src/main/java/com/mravel/booking/repository/RestaurantBookingRepository.java
package com.mravel.booking.repository;

import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.RestaurantBooking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RestaurantBookingRepository extends JpaRepository<RestaurantBooking, Long> {
  Optional<RestaurantBooking> findByCode(String code);

  List<RestaurantBooking> findByUserIdOrderByCreatedAtDesc(Long userId);
  List<RestaurantBooking> findByGuestSessionIdOrderByCreatedAtDesc(String guestSessionId);

  List<RestaurantBooking> findByPaymentStatusAndStatusAndCreatedAtBefore(
      BookingBase.PaymentStatus paymentStatus,
      BookingBase.BookingStatus status,
      Instant before
  );
  
  @Query("""
    select b from RestaurantBooking b
    left join fetch b.tables t
    where b.code = :code
  """)
  Optional<RestaurantBooking> findWithTablesByCode(@Param("code") String code);

  @Query("""
    select distinct b from RestaurantBooking b
    left join fetch b.tables t
    where b.paymentStatus = :ps
      and b.status = :st
      and b.createdAt < :cutoff
  """)
  List<RestaurantBooking> findPendingsWithTables(
      @Param("ps") BookingBase.PaymentStatus ps,
      @Param("st") BookingBase.BookingStatus st,
      @Param("cutoff") Instant cutoff
  );

  Optional<RestaurantBooking> findByPendingPaymentOrderId(String orderId);

  List<RestaurantBooking> findByRestaurantIdInOrderByCreatedAtDesc(List<String> restaurantIds);

  List<RestaurantBooking> findByRestaurantIdInAndStatusOrderByCreatedAtDesc(
      List<String> restaurantIds,
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

  @Query("select coalesce(sum(b.amountPaid), 0) from RestaurantBooking b "
      + "where b.paymentStatus = :ps and b.createdAt between :start and :end")
  BigDecimal sumAmountPaid(@Param("ps") BookingBase.PaymentStatus ps,
      @Param("start") Instant start, @Param("end") Instant end);

  @Query("select b.status, count(b) from RestaurantBooking b "
      + "where b.createdAt between :start and :end group by b.status")
  List<Object[]> countGroupByStatus(@Param("start") Instant start, @Param("end") Instant end);

  @Query("select b.restaurantId, b.restaurantName, count(b), coalesce(sum(b.amountPaid), 0) from RestaurantBooking b "
      + "where b.paymentStatus = :ps and b.createdAt between :start and :end "
      + "group by b.restaurantId, b.restaurantName order by coalesce(sum(b.amountPaid), 0) desc")
  List<Object[]> topServices(@Param("ps") BookingBase.PaymentStatus ps,
      @Param("start") Instant start, @Param("end") Instant end, Pageable pageable);

  @Query("select b.createdAt, b.amountPaid from RestaurantBooking b "
      + "where b.paymentStatus = :ps and b.createdAt between :start and :end")
  List<Object[]> revenueRows(@Param("ps") BookingBase.PaymentStatus ps,
      @Param("start") Instant start, @Param("end") Instant end);
}