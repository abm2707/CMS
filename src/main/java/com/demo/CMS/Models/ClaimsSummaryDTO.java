package com.demo.CMS.Models;

import lombok.Data;
import java.util.Date;

@Data
public class ClaimsSummaryDTO {
    private Date reportGenerated;
    private Integer numberOfClaims;
    private Double totalAmount;

    public ClaimsSummaryDTO() {}

    public ClaimsSummaryDTO(Date reportGenerated, Integer numberOfClaims, Double totalAmount) {
        this.reportGenerated = reportGenerated;
        this.numberOfClaims = numberOfClaims;
        this.totalAmount = totalAmount;
    }
}
