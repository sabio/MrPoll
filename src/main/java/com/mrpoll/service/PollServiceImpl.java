package com.mrpoll.service;

import com.mrpoll.dao.PollRepository;
import com.mrpoll.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("pollService")
@Transactional
public class PollServiceImpl implements PollService{
    
    private static final int PAGE_SIZE = 5;
    
//    @Autowired
//    private PollRepository pollRepository;
    
    @Override
    public Page<Poll> getPollList(Integer pageNumber){
//        PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);
//        return pollRepository.findAll(pageRequest);
        return null;
    }
}
