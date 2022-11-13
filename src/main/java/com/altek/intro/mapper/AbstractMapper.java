package com.altek.intro.mapper;

import java.util.List;

/**
 * AbstractMapper
 *
 * @author NGUYEN HOANG VU
 * @return
 */
public interface AbstractMapper{

    Object convertToDTO(Object dto, Object entity);
    Object convertToEntity(Object dto, Object entity);
    List<Object> mapList(List<Object> listEntity, Object dto);

}
