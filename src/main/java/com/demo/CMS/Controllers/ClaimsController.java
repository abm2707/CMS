package com.demo.CMS.Controllers;

import com.demo.CMS.DTOs.ClaimsDTO;
import com.demo.CMS.Models.Claim;
import com.demo.CMS.Services.ClaimService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claims/v1")
public class ClaimsController {

    @Autowired
    ClaimService claimService;

    @PostMapping("/genClaim")
    public ResponseEntity<ClaimsDTO> generateClaim(@RequestBody Claim claim){
        ClaimsDTO submittedClaim = claimService.generateClaim(claim);
         return new ResponseEntity<>(submittedClaim, HttpStatus.OK);
    }

    @GetMapping("/{claimId}")
    public ResponseEntity<?> getClaimByClaimId(@PathVariable Long claimId){
        try{
            Claim claim = claimService.getClaimByClaimId(claimId);
            return new ResponseEntity<>(claim, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{claimId}")
    public ResponseEntity<?> updateClaim(@PathVariable Long claimId, @RequestBody Claim claim){
        try {
            ClaimsDTO val = claimService.updateClaim(claimId,claim);
            return new ResponseEntity<>(val, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deleteById/{ClaimId}")
    @Transactional
    public ResponseEntity<?> deleteClaimId(@PathVariable Long ClaimId){
        try{
            claimService.deleteClaimById(ClaimId);
            return new ResponseEntity<>("Claim with Id: "+ClaimId +" deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
