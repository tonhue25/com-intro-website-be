package com.altek.intro.service;

import com.altek.intro.dto.request.MailDto;
import com.altek.intro.dto.response.BaseResponse;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

public interface SendMailService {
    BaseResponse sendMail(MailDto dto) throws MessagingException;
}
