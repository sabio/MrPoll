package com.mrpoll.validator;

import com.mrpoll.model.Choice;
import com.mrpoll.model.Poll;
import com.mrpoll.model.Question;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class PollFormValidator implements Validator {
    
    private final int NAME_MAX_LENGTH =  100;
    private final int QUESTION_MAX_LENGTH =  300;
    private final int CHOICE_MAX_LENGTH =  50;
    

    @Override
    public boolean supports(Class<?> type) {
        return Poll.class.equals(type);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Poll poll = (Poll)obj;
        
        //Validate name 
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "poll.namemustnotbeempty");
        if(poll.getName() != null && poll.getName().length() > NAME_MAX_LENGTH){
            errors.rejectValue("name", "poll.nametoolong");
        }
        
        
        //Validate expiration date 
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expirationDatetime", "poll.expirationdatetimemustnotbeempty");
        Date expirationDatetime = poll.getExpirationDatetime();
        if(expirationDatetime != null && new Date().after(expirationDatetime)){
            errors.rejectValue("expirationDatetime", "poll.expirationdatetimemustbeinthefuture");
        }
        
        
        //If there is no any question, then reject and return
        if(poll.getQuestions() == null || poll.getQuestions().isEmpty()){
            errors.reject("poll.theremustbeatleastonequestion");
            return;
        }
        
        
        //Validate questions and choices
        for(int i=0; i<poll.getQuestions().size(); i++){
            String questionField = "questions["+i+"].questionText";
            Question q = poll.getQuestions().get(i);
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, questionField, "poll.questionmustnotbeempty");
            if(q.getQuestionText() != null && q.getQuestionText().length() > QUESTION_MAX_LENGTH){
                errors.rejectValue(questionField, "poll.questiontoolong");
            }
            
            if(q.getChoices() == null || q.getChoices().isEmpty()){
                errors.rejectValue(questionField, "poll.theremustbeatleastonechoice");
            }
            else{
                for(int j=0; j<q.getChoices().size(); j++){
                    String choiceField = "questions["+i+"].choices["+j+"].choiceText";
                    Choice c = q.getChoices().get(j);
                    ValidationUtils.rejectIfEmptyOrWhitespace(errors, choiceField, "poll.choicemustnotbeempty");
                    if(c.getChoiceText() !=  null && c.getChoiceText().length() > CHOICE_MAX_LENGTH){
                        errors.rejectValue(choiceField, "poll.choicetoolong");
                    }
                }
            }
        }
    }
}
