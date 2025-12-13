// src/main/java/com/mravel/booking/repository/PaymentRepository.java
package com.mravel.booking.repository;

import com.mravel.booking.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}