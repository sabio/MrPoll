package com.mrpoll.utils;

import org.springframework.stereotype.Component;

@Component
public class CommonMethods {
        
    /**
     * Converts a String into a Integer
     * @param s The String to be converted
     * @param defaultValue If s can't be coverted into a Integer, defaultValue is returned instead
     * @return The numeric value represented by s after conversion to type Integer. If s can't be coverted into a Integer, defaultValue is returned instead
    */
    public static Integer stringToInteger(String s, Integer defaultValue){
        try{
            return new Integer(s);
        }
        catch(NumberFormatException e){
            return defaultValue;
        }
    }
}
