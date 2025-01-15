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
@Table(name = "claims_summaries")
@Data
public class ClaimsSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_generated")
    private Date reportGenerated;

    @Column(name = "number_of_claims")
    private Integer numberOfClaims;

    @Column(name = "total_amount")
    private Double totalAmount;
}


