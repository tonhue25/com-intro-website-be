package com.altek.intro.mapper.impl;

import com.altek.intro.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;

/**
 * AbstractMapperImpl
 *
 * @author NGUYEN HOANG VU
 * @return
 */
public class AbstractMapperImpl implements AbstractMapper {

    @Override
    public Object convertToDTO(Object objectDto, Object objectEntity) {
        ModelMapper modelMapper = new ModelMapper();
        Object result = modelMapper.map(objectEntity, objectDto.getClass());
        return result;
    }

    @Override
    public Object convertToEntity( Object objectDto, Object objectEntity) {
        ModelMapper modelMapper = new ModelMapper();
        Object result = modelMapper.map(objectDto, objectEntity.getClass());
        return result;
    }

}
