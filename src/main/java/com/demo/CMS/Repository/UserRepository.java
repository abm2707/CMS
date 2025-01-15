package com.demo.CMS.Repository;

import com.demo.CMS.DTOs.UserDTO;
import com.demo.CMS.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
