package com.demo.CMS.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "email_config")
public class APEmails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer template_id;

    private String email_body;

    private String email_footer;

    private String email_header;

    private String subject;
}
