package com.vapor.eshop.service.impl;

import com.vapor.eshop.entity.OrderDetails;
import com.vapor.eshop.mapper.OrderDetailsMapper;
import com.vapor.eshop.service.OrderDetailsService;
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
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails> implements OrderDetailsService {

}
