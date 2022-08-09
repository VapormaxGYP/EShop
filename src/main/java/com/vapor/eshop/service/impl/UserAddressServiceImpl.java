package com.vapor.eshop.service.impl;

import com.vapor.eshop.entity.UserAddress;
import com.vapor.eshop.mapper.UserAddressMapper;
import com.vapor.eshop.service.UserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

}
