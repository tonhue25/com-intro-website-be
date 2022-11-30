package com.altek.intro.enums;

import java.util.Arrays;
import java.util.List;

public enum ContactType {
    FEEDBACK, SUBSCRIBE, UNSUBSCRIBE;

    public static List<ContactType> getAllContactType(){
        return Arrays.asList(FEEDBACK,SUBSCRIBE,UNSUBSCRIBE);
    }
}
