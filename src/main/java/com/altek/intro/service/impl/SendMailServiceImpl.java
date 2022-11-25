package com.altek.intro.service.impl;

import com.altek.intro.dto.request.MailDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.SendMailService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    public JavaMailSender emailSender;

    public BaseResponse sendMail(MailDto dto) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = false;
        if (!DataUtil.isEmpty(dto.getFiles().get(0))) {
            multipart = true;
        }
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart);
        helper.setTo(dto.getEmail());
        helper.setSubject(dto.getSubject());
        helper.setText(dto.getContent());
        if (multipart == true) {
            List<MultipartFile> file = dto.getFiles();
            for (int i = 0; i < file.size(); i++) {
                helper.addAttachment(file.get(i).getOriginalFilename(), file.get(i));
            }
        }
        emailSender.send(message);
        return new BaseResponse(Constant.SUCCESS, "send.mail", "Email Sent!");
    }
}
