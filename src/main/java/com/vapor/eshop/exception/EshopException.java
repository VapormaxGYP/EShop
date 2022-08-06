package com.vapor.eshop.exception;

import com.vapor.eshop.errors.ResponseEnum;

public class EshopException extends RuntimeException{

    private ResponseEnum responseEnum;
    public EshopException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }
}
