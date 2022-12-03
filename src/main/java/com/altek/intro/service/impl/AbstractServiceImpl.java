package com.altek.intro.service.impl;

import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.response.AbstractResponseDto;
import com.altek.intro.entity.AbstractEntity;
import com.altek.intro.service.AbstractService;
import com.altek.intro.util.Constant;
import com.altek.intro.util.DataUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * AbstractMapperImpl
 *
 * @author NGUYEN HOANG VU
 * @return
 */

public class AbstractServiceImpl<D extends AbstractResponseDto, E extends AbstractEntity> implements AbstractService<D, E> {


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

    @Override
    public Pageable getPageable(BaseRequest requestDto){
        if (DataUtil.isEmpty(requestDto.getPage())) {
            requestDto.setPage(Constant.FIRST_PAGE);
        }
        if (DataUtil.isEmpty(requestDto.getSize())) {
            requestDto.setSize(Constant.DEFAULT_SIZE);
        }
        Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize());
        if (!DataUtil.isEmpty(requestDto.getSortBy()) && !DataUtil.isEmpty(requestDto.getSortType())) {
            Sort.Direction sort = Sort.Direction.ASC;
            if (requestDto.getSortType().equals("DESC")) {
                sort = Sort.Direction.DESC;
            }
            pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize(), Sort.by(sort, requestDto.getSortBy()));
        }
        return pageable;
    }
}
