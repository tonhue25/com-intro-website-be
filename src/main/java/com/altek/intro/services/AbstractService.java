package com.altek.intro.services;

import com.altek.intro.dto.response.AbstractResponseDTO;
import com.altek.intro.entities.AbstractEntity;
import org.springframework.data.domain.Pageable;

/**
 * AbstractService
 *
 * @author NGUYEN HOANG VU
 * @return
 */
public interface AbstractService<D extends AbstractResponseDTO, E extends AbstractEntity> {


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


}
