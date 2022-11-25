package com.altek.intro.service;

import com.altek.intro.dto.request.MailDTO;
import com.altek.intro.dto.response.BaseResponse;

import javax.mail.MessagingException;

public interface SendMailService {
    BaseResponse sendMail(MailDTO dto) throws MessagingException;
}
