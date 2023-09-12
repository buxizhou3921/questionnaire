package com.demo.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ClassName: ProblemController
 * Package: com.demo.controller
 * Description: 获取当前IP
 *
 * @Author: 王方舟
 * @Create: 2023-06-26 - 23:57
 * @Version: v1.0
 */

public class IPUtil {

    public static String getIp(){
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            String ip = localhost.getHostAddress();
            return ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
