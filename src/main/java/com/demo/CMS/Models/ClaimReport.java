package com.demo.CMS.Models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "claim_reports")
@Data
public class ClaimReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String claimStatus;

    @Column(name = "total_claims")
    private Long totalClaims;

    @Column(name = "total_amount")
    private Double totalClaimAmount;

    @Column(name = "report_date")
    private Date reportDate;
}

