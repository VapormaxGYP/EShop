package com.vapor.eshop.exception;

import com.vapor.eshop.entity.Result;
import com.vapor.eshop.errors.ResponseEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice("com.vapor.eshop")
public class ExceptHandler {

    @ExceptionHandler(EshopException.class)
    public Result<?> ExceptionHandler(EshopException e){
        ResponseEnum responseEnum = e.getResponseEnum();

        Result<ResponseEnum> responseEnumResult = new Result<>();
        responseEnumResult.setCode(responseEnum.getCode());
        responseEnumResult.setMsg(responseEnum.getMsg());

        return responseEnumResult;
    }

    @ExceptionHandler(Exception.class)
    public Result<?> ExceptionHandler(Exception e){
        //generate exception fifth
        Result<Object> result = new Result<>();
        result.setCode(501);
        result.setMsg("System Error");

        return result;
    }

}
