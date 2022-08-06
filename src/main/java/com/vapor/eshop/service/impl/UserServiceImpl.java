package com.vapor.eshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.entity.User;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;
import com.vapor.eshop.form.UserLoginForm;
import com.vapor.eshop.form.UserRegisterForm;
import com.vapor.eshop.mapper.UserMapper;
import com.vapor.eshop.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vapor.eshop.utils.JWTUtils;
import com.vapor.eshop.utils.RegexValidUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
    public Result<?> userLogin(UserLoginForm userLoginForm){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("LoginName", userLoginForm.getLoginname());

        User user = this.userMapper.selectOne(queryWrapper);
        Result<User> result = new Result<>();
        if(user == null)
        {
            throw new EshopException(ResponseEnum.LOGIN_NAME_NOT_EXIST);
        }

        String md5pass = DigestUtils.md5DigestAsHex(userLoginForm.getPassword().getBytes());

        if(!user.getPassword().equals(md5pass))
        {
            throw new EshopException(ResponseEnum.PASSWORD_ERROR);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userid", user.getUserid());
        map.put("username", user.getUsername());
        map.put("loginname", user.getLoginname());
        map.put("gender", user.getGender());
        map.put("phone", user.getPhone());
        map.put("emailAdd", user.getEmailAdd());
        String token;

        token = JWTUtils.getToken(map);

        result.setCode(200);
        result.setMsg(token);
        result.setDetail(user);

        return result;
    }


    /**
     * User register
     */
    @Override
    public Result<?> userRegister(UserRegisterForm userRegisterForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("LoginName", userRegisterForm.getLoginname());

        Result<User> result = new Result<>();

        User user = this.userMapper.selectOne(queryWrapper);
        if(user != null)
            throw new EshopException(ResponseEnum.USER_EXIST_ERROR);

        //Email check
        if(!RegexValidUtils.checkEmail(userRegisterForm.getEmailAdd()))
            throw new EshopException(ResponseEnum.EMAIL_ERROR);

        //change the password to MD5 password
        String md5Pass = DigestUtils.md5DigestAsHex(userRegisterForm.getPassword().getBytes());
        userRegisterForm.setPassword(md5Pass);

        User insertUser = new User();
        BeanUtils.copyProperties(userRegisterForm, insertUser);
        int insert = this.userMapper.insert(insertUser);

        if(insert != 1){
            throw new EshopException(ResponseEnum.REGISTER_ERROR);
        }

        result.setCode(200);
        result.setMsg("Register Success");
        result.setDetail(insertUser);

        return result;
    }
}
