package com.altek.intro.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
    private String name;
    private String subject;
    private String content;
    private String email;
    private MultipartFile file;
}
