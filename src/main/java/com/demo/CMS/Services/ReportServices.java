package com.demo.CMS.Services;

import com.demo.CMS.Models.ClaimReport;
import com.demo.CMS.DTOs.ClaimReportDTO;
import com.demo.CMS.Models.ClaimsSummary;
import com.demo.CMS.Repository.ClaimReportRepository;
import com.demo.CMS.Repository.ClaimSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServices {

    @Autowired
    ClaimReportRepository claimReportRepository;

    @Autowired
    ClaimSummaryRepository claimSummaryRepository;

    public List<ClaimReportDTO> generateReportByStatus() {
        List<ClaimReport> reports = claimReportRepository.findAll();
        return reports.stream().map(this::convertToClaimReportDTO)
                                .collect(Collectors.toList());
    }

    private ClaimReportDTO convertToClaimReportDTO(ClaimReport report) {
        return new ClaimReportDTO(report.getClaimStatus(), report.getTotalClaims(), report.getTotalClaimAmount());
    }

    public ClaimsSummary generateClaimsSummary() {
        return claimSummaryRepository.findTopByOrderByReportGeneratedDesc();
    }
}
