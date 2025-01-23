package com.demo.CMS.Repository;

import com.demo.CMS.Models.ClaimReport;
import com.demo.CMS.Models.ClaimsSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimReportRepository extends JpaRepository<ClaimReport,Long> {

}
