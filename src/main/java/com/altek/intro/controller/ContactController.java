package com.altek.intro.controller;


import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.ContactRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
@Slf4j
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/list")
    public ResponseEntity<BaseResponse> getContacts(@RequestBody BaseRequest dto) {
        try {
            return new ResponseEntity(contactService.getContacts(dto), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createContact(@RequestBody @Valid ContactRequestDto request) {
        try {
            return new ResponseEntity<BaseResponse>(contactService.createContact(request), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteContact(@RequestParam Long id) {
        try {
            return new ResponseEntity<BaseResponse>(contactService.deleteContact(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
