package com.altek.intro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetailRequestDto {
    private String recipient;
    private String msgBody;
    private String subject;
    private MultipartFile attachment;
}
