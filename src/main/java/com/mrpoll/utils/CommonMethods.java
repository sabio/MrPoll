package com.mrpoll.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

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
    
    
    public static List<String> retrieveErrorsFromBindingResult(BindingResult result){
        List<String> errorMessages = new ArrayList<>();
            
        result.getFieldErrors().forEach((e) -> {
            errorMessages.add(e.getField()+" - "+e.getDefaultMessage());
        });
        result.getGlobalErrors().forEach((e) -> {
            errorMessages.add(e.getDefaultMessage());
        });
        
        return errorMessages;
    }
}
