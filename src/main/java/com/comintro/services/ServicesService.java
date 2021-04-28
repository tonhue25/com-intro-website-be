package com.comintro.services;

import com.comintro.dto.ServicesDTO;
import com.comintro.entities.ServicesEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ServicesService extends AbstractService<ServicesDTO, ServicesEntity> {
    List<ServicesDTO> getAllServices();
    ServicesDTO getServiceById(Long id);
}
