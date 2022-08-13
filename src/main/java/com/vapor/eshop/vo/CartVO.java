package com.vapor.eshop.vo;

import com.vapor.eshop.entity.Cart;
import com.vapor.eshop.entity.ProductInfo;
import lombok.Data;

@Data
public class CartVO {

    private Integer productId;
    private String productName;
    private Integer productQuantity;
    private Integer productPrice;

}
