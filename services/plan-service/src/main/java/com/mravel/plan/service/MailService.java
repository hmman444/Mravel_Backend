package com.mravel.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Gửi email là tác dụng phụ, không thuộc nghiệp vụ chính (mời/duyệt/từ chối).
 * Mọi phương thức đều {@code @Async} + bọc try/catch: lỗi SMTP chạy ở thread nền,
 * KHÔNG làm treo request, KHÔNG rollback giao dịch, KHÔNG trả 500. Cấu hình SMTP
 * đọc từ biến môi trường (xem application.yml) và có timeout để fail-fast khi cổng
 * bị chặn thay vì treo.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    /**
     * Địa chỉ người gửi. BẮT BUỘC set From khi gửi qua Gmail: nếu bỏ trống,
     * JavaMail suy ra người gửi từ địa chỉ local (vd {@code root@<hostname-container>})
     * và Gmail TỪ CHỐI (5xx) → mail "gửi mà không tới". auth-service gửi được vì
     * notification-core luôn set From = spring.mail.username; ở đây làm giống hệt.
     */
    @Value("${spring.mail.username:}")
    private String mailFrom;

    /** From an toàn: dùng username SMTP; fallback địa chỉ trung tính nếu chưa cấu hình. */
    private String resolveFrom() {
        return (mailFrom != null && !mailFrom.isBlank()) ? mailFrom : "no-reply@mravel.local";
    }

    // mail mời tham gia kế hoạch
    @Async
    public void sendInviteEmail(String email, String planTitle, String joinUrl) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(resolveFrom());
            msg.setTo(email);
            msg.setSubject("Mravel - Lời mời tham gia kế hoạch: " + planTitle);
            msg.setText("Bạn được mời tham gia vào kế hoạch: " + planTitle + "\n\n" + "Nhấn vào link để tham gia:\n"
                    + joinUrl + "\n\n" + "Link này sẽ hết hạn sau 3 ngày.\n" + "Mravel Team");

            mailSender.send(msg);
        } catch (Exception ex) {
            log.error("[MAIL] Cannot send invite email to {}: {}", email, ex.getMessage(), ex);
        }
    }

    // mail yêu cầu truy cập kế hoạch
    @Async
    public void sendRequestEmailToOwner(String ownerEmail, String planTitle,
            String requesterName, String requesterEmail,
            String requestType) {

        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(resolveFrom());
            msg.setTo(ownerEmail);
            msg.setSubject("Mravel - Yêu cầu truy cập mới");
            msg.setText("Người dùng: " + requesterName + " (" + requesterEmail + ")\n"
                    + "vừa gửi yêu cầu truy cập vào kế hoạch: " + planTitle + "\n\n" + "Loại quyền yêu cầu: "
                    + requestType + "\n\n" + "Hãy mở trang chia sẻ để xem và xử lý yêu cầu.\n" + "Mravel Team");

            mailSender.send(msg);
        } catch (Exception ex) {
            log.error("[MAIL] Cannot send request email: {}", ex.getMessage());
        }
    }

    // gửi mail khi yêu cầu được chấp nhận
    @Async
    public void sendApproveEmail(String targetEmail, String planTitle, String role) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(resolveFrom());
            msg.setTo(targetEmail);
            msg.setSubject("Mravel - Yêu cầu truy cập được chấp nhận");

            msg.setText(
                    "Yêu cầu truy cập vào kế hoạch \"" + planTitle + "\" đã được chấp nhận.\n\n" +
                            "Bạn được cấp quyền: " + role + "\n" +
                            "Bạn có thể truy cập plan ngay bây giờ.\n\n" +
                            "Mravel Team");

            mailSender.send(msg);
        } catch (Exception ex) {
            // Gửi mail là tác dụng phụ: không được làm hỏng việc duyệt yêu cầu (tránh
            // rollback + 500 khi SMTP lỗi). Chỉ log để chẩn đoán cấu hình mail.
            log.error("[MAIL] Cannot send approve email to {}: {}", targetEmail, ex.getMessage(), ex);
        }
    }

    // gửi mail khi yêu cầu bị từ chối
    @Async
    public void sendRejectEmail(String targetEmail, String planTitle) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(resolveFrom());
            msg.setTo(targetEmail);
            msg.setSubject("Mravel - Yêu cầu truy cập bị từ chối");

            msg.setText(
                    "Yêu cầu truy cập vào kế hoạch \"" + planTitle + "\" đã bị từ chối.\n\n" +
                            "Nếu bạn nghĩ đây là nhầm lẫn, vui lòng liên hệ chủ sở hữu kế hoạch.\n" +
                            "Mravel Team");

            mailSender.send(msg);
        } catch (Exception ex) {
            log.error("[MAIL] Cannot send reject email to {}: {}", targetEmail, ex.getMessage(), ex);
        }
    }
}
