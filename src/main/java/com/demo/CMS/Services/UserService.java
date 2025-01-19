package com.demo.CMS.Services;

import com.demo.CMS.DTOs.UserDTO;
import com.demo.CMS.Models.Users;
import com.demo.CMS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    String userResp;
    boolean isExist;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserDTO loginUser(String username, String password) {
        Users user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User for found"));
        if (user != null && passwordEncoder.matches(password, user.getPasswordHash())) {
            user.setLastLogin(new Date());
            userRepository.save(user);
            return convertToDTO(user);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private UserDTO convertToDTO(Users user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getStatus()
        );
    }

    public void registerUser(Users user) {
        // User gives a normal password. Converting to hashed password.
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);
    }

    public boolean checkIfUserExists(String userName) {
        return userRepository.findByUsername(userName).isPresent();
    }

    public Optional<Users> findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public UserDTO updateUser(Long userId, Users updatedUser) {
        Users user = userRepository.findById(Math.toIntExact(userId)).orElseThrow(()-> new RuntimeException("User Not Found"));
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());
        user.setPasswordHash(updatedUser.getPasswordHash());
        user.setUpdatedAt(new Date());
        userRepository.save(user);
        return convertToDTO(user);
    }

    public void deleteUserByUserName(String userName) {
        // orElseThrow() can only be applicable when the return type is  Optional.
        Users user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("User not found with userName: " + userName));
        userRepository.delete(user);
        ResponseEntity.ok("");
    }
}
