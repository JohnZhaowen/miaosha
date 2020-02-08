package com.john.miaosha.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TradeNumUtil {

    public static String generateTradeNum(){
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return UUID.randomUUID() + format.format(new Date());
    }
}
