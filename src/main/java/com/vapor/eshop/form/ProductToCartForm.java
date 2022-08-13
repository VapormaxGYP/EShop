package com.vapor.eshop.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductToCartForm {

    @NotNull(message = "Empty product Id")
    private Integer productId;
    @NotNull(message = "Empty product Count")
    private Integer count;
}
