package com.mravel.auth.service;

import com.mravel.auth.config.JwtUtil;
import com.mravel.auth.model.RefreshToken;
import com.mravel.auth.repository.RefreshTokenRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public RefreshToken createToken(String email) {
        String token = jwtUtil.generateRefreshToken(email);

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .email(email)
                .expiryDate(Instant.now().plusMillis(jwtUtil.getRefreshTokenExpiration()))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public boolean validate(String token) {
        if (!jwtUtil.validateToken(token)) {
            return false;
        }
        RefreshToken stored = refreshTokenRepository.findByToken(token).orElse(null);
        if (stored == null) {
            return false;
        }
        if (stored.getExpiryDate() == null || stored.getExpiryDate().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Refresh token expired");
        }
        return true;
    }

    @Transactional
    public void deleteByEmail(String email) {
        refreshTokenRepository.deleteByEmail(email);
    }

    @Transactional
    public void deleteByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
