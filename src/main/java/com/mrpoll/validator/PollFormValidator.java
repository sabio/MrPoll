package com.mrpoll.validator;

import com.mrpoll.model.Choice;
import com.mrpoll.model.Poll;
import com.mrpoll.model.Question;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class PollFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Poll.class.equals(type);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Poll poll = (Poll)obj;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "poll.namemustnotbeempty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expirationDate", "poll.expirationdatemustnotbeempty");
        
        
        if(poll.getQuestions() == null || poll.getQuestions().isEmpty()){
            errors.reject("poll.theremustbeatleastonequestion");
        }
        else{
            for(int i=0; i<poll.getQuestions().size(); i++){
                Question q = poll.getQuestions().get(i);
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "questions["+i+"].questionText", "poll.questionmustnotbeempty");
                
                if(q.getChoices() == null || q.getChoices().isEmpty()){
                    errors.reject("questions["+i+"].questionText", "poll.theremustbeatleastonechoice");
                }
                else{
                    for(int j=0; j<q.getChoices().size(); j++){
                        Choice c = q.getChoices().get(i);
                        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "questions["+i+"].choices["+j+"].choiceText", "poll.choicemustnotbeempty");
                        
                        
                    }
                }
                
                
                
                
            }
        }
        
        

        
    }
    
}
