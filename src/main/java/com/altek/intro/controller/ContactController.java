package com.altek.intro.controller;


import com.altek.intro.dto.request.ListRequestDto;
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
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.ContactService;

@RestController
@RequestMapping("/contact")
@Slf4j
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<BaseResponse> listAll() {
        try {
            BaseResponse response = contactService.getAllContact();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/list")
    public ResponseEntity<BaseResponse> getListContact(@RequestBody ListRequestDto dto) {
        return new ResponseEntity(contactService.getAllContact(dto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody ContactRequestDto request) {
        return new ResponseEntity<BaseResponse>(contactService.create(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam(value = "id", required = true) Long id) {
        return new ResponseEntity<BaseResponse>(contactService.delete(id), HttpStatus.OK);
    }
}
