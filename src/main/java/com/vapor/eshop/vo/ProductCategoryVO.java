package com.vapor.eshop.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVO {
    private Integer key;
    private String label;
    private List<ProductCategoryVO> children;

    public ProductCategoryVO(ProductCategoryTemp productCategoryTemp){
        this.key = productCategoryTemp.getKey();
        this.label = productCategoryTemp.getLabel();
    }
}
