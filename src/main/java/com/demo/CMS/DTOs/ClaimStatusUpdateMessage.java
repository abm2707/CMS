package com.demo.CMS.DTOs;

import lombok.Data;

@Data
public class ClaimStatusUpdateMessage {
    private Long claimId;
    private String newStatus;
    private String userEmail;

    public ClaimStatusUpdateMessage() {
    }

    public ClaimStatusUpdateMessage(Long claimId, String newStatus, String userEmail) {
        this.claimId = claimId;
        this.newStatus = newStatus;
        this.userEmail = userEmail;
    }
}
