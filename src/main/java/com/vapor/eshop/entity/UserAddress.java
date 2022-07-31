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
@TableName("UserAddress")
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ID", type = IdType.AUTO)
      private Integer id;

    @TableField("UserID")
    private Integer userid;

    @TableField("Address")
    private String address;

    @TableField("isDefault")
    private Integer isdefault;

    
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
    
    public String getAddress() {
        return address;
    }

      public void setAddress(String address) {
          this.address = address;
      }
    
    public Integer getIsdefault() {
        return isdefault;
    }

      public void setIsdefault(Integer isdefault) {
          this.isdefault = isdefault;
      }

    @Override
    public String toString() {
        return "UserAddress{" +
              "id=" + id +
                  ", userid=" + userid +
                  ", address=" + address +
                  ", isdefault=" + isdefault +
              "}";
    }
}
