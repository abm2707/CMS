package com.demo.CMS.DTOs;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ValidateOTP {
    String generatedOtp;
    String enteredOtp;
    String userName;
}
