package com.vapor.eshop.Interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vapor.eshop.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //get token from HTTP Header param "Authorization"
        String token = request.getHeader("Authorization");

        Map<String, Object> map = new HashMap<>();
        try{
            JWTUtils.verify(token);
            return true;
        } catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("msg", "Invalid Signature");
        } catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg", "Expired Token");
        } catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg", "Algorithm MisMatch");
        } catch (Exception e){
            e.printStackTrace();
            map.put("msg", "Invalid Token");
        }

        map.put("State", false);
        String resJson = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(resJson);

        return false;
    }
}
