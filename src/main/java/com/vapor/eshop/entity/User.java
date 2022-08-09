package com.vapor.eshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Vapor
 * @since 2022-08-10
 */
@TableName("User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "user_id", type = IdType.AUTO)
      private Integer userId;

    private String userName;

    private String loginName;

      /**
     * MD5 Password
     */
      private String password;

    private Integer gender;

    private String phone;

    private String emailAdd;

    
    public Integer getUserId() {
        return userId;
    }

      public void setUserId(Integer userId) {
          this.userId = userId;
      }
    
    public String getUserName() {
        return userName;
    }

      public void setUserName(String userName) {
          this.userName = userName;
      }
    
    public String getLoginName() {
        return loginName;
    }

      public void setLoginName(String loginName) {
          this.loginName = loginName;
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
              "userId=" + userId +
                  ", userName=" + userName +
                  ", loginName=" + loginName +
                  ", password=" + password +
                  ", gender=" + gender +
                  ", phone=" + phone +
                  ", emailAdd=" + emailAdd +
              "}";
    }
}
