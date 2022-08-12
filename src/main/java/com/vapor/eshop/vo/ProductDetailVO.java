package com.vapor.eshop.vo;

import com.vapor.eshop.entity.ProductInfo;
import lombok.Data;

@Data
public class ProductDetailVO {
    private String productName;
    private String productDesc;
    private Integer productPrice;
    private Integer productStock;
    private String productPicture;

    public ProductDetailVO(ProductInfo productInfo){
        this.productName = productInfo.getProductName();
        this.productDesc = productInfo.getProductDesc();
        this.productPrice = productInfo.getProductPrice();
        this.productStock = productInfo.getProductStock();
        this.productPicture = productInfo.getProductPicture();
    }
}
