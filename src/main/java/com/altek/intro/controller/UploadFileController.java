package com.altek.intro.controller;


import com.altek.intro.dto.response.BaseResponse;
import com.altek.intro.util.UploadFileUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload-files")
public class UploadFileController {
    //    data:image/png;base64,
    @PostMapping
    public ResponseEntity<BaseResponse> uploadFile(@RequestBody MultipartFile file) {
        try {
            return new ResponseEntity<BaseResponse>(UploadFileUtil.uploadFile(file), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

