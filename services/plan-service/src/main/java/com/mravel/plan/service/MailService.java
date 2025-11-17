package com.mravel.plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendInviteEmail(String email, String planTitle, String joinUrl) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Mravel - Lời mời tham gia kế hoạch: " + planTitle);

        msg.setText(
                "Bạn được mời tham gia vào kế hoạch: " + planTitle + "\n\n" +
                        "Nhấn vào link để tham gia:\n" + joinUrl + "\n\n" +
                        "Link này sẽ hết hạn sau 3 ngày.\n" +
                        "Mravel Team");

        mailSender.send(msg);
    }
}
