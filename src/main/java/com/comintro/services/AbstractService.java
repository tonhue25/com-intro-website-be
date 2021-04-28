package com.comintro.services;

import com.comintro.dto.AbstractDTO;
import com.comintro.entities.AbstractEntities;
import org.springframework.data.domain.Pageable;


/**
 * AbstractService
 *
 * @author NGUYEN HOANG VU
 * @return 
 */
public interface AbstractService<D extends AbstractDTO, E extends AbstractEntities> {

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
