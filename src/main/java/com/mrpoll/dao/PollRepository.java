package com.mrpoll.dao;

import com.mrpoll.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PollRepository extends JpaRepository<Poll, Integer>  {
    
}
