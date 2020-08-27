package com.jun.common.result;

import com.jun.common.enums.BaseCode;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
public class ResponseDataUtils {

    public static <T> ResponseData ok(Integer code,T data){

        return new ResponseData<>(code,"success",data);

    }
    public static <T> ResponseData ok(T data){

        return new ResponseData<>(BaseCode.SUCCESS.getCode(),"success",data);
    }
    public static <T> ResponseData error(Integer code,String msg){

        return new ResponseData<>(code,msg);

    }

    public static <T> ResponseData error(){

        return new ResponseData<>(BaseCode.ERROR.getCode(),BaseCode.ERROR.getMsg());

    }
}
