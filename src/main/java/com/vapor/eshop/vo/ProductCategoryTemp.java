package com.vapor.eshop.vo;

import com.vapor.eshop.entity.ProductCategory;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryTemp {

    private Integer key;
    private String label;
    private Integer level;

    private Integer parentId;

    private List<ProductCategoryTemp> children;

    public ProductCategoryTemp(ProductCategory productCategory) {
        this.key = productCategory.getKindId();
        this.label = productCategory.getKindName();
        this.level = productCategory.getKindLevel();
        this.parentId = productCategory.getParentId();
    }

}
