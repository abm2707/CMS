package com.demo.CMS.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequestMapping("/emailsend")
@RestController
public class EmailService {

    String fromEmail = "bakhilmenon@gmail.com";
    String fromEmailWithName = "Orient Insurance Co. Ltd. <" + fromEmail + ">";

    @Autowired
    private JavaMailSender emailSender;

    /*public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bakhilmenon@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }*/

    // Send OTP via Email
    public String sendEmail(String subject, String recipient_email, String emailBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //String setText = EmailBodyFormatter(templateId, accessId);
        helper.setTo(recipient_email);
        helper.setSubject(subject);
        helper.setFrom(fromEmailWithName);
        helper.setText(emailBody, true);
        emailSender.send(message);
        return "Success";
    }
}