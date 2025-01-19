package com.demo.CMS.Controllers;

import com.demo.CMS.DTOs.ClaimReportDTO;
import com.demo.CMS.Models.ClaimsSummaryDTO;
import com.demo.CMS.Services.ReportServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportControllers {

    @Autowired
    ReportServices reportServices;

    @GetMapping("/claims/status")
        public ResponseEntity<List<ClaimReportDTO>> getClaimsReportByStatus() {
        try {
            List<ClaimReportDTO> reports = reportServices.generateReportByStatus();
            return ResponseEntity.ok(reports);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

//    @GetMapping("/claims/summary")
//    public ResponseEntity<ClaimsSummaryDTO> getClaimsSummary() {
//        ClaimsSummaryDTO summary = reportServices.generateClaimsSummary();
//        return ResponseEntity.ok(summary);
//    }
}
