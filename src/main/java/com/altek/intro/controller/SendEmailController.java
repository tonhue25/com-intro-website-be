package com.altek.intro.controller;

import com.altek.intro.dto.request.MailDTO;
import com.altek.intro.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.MessagingException;

@RestController
public class SendEmailController {

    @Autowired
    SendMailService sendMailService;

    @ResponseBody
    @RequestMapping("/send-mail")
    public ResponseEntity<?> sendEmail(MailDTO dto) throws MessagingException {
        return new ResponseEntity(sendMailService.sendMail(dto), HttpStatus.OK);
    }
}
