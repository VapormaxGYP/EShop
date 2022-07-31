package com.vapor.eshop.service.impl;

import com.vapor.eshop.entity.Cart;
import com.vapor.eshop.mapper.CartMapper;
import com.vapor.eshop.service.CartService;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}
