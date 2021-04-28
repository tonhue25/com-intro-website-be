package com.comintro.services;

import com.comintro.dto.CustomersDTO;
import com.comintro.entities.CustomersEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomersService extends AbstractService<CustomersDTO, CustomersEntity> {
    List<CustomersDTO> getAllCustomers();
}
