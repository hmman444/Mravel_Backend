package com.mravel.plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    // mail mời tham gia kế hoạch
    public void sendInviteEmail(String email, String planTitle, String joinUrl) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(email);
            msg.setSubject("Mravel - Lời mời tham gia kế hoạch: " + planTitle);
            msg.setText("Bạn được mời tham gia vào kế hoạch: " + planTitle + "\n\n" + "Nhấn vào link để tham gia:\n"
                    + joinUrl + "\n\n" + "Link này sẽ hết hạn sau 3 ngày.\n" + "Mravel Team");

            mailSender.send(msg);
        } catch (Exception ex) {
            System.err.println("[MAIL ERROR] Cannot send invite email: " + ex.getMessage());
        }
    }

    // mail yêu cầu truy cập kế hoạch
    public void sendRequestEmailToOwner(String ownerEmail, String planTitle,
            String requesterName, String requesterEmail,
            String requestType) {

        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(ownerEmail);
            msg.setSubject("Mravel - Yêu cầu truy cập mới");
            msg.setText("Người dùng: " + requesterName + " (" + requesterEmail + ")\n"
                    + "vừa gửi yêu cầu truy cập vào kế hoạch: " + planTitle + "\n\n" + "Loại quyền yêu cầu: "
                    + requestType + "\n\n" + "Hãy mở trang chia sẻ để xem và xử lý yêu cầu.\n" + "Mravel Team");

            mailSender.send(msg);
        } catch (Exception ex) {
            System.err.println("[MAIL ERROR] Cannot send request email: " + ex.getMessage());
        }
    }

    // gửi mail khi yêu cầu được chấp nhận
    public void sendApproveEmail(String targetEmail, String planTitle, String role) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(targetEmail);
        msg.setSubject("Mravel - Yêu cầu truy cập được chấp nhận");

        msg.setText(
                "Yêu cầu truy cập vào kế hoạch \"" + planTitle + "\" đã được chấp nhận.\n\n" +
                        "Bạn được cấp quyền: " + role + "\n" +
                        "Bạn có thể truy cập plan ngay bây giờ.\n\n" +
                        "Mravel Team");

        mailSender.send(msg);
    }

    // gửi mail khi yêu cầu bị từ chối
    public void sendRejectEmail(String targetEmail, String planTitle) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(targetEmail);
        msg.setSubject("Mravel - Yêu cầu truy cập bị từ chối");

        msg.setText(
                "Yêu cầu truy cập vào kế hoạch \"" + planTitle + "\" đã bị từ chối.\n\n" +
                        "Nếu bạn nghĩ đây là nhầm lẫn, vui lòng liên hệ chủ sở hữu kế hoạch.\n" +
                        "Mravel Team");

        mailSender.send(msg);
    }
}
