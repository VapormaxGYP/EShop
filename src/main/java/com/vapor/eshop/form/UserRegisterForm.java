package com.vapor.eshop.form;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class UserRegisterForm {

    @NotEmpty(message = "UserName can not be Empty")
    private String username;

    @NotEmpty(message = "LoginName can not be Empty")
    private String loginname;

    @NotEmpty(message = "Password can not be Empty")
    private String password;

    @NotNull(message = "Gender can not be Empty")
    private Integer gender;

    @NotEmpty(message = "Phone can not be Empty")
    private String phone;

    @NotEmpty(message = "Email can not be Empty")
    private String emailAdd;
}
