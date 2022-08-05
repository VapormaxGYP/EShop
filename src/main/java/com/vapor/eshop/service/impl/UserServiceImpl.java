package com.vapor.eshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.entity.User;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;
import com.vapor.eshop.form.UserLoginForm;
import com.vapor.eshop.mapper.UserMapper;
import com.vapor.eshop.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vapor.eshop.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vapor
 * @since 2022-08-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public Result<?> userLogin(UserLoginForm userLoginForm) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("LoginName", userLoginForm.getLoginname());

        User user = this.userMapper.selectOne(queryWrapper);
        Result<User> result = new Result<>();
        if(user == null)
        {
            result.setSuccess("Fail");
            result.setMsg("Can not find a user, please register");
            return result;
        }

        String md5pass = DigestUtils.md5DigestAsHex(userLoginForm.getPassword().getBytes());

        if(!user.getPassword().equals(md5pass))
        {
            result.setSuccess("Fail");
            result.setMsg("Password is wrong, Try again");
            return result;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("loginname", user.getLoginname());
        map.put("gender", user.getGender());
        String token = "";

        try {
            token = JWTUtils.getToken(map);
        } catch (Exception e) {
            result.setSuccess("Fail");
            result.setMsg(e.getMessage());
            return result;
        }

        result.setSuccess("Success");
        result.setMsg(token);
        result.setDetail(user);

        return result;
    }
}
