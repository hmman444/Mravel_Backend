// src/main/java/com/mravel/booking/repository/HotelBookingRepository.java
package com.mravel.booking.repository;

import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.HotelBooking;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
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
}