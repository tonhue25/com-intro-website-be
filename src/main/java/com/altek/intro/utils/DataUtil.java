package com.altek.intro.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class DataUtil {

    public static Date stringToDate(String input){
        Date date;
        try {
            date = DateUtils.parseDate(input,
                    new String[] { "yyyy-MM-dd HH:mm:ss", "dd/MM-yyyy","dd/MM/yyyy","yyyy-MM-dd" });
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public static boolean isEmpty(Integer input) {
        if (input == null || StringUtils.isBlank(input.toString())) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(CommonsMultipartFile input) {
        if (input == null || StringUtils.isBlank(input.getOriginalFilename())) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Long input) {
        if (input == null || StringUtils.isBlank(input.toString())) {
            return true;
        }
        return false;
    }
    
    public static boolean isEmpty(String strInput) {
        if (strInput == null || strInput.trim().equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(List<?> vctInput) {
        try {
            if (vctInput == null || vctInput.size() == 0) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }
}
