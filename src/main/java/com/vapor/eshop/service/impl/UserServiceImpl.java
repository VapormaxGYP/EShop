package com.vapor.eshop.service.impl;

import com.auth0.jwt.interfaces.Claim;
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
import com.vapor.eshop.vo.UserDetailVO;
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
 * @since 2022-08-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Result<?> userLogin(UserLoginForm userLoginForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", userLoginForm.getLoginName());
        User user = this.userMapper.selectOne(queryWrapper);

        Result<Object> result = new Result<>();
        if(user == null)
            throw new EshopException(ResponseEnum.LOGIN_NAME_NOT_EXIST);

        String md5pass = DigestUtils.md5DigestAsHex(userLoginForm.getPassword().getBytes());
        if(!md5pass.equals(user.getPassword()))
            throw new EshopException(ResponseEnum.PASSWORD_ERROR);

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("userId", user.getUserId());
        payloadMap.put("loginName", user.getLoginName());

        String token = JWTUtils.getToken(payloadMap);

        Map<String, String> resMap = new HashMap<>();
        resMap.put("JWT", token);

        result.setCode(0);
        result.setMsg("Success Login");
        result.setData(resMap);

        return result;
    }

    @Override
    public Result<?> userRegister(UserRegisterForm userRegisterForm) {
        Result<Object> result = new Result<>();
        //Whether the user is already exist
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", userRegisterForm.getLoginName());
        User checkUser = this.userMapper.selectOne(queryWrapper);

        if(checkUser != null)
            throw new EshopException(ResponseEnum.USER_EXIST_ERROR);

        //check Email form
        if(!RegexValidUtils.checkEmail(userRegisterForm.getEmailAdd()))
            throw new EshopException(ResponseEnum.EMAIL_ERROR);

        String md5pass = DigestUtils.md5DigestAsHex(userRegisterForm.getPassword().getBytes());
        userRegisterForm.setPassword(md5pass);

        User insertUser = new User();
        BeanUtils.copyProperties(userRegisterForm, insertUser);
        int insert = this.userMapper.insert(insertUser);
        if(insert != 1)
            throw new EshopException(ResponseEnum.REGISTER_ERROR);

        result.setCode(0);
        result.setMsg("Register Success");

        return result;
    }

    @Override
    public Result<?> getUserDetails(String jwt) {

        Map<String, Claim> claims = JWTUtils.verifyAndGetClaims(jwt);
        Integer userID = claims.get("userId").asInt();
        String loginName = claims.get("loginName").asString();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userID);
        User user = this.userMapper.selectOne(queryWrapper);

        UserDetailVO userDetailVO = new UserDetailVO(user);

        Result<UserDetailVO> result = new Result<>();
        result.setCode(0);
        result.setMsg("Success Get User Detail Info");
        result.setData(userDetailVO);

        return result;
    }
}
