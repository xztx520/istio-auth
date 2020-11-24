package com.csse.auth.utils;

/**
 * @description:
 * @company: 喝咖啡的大象
 * @author: 施海洲
 * @date: Created in 2020/7/1 0008 上午 11:28
 */
public class StringHelper {
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
