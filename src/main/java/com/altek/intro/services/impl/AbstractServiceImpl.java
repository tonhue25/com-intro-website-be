package com.altek.intro.services.impl;

import com.altek.intro.entites.AbstractEntity;
import com.altek.intro.model.AbstractModel;
import com.altek.intro.services.AbstractService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * AbstractMapperImpl
 *
 * @author NGUYEN HOANG VU
 * @return
 */
public class AbstractServiceImpl<D extends AbstractEntity, E extends AbstractEntity> implements AbstractService<D, E> {

    /**
     * get Pageable object with sortable
     *
     * @param page {@link Integer}
     * @param size {@link Integer}
     * @return Pageable
     */
    @Override
    public Pageable getPageable(Integer page, Integer size, boolean sortASC, String by) {
        Sort sortable;
        if (by != null) {
            if (sortASC) {
                sortable = Sort.by(by).ascending();
            } else {
                sortable = Sort.by(by).descending();
            }
            return PageRequest.of(page, size, sortable);
        }
        return PageRequest.of(page, size);
    }

    /**
     * get Pageable object
     *
     * @param page {@link Integer}
     * @param size {@link Integer}
     * @return Pageable
     */
    @Override
    public Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(page, size);
    }
}
