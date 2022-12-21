package com.altek.intro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponseDto extends AbstractResponseDto {
    private String label;
    private String link;
    private String languageId;
    private Long menuId;

    public MenuResponseDto(Long id, Integer status, String createdBy, String createdTime, String lastUpdatedBy,
                           String lastUpdatedTime, String label, String link, String languageId, Long menuId) {
        super(id, status, createdBy, createdTime, lastUpdatedBy, lastUpdatedTime);
        this.label = label;
        this.link = link;
        this.languageId = languageId;
        this.menuId = menuId;
    }
}
