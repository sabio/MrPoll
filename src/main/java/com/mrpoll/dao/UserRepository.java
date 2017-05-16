package com.mrpoll.dao;

import com.mrpoll.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
    
    public User findByEmail(String email);
}
