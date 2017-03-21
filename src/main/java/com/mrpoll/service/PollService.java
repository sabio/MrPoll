package com.mrpoll.service;

import com.mrpoll.model.Poll;
import com.mrpoll.model.User;
import org.springframework.data.domain.Page;

public interface PollService {
    
    Poll findById(Integer id);
    
    Page<Poll> getPollListPage(Integer pageNumber, Integer pageSize);

    public void savePoll(Poll poll);
    
    public void updatePoll(Poll poll);
    
    public User getPollOwner(Integer idPoll);
}
