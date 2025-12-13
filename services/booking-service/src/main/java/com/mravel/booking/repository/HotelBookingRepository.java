package com.mravel.booking.repository;

import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {

    Optional<HotelBooking> findByCode(String code);

    boolean existsByCode(String code);

    List<HotelBooking> findByStatusAndCreatedAtBefore(
            BookingBase.BookingStatus status,
            Instant cutoff
    );
}