package com.mrpoll.dao;

import com.mrpoll.model.Poll;
import org.springframework.stereotype.Repository;

@Repository("pollDao")
public class PollDaoImpl  extends AbstractDao<Integer, Poll> implements PollDao {

    @Override
    public void save(Poll poll) {
        persist(poll);
    }
    
}
