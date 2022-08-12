package com.vapor.eshop.service;

import com.vapor.eshop.entity.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.form.GetProductInfoForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
public interface ProductInfoService extends IService<ProductInfo> {

    public Result<?> getProductList(GetProductInfoForm productInfoForm);
    public Result<?> getProductInfo(Integer productId);

}
