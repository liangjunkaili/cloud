package com.liangjun.servicelogin.util;

import com.liangjun.servicelogin.config.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 该类主要提供密码的加密方式、每次登录时key的生成
 */
public class SignUtil {

    public static String encrypt(String strSrc, String encName) {
        MessageDigest md;
        String strDes;
        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }
    //转换为16进制
    private static String bytes2Hex(byte[] bts) {
        StringBuilder des = new StringBuilder();
        String tmp;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }

    public static String getToken(String account){
        String token;
        String tmp = "account="+account+"&key="+ Constant.salt;
        tmp = tmp.trim();
        String sign = encrypt(tmp,null);
        token = Constant.prefix+sign.toLowerCase();
        return token;
    }
    public static void main(String[] args) {
//        System.out.println(encrypt("123456","MD5"));
        System.out.println(getToken("15600806167"));
    }
}
