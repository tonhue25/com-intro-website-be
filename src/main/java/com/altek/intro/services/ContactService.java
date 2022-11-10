package com.altek.intro.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.altek.intro.dto.request.ContactRequestDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ContactResponseDTO;

@Service
public interface ContactService extends AbstractService{
    List<ContactResponseDTO> getAllContact();

    BaseResponse create(ContactRequestDTO request);

    BaseResponse delete(Long id);
}
