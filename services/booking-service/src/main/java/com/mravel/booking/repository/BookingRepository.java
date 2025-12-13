// src/main/java/com/mravel/booking/repository/BookingRepository.java
package com.mravel.booking.repository;

import com.mravel.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByCode(String code);

    boolean existsByCode(String code);

    List<Booking> findByTypeAndStatusAndCreatedAtBefore(
        Booking.BookingType type,
        Booking.BookingStatus status,
        Instant cutoff
    );
}