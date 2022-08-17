package com.vapor.eshop.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class GenerateOrderForm {
    @NotEmpty
    private String address;
    @NotNull
    private Integer totalCost;
    @NotEmpty
    private String orderDate;
}
