package com.demo.CMS.Config;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OTPGenerator {

    private final Random random = new Random();

    public String generateOTP() {
        int otp = 100000 + random.nextInt(900000); // Generates a 6-digit OTP
        return String.valueOf(otp);
    }
}
