// src/main/java/com/mravel/booking/repository/BookingRoomRepository.java
package com.mravel.booking.repository;

import com.mravel.booking.model.BookingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRoomRepository extends JpaRepository<BookingRoom, Long> {
}