package com.vapor.eshop.controller;


import com.vapor.eshop.entity.Result;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;
import com.vapor.eshop.form.ProductToCartForm;
import com.vapor.eshop.service.CartService;
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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @PostMapping("/addToCart")
    public Result<?> addToCart(@RequestHeader("Authorization") String jwt,
                               @Valid @RequestBody ProductToCartForm productToCartForm,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new EshopException(ResponseEnum.PRODUCT_TO_CART_FORM_EMPTY);
        }
        return cartService.AddToCart(jwt, productToCartForm);
    }

    @PutMapping("/updateCart")
    public Result<?> updateCart(@RequestHeader("Authorization") String jwt,
                                @RequestParam("cartId") Integer cartId,
                                @RequestParam("productId") Integer productId,
                                @RequestParam("count") Integer count
                                ){
        if(count == 0)
            throw new EshopException(ResponseEnum.ILLEGAL_COUNT);

        return cartService.UpdateCart(jwt, cartId, productId, count);

    }

}

