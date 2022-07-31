package com.vapor.eshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Vapor
 * @since 2022-08-01
 */
@TableName("OrderDetails")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ID", type = IdType.AUTO)
      private Integer id;

    @TableField("OrderID")
    private Integer orderid;

    @TableField("ProductID")
    private Integer productid;

    @TableField("Quantity")
    private Integer quantity;

    @TableField("Cost")
    private Integer cost;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public Integer getOrderid() {
        return orderid;
    }

      public void setOrderid(Integer orderid) {
          this.orderid = orderid;
      }
    
    public Integer getProductid() {
        return productid;
    }

      public void setProductid(Integer productid) {
          this.productid = productid;
      }
    
    public Integer getQuantity() {
        return quantity;
    }

      public void setQuantity(Integer quantity) {
          this.quantity = quantity;
      }
    
    public Integer getCost() {
        return cost;
    }

      public void setCost(Integer cost) {
          this.cost = cost;
      }

    @Override
    public String toString() {
        return "OrderDetails{" +
              "id=" + id +
                  ", orderid=" + orderid +
                  ", productid=" + productid +
                  ", quantity=" + quantity +
                  ", cost=" + cost +
              "}";
    }
}
