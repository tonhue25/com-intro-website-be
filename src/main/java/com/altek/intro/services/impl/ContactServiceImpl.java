package com.altek.intro.services.impl;

import com.altek.intro.dto.request.ContactRequestDTO;
import com.altek.intro.entites.ContactEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ContactMapper;
import com.altek.intro.repository.ContactRepository;
import com.altek.intro.services.ContactService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl extends AbstractServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<ContactRequestDTO> getAllContact() {
        try {
            List<ContactRequestDTO> contactDTOS = new ArrayList<ContactRequestDTO>();

            List<ContactEntity> contactEntities = contactRepository.findAll();
            ContactRequestDTO dto = new ContactRequestDTO();
            if (CollectionUtils.isNotEmpty(contactEntities)) {
                contactDTOS = contactEntities.stream().map(item -> (ContactRequestDTO) contactMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return contactDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
