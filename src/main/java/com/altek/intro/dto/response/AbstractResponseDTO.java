package com.altek.intro.dto.response;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractResponseDTO {

    private Long id;
    private Integer status;
    private String createdBy;
    private String createdTime;
    private String lastUpdatedBy;
    private String lastUpdatedTime;
    
}
