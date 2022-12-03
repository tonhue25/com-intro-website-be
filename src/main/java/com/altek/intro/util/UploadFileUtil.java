package com.altek.intro.util;

import com.altek.intro.dto.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

public class UploadFileUtil {

    public static BaseResponse uploadFile(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String base64Str = Base64.getEncoder().encodeToString(bytes);
            return new BaseResponse(Constant.SUCCESS, "upload.file", base64Str);
        } catch (Exception e) {
            return new BaseResponse(Constant.FAIL, "error.upload.file", e.getMessage());
        }
    }
}
