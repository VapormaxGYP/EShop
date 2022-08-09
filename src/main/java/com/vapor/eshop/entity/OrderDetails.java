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
@TableName("OrderDetails")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer orderId;

    private Integer productId;

    private Integer productQuantity;

    private Integer detailCost;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public Integer getOrderId() {
        return orderId;
    }

      public void setOrderId(Integer orderId) {
          this.orderId = orderId;
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
    
    public Integer getDetailCost() {
        return detailCost;
    }

      public void setDetailCost(Integer detailCost) {
          this.detailCost = detailCost;
      }

    @Override
    public String toString() {
        return "OrderDetails{" +
              "id=" + id +
                  ", orderId=" + orderId +
                  ", productId=" + productId +
                  ", productQuantity=" + productQuantity +
                  ", detailCost=" + detailCost +
              "}";
    }
}
