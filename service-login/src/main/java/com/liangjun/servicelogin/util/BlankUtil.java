package com.liangjun.servicelogin.util;

public class BlankUtil {
    public static boolean isBlank(String o){
        if (o==null || "".equals(o) || o.length()<11){
            return true;
        }
        return false;
    }
}
