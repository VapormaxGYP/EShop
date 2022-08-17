package com.vapor.eshop.vo;

import com.vapor.eshop.entity.Order;
import lombok.Data;

@Data
public class OrderVO {
    private Integer orderId;
    private String address;
    private Integer totalCost;
    private String Date;

    public OrderVO(Order order){
        this.orderId = order.getOrderId();
        this.address = order.getUserAddress();
        this.totalCost = order.getOrderCost();
        this.Date = order.getOrderNo();
    }
}
