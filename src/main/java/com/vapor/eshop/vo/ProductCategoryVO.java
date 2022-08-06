package com.vapor.eshop.vo;

import com.vapor.eshop.entity.ProductCategory;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVO {

    private Integer id;
    private String name;
    private Integer parentid;
    private Integer level;

    private List<ProductCategoryVO> children;

    public ProductCategoryVO(ProductCategory productCategory) {
        this.id = productCategory.getId();
        this.name = productCategory.getName();
        this.parentid = productCategory.getParentId();
        this.level = productCategory.getLevel();
    }

}
