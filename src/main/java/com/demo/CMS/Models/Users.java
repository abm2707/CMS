package com.demo.CMS.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Date;


@Table(name = "users")
@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column
    private String username;

    @Column
    private String passwordHash;

    @Column
    private String email;

    @Column
    private String role;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @Column
    private Date lastLogin;

    @Column
    private String status;
}
