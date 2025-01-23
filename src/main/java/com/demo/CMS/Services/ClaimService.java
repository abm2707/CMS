package com.demo.CMS.Services;

import com.demo.CMS.DTOs.ClaimsDTO;
import com.demo.CMS.Models.Claim;
import com.demo.CMS.Repository.ClaimRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ClaimService {

    String oldStatus, claimId;
    String redisKey = "ClaimId"+claimId;
    private static final Logger logger = LoggerFactory.getLogger(ClaimService.class);

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    KafkaNotificationsService kafkaNotificationsService;

    @Autowired
    RedisTemplate redisTemplate;

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

    // Updating claim status and sending notification to users.
    public ClaimsDTO updateClaim(Long claimId, Claim claim) {
        try {
            //if(redisTemplate.opsForValue().get(redisKey) == null){
            Claim claimVal = claimRepository.findById(claimId).orElseThrow(() -> new RuntimeException("Claim ID Not Found"));
            oldStatus = claimVal.getClaimStatus();
            claimVal.setClaimStatus(claim.getClaimStatus());
            claimVal.setClaimDate(new Date());
            claimVal.setClaimType(claim.getClaimType());
            claimVal.setClaimAmount(claim.getClaimAmount());
            claimVal.setLastUpdated(claim.getLastUpdated());

            // Storing the claim status information in redis.
            redisTemplate.opsForValue().set(redisKey,oldStatus);

            // Checking if status has changed, then ony trigger alert to user.
            if (!oldStatus.equalsIgnoreCase(claim.getClaimStatus())) {
                System.out.println("Status of Claim Changed");
                kafkaNotificationsService.sendNotificationForClaimStatusChange(claim.getClaimId(), claim.getClaimStatus(), "bakhilmenon@gmail.com");
            }
            claimRepository.save(claimVal);
            return convertToDTO(claimVal);
        } catch (Exception e) {
            logger.error("Error Occurred while Updating Claim{}", String.valueOf(e));
            throw e;
        }
    }

    public void deleteClaimById(Long claimId) {
        claimRepository.deleteById(claimId);
    }
}
