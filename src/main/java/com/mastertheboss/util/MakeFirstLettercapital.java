package com.mastertheboss.util;

public class MakeFirstLettercapital {
    public static String makeCapital(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
