package com.altek.intro.controller;


import com.altek.intro.dto.request.BaseRequest;
import com.altek.intro.dto.request.ContactRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/list")
    public ResponseEntity<BaseResponse> getListContact(@RequestBody BaseRequest dto) {
        try {
            return new ResponseEntity(contactService.getListContact(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid ContactRequestDto request) {
        try {
            return new ResponseEntity<BaseResponse>(contactService.create(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam Long id) {
        try {
            return new ResponseEntity<BaseResponse>(contactService.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
