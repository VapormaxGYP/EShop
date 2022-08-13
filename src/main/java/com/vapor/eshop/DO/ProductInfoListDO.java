package com.vapor.eshop.DO;

import com.vapor.eshop.entity.ProductInfo;
import lombok.Data;

@Data
public class ProductInfoListDO {

    private Integer productId;
    private String productName;
    private String productDesc;
    private Integer productPrice;
    private Integer productStock;
    private String productPicture;

    public ProductInfoListDO(ProductInfo productInfo){
        this.productId = productInfo.getProductId();
        this.productName = productInfo.getProductName();
        this.productDesc = productInfo.getProductDesc();
        this.productPrice = productInfo.getProductPrice();
        this.productStock = productInfo.getProductStock();
        this.productPicture = productInfo.getProductPicture();
    }
}
