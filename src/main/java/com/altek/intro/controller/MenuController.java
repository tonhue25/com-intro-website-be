package com.altek.intro.controller;


import com.altek.intro.dto.request.MenuRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.exception.AuthorizedException;
import com.altek.intro.exception.ParameterIllegalException;
import com.altek.intro.exception.ResourceNotFoundException;
import com.altek.intro.exception.SystemErrorException;
import com.altek.intro.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/menus")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity<BaseResponse> getMenus(@RequestHeader("Accept-Language") String lang) {
        try {
            return new ResponseEntity(menuService.getMenus(lang), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createMenu(@RequestBody MenuRequestDto request) {
        BaseResponse response = new BaseResponse();
        try {
            log.info("#MenuController.createMenu: START -- ");
            response = menuService.createMenu(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ParameterIllegalException ex) {
            ex.printStackTrace();
            log.error("#MenuController.createMenu ParameterIllegalException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.BAD_REQUEST.toString());
            response.setMessage("common.error.param");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (AuthorizedException ex) {
            ex.printStackTrace();
            log.error("#MenuController.createMenu AuthorizedException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.UNAUTHORIZED.toString());
            response.setMessage("common.error.unauthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (SystemErrorException ex) {
            ex.printStackTrace();
            log.error("#MenuController.createMenu SystemErrorException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("common.system.error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateMenu(@RequestBody MenuRequestDto request) {
        BaseResponse response = new BaseResponse();
        try {
            log.info("#MenuController.updateMenu: START -- ");
            response = menuService.updateMenu(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ParameterIllegalException ex) {
            ex.printStackTrace();
            log.error("#MenuController.updateMenu ParameterIllegalException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.BAD_REQUEST.toString());
            response.setMessage("common.error.param");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (AuthorizedException ex) {
            ex.printStackTrace();
            log.error("#MenuController.updateMenu AuthorizedException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.UNAUTHORIZED.toString());
            response.setMessage("common.error.unauthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (SystemErrorException ex) {
            ex.printStackTrace();
            log.error("#MenuController.updateMenu SystemErrorException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("common.system.error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMenu(@RequestBody MenuRequestDto request) {
        BaseResponse response = new BaseResponse();
        try {
            log.info("#MenuController.deleteMenu: START -- ");
            response = menuService.deleteMenu(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ResourceNotFoundException ex) {
            ex.printStackTrace();
            log.error("#MenuController.deleteMenu ResourceNotFoundException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.NOT_FOUND.toString());
            response.setMessage("common.error.param");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (AuthorizedException ex) {
            ex.printStackTrace();
            log.error("#MenuController.deleteMenu AuthorizedException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.UNAUTHORIZED.toString());
            response.setMessage("common.error.unauthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (SystemErrorException ex) {
            ex.printStackTrace();
            log.error("#MenuController.deleteMenu SystemErrorException : " + ex.getMessage(), ex);
            response.setHttp_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setMessage("common.system.error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
