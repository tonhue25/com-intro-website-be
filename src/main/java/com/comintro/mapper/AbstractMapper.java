package com.comintro.mapper;

/**
 * AbstractMapper
 *
 * @author NGUYEN HOANG VU
 * @return 
 */
public interface AbstractMapper {
	Object convertToDTO(Object dto, Object entity);
	Object convertToEntity(Object dto, Object entity);
}
