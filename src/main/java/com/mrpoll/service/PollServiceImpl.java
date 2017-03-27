package com.mrpoll.service;

import com.mrpoll.dao.PollDao;
import com.mrpoll.dao.PollRepository;
import com.mrpoll.model.Poll;
import com.mrpoll.model.User;
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
    
    @Autowired
    private PollDao pollDao;
    
    @Override
    public Page<Poll> getPollListPage(Integer pageNumber, Integer pageSize){
//        pageNumber = pageNumber == null ? Constants.DEFAULT_PAGE_NUMBER : pageNumber;
//        pageSize = pageSize == null ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        
        return pollRepository.findAll(new PageRequest(pageNumber, pageSize));
    }

    @Override
    public void savePoll(Poll poll) {
        pollDao.save(poll);
    }

    @Override
    public Poll findById(Integer id) {
        return pollDao.findById(id);
    }

    @Override
    public void updatePoll(Poll poll) {
        pollDao.update(poll);
    }

    @Override
    public User getPollOwner(Integer idPoll) {
        return pollDao.getPollOwner(idPoll);
        
    }

    @Override
    public void deletePollById(Integer id) {
        pollDao.deletePollById(id);
    }

    @Override
    public Poll findByUUID(String uuid) {
        return pollDao.findByUUID(uuid);
    }
}
