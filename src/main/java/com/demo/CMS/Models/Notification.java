package com.demo.CMS.Models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "notifications")
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private String message;

    @Column
    private Date timestamp;

    // Constructors
    public Notification() {}

    public Notification(Long userId, String message, Date timestamp) {
        this.userId = userId;
        this.message = message;
        this.timestamp = timestamp;
    }
}

