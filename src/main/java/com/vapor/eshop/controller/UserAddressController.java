package com.vapor.eshop.controller;


import com.vapor.eshop.entity.Result;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;
import com.vapor.eshop.form.UserAddressForm;
import com.vapor.eshop.form.UserAddressUpdateForm;
import com.vapor.eshop.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
@RestController
@RequestMapping("/userAddress")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;
    @GetMapping("/getAddress")
    public Result<?> getAddress(@RequestHeader("Authorization") String jwt){

        return userAddressService.getAddress(jwt);
    }

    @PostMapping("/addAddress")
    public Result<?> addAddress(@RequestHeader("Authorization") String jwt,
                                @Valid @RequestBody UserAddressForm userAddressForm,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new EshopException(ResponseEnum.USER_ADD_ADDRESS);
        }

        return userAddressService.addAddress(jwt, userAddressForm);
    }


    @PutMapping("/updateAddress")
    public Result<?> updateAddress(@RequestHeader("Authorization") String jwt,
                                   @Valid @RequestBody UserAddressUpdateForm userAddressUpdateForm,
                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new EshopException(ResponseEnum.EMPTY_UPDATE_ADDRESS);
        }

        return userAddressService.updateAddress(jwt, userAddressUpdateForm);

    }


    @DeleteMapping("/deleteAddress")
    public Result<?> deleteAddress(@RequestHeader("Authorization") String jwt,
                                   @RequestBody Integer[] addressIds){
        return userAddressService.deleteAddress(jwt, addressIds);
    }
}

