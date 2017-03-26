package com.mrpoll.service;

import com.mrpoll.controller.FormResponse;
import com.mrpoll.model.Poll;
import com.mrpoll.model.User;
import org.springframework.data.domain.Page;

public interface PollService {
    
    Poll findById(Integer id);
    
    void deletePollById(Integer id);
    
    Page<Poll> getPollListPage(Integer pageNumber, Integer pageSize);

    void savePoll(Poll poll);
    
    void updatePoll(Poll poll);
    
    User getPollOwner(Integer idPoll);
}
