package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
    private String subject;
    private String content;
    private String email;
    private List<MultipartFile> files;
}
