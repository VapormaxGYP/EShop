package com.vapor.eshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
@TableName("Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "order_id", type = IdType.AUTO)
      private Integer orderId;

    private Integer userId;

    private String loginName;

    private String userAddress;

    private Integer orderCost;

    private String orderNo;

    
    public Integer getOrderId() {
        return orderId;
    }

      public void setOrderId(Integer orderId) {
          this.orderId = orderId;
      }
    
    public Integer getUserId() {
        return userId;
    }

      public void setUserId(Integer userId) {
          this.userId = userId;
      }
    
    public String getLoginName() {
        return loginName;
    }

      public void setLoginName(String loginName) {
          this.loginName = loginName;
      }
    
    public String getUserAddress() {
        return userAddress;
    }

      public void setUserAddress(String userAddress) {
          this.userAddress = userAddress;
      }
    
    public Integer getOrderCost() {
        return orderCost;
    }

      public void setOrderCost(Integer orderCost) {
          this.orderCost = orderCost;
      }
    
    public String getOrderNo() {
        return orderNo;
    }

      public void setOrderNo(String orderNo) {
          this.orderNo = orderNo;
      }

    @Override
    public String toString() {
        return "Order{" +
              "orderId=" + orderId +
                  ", userId=" + userId +
                  ", loginName=" + loginName +
                  ", userAddress=" + userAddress +
                  ", orderCost=" + orderCost +
                  ", orderNo=" + orderNo +
              "}";
    }
}
