package com.demo.CMS.Services;

import com.demo.CMS.DTOs.ClaimsDTO;
import com.demo.CMS.Models.Claim;
import com.demo.CMS.Repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ClaimService {

    @Autowired
    ClaimRepository claimRepository;

    public ClaimsDTO generateClaim(Claim claim) {
        claim.setClaimDate(new Date());
        claim.setClaimStatus("Submitted");
        return convertToDTO(claimRepository.save(claim));
    }

    public ClaimsDTO convertToDTO(Claim claim){
        return new ClaimsDTO(claim.getClaimId(), claim.getUserId() != null ? claim.getUserId() : null,claim.getClaimDate(), claim.getClaimAmount(), claim.getClaimStatus(), claim.getLastUpdated());
    }

    public Claim getClaimByClaimId(Long claimId) {
        return claimRepository.findByClaimId(claimId).orElseThrow(()-> new RuntimeException("Claim Id could not be found !!"));
    }

    public ClaimsDTO updateClaim(Long claimId, Claim claim) {
        Claim claimVal = claimRepository.findById(claimId).orElseThrow(()-> new RuntimeException("User Not Found"));
        claimVal.setClaimStatus(claim.getClaimStatus());
        claimVal.setClaimDate(new Date());
        claimVal.setClaimType(claim.getClaimType());
        claimVal.setClaimAmount(claim.getClaimAmount());
        claimVal.setLastUpdated(claim.getLastUpdated());
        claimRepository.save(claimVal);
        return convertToDTO(claimVal);
    }

    public void deleteClaimById(Long claimId) {
        claimRepository.deleteById(claimId);
    }
}
