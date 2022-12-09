package com.altek.intro.controller;


import com.altek.intro.dto.request.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altek.intro.dto.request.ContactRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.service.ContactService;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
@Slf4j
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/list")
    public ResponseEntity<BaseResponse> getListContact(@RequestBody @Valid BaseRequest dto) {
        return new ResponseEntity(contactService.getListContact(dto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid ContactRequestDto request) {
        return new ResponseEntity<BaseResponse>(contactService.create(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam(value = "id", required = true) Long id) {
        return new ResponseEntity<BaseResponse>(contactService.delete(id), HttpStatus.OK);
    }
}
