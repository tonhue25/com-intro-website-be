package com.altek.intro.services.impl;

import com.altek.intro.dto.request.SliderRequestDTO;
import com.altek.intro.entites.SliderEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.SliderMapper;
import com.altek.intro.repository.SliderRepository;
import com.altek.intro.services.SliderService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SliderServiceImpl extends AbstractServiceImpl implements SliderService {

    @Autowired
    private SliderRepository sliderRepository;

    @Autowired
    private SliderMapper sliderMapper;

    @Override
    public List<SliderRequestDTO> getAllSlide() {
        try {
            List<SliderRequestDTO> sliderDTOs = new ArrayList<SliderRequestDTO>();

            List<SliderEntity> sliderEntities = sliderRepository.findAll();
            SliderRequestDTO dto = new SliderRequestDTO();
            if (CollectionUtils.isNotEmpty(sliderEntities)) {
                sliderDTOs = sliderEntities.stream().map(item -> (SliderRequestDTO) sliderMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return sliderDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

}
