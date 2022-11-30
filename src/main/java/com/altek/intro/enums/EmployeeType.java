package com.altek.intro.enums;

import java.util.Arrays;
import java.util.List;

public enum EmployeeType {
    BOD,LEAD;
    public static List<EmployeeType> getAllEmployeeType(){
        return Arrays.asList(BOD,LEAD);
    }
}
