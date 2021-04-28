package com.comintro.services.impl;

import com.comintro.dto.CustomersDTO;
import com.comintro.entities.CustomersEntity;
import com.comintro.repository.CustomersRepository;
import com.comintro.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomersServiceImpl extends AbstractServiceImpl<CustomersDTO, CustomersEntity> implements CustomersService {
    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public List<CustomersDTO> getAllCustomers() {
        return null;
    }
}
