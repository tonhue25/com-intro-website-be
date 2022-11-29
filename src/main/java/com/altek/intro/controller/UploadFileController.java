package com.altek.intro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/upload-files")
public class UploadFileController {
//    data:image/png;base64,
    @PostMapping
    public ResponseEntity<?> uploadFileProduct(@RequestBody MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String base64Str = Base64.getEncoder().encodeToString(bytes);
        return new ResponseEntity<String>(base64Str, HttpStatus.OK);
    }
}
