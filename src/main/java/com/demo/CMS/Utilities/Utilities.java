package com.demo.CMS.Utilities;

import com.demo.CMS.Models.APEmails;
import com.demo.CMS.Repository.APEmailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Utilities {

    String emailBody;

    @Autowired
    APEmailsRepository apEmailsRepository;

    public String getEmailBody(Integer template_id, String recipient_email){
        System.out.println("Inside Get Email Method");
        Optional<APEmails> emailData = Optional.ofNullable(apEmailsRepository.findById(1).orElseThrow(() -> new RuntimeException("No Email Body found")));
        if(emailData.isPresent()){
            emailBody = emailData.get().getEmail_body();
        }
        return emailBody;
    }
}
