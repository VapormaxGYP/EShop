package com.vapor.eshop.controller;


import com.vapor.eshop.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Vapor
 * @since 2022-08-01
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @PostMapping("/register")
    public String register(User user){
        System.out.println(user);
        return null;
    }

}

