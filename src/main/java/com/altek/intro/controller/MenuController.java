package com.altek.intro.controller;

import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.dto.response.MenuResponseDTO;
import com.altek.intro.exceptions.ResourceNotFoundException;
import com.altek.intro.services.MenuService;
import com.altek.intro.utils.Constant;
import com.altek.intro.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private ResponseUtil responseUtil;

    @GetMapping
    public ResponseEntity<MenuResponseDTO> listAll() {
        try {
            List<MenuResponseDTO> response = menuService.getAll();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody MenuRequestDto request) {
        try {
            return new ResponseEntity<BaseResponse>(menuService.create(request), HttpStatus.OK);
        } catch (Exception ex) {
            BaseResponse result = responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error.");
            return new ResponseEntity<BaseResponse>(result, HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam(value = "id", required = true) Long id) {
        try {
            return new ResponseEntity<BaseResponse>(menuService.delete(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<BaseResponse>(responseUtil.responseBean(Constant.ERROR_SYSTEM,
                    "ex.common.system.error."), HttpStatus.OK);
        }
    }
}
