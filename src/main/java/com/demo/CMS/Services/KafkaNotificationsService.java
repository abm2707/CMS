package com.demo.CMS.Services;

import com.demo.CMS.DTOs.ClaimStatusUpdateMessage;
import com.demo.CMS.Models.APEmails;
import com.demo.CMS.Utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KafkaNotificationsService {

    private static final String topic_name = "claim-status-update";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    Utilities utilities;

    private static final Logger logger = LoggerFactory.getLogger(KafkaNotificationsService.class);

    public void sendNotificationForClaimStatusChange(Long ClaimId, String newStatus, String userEmail){
        // Passing the parameters to POJO.
        ClaimStatusUpdateMessage msg = new ClaimStatusUpdateMessage(ClaimId, newStatus, "bakhilmenon@gmail.com");

        // Using the kafka template to send this topic name to listener.
        kafkaTemplate.send("claim-status-update",msg);
    }

    public void sendEmailOtp(String otp, String recipient_email){
        // Using the kafka template to send this topic name to listener.
        System.out.println("Inside send Email ot method");
        String emailBody = utilities.getEmailBody(1,recipient_email);
        kafkaTemplate.send("login-otp-topic",emailBody,recipient_email);
    }
}
