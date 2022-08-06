package com.vapor.eshop.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserLoginForm {

    @NotEmpty(message = "Empty LoginName")
    private String loginname;

    @NotEmpty(message = "Empty Password")
    private String password;
}
