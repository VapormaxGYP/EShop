package com.vapor.eshop.vo;

import com.vapor.eshop.entity.ProductCategory;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVO {

    private Integer kindId;
    private String kindName;
    private Integer parentId;
    private Integer kindLevel;

    private List<ProductCategoryVO> children;

    public ProductCategoryVO(ProductCategory productCategory) {
        this.kindId = productCategory.getKindId();
        this.kindName = productCategory.getKindName();
        this.parentId = productCategory.getParentId();
        this.kindLevel = productCategory.getKindLevel();
    }

}
