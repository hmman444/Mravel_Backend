package com.mravel.auth.service;

import com.mravel.auth.model.OtpToken;
import com.mravel.auth.repository.OtpTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final OtpTokenRepository otpTokenRepository;
    
    @Transactional
    public String generateOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        OtpToken token = new OtpToken(null, email, otp, LocalDateTime.now().plusMinutes(5));
        otpTokenRepository.deleteByEmail(email);
        otpTokenRepository.save(token);
        return otp;
    }

    public boolean validateOtp(String email, String otpCode) {
        return otpTokenRepository.findByEmailAndOtpCode(email, otpCode)
                .filter(t -> t.getExpiryDate().isAfter(LocalDateTime.now()))
                .isPresent();
    }
}
