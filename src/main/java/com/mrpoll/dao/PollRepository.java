package com.mrpoll.dao;

import com.mrpoll.entity.Poll;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PollRepository extends JpaRepository<Poll, Integer>  {
    
    @Query("SELECT p FROM Poll p WHERE p.expirationDatetime > :date ORDER BY p.id DESC")
    List<Poll> getAvailablePolls(@Param("date") Date date);
}
