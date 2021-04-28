package com.comintro.services.impl;

import com.comintro.dto.AbstractDTO;
import com.comintro.entities.AbstractEntities;
import com.comintro.services.AbstractService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * AbstractMapperImpl
 *
 * @author NGUYEN HOANG VU
 * @return
 */
public class AbstractServiceImpl<D extends AbstractDTO, E extends AbstractEntities> implements AbstractService<D, E> {
	
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
