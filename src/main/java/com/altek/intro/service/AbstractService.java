package com.altek.intro.service;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.AbstractResponseDto;
import com.altek.intro.entity.AbstractEntity;
import org.springframework.data.domain.Pageable;

/**
 * AbstractService
 *
 * @author NGUYEN HOANG VU
 * @return
 */
public interface AbstractService<D extends AbstractResponseDto, E extends AbstractEntity> {


    /**
     * get Pageable object with sortable
     *
     * @param page {@link Integer}
     * @param size {@link Integer}
     * @return pageable {@link Pageable}
     */
    Pageable getPageable(Integer page, Integer size, boolean sortASC, String by);

    /**
     * get Pageable object
     *
     * @param page {@link Integer}
     * @param size {@link Integer}
     * @return pageable {@link Pageable}
     */
    Pageable getPageable(Integer page, Integer size);

    Pageable getPageable(BaseRequest requestDto);
}
