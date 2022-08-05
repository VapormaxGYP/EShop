package com.vapor.eshop.exception;

import com.vapor.eshop.errors.ResponseEnum;

public class EshopException extends RuntimeException{
    public EshopException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
    }
}
