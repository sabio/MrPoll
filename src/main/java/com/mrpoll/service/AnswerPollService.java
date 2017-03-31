package com.mrpoll.service;

import com.mrpoll.controller.FormResponse;
import com.mrpoll.controller.QuestionResult;
import java.util.List;


public interface AnswerPollService {
    
    void saveAnswers(FormResponse formResponse);
    
    List<QuestionResult> getResults(Integer idPoll);
}
