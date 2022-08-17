package com.vapor.eshop.service;

import com.vapor.eshop.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.form.GenerateOrderForm;
import com.vapor.eshop.form.ProductToCartForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
public interface CartService extends IService<Cart> {

    public Result<?> AddToCart(String jwt, ProductToCartForm productToCartForm);
    public Result<?> UpdateCart(String jwt, Integer productId ,Integer count);
    public Result<?> getCartDetails(String jwt);
    public Result<?> deleteCartRecord(String jwt, Integer[] productIds);
    public Result<?> generateOrder(String jwt, GenerateOrderForm form);


}
