package com.altek.intro.mapper.impl;

import com.altek.intro.mapper.AbstractMapper;

import java.util.List;
import java.util.stream.Collectors;

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
    public Object convertToEntity(Object objectDto, Object objectEntity) {
        ModelMapper modelMapper = new ModelMapper();
        Object result = modelMapper.map(objectDto, objectEntity.getClass());
        return result;
    }

    @Override
    public List<Object> mapList(List<Object> list, Object objectEntity) {
        ModelMapper modelMapper = new ModelMapper();
        List<Object> result = list.stream().map(item -> modelMapper.map(item, objectEntity.getClass()))
                .collect(Collectors.toList());
        return result;
    }
}
