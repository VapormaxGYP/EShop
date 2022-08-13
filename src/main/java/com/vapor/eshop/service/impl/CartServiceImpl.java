package com.vapor.eshop.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.vapor.eshop.entity.Cart;
import com.vapor.eshop.entity.ProductInfo;
import com.vapor.eshop.entity.Result;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;
import com.vapor.eshop.form.ProductToCartForm;
import com.vapor.eshop.mapper.CartMapper;
import com.vapor.eshop.mapper.ProductInfoMapper;
import com.vapor.eshop.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vapor.eshop.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author Vapor
 * @since 2022-08-10
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public Result<?> AddToCart(String jwt, ProductToCartForm productToCartForm) {
        Map<String, Claim> map = JWTUtils.verifyAndGetClaims(jwt);
        Integer userId = map.get("userId").asInt();
        Integer productId = productToCartForm.getProductId();
        Integer count = productToCartForm.getCount();

        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        ProductInfo productInfo = this.productInfoMapper.selectOne(queryWrapper);

        if(count > productInfo.getProductStock())
            throw new EshopException(ResponseEnum.OUT_OF_STOCK);
        Integer productPrice = productInfo.getProductPrice();
        Integer cost = productPrice * count;

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setProductQuantity(count);
        cart.setCost(cost);

        int res = this.cartMapper.insert(cart);
        if(res != 1)
            throw new EshopException(ResponseEnum.ADD_CART_ERROR);

        Result<Object> result = new Result<>();
        result.setCode(0);
        result.setMsg("Success Add to Cart");

        return result;
    }

    @Override
    public Result<?> UpdateCart(String jwt, Integer cartId, Integer productId ,Integer count) {
        Map<String, Claim> map = JWTUtils.verifyAndGetClaims(jwt);
        Integer userId = map.get("userId").asInt();
        Integer stock = this.productInfoMapper.selectById(productId).getProductStock();
        Integer price = this.productInfoMapper.selectById(productId).getProductPrice();
        Cart cart = this.cartMapper.selectById(cartId);
        if(cart == null)
            throw new EshopException(ResponseEnum.NO_SUCH_RECORD);
        if(count > stock)
            throw new EshopException(ResponseEnum.OUT_OF_STOCK);

        UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId)
                .and(wrapper -> wrapper.eq("id",cartId))
                .set("product_quantity", count)
                .set("cost",count * price);

        this.cartMapper.update(new Cart(), updateWrapper);
        Result<Object> result = new Result<>();
        result.setCode(0);
        result.setMsg("Success Update Cart");

        return result;
    }
}
