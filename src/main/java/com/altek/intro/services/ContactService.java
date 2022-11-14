package com.altek.intro.services;

import com.altek.intro.dto.request.ContactRequestDTO;
import com.altek.intro.dto.request.ListRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.ContactResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService extends AbstractService{

    BaseResponse getAllContact();

    BaseResponse create(ContactRequestDTO request);

    BaseResponse delete(Long id);

    BaseResponse getAllContact(ListRequestDto dto);
}
