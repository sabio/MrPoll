package com.mrpoll.service;

import com.mrpoll.controller.FormResponse;
import com.mrpoll.dao.AnswerPollDao;
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
    
}
