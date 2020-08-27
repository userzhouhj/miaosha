package com.jun.common.enums;

import lombok.Getter;

@Getter
public enum BaseCode {
    /**
     * 基础状态码
     */
    SUCCESS(20000,"success"),
    ERROR(50000,"The server find exception"),
    USER_PASSWORD_ERROR(40001,"用户密码错误"),
    USER_NOT_LOGIN(40002,"用户未登录");

    private Integer code;

    private String msg;

    private BaseCode(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
