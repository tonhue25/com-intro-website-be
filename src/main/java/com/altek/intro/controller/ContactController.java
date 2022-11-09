package com.altek.intro.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altek.intro.dto.ContactDTO;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.ContactService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.ResponseUtil;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContactController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private ResponseUtil responseUtil;

    @GetMapping("")
    public ResponseEntity<ContactDTO> listAll() {
        try {
            List<ContactDTO> response = contactService.getAllContact();
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody ContactDTO request) {
        try {
            return new ResponseEntity<BaseResponse>(contactService.create(request), HttpStatus.OK);
        } catch (Exception ex) {
            BaseResponse result = responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error.");
            return new ResponseEntity<BaseResponse>(result, HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> delete(@RequestParam(value = "id", required = true) Long id) {
        try {
            
            return new ResponseEntity<BaseResponse>(contactService.delete(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<BaseResponse>(responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error."), HttpStatus.OK);
        }
    }
}
