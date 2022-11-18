package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MenuResponseDto extends AbstractResponseDto{
    private Long menuId;
    private String label;
    private String link;

}
