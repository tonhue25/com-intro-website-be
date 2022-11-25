package com.altek.intro.controller;

import com.altek.intro.dto.request.EmailDetailRequestDto;
import com.altek.intro.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToOne;

@RestController
@Slf4j
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public String sendMail(EmailDetailRequestDto emailDetails){
        String status = emailService.sendSimpleMail(emailDetails);
        return status;
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@ModelAttribute EmailDetailRequestDto details){
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }
}
