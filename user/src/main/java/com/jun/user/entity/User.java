package com.jun.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author userzhou
 * @since 2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用于标记用户")
    private Long userId;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "MD5(MD5(pass明文+固定salt) + salt)")
    private String password;

    @ApiModelProperty(value = "头像，云存储的ID")
    private String head;

    @ApiModelProperty(value = "注册时间")
    private Date registerDate;

    @ApiModelProperty(value = "上蔟登录时间")
    private Date lastLoginDate;

    @ApiModelProperty(value = "登录次数")
    private Integer loginCount;


}
