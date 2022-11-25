package com.altek.intro.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractResponseDto {
    private Long id;
    private Integer status;
    private String createdBy;
    private String createdTime;
    private String lastUpdatedBy;
    private String lastUpdatedTime;
}
