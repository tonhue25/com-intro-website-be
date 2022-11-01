package com.altek.intro.services.impl;

import com.altek.intro.dto.DetailServiceDTO;
import com.altek.intro.dto.MenuViewDTO;
import com.altek.intro.entites.DetailServiceEntity;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.DetailServiceMapper;
import com.altek.intro.repository.DetailServiceRepository;
import com.altek.intro.services.DetailServiceOfService;
import com.altek.intro.services.MenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DetailServiceOfServiceImpl extends AbstractServiceImpl implements DetailServiceOfService {

    @Autowired
    private DetailServiceRepository detailServiceRepository;

    @Autowired
    private DetailServiceMapper detailServiceMapper;

    @Override
    public List<DetailServiceDTO> getAllDetailService() {
        try {
            List<DetailServiceDTO> detailServiceDTOs = new ArrayList<DetailServiceDTO>();

            List<DetailServiceEntity> detailServiceEntities = detailServiceRepository.findAll();
            DetailServiceDTO dto = new DetailServiceDTO();
            if (CollectionUtils.isNotEmpty(detailServiceEntities)) {
                detailServiceDTOs = detailServiceEntities.stream().map(item -> (DetailServiceDTO) detailServiceMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return detailServiceDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
