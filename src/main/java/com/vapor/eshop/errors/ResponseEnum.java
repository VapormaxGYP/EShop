package com.vapor.eshop.errors;

public enum ResponseEnum {
    LOGIN_NAME_EMPTY(100, "LoginName can not be Empty"),
    ;

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
