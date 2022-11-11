package com.altek.intro.utils;

import java.util.List;

public class DataUtil {

    public static boolean isEmpty(Integer input) {
        if (input == null) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Long input) {
        if (input == null) {
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