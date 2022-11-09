package com.altek.intro.services.impl;

import com.altek.intro.controller.ContactDto;
import com.altek.intro.dto.ContactDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.entites.ContactEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ContactMapper;
import com.altek.intro.repository.ContactRepository;
import com.altek.intro.services.ContactService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.ResponseUtil;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl extends AbstractServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ContactDTO> getAllContact() {
        try {
            List<ContactDTO> contactDTOS = new ArrayList<ContactDTO>();

            List<ContactEntity> contactEntities = contactRepository.findAll();
            ContactDTO dto = new ContactDTO();
            if (CollectionUtils.isNotEmpty(contactEntities)) {
                contactDTOS = contactEntities.stream().map(item -> (ContactDTO) contactMapper.convertToDTO(dto, item))
                        .collect(Collectors.toList());
            }
            return contactDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public BaseResponse create(ContactDTO request) {
        try {
            ContactEntity entity = new ContactEntity();
            entity = (ContactEntity) contactMapper.convertToEntity(request, entity);
            entity = contactRepository.save(entity);
            ContactDTO response = modelMapper.map(entity, ContactDTO.class);
            return responseUtil.responseBean(Constant.SUCCESS, "add.or.update.contact", response);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constant.ERROR_SYSTEM, "ex.common.system.error.");
        }
    }

    @Override
    public BaseResponse delete(Long id) {
        try {
            Optional<ContactEntity> optional = contactRepository.findById(id);
            if (!optional.isPresent()) {
                throw new ResourceNotFoundException(String.format("contact.not.found.with.id:%s", id));
            }
            ContactEntity entity = optional.get();
            entity.setStatus(Constant.DELETE);
            entity = contactRepository.save(entity);
            ContactDTO response = modelMapper.map(entity, ContactDTO.class);
            return responseUtil.responseBean(Constant.SUCCESS, String.format("delete.contact.with.id:%s", id), response);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error.");
        }
    }
}
