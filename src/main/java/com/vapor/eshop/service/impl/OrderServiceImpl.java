package com.vapor.eshop.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vapor.eshop.entity.Order;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.mapper.OrderMapper;
import com.vapor.eshop.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vapor.eshop.utils.JWTUtils;
import com.vapor.eshop.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Result<?> getOrders(String jwt) {

        Map<String, Claim> map = JWTUtils.verifyAndGetClaims(jwt);
        Integer userId = map.get("userId").asInt();

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Order> orders = this.orderMapper.selectList(queryWrapper);
        List<OrderVO> orderVOs = orders.stream().map(OrderVO::new).collect(Collectors.toList());
        Result<List<OrderVO>> result = new Result<>();
        result.setCode(0);
        result.setMsg("Success Get Orders");
        result.setData(orderVOs);
        return result;
    }
}
