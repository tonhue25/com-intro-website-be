package com.comintro.services.impl;

import com.comintro.dto.AboutUsDTO;
import com.comintro.entities.AboutUsEntity;
import com.comintro.repository.AboutUsRepository;
import com.comintro.services.ABoutUsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AboutUsServiceImpl extends AbstractServiceImpl<AboutUsDTO, AboutUsEntity> implements ABoutUsService {
    @Autowired
    private AboutUsRepository aboutUsRepository;

    @Override
    public List<AboutUsDTO> getAboutUsByType(Integer type) {
        return null;
    }
}
