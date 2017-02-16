package com.mrpoll.utils;


public class CommonMethods {
    public static Integer stringToInteger(String string, Integer defaultValue){
        try{
            return new Integer(string);
        }
        catch(NumberFormatException e){
            return defaultValue;
        }
    }
}
