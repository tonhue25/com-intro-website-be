package com.comintro.services.impl;

import com.comintro.dto.SolusionsDTO;
import com.comintro.entities.SolusionsEntity;
import com.comintro.repository.SolusionsRepository;
import com.comintro.services.SolusionsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SolusionsServiceImpl extends AbstractServiceImpl<SolusionsDTO, SolusionsEntity> implements SolusionsService {
    @Autowired
    private SolusionsRepository solusionsRepository;

    @Override
    public List<SolusionsDTO> getAllSolusions() {
        return null;
    }

    @Override
    public SolusionsDTO getSolusionById(Long id) {
        return null;
    }
}
