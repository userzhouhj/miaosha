package com.jun.user.redis;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
public class UserKey {

    public static String userTokenKey = "user:token:";
    public static Long userTokenExpire = 60*60*24*7L;
}
