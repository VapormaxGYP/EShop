package com.vapor.eshop.vo;

import com.vapor.eshop.entity.User;
import lombok.Data;

@Data
public class UserDetailVO {

    private Integer uid;
    private String loginName;
    private String userName;
    private Integer gender;
    private String phone;
    private String email;

    public UserDetailVO(User user){
        this.uid = user.getUserId();
        this.loginName = user.getLoginName();
        this.userName = user.getUserName();
        this.gender = user.getGender();
        this.phone = user.getPhone();
        this.email = user.getEmailAdd();
    }
}
