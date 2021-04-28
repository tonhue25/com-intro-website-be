package com.comintro.services;

import com.comintro.dto.SolusionsDTO;
import com.comintro.entities.SolusionsEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SolusionsService extends AbstractService<SolusionsDTO, SolusionsEntity>{
    List<SolusionsDTO> getAllSolusions();
    SolusionsDTO getSolusionById(Long id);
}
