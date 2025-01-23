package com.demo.CMS.Repository;

import com.demo.CMS.Models.ClaimsSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimSummaryRepository extends JpaRepository<ClaimsSummary, Long> {

    ClaimsSummary findTopByOrderByReportGeneratedDesc();
}
