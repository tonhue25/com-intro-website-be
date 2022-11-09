package com.altek.intro.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.altek.intro.dto.ContactDTO;
import com.altek.intro.dto.response.BaseResponse;

@Service
public interface ContactService extends AbstractService{
    List<ContactDTO> getAllContact();

    BaseResponse create(ContactDTO request);

    BaseResponse delete(Long id);
}
