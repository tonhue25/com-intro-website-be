package com.altek.intro.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.altek.intro.dto.response.RecruitmentResponseDTO;
import com.altek.intro.entites.RecruitmentEntity;
import com.altek.intro.mapper.RecruitmentMapper;

@Component
public class RecruitmentMapperImpl extends AbstractMapperImpl implements RecruitmentMapper {

//    @Override
//    public List<RecruitmentResponseDTO> mapList(List<RecruitmentEntity> listEntity, RecruitmentResponseDTO dto) {
//        ModelMapper modelMapper = new ModelMapper();
//        List<RecruitmentResponseDTO> listDTO = listEntity.stream().map(item -> modelMapper.map(item, dto.getClass()))
//				.collect(Collectors.toList());
//        return listDTO;
//    }
}
