package com.altek.intro.controller;

import com.altek.intro.dto.request.MailDto;
import com.altek.intro.service.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SendEmailController {

    @Autowired
    SendMailService sendMailService;

    @ResponseBody
    @RequestMapping("/send-mails")
    public ResponseEntity<?> sendEmail(MailDto dto) {
        try {
            return new ResponseEntity(sendMailService.sendMail(dto), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
