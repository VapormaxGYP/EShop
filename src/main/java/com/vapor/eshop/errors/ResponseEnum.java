package com.vapor.eshop.errors;

public enum ResponseEnum {
    LOGIN_INFO_EMPTY(301, "LoginName or Password can not be Empty"),
    LOGIN_NAME_NOT_EXIST(302, "User is not Exist"),
    PASSWORD_ERROR(303, "Password is Error"),
    TOKEN_GENERATE_ERROR(304, "Token can not generate"),
    USER_EXIST_ERROR(305, "User already Exist"),
    EMAIL_ERROR(306, "Email Form Error"),
    REGISTER_ERROR(308, "Register Error"),
    REGISTER_INFO_EMPTY(309, "Register Form has Empty info"),
    EMPTY_JWT(443, "Empty JWT");
    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
