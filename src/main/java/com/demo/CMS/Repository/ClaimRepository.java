package com.demo.CMS.Repository;

import com.demo.CMS.Models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    Optional<Claim> findByClaimId(Long claimId);

    Optional<Claim> deleteByClaimId(Long claimId);
}
