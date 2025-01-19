package com.demo.CMS.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name = "claims")
@Data
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    @Column(name = "userId")
    private Long userId;

    @Column
    private Date claimDate;

    @Column
    private Double claimAmount;
    
    @Column
    private String emailId;

    @Column
    private String claimType;

    @Column
    private String claimStatus;

    @Column
    private Date lastUpdated;
}

