package com.comintro.services;

import com.comintro.dto.AboutUsDTO;
import com.comintro.entities.AboutUsEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ABoutUsService extends AbstractService<AboutUsDTO, AboutUsEntity> {
    List<AboutUsDTO> getAboutUsByType(Integer type);
}
