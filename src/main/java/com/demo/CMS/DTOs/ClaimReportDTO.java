package com.demo.CMS.DTOs;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.Date;

@Data
public class ClaimReportDTO {
    private String claimStatus;
    private Long totalClaims;
    private Double totalClaimAmount;

    public ClaimReportDTO() {}

    public ClaimReportDTO(String claimStatus, Long totalClaims, Double totalClaimAmount) {
        this.claimStatus = claimStatus;
        this.totalClaims = totalClaims;
        this.totalClaimAmount = totalClaimAmount;
    }
}
