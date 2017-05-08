package com.mrpoll.validator;

import com.mrpoll.model.FormChoiceResponse;
import com.mrpoll.model.FormResponse;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AnswerPollFormValidator implements Validator  {

    @Override
    public boolean supports(Class<?> type) {
        return FormResponse.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FormResponse formResponse = (FormResponse) o;
        List<FormChoiceResponse> formChoiceResponses = formResponse.getFormChoiceResponses();
        
        for(int i=0; i< formChoiceResponses.size(); i++){
            FormChoiceResponse formChoiceResponse =  formChoiceResponses.get(i);
            if(formChoiceResponse.getIdChoice() == null){
                errors.rejectValue("formChoiceResponses["+i+"].idQuestion", "answerpoll.pleaseanswerthisquestion");
            }
        }
        
    }
    
}
