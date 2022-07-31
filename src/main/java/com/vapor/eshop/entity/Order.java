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
@TableName("Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ID", type = IdType.AUTO)
      private Integer id;

    @TableField("UserID")
    private Integer userid;

    @TableField("LoginName")
    private String loginname;

    @TableField("UserAddress")
    private String useraddress;

    @TableField("Cost")
    private Integer cost;

    @TableField("OrderNo")
    private String orderno;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public Integer getUserid() {
        return userid;
    }

      public void setUserid(Integer userid) {
          this.userid = userid;
      }
    
    public String getLoginname() {
        return loginname;
    }

      public void setLoginname(String loginname) {
          this.loginname = loginname;
      }
    
    public String getUseraddress() {
        return useraddress;
    }

      public void setUseraddress(String useraddress) {
          this.useraddress = useraddress;
      }
    
    public Integer getCost() {
        return cost;
    }

      public void setCost(Integer cost) {
          this.cost = cost;
      }
    
    public String getOrderno() {
        return orderno;
    }

      public void setOrderno(String orderno) {
          this.orderno = orderno;
      }

    @Override
    public String toString() {
        return "Order{" +
              "id=" + id +
                  ", userid=" + userid +
                  ", loginname=" + loginname +
                  ", useraddress=" + useraddress +
                  ", cost=" + cost +
                  ", orderno=" + orderno +
              "}";
    }
}
