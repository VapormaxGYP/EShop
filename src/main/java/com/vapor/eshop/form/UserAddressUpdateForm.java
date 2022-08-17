package com.vapor.eshop.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class UserAddressUpdateForm {

    @NotNull(message = "Address Id cannot be Empty")
    private Integer addressId;

    private String address;
    private Integer isDefault;
}
