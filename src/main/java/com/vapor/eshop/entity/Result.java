package com.vapor.eshop.entity;

import lombok.Data;

@Data
public class Result<T>{

    private Integer code;
    private String msg;
    private T data;
}
