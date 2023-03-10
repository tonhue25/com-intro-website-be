package com.altek.intro.mapper.impl;

import com.altek.intro.dto.request.ContactRequestDto;
import com.altek.intro.dto.response.ContactResponseDto;
import com.altek.intro.entity.Contact;
import com.altek.intro.enums.ContactType;
import com.altek.intro.mapper.ContactMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactMapperImpl extends AbstractMapperImpl implements ContactMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ContactResponseDto> mapList(List<Contact> list) {
        List<ContactResponseDto> response = list.stream().map(item -> modelMapper.map(item, ContactResponseDto.class))
                .collect(Collectors.toList());
        return response;
    }

    public Contact convertToEntity(Contact entity , ContactRequestDto request){
        entity.setStatus(request.getStatus());
        entity.setMessage(request.getMessage());
        entity.setFullName(request.getFullName());
        entity.setType(modelMapper.map(request.getType(), ContactType.class));
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setEmail(request.getEmail());
        return entity;
    }
}
