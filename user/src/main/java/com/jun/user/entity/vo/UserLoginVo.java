package com.jun.user.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
@Data
public class UserLoginVo implements Serializable {
    /**
     * 用户手机号或者昵称
     */
    private String username;
    private String password;
}
