package com.altek.intro.controller;


import com.altek.intro.dto.request.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
public class SimpleEmailExampleController {
    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping("/send-mail")
    public String sendEmail(MailDTO dto) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart);
        helper.setTo(dto.getEmail());
        helper.setSubject(dto.getSubject());
        helper.setText(dto.getContent());
        helper.addAttachment(dto.getFile().getOriginalFilename(), dto.getFile());
        emailSender.send(message);
        return "Email Sent!";
    }
}
