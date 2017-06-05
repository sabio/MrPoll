package com.mrpoll.customMatchers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;


public class AttributeErrorsMatchers extends ModelResultMatchers {

    private AttributeErrorsMatchers() {
    }

    public static AttributeErrorsMatchers attributeErrors() {
        return new AttributeErrorsMatchers();
    }

    public ResultMatcher hasAttributeError(String attributeName, String expectedCode) {
        return result -> {
            BindingResult bindingResult = getBindingResult(
                result.getModelAndView(), attributeName
            );
            bindingResult.getGlobalErrors().stream()
                .filter(objectError -> attributeName.equals(objectError.getObjectName()))
                .forEach(objectError -> assertEquals(
                    "Expected default message", expectedCode, objectError.getCode())
                );
        };
    }

    private BindingResult getBindingResult(ModelAndView mv, String attributeName) {
        BindingResult result = (BindingResult) mv.getModel().get(BindingResult.MODEL_KEY_PREFIX + attributeName);
        assertTrue("No BindingResult for attribute: " + attributeName, result != null);
        assertTrue("No global errors for attribute: " + attributeName, result.getGlobalErrorCount() > 0);
        return result;
    }
}