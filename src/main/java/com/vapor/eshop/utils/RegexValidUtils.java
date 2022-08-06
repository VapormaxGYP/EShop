package com.vapor.eshop.utils;

import java.util.regex.Pattern;

public class RegexValidUtils {

    public static boolean checkEmail(String email){
        String regex = "^[a-zA-Z\\d_-]+@[a-zA-Z\\d_-]+(\\.[a-zA-Z\\d_-]+)+$";
        return Pattern.matches(regex, email);
    }
}
