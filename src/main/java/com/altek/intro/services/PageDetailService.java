package com.altek.intro.services;

import com.altek.intro.dto.PageDetailDTO;

public interface PageDetailService extends AbstractService{
    PageDetailDTO getByPageContentId(Long id);

}
