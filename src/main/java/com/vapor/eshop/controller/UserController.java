package com.vapor.eshop.controller;


import com.vapor.eshop.entity.Result;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;
import com.vapor.eshop.form.UserLoginForm;
import com.vapor.eshop.form.UserRegisterForm;
import com.vapor.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
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
        if(bindingResult.hasErrors()){
            throw new EshopException(ResponseEnum.REGISTER_INFO_EMPTY);
        }

        return userService.userRegister(userRegisterForm);
    }

    @CrossOrigin
    @GetMapping("/getUserDetails")
    public Result<?> getUserInfo(@RequestHeader("Authorization") String jwt){
        return userService.getUserDetails(jwt);
    }

    @GetMapping("/test")
    public void test(@RequestParam(value = "para", required = true) Integer para){
        System.out.println("Test");
    }


}

