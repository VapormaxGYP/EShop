package com.vapor.eshop.Interceptors;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;
import com.vapor.eshop.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //get token from HTTP Header param "Authorization"
        String token = request.getHeader("Authorization");
        if(token == null)
            throw new EshopException(ResponseEnum.EMPTY_JWT);

        DecodedJWT decodedJWT = JWTUtils.verify(token);
        return decodedJWT != null;
    }
}
