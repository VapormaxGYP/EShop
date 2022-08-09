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
@TableName("UserAddress")
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "address_id", type = IdType.AUTO)
      private Integer addressId;

    private Integer userId;

    private String address;

    private Integer isDefault;

    
    public Integer getAddressId() {
        return addressId;
    }

      public void setAddressId(Integer addressId) {
          this.addressId = addressId;
      }
    
    public Integer getUserId() {
        return userId;
    }

      public void setUserId(Integer userId) {
          this.userId = userId;
      }
    
    public String getAddress() {
        return address;
    }

      public void setAddress(String address) {
          this.address = address;
      }
    
    public Integer getIsDefault() {
        return isDefault;
    }

      public void setIsDefault(Integer isDefault) {
          this.isDefault = isDefault;
      }

    @Override
    public String toString() {
        return "UserAddress{" +
              "addressId=" + addressId +
                  ", userId=" + userId +
                  ", address=" + address +
                  ", isDefault=" + isDefault +
              "}";
    }
}
