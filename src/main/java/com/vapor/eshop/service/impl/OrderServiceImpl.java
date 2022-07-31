package com.vapor.eshop.service.impl;

import com.vapor.eshop.entity.Order;
import com.vapor.eshop.mapper.OrderMapper;
import com.vapor.eshop.service.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
