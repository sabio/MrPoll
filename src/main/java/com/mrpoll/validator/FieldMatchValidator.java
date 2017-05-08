package com.mrpoll.validator;

import com.mrpoll.model.FormUser;
import java.lang.reflect.InvocationTargetException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, FormUser> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final FormUser value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
            
            
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
            
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            // ignore
        }
        return true;
    }
}
