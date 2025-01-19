package com.demo.CMS.DTOs;

import lombok.Data;
import java.util.Date;

@Data
public class ClaimsDTO {
    private Long claimId;
    private Long userId;
    private String emailId;
    private Date claimDate;
    private Double claimAmount;
    private String claimType;
    private String claimStatus;
    private Date lastUpdated;

    public void ClaimsSummaryDTO() {}

    public ClaimsDTO(Long claimId, Long userId, Date claimDate, Double claimAmount, String claimType, Date lastUpdated) {
        this.claimId = claimId;
        this.userId = userId;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.claimType = claimType;
        this.claimStatus = claimStatus;
        this.lastUpdated = lastUpdated;
    }
}
