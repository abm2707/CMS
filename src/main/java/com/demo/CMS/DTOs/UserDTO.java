package com.demo.CMS.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String role;
    private String status;

    // Constructors, getters, and setters
    public UserDTO() {}

    public UserDTO(Long userId, String username, String email, String role, String status) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
    }
}


