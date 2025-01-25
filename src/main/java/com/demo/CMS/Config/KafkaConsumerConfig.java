package com.demo.CMS.Config;

import com.demo.CMS.DTOs.ClaimStatusUpdateMessage;
import com.demo.CMS.Services.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import org.apache.kafka.common.serialization.StringDeserializer;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaConsumerConfig {
    @Autowired
    private EmailService emailService;

    @Bean
    public ConsumerFactory<String, ClaimStatusUpdateMessage> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        JsonDeserializer<ClaimStatusUpdateMessage> jsonDeserializer = new JsonDeserializer<>(ClaimStatusUpdateMessage.class);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ClaimStatusUpdateMessage> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ClaimStatusUpdateMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @KafkaListener(topics = "claim-status-update", groupId = "group_id")
    public void handleClaimStatusUpdate(ClaimStatusUpdateMessage message) throws MessagingException {
        // Example email sending function call
        System.out.println("Inside kafka listener !!");
        emailService.sendEmail(message.getUserEmail(), "claim-status-update",
                "Your claim #" + message.getClaimId() + " has been updated to: " + message.getNewStatus());
    }

    @KafkaListener(topics = "login-otp-topic", groupId = "group_id")
    public void handleLoginUpdate(String message) throws MessagingException {
        // Example email sending function call
        System.out.println("Inside kafka listener");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> data = objectMapper.readValue(message, new TypeReference<Map<String, String>>() {});
            String emailBody = data.get("emailBody");
            String recipient_email = data.get("recipient_email");

            System.out.println("Email Body: " + emailBody);
            System.out.println("Recipient Email: " + recipient_email);

            // Send email
            emailService.sendEmail("Login OTP", recipient_email, emailBody);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to parse message: " + message);
        }
    }

}