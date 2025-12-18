package com.mravel.booking.repository;

import com.mravel.booking.model.Payment;
import com.mravel.booking.model.BookingBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

  Optional<Payment> findByProviderRequestId(String providerRequestId);

  List<Payment> findByBookingAndStatusOrderByCreatedAtDesc(
      BookingBase booking,
      Payment.PaymentStatus status
  );

  List<Payment> findByBookingAndStatus(
      BookingBase booking,
      Payment.PaymentStatus status
  );
}