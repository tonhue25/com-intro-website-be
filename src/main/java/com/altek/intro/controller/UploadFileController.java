//package com.altek.intro.controller;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/upload-files")
//public class UploadFileController {
//
//    @Autowired
//    private Cloudinary cloudinary;
//
//    @PostMapping
//    public ResponseEntity<?> uploadFileProduct(@RequestBody MultipartFile file) throws IOException {
//        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//        return new ResponseEntity<String>(uploadResult.get("url").toString(), HttpStatus.OK);
//    }
//}

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

import java.io.IOException;
import java.util.Base64;

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

