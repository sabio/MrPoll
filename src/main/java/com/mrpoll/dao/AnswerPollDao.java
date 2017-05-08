package com.mrpoll.dao;

import com.mrpoll.model.FormResponse;
import com.mrpoll.controller.QuestionResult;
import java.util.List;


public interface AnswerPollDao {
    
    public void insertResponse(FormResponse formResponse);
    
    public List<QuestionResult> getResults(Integer idPoll);
}
