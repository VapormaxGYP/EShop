package com.vapor.eshop.form;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


@Data
public class UserRegisterForm {

    @NotEmpty(message = "Empty UserName")
    private String username;

    @NotEmpty(message = "Empty loginName")
    private String loginname;

    @NotEmpty(message = "Empty password")
    private String password;

    @NotNull(message = "Empty Gender")
    private Integer gender;

    @NotEmpty(message = "Empty Phone")
    private String phone;

    @NotEmpty(message = "Empty Email Address")
    private String emailAdd;
}
