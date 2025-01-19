package com.demo.CMS.Config;

import com.demo.CMS.DTOs.ClaimStatusUpdateMessage;
import com.demo.CMS.DTOs.LoginOtpMessage;
import com.demo.CMS.Services.EmailService;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.HashMap;
import java.util.Map;

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

    @KafkaListener(topics = "claim-updates", groupId = "group_id")
    public void handleClaimStatusUpdate(ClaimStatusUpdateMessage message) {
        // Example email sending function call
        emailService.sendEmail(message.getUserEmail(), "Claim Status Update",
                "Your claim #" + message.getClaimId() + " has been updated to: " + message.getNewStatus());
    }

    @KafkaListener(topics = "login-otp-topic", groupId = "group_id")
    public void handleLoginUpdate(@RequestBody LoginOtpMessage message) {
        // Example email sending function call
        emailService.sendEmail(message.getEmail(), "Login Update",
                "You have logged into the portal. If not, kindly contact customer care.");
    }
}