package com.demo.CMS.Controllers;

import com.demo.CMS.DTOs.UserCredentialsDTO;
import com.demo.CMS.DTOs.UserDTO;
import com.demo.CMS.Models.User;
import com.demo.CMS.Security.JWTHelper;
import com.demo.CMS.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    boolean resp;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserCredentialsDTO credentials, HttpServletRequest request) {
        UserDTO user = userService.loginUser(credentials.getUsername(), credentials.getPassword());
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
    public ResponseEntity<?> registerUser(@RequestBody User user, HttpServletRequest request) {
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
    public User getUserDetailsByUserId(@PathVariable String userName){
        return userService.findUserByUserName(userName);
    }

}
