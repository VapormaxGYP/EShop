package com.vapor.eshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Vapor
 * @since 2022-08-01
 */
@TableName("User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "UserID", type = IdType.AUTO)
    private Integer userid;

    @TableField("UserName")
    private String username;

    @TableField("LoginName")
    private String loginname;

      /**
     * MD5 Password
     */
      @TableField("Password")
    private String password;

    @TableField("Gender")
    private Integer gender;

    @TableField("Phone")
    private String phone;

    @TableField("Email_Add")
    private String emailAdd;

    
    public Integer getUserid() {
        return userid;
    }

      public void setUserid(Integer userid) {
          this.userid = userid;
      }
    
    public String getUsername() {
        return username;
    }

      public void setUsername(String username) {
          this.username = username;
      }
    
    public String getLoginname() {
        return loginname;
    }

      public void setLoginname(String loginname) {
          this.loginname = loginname;
      }
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public Integer getGender() {
        return gender;
    }

      public void setGender(Integer gender) {
          this.gender = gender;
      }
    
    public String getPhone() {
        return phone;
    }

      public void setPhone(String phone) {
          this.phone = phone;
      }
    
    public String getEmailAdd() {
        return emailAdd;
    }

      public void setEmailAdd(String emailAdd) {
          this.emailAdd = emailAdd;
      }

    @Override
    public String toString() {
        return "User{" +
              "userid=" + userid +
                  ", username=" + username +
                  ", loginname=" + loginname +
                  ", password=" + password +
                  ", gender=" + gender +
                  ", phone=" + phone +
                  ", emailAdd=" + emailAdd +
              "}";
    }
}
