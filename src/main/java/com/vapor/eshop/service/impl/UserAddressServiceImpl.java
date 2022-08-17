package com.vapor.eshop.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.entity.UserAddress;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;
import com.vapor.eshop.form.UserAddressForm;
import com.vapor.eshop.form.UserAddressUpdateForm;
import com.vapor.eshop.mapper.UserAddressMapper;
import com.vapor.eshop.service.UserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vapor.eshop.utils.JWTUtils;
import com.vapor.eshop.vo.UserAddressListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Override
    public Result<?> getAddress(String jwt) {
        Map<String, Claim> map = JWTUtils.verifyAndGetClaims(jwt);
        Integer userId = map.get("userId").asInt();

        QueryWrapper<UserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserAddress> allAddress = this.userAddressMapper.selectList(queryWrapper);
        List<UserAddressListVO> addressVO = allAddress.stream().map(UserAddressListVO::new).collect(Collectors.toList());

        Result<List<UserAddressListVO>> result = new Result<>();
        result.setCode(0);
        result.setMsg("Success Get User Address");
        result.setData(addressVO);

        return result;
    }

    @Override
    public Result<?> addAddress(String jwt, UserAddressForm userAddressForm) {
        Map<String, Claim> map = JWTUtils.verifyAndGetClaims(jwt);
        Integer userId = map.get("userId").asInt();
        String newAddress = userAddressForm.getAddress();

        QueryWrapper<UserAddress> queryWrapperCheck = new QueryWrapper<>();
        queryWrapperCheck.eq("address", newAddress);
        UserAddress checkAddress = this.userAddressMapper.selectOne(queryWrapperCheck);
        if(checkAddress != null)
            throw new EshopException(ResponseEnum.ADDRESS_ALREADY_EXIST);

        QueryWrapper<UserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_default", 1)
                .and(wrapper -> wrapper.eq("user_id", userId));

        UserAddress userAddress = this.userAddressMapper.selectOne(queryWrapper);
        UpdateWrapper<UserAddress> updateWrapper = new UpdateWrapper<>();

        if(userAddressForm.getIsDefault() == 1 && userAddress != null)
        {
            updateWrapper.eq("user_id", userAddress.getUserId())
                    .eq("is_default", 1)
                    .set("is_default", 0);
            this.userAddressMapper.update(userAddress, updateWrapper);
        }

        UserAddress insert = new UserAddress();
        insert.setUserId(userId);
        insert.setAddress(userAddressForm.getAddress());
        insert.setIsDefault(userAddressForm.getIsDefault());
        int res = this.userAddressMapper.insert(insert);
        if(res != 1)
            throw new EshopException(ResponseEnum.INSERT_ADDRESS_ERROR);

        Result<Object> result = new Result<>();
        result.setCode(0);
        result.setMsg("Success Insert New Address");

        return result;
    }

    @Override
    public Result<?> updateAddress(String jwt, UserAddressUpdateForm userAddressUpdateForm) {
        Map<String, Claim> map = JWTUtils.verifyAndGetClaims(jwt);
        Integer userId = map.get("userId").asInt();

        Integer addressId = userAddressUpdateForm.getAddressId();
        String address = userAddressUpdateForm.getAddress();
        Integer isDefault = userAddressUpdateForm.getIsDefault();

        if(address == null && isDefault == null)
            throw new EshopException(ResponseEnum.EMPTY_UPDATE_ADDRESS);

        if(this.userAddressMapper.selectById(addressId) == null)
            throw new EshopException(ResponseEnum.NO_SUCH_ADDRESS);

        if(isDefault == 1)
        {
            QueryWrapper<UserAddress> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_default", 1)
                    .and(wrapper -> wrapper.eq("user_id", userId));
            UserAddress userAddress = this.userAddressMapper.selectOne(queryWrapper);

            if(userAddress.getAddressId() != addressId)
            {
                UpdateWrapper<UserAddress> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("address_id", userAddress.getAddressId())
                        .set("is_default", 0);
                this.userAddressMapper.update(userAddress, updateWrapper);
            }
        }

        UpdateWrapper<UserAddress> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("address_id", addressId)
                .set("address", address)
                .set("is_default", isDefault);

        this.userAddressMapper.update(new UserAddress(), updateWrapper);
        Result<Object> result = new Result<>();
        result.setCode(0);
        result.setMsg("Success Update User Address");

        return result;
    }

    @Override
    public Result<?> deleteAddress(String jwt, Integer[] addressIds) {
        Map<String, Claim> map = JWTUtils.verifyAndGetClaims(jwt);
        Integer userId = map.get("userId").asInt();

        if(addressIds.length == 0)
            throw new EshopException(ResponseEnum.EMPTY_ADDRESS_ID);

        for(Integer addressId : addressIds){
            deleteByID(addressId, userId);
        }
        Result<Object> result = new Result<>();
        result.setCode(0);
        result.setMsg("Success Delete Address");

        return result;
    }

    public void deleteByID(Integer addressId, Integer userId){

        QueryWrapper<UserAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("address_id", addressId)
                .and(wrapper -> wrapper.eq("user_id", userId));
        if(this.userAddressMapper.selectOne(queryWrapper) == null)
            throw new EshopException(ResponseEnum.NO_SUCH_ADDRESS);

        this.userAddressMapper.delete(queryWrapper);
    }
}
