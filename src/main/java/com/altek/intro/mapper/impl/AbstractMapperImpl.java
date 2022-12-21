package com.altek.intro.mapper.impl;

import com.altek.intro.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AbstractMapperImpl
 *
 * @author NGUYEN HOANG VU
 * @return
 */
public class AbstractMapperImpl<E, D> implements AbstractMapper<E, D> {

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
    public List convertListToDto(List list, Object dto) {
        ModelMapper modelMapper = new ModelMapper();
        List<D> result =
                (List<D>) list.stream().map(item -> modelMapper.map(item, dto.getClass()))
                        .collect(Collectors.toList());
        return result;
    }
}
