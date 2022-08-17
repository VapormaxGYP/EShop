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
    EMPTY_JWT(443, "Empty JWT"),
    PAGE_INFO_EMPTY(444, "Empty PageNum or PageSize"),
    GET_PRODUCT_INFO_FORM_EMPTY(445, "Empty Info in Get Product"),
    EMPTY_PRODUCT_ID(446, "Empty Product ID"),
    ADD_CART_ERROR(447, "Add Cart Error"),
    OUT_OF_STOCK(448, "Count is Larger than Stock"),
    PRODUCT_TO_CART_FORM_EMPTY(449, "Product Add to Cart form Empty"),
    ILLEGAL_COUNT(450, "Count Number is Illegal"),
    NO_SUCH_RECORD(451, "No Such Cart Record"),
    USER_ADD_ADDRESS(452,"Have Empty info"),
    INSERT_ADDRESS_ERROR(453, "Insert Address Error"),
    ADDRESS_ALREADY_EXIST(454, "Address Already Exist"),
    EMPTY_UPDATE_ADDRESS(455, "Update Address Form Empty"),
    NO_SUCH_ADDRESS(456, "No Such Address Record"),
    EMPTY_ADDRESS_ID(457, "Empty Address ID for Delete");
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
