package com.demo.utils;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取一个UUID
     * @return
     */
    public static String getOneUUID(){
        String s = UUID.randomUUID().toString();
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }

}
