package com.altek.intro.service;

import com.altek.intro.dto.request.EmailDetailRequestDto;

public interface EmailService {

    String sendSimpleMail(EmailDetailRequestDto details);

    String sendMailWithAttachment(EmailDetailRequestDto details);
}
