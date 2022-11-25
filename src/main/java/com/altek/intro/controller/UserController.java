package com.altek.intro.controller;

import com.altek.intro.dto.request.UserRequestDto;
import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<BaseResponse> getUser(@RequestParam String username){
        return new ResponseEntity<BaseResponse>(userService.getUser(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createUser(@RequestBody UserRequestDto request){
        return new ResponseEntity<BaseResponse>(userService.createUser(request), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteUser(@RequestParam Long id){
        return new ResponseEntity<BaseResponse>(userService.deleteUser(id), HttpStatus.OK);
    }
}
