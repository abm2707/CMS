package com.demo.CMS.DTOs;

import lombok.Data;

@Data
public class UserCredentialsDTO {
    private String username;
    private String password;

    // Default constructor
    public UserCredentialsDTO() {}

    // Parameterized constructor
    public UserCredentialsDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
