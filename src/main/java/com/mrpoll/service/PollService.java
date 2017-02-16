package com.mrpoll.service;

import com.mrpoll.model.Poll;
import org.springframework.data.domain.Page;

public interface PollService {
    
    Page<Poll> getPollList(Integer pageNumber);
}
