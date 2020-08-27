package com.jun.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseData<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public ResponseData(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public ResponseData(Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
