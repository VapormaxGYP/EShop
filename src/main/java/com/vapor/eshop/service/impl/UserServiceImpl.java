package com.vapor.eshop.service.impl;

import com.vapor.eshop.entity.User;
import com.vapor.eshop.mapper.UserMapper;
import com.vapor.eshop.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
