package com.comintro.services.impl;

import com.comintro.dto.ServicesDTO;
import com.comintro.entities.ServicesEntity;
import com.comintro.repository.ServicesRepository;
import com.comintro.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServicesServiceImpl extends AbstractServiceImpl<ServicesDTO, ServicesEntity> implements ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    @Override
    public List<ServicesDTO> getAllServices() {
        return null;
    }

    @Override
    public ServicesDTO getServiceById(Long id) {
        return null;
    }
}
