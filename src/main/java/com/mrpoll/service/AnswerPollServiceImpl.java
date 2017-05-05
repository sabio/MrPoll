package com.mrpoll.service;

import com.mrpoll.model.FormResponse;
import com.mrpoll.controller.QuestionResult;
import com.mrpoll.dao.AnswerPollDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("answerPollService")
@Transactional
public class AnswerPollServiceImpl implements AnswerPollService{
    
    @Autowired
    private AnswerPollDao answerPollDao;
    
    @Override
    public void saveAnswers(FormResponse formResponse) {
        answerPollDao.insertResponse(formResponse);
    }

    @Override
    public List<QuestionResult> getResults(Integer idPoll) {
        return answerPollDao.getResults(idPoll);
    }
    
}
