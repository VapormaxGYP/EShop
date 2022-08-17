package com.vapor.eshop.vo;

import com.vapor.eshop.entity.UserAddress;
import lombok.Data;

@Data
public class UserAddressListVO {

    private Integer addressId;
    private String address;
    private Integer isDefault;

    public UserAddressListVO(UserAddress userAddress){

        this.addressId = userAddress.getAddressId();
        this.address = userAddress.getAddress();
        this.isDefault = userAddress.getIsDefault();
    }
}
