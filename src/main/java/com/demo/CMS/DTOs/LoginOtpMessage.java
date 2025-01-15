package com.demo.CMS.DTOs;

import lombok.Data;

@Data
public class LoginOtpMessage {
    private String email;
    private String otp;

    // Default constructor
    public LoginOtpMessage() {
    }
}
