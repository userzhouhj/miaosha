package com.jun.user.entity.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
@Data
public class UserRegisterVo implements Serializable {


    private String nickname;

    private String phone;

    private String password;
}
