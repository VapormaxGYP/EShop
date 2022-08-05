package com.vapor.eshop.entity;

import lombok.Data;

@Data
public class Result<T>{

    private String success;
    private String msg;
    private T detail;
}
