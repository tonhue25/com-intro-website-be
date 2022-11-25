package com.altek.intro.services;

import com.altek.intro.dto.request.EmailDetailRequestDto;
import org.eclipse.jetty.util.MathUtils;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

    String sendSimpleMail(EmailDetailRequestDto details);

    String sendMailWithAttachment(EmailDetailRequestDto details);
}
