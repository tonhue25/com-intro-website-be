package com.altek.intro.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.altek.intro.dto.request.PageDetailRequestDTO;
import com.altek.intro.entities.PageContentEntity;
import com.altek.intro.entities.PageDetailEntity;
import com.altek.intro.mapper.PageDetailMapper;
import org.modelmapper.ModelMapper;

import com.altek.intro.mapper.AbstractMapper;

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

}
