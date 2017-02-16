package com.mrpoll.service;

import com.mrpoll.dao.PollRepository;
import com.mrpoll.model.Poll;
import com.mrpoll.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("pollService")
@Transactional
public class PollServiceImpl implements PollService{
    
    @Autowired
    private PollRepository pollRepository;
    
    @Override
    public Page<Poll> getPagePollList(Integer pageNumber, Integer pageSize){
        pageNumber = pageNumber == null ? Constants.DEFAULT_PAGE_NUMBER : pageNumber;
        pageSize = pageSize == null ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        
        return pollRepository.findAll(new PageRequest(pageNumber, pageSize));
    }
}
