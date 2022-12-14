package com.vapor.eshop.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vapor.eshop.errors.ResponseEnum;
import com.vapor.eshop.exception.EshopException;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    private static final String signKey = "vapor&moozon";
    /**
     * Generate Token
     * @param map:claim body
     */
    public static String getToken(Map<String, ?> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);

        String jwtToken = JWT.create()
                .withPayload(map)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(signKey));

        if(jwtToken == null || jwtToken.equals(""))
            throw new EshopException(ResponseEnum.TOKEN_GENERATE_ERROR);
        return jwtToken;
    }


    /**
     * Verify Token and Get Claims
     * @param token: a token which need to be verified
     */

    public static Map<String, Claim> verifyAndGetClaims(String token){

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(signKey)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaims();
    }


    /**
     * Verify Token and Get DecodedJWT
     * @param token: a token which need to be verified
     * @return DecodedJWT
     */
    public static DecodedJWT verify(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(signKey)).build();
        return jwtVerifier.verify(token);
    }
}
