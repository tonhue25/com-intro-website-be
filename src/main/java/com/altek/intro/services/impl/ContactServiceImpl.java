package com.altek.intro.services.impl;

import com.altek.intro.dto.request.ContactRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ContactResponseDTO;

import com.altek.intro.entites.ContactEntity;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.mapper.ContactMapper;
import com.altek.intro.repository.ContactRepository;
import com.altek.intro.services.ContactService;
import com.altek.intro.utils.Constant;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    private ModelMapper modelMapper;

    @Override
    public List<ContactResponseDTO> getAllContact() {
        try {
            List<ContactResponseDTO> contactDTOS = new ArrayList<ContactResponseDTO>();
            List<ContactEntity> contactEntities = contactRepository.findAll();
            ContactResponseDTO dto = new ContactResponseDTO();
            if (CollectionUtils.isNotEmpty(contactEntities)) {
                contactDTOS = contactEntities.stream().map(item -> (ContactResponseDTO) contactMapper.convertToDTO(dto, item))

                        .collect(Collectors.toList());
            }
            return contactDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public BaseResponse create(ContactRequestDTO request) {
        try {
            // checkInputParams(request);
            if (StringUtils.isBlank(request.getEmail())) {
                throw new ResourceNotFoundException(null);
            }
            ContactEntity entity = new ContactEntity();
            entity = (ContactEntity) contactMapper.convertToEntity(request, entity);
            entity = contactRepository.save(entity);
            ContactResponseDTO response = modelMapper.map(entity, ContactResponseDTO.class);
            return new BaseResponse(Constant.SUCCESS, "add.or.update.contact", response);
        } catch (Exception ex) {
            return new BaseResponse(Constant.FAIL, ex.getMessage());
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
            ContactResponseDTO response = modelMapper.map(entity, ContactResponseDTO.class);
            return new BaseResponse(Constant.SUCCESS, String.format("delete.contact.with.id:%s", id),
                    response);
        } catch (Exception ex) {
            return new BaseResponse(Constant.FAIL,
                    ex.getMessage());
        }
    }
}
