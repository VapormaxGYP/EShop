package com.vapor.eshop.exception;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
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

    @ExceptionHandler(SignatureVerificationException.class)
    public Result<?> ExceptionHandler(SignatureVerificationException e){
        Result<Object> responseResult = new Result<>();

        responseResult.setCode(440);
        responseResult.setMsg("JWT Invalid Signature");
        return responseResult;
    }

    @ExceptionHandler(TokenExpiredException.class)
    public Result<?> ExceptionHandler(TokenExpiredException e){
        Result<Object> responseResult = new Result<>();

        responseResult.setCode(441);
        responseResult.setMsg("JWT has Expired");
        return responseResult;
    }

    @ExceptionHandler(AlgorithmMismatchException.class)
    public Result<?> ExceptionHandler(AlgorithmMismatchException e){
        Result<Object> responseResult = new Result<>();

        responseResult.setCode(442);
        responseResult.setMsg("JWT Algorithm Mismatch");
        return responseResult;
    }

    @ExceptionHandler(Exception.class)
    public Result<?> ExceptionHandler(Exception e){
        //generate exception sixth
        Result<Object> responseResult = new Result<>();
        responseResult.setCode(501);
        responseResult.setMsg(e.getMessage());
        return responseResult;
    }

}
