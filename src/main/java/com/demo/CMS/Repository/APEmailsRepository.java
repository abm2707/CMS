package com.demo.CMS.Repository;

import com.demo.CMS.Models.APEmails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APEmailsRepository extends JpaRepository<APEmails, Integer> {
}
