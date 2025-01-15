package com.demo.CMS.Services;

import com.demo.CMS.DTOs.UserDTO;
import com.demo.CMS.Models.User;
import com.demo.CMS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    String userResp;
    boolean isExist;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserDTO loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPasswordHash())) {
            user.setLastLogin(new Date());
            userRepository.save(user); // Saving last login time.
            return convertToDTO(user);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getStatus()
        );
    }

    public void registerUser(User user) {
        // User gives a normal password. Converting to hashed password.
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);
    }

    public boolean checkIfUserExists(String userName) {
        User user = userRepository.findByUsername(userName);
        // If user is found, check if username is non-null and non-empty
        if (user != null && user.getUsername() != null && !user.getUsername().isEmpty()) {
            return true; // User exists and has a valid username
        }
        return false; // Either user is not found or username is null/empty
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
}
