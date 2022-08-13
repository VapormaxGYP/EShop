package com.vapor.eshop.vo;

import com.vapor.eshop.DO.ProductInfoListDO;
import com.vapor.eshop.entity.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProductInfoListVO {
    private List<ProductInfoListDO> infoList;
    private Long totalPages;
    private Long totalItems;

    public ProductInfoListVO(List<ProductInfoListDO> infoList, Long totalPages, Long totalItems){
        this.infoList = infoList;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }
}
