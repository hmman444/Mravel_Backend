// src/main/java/com/mravel/booking/repository/RestaurantBookingRepository.java
package com.mravel.booking.repository;

import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.RestaurantBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface RestaurantBookingRepository extends JpaRepository<RestaurantBooking, Long> {
  Optional<RestaurantBooking> findByCode(String code);

  List<RestaurantBooking> findByUserIdOrderByCreatedAtDesc(Long userId);
  List<RestaurantBooking> findByGuestSessionIdOrderByCreatedAtDesc(String guestSessionId);

  List<RestaurantBooking> findByPaymentStatusAndStatusAndCreatedAtBefore(
      RestaurantBooking.PaymentStatus paymentStatus,
      RestaurantBooking.BookingStatus status,
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
}