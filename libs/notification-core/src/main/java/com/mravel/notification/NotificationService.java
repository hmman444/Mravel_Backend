package com.mravel.notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otpCode) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("🔐 Mã xác thực OTP của bạn - Mravel");

            // 🎨 Nội dung HTML cho email OTP
            String htmlContent = """
                        <div style="font-family: 'Segoe UI', Arial, sans-serif; background-color: #f4f7fb; padding: 20px;">
                            <div style="max-width: 600px; margin: 0 auto; background: white; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 10px rgba(0,0,0,0.08);">
                                <div style="background: linear-gradient(to right, #2563eb, #3b82f6); color: white; text-align: center; padding: 20px 10px;">
                                    <h1 style="margin: 0; font-size: 22px;">Mravel - Xác Thực Tài Khoản</h1>
                                </div>
                                <div style="padding: 30px; text-align: center;">
                                    <p style="font-size: 16px; color: #333;">Xin chào,</p>
                                    <p style="font-size: 16px; color: #333;">Mã OTP của bạn để đặt lại mật khẩu là:</p>
                                    <h2 style="font-size: 36px; color: #2563eb; letter-spacing: 4px; margin: 20px 0;">%s</h2>
                                    <p style="font-size: 15px; color: #666;">Mã OTP có hiệu lực trong <b>5 phút</b>. Vui lòng không chia sẻ mã này với bất kỳ ai.</p>
                                    <p style="font-size: 14px; color: #999; margin-top: 30px;">Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.</p>
                                </div>
                                <div style="background: #f4f7fb; text-align: center; padding: 15px; font-size: 13px; color: #999;">
                                    © 2025 Mravel Team. All rights reserved.
                                </div>
                            </div>
                        </div>
                    """
                    .formatted(otpCode);

            helper.setText(htmlContent, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Không thể gửi email OTP: " + e.getMessage());
        }
    }
}
