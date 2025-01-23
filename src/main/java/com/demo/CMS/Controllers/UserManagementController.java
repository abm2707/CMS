package com.demo.CMS.Controllers;

import com.demo.CMS.Config.OTPGenerator;
import com.demo.CMS.DTOs.UserCredentialsDTO;
import com.demo.CMS.DTOs.UserDTO;
import com.demo.CMS.Models.Users;
import com.demo.CMS.Security.JWTHelper;
import com.demo.CMS.Services.KafkaNotificationsService;
import com.demo.CMS.Services.UserService;
import com.demo.CMS.Utilities.Utilities;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    boolean resp;
    String generatedOtp;

    @Autowired
    UserService userService;

    @Autowired
    KafkaNotificationsService kafkaNotificationsService;

    @Autowired
    OTPGenerator otpGenerator;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserCredentialsDTO credentials, HttpServletRequest request) {
        UserDTO user = userService.loginUser(credentials.getUsername(), credentials.getPassword());

        // Generate otp.
        generatedOtp = otpGenerator.generateOTP();

        // Send otp as email
        kafkaNotificationsService.sendEmailOtp(generatedOtp, credentials.getUsername());

        if (user != null) {
            // Create a JWT token.
            String token = JWTHelper.generateToken(user.getUsername(), request.getRequestURL().toString());
            // Return the ResponseEntity with status OK, and add the token in the Authorization header
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).body(user);
        } else {
            // If the user is not found or the authentication failed, return an unauthorized status
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user, HttpServletRequest request) {
        try {
            resp = userService.checkIfUserExists(String.valueOf(user.getUsername()));
            if (!resp) {
                userService.registerUser(user);
                return new ResponseEntity<>("User registered successfully !!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User already exits", HttpStatus.CONFLICT);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getUser/{userName}")
    public Optional<Users> getUserDetailsByUserId(@PathVariable String userName){
        return userService.findUserByUserName(userName);
    }

    @PutMapping("/editUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody Users updatedUser){
        try{
            UserDTO user = userService.updateUser(userId, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<?> deleteUserByUserName(@PathVariable String userName){
        try{
            userService.deleteUserByUserName(userName);
            return new ResponseEntity<>("Deletion Successful",HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
