package com.mrpoll.dao;

import com.mrpoll.model.Poll;
import com.mrpoll.model.User;

public interface PollDao {
    Poll findById(Integer id);
    
    void save(Poll poll);
    
    void update(Poll poll);
    
    User getPollOwner(Integer idPoll);
}
