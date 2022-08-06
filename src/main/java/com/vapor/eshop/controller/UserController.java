package com.vapor.eshop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.entity.User;
import com.vapor.eshop.entity.UserAddress;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;
import com.vapor.eshop.form.UserLoginForm;
import com.vapor.eshop.form.UserRegisterForm;
import com.vapor.eshop.service.UserAddressService;
import com.vapor.eshop.service.UserService;
import com.vapor.eshop.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Vapor
 * @since 2022-08-01
 */
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<?> loginResult(@Valid @RequestBody UserLoginForm userLoginForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new EshopException(ResponseEnum.LOGIN_INFO_EMPTY);
        }
        return userService.userLogin(userLoginForm);
    }

    @PostMapping("/register")
    public Result<?> registerResult(@Valid @RequestBody UserRegisterForm userRegisterForm, BindingResult bindingResult){
        System.out.println(userRegisterForm.toString());
        if(bindingResult.hasErrors()){
            throw new EshopException(ResponseEnum.REGISTER_INFO_EMPTY);
        }

        return userService.userRegister(userRegisterForm);
    }

    @GetMapping("/test")
    public List<User> getAllUser(){
        return userService.list();
    }


}

