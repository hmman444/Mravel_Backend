package com.mravel.notification;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender mailSender;

    @org.springframework.beans.factory.annotation.Value("${spring.mail.username:}")
    private String mailFrom;

    public void sendOtpEmail(String toEmail, String otpCode) {
        // Guard null inputs để tránh Eclipse null-analysis warning + crash khi caller bất cẩn.
        if (toEmail == null || toEmail.isBlank()) {
            log.warn("sendOtpEmail called with null/blank toEmail; skip");
            return;
        }
        final String safeTo = toEmail;
        final String safeOtp = (otpCode == null) ? "" : otpCode;
        final String from = (mailFrom != null && !mailFrom.isBlank()) ? mailFrom : "no-reply@mravel.local";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setFrom(from);
            helper.setTo(safeTo);
            helper.setSubject("🔐 Mã xác thực Mravel — " + safeOtp);
            helper.setText(buildOtpHtml(safeOtp), true); // true = HTML

            mailSender.send(message);
            log.info("OTP email sent to {}", safeTo);
        } catch (Exception ex) {
            log.error("Cannot send OTP email to {}: {}", safeTo, ex.getMessage(), ex);
        }
    }

    private String buildOtpHtml(String otpCode) {
        return ("""
                <!DOCTYPE html>
                <html lang="vi">
                <head>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <title>Mã xác thực Mravel</title>
                </head>
                <body style="margin:0;padding:0;background-color:#f5f7fb;font-family:'Segoe UI','Helvetica Neue',Arial,sans-serif;color:#0f172a;">
                  <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" border="0" style="background-color:#f5f7fb;padding:40px 16px;">
                    <tr>
                      <td align="center">
                        <table role="presentation" width="600" cellpadding="0" cellspacing="0" border="0" style="max-width:600px;width:100%%;background-color:#ffffff;border-radius:16px;box-shadow:0 4px 24px rgba(15,23,42,0.06);overflow:hidden;">

                          <!-- Header với brand gradient -->
                          <tr>
                            <td style="padding:36px 40px 28px 40px;text-align:center;background:linear-gradient(135deg,#0ea5e9 0%%,#3b82f6 100%%);">
                              <div style="font-size:28px;font-weight:700;color:#ffffff;letter-spacing:0.5px;line-height:1.2;">
                                ✈️ Mravel
                              </div>
                              <div style="margin-top:6px;font-size:13px;color:rgba(255,255,255,0.88);letter-spacing:0.3px;">
                                Du lịch &amp; lưu trú thông minh
                              </div>
                            </td>
                          </tr>

                          <!-- Body -->
                          <tr>
                            <td style="padding:40px 40px 24px 40px;">
                              <h1 style="margin:0 0 14px 0;font-size:22px;font-weight:600;color:#0f172a;text-align:center;">
                                Mã xác thực của bạn
                              </h1>
                              <p style="margin:0 0 32px 0;font-size:15px;color:#475569;text-align:center;line-height:1.65;">
                                Xin chào,<br/>
                                Bạn vừa yêu cầu mã xác thực trên Mravel. Vui lòng nhập mã bên dưới để tiếp tục.
                              </p>

                              <!-- OTP code box -->
                              <div style="margin:0 auto 28px auto;padding:24px 16px;background-color:#f1f5f9;border:1px dashed #cbd5e1;border-radius:12px;text-align:center;">
                                <div style="font-family:'Courier New',monospace;font-size:38px;font-weight:700;letter-spacing:12px;color:#0ea5e9;line-height:1.1;">
                                  %s
                                </div>
                              </div>

                              <!-- Expire note -->
                              <div style="margin:0 0 24px 0;padding:14px 16px;background-color:#fffbeb;border-left:4px solid #f59e0b;border-radius:8px;">
                                <p style="margin:0;font-size:14px;color:#92400e;line-height:1.55;">
                                  ⏱️ <strong>Mã có hiệu lực trong 5 phút.</strong> Vui lòng nhập mã trước khi hết hạn.
                                </p>
                              </div>

                              <!-- Disclaimer -->
                              <p style="margin:0;font-size:13px;color:#94a3b8;line-height:1.65;text-align:center;">
                                Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email — tài khoản của bạn vẫn an toàn.
                              </p>
                            </td>
                          </tr>

                          <!-- Footer -->
                          <tr>
                            <td style="padding:22px 40px 24px 40px;background-color:#f8fafc;border-top:1px solid #e2e8f0;text-align:center;">
                              <p style="margin:0 0 6px 0;font-size:13px;color:#64748b;line-height:1.55;">
                                Email này được gửi tự động — vui lòng không trả lời.
                              </p>
                              <p style="margin:0;font-size:12px;color:#94a3b8;">
                                © 2026 Mravel. Bản quyền thuộc về dự án.
                              </p>
                            </td>
                          </tr>

                        </table>

                        <p style="margin:16px 0 0 0;font-size:12px;color:#94a3b8;text-align:center;">
                          Cần hỗ trợ? Liên hệ:
                          <a href="mailto:support@mravel.vn" style="color:#0ea5e9;text-decoration:none;">support@mravel.vn</a>
                        </p>
                      </td>
                    </tr>
                  </table>
                </body>
                </html>
                """).formatted(otpCode);
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
