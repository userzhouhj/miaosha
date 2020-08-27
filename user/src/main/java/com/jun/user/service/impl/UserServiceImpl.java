package com.jun.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jun.user.entity.User;
import com.jun.user.entity.vo.UserLoginVo;
import com.jun.user.entity.vo.UserRegisterVo;
import com.jun.user.mapper.UserMapper;
import com.jun.user.redis.UserKey;
import com.jun.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author userzhou
 * @since 2020
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String dologin(UserLoginVo userLoginVo){
        //登录流程
        //取出登录信息中的用户名或者电话号
        String phonePattern = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("passwor",userLoginVo.getPassword());
        if(Pattern.matches(phonePattern,userLoginVo.getUsername())){
            //电话号登录
            wrapper.eq("phone",userLoginVo.getUsername());
        }else{
            wrapper.eq("nickname",userLoginVo.getUsername());
        }
        //通过数据库验证信息是否正确
        User user = baseMapper.selectOne(wrapper);

        if(user == null){
            throw new RuntimeException("用户名密码错误");
        }
        //信息正确生成token
        String token = createToken();
        //将token作为键存入缓存，并设置过期时间
        user.setLastLoginDate(new Date());
        user.setLoginCount(user.getLoginCount() +1);
        baseMapper.updateById(user);
        redisTemplate.opsForValue().set(UserKey.userTokenKey+token,user,UserKey.userTokenExpire, TimeUnit.SECONDS);
        //返回token
        return token;
    }

    public String doRegistr(UserRegisterVo userRegisterVo){
        Date date = new Date();
        User user = userRegisterToUser(userRegisterVo);
        user.setLoginCount(0);
        user.setLastLoginDate(date);
        user.setRegisterDate(date);
        user.setUserId(System.currentTimeMillis());

        baseMapper.insert(user);
        String token = createToken();
        redisTemplate.opsForValue().set(UserKey.userTokenKey+token,user,UserKey.userTokenExpire, TimeUnit.SECONDS);
        return token;
    }

    public User getUserByToken(String userToken){
        User user = (User)redisTemplate.opsForValue().get(UserKey.userTokenKey+userToken);

        return user;
    }

    public String createToken(){
        return IdUtil.simpleUUID();
    }
    private User userRegisterToUser(UserRegisterVo userRegisterVo){
        User user = new User();
        BeanUtils.copyProperties(userRegisterVo,user);

        return user;
    }
}
