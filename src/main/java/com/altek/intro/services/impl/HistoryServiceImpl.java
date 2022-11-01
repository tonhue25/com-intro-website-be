package com.altek.intro.services.impl;

import com.altek.intro.dto.HistoryDTO;
import com.altek.intro.dto.MenuViewDTO;
import com.altek.intro.entites.HistoryEntity;
import com.altek.intro.entites.MenuEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.HistoryMapper;
import com.altek.intro.repository.HistoryRepository;
import com.altek.intro.services.HistoryService;
import com.altek.intro.services.SliderService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl extends AbstractServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryMapper historyMapper;
    @Override
    public List<HistoryDTO> getAllHistory() {
        try {
            List<HistoryDTO> HistoryDTOs = new ArrayList<HistoryDTO>();

            List<HistoryEntity> HistoryEntities = historyRepository.findAll();
            HistoryDTO dto = new HistoryDTO();
            if (CollectionUtils.isNotEmpty(HistoryEntities)) {
                HistoryDTOs = HistoryEntities.stream().map(item -> (HistoryDTO) historyMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return HistoryDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
