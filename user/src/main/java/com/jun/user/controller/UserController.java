package com.jun.user.controller;


import com.jun.common.enums.BaseCode;
import com.jun.common.result.ResponseData;
import com.jun.common.result.ResponseDataUtils;
import com.jun.user.entity.User;
import com.jun.user.entity.vo.UserLoginVo;
import com.jun.user.entity.vo.UserRegisterVo;
import com.jun.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author userzhou
 * @since 2020
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseData login(@RequestBody UserLoginVo userLoginVo){
        String token = userService.dologin(userLoginVo);
        return ResponseDataUtils.ok(BaseCode.SUCCESS.getCode(),token);
    }

    @PostMapping("/register")
    public ResponseData register(@RequestBody UserRegisterVo userRegisterVo){
        String token = userService.doRegistr(userRegisterVo);
        return ResponseDataUtils.ok(BaseCode.SUCCESS.getCode(),token);
    }

    @GetMapping("/get_user_by_token")
    public ResponseData getUserByToken(@RequestParam("token") String token){

        User user = userService.getUserByToken(token);

        if(user == null){
            return ResponseDataUtils.error(BaseCode.USER_NOT_LOGIN.getCode(),BaseCode.USER_NOT_LOGIN.getMsg());
        }
        //用户已经登录
        return ResponseDataUtils.ok(user);
    }

}

