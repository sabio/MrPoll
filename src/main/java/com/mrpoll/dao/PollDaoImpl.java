package com.mrpoll.dao;

import com.mrpoll.model.Poll;
import com.mrpoll.model.User;
import org.springframework.stereotype.Repository;

@Repository("pollDao")
public class PollDaoImpl extends AbstractDao<Integer, Poll> implements PollDao {

    @Override
    public void save(Poll poll) {
        persist(poll);
    }

    @Override
    public Poll findById(Integer id) {
        return getByKey(id);
    }

    @Override
    public User getPollOwner(Integer idPoll) {
        Poll poll = findById(idPoll);
        
        if(poll == null) return null;
        User user = poll.getUserId();
        this.getSession().evict(poll);
        return user;
    }
    
}
