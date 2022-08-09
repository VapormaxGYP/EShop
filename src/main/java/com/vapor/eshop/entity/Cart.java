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
@TableName("Cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer productId;

    private Integer productQuantity;

    private Integer userId;

    private Integer cost;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public Integer getProductId() {
        return productId;
    }

      public void setProductId(Integer productId) {
          this.productId = productId;
      }
    
    public Integer getProductQuantity() {
        return productQuantity;
    }

      public void setProductQuantity(Integer productQuantity) {
          this.productQuantity = productQuantity;
      }
    
    public Integer getUserId() {
        return userId;
    }

      public void setUserId(Integer userId) {
          this.userId = userId;
      }
    
    public Integer getCost() {
        return cost;
    }

      public void setCost(Integer cost) {
          this.cost = cost;
      }

    @Override
    public String toString() {
        return "Cart{" +
              "id=" + id +
                  ", productId=" + productId +
                  ", productQuantity=" + productQuantity +
                  ", userId=" + userId +
                  ", cost=" + cost +
              "}";
    }
}
