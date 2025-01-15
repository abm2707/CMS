package com.demo.CMS.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationDTO {
    private Long id;
    private Long userId;
    private String message;
    private Date timestamp;

    public void NotificationDTO(Long id, Long userId, String message, Date timestamp) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.timestamp = timestamp;
    }

}
