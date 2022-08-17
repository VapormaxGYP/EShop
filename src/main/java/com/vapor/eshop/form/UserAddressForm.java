package com.vapor.eshop.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class UserAddressForm {

    @NotEmpty(message = "Address Can not be Empty")
    private String address;

    @NotNull(message = "Is default can not be Empty")
    private Integer isDefault;
}
