package com.vapor.eshop.service;

import com.vapor.eshop.entity.Result;
import com.vapor.eshop.entity.UserAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vapor.eshop.form.UserAddressForm;
import com.vapor.eshop.form.UserAddressUpdateForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
public interface UserAddressService extends IService<UserAddress> {

    public Result<?> getAddress(String jwt);

    public Result<?> addAddress(String jwt, UserAddressForm userAddressForm);

    public Result<?> updateAddress(String jwt, UserAddressUpdateForm userAddressUpdateForm);

    public Result<?> deleteAddress(String jwt, Integer[] addressIds);

}
