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
import com.vapor.eshop.vo.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author Vapor
 * @since 2022-08-10
 */
@Service
@Slf4j
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

        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id", userId).eq("product_id", productId);

        Cart cart = this.cartMapper.selectOne(cartQueryWrapper);

        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        ProductInfo productInfo = this.productInfoMapper.selectOne(queryWrapper);

        if(count > productInfo.getProductStock())
            throw new EshopException(ResponseEnum.OUT_OF_STOCK);
        Integer productPrice = productInfo.getProductPrice();

        if(cart == null)
        {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setProductQuantity(count);
            cart.setProductId(productId);
            cart.setCost(productPrice);
            int res = this.cartMapper.insert(cart);
            if(res != 1)
                throw new EshopException(ResponseEnum.ADD_CART_ERROR);
        }
        else{
            count = cart.getProductQuantity() + count;
            UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_id", cart.getUserId()).eq("product_id", cart.getProductId())
                    .set("product_quantity", count).set("cost", productPrice);
            this.cartMapper.update(cart, updateWrapper);
        }

        Result<Object> result = new Result<>();
        result.setCode(0);
        result.setMsg("Success Add to Cart");

        return result;
    }

    @Override
    public Result<?> UpdateCart(String jwt, Integer productId ,Integer count) {
        Map<String, Claim> map = JWTUtils.verifyAndGetClaims(jwt);
        Integer userId = map.get("userId").asInt();
        Integer stock = this.productInfoMapper.selectById(productId).getProductStock();
        Integer price = this.productInfoMapper.selectById(productId).getProductPrice();
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_id", userId).eq("product_id", productId);
        Cart cart = this.cartMapper.selectOne(queryWrapper);
        if(cart == null)
            throw new EshopException(ResponseEnum.NO_SUCH_RECORD);
        if(count > stock)
            throw new EshopException(ResponseEnum.OUT_OF_STOCK);

        UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId)
                .and(wrapper -> wrapper.eq("product_id",productId))
                .set("product_quantity", count)
                .set("cost",price);

        this.cartMapper.update(new Cart(), updateWrapper);
        Result<Object> result = new Result<>();
        result.setCode(0);
        result.setMsg("Success Update Cart");

        return result;
    }

    @Override
    public Result<?> getCartDetails(String jwt) {

        Result<List<CartVO>> result = new Result<>();
        Map<String, Claim> map = JWTUtils.verifyAndGetClaims(jwt);
        Integer userId = map.get("userId").asInt();

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        log.info("select List");
        List<Cart> cartList = this.cartMapper.selectList(queryWrapper);
        List<CartVO> voList = new ArrayList<>();

        if(cartList.size() == 0)
        {
            result.setCode(0);
            result.setMsg("You have no Cart Record");
            return result;
        }

        Iterator<Cart> iterator = cartList.iterator();
        while (iterator.hasNext()) {
            Cart cart = iterator.next();
            Integer productId = cart.getProductId();

            ProductInfo product = this.productInfoMapper.selectById(productId);
            String productName = product.getProductName();
            Integer price = product.getProductPrice();

            CartVO vo = new CartVO();
            vo.setProductId(productId);
            vo.setProductName(productName);
            vo.setProductQuantity(cart.getProductQuantity());
            vo.setProductPrice(price);
            voList.add(vo);
            log.info("Add Vo success");
        }

        result.setCode(0);
        result.setMsg("Success Get Cart Detail");
        result.setData(voList);

        return result;
    }

    @Override
    public Result<?> deleteCartRecord(String jwt, Integer[] productIds) {
        if(productIds.length == 0)
            throw new EshopException(ResponseEnum.EMPTY_PRODUCT_ID);

        Map<String, Claim> map = JWTUtils.verifyAndGetClaims(jwt);
        Integer userId = map.get("userId").asInt();

        Result<Object> result = new Result<>();
        for(int productId : productIds){
            delete(productId, userId);
        }

        result.setCode(0);
        result.setMsg("Success Delete Record");
        return result;
    }
    public void delete(Integer productId, Integer userId){
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("product_id", productId);
        Cart record = this.cartMapper.selectOne(queryWrapper);
        if(record == null)
            throw new EshopException(ResponseEnum.NO_SUCH_RECORD);
        this.cartMapper.delete(queryWrapper);
    }
}
