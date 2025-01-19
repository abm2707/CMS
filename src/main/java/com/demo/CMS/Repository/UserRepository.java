package com.demo.CMS.Repository;

import com.demo.CMS.DTOs.UserDTO;
import com.demo.CMS.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);

    void deleteByUsername(String userName);
}
