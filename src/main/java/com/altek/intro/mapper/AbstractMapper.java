package com.altek.intro.mapper;

import java.util.List;

/**
 * AbstractMapper
 *
 * @author NGUYEN HOANG VU
 * @return
 */
public interface AbstractMapper<E, D> {

    Object convertToDTO(Object dto, Object entity);

    Object convertToEntity(Object dto, Object entity);

    List<D> convertListToDto(List<E> list, D dto);
}
