package com.vapor.eshop.service;

import com.vapor.eshop.entity.Result;
import com.vapor.eshop.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vapor.eshop.form.UserLoginForm;
import com.vapor.eshop.form.UserRegisterForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
public interface UserService extends IService<User> {

    public Result<?> userLogin(UserLoginForm userLoginForm);
    public Result<?> userRegister(UserRegisterForm userRegisterForm);

}
