package com.altek.intro.services.impl;

import com.altek.intro.dto.request.EmailDetailRequestDto;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.EmailService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    // người gửi
    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(EmailDetailRequestDto details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());  // địa chỉ người nhận
            mailMessage.setText(details.getMsgBody());  // nội dung thư
            mailMessage.setSubject(details.getSubject()); // tiêu đề thư

            // gửi email
            javaMailSender.send(mailMessage);
            return "Mail sent successfully";

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    // gửi email đính kèm file
    @Override
    public String sendMailWithAttachment(EmailDetailRequestDto details) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try{
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient()); // địa chỉ người nhận
            mimeMessageHelper.setText(details.getMsgBody()); // nội dụng thư
            mimeMessageHelper.setSubject(details.getSubject()); // tiêu đề thư

            //thêm tệp đính kèm
            FileSystemResource file = new FileSystemResource(new File(details.getAttachment().toString()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);

            //gửi email
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (MessagingException e){
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
