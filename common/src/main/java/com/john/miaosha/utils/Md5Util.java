package com.john.miaosha.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class Md5Util {

    public static final String MD5_KEY = "MIAOSHAO";

    public static String md5(String text, String key){
        String encodedStr = DigestUtils.md2Hex(text + key);
        System.out.println("加密后的字符串为： " + encodedStr);
        return encodedStr;
    }

    public static boolean verify(String text, String key, String md5){
        String encodedStr = md5(text, key);
        return StringUtils.equals(encodedStr, md5);
    }
}
