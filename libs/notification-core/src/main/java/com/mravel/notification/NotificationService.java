package com.mravel.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender mailSender;

    @org.springframework.beans.factory.annotation.Value("${spring.mail.username:}")
    private String mailFrom;

    public void sendOtpEmail(String toEmail, String otpCode) {
        try {
            String from = (mailFrom != null && !mailFrom.isBlank()) ? mailFrom : "no-reply@mravel.local";

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(toEmail);
            msg.setSubject("🔐 Mã xác thực OTP của bạn - Mravel");
            msg.setText("Xin chao,\n\nMa OTP cua ban la: " + otpCode + "\nMa co hieu luc trong 5 phut.\n\nMravel Team");

            mailSender.send(msg);
            log.info("OTP email sent to {}", toEmail);
        } catch (Exception ex) {
            log.error("Cannot send OTP email to {}: {}", toEmail, ex.getMessage(), ex);
        }
    }

    /**
     * Test SMTP connection using the configured JavaMailSenderImpl (returns true if
     * connection successful)
     */
    public boolean testSmtpConnection() {
        if (!(mailSender instanceof JavaMailSenderImpl)) {
            log.warn("mailSender is not a JavaMailSenderImpl, cannot test connection");
            return false;
        }

        JavaMailSenderImpl impl = (JavaMailSenderImpl) mailSender;
        try {
            impl.testConnection();
            log.info("SMTP test connection OK (host={})", impl.getHost());
            return true;
        } catch (Exception ex) {
            log.error("SMTP test connection failed: {}", ex.getMessage(), ex);
            return false;
        }
    }
}
