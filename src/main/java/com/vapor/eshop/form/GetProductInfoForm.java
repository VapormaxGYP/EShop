package com.vapor.eshop.form;

import lombok.Data;

@Data
public class GetProductInfoForm {
    private Integer pageNum;
    private Integer pageSize;
    private String keyword;
    private Integer categoryId;
}
