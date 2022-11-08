package com.altek.intro.services;

import com.altek.intro.dto.ContactDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ContactService extends AbstractService{
    List<ContactDTO> getAllContact();
}
