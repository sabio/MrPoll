package com.mrpoll.dao;

import com.mrpoll.model.User2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User2, Integer> {
    public User2 findByUsername(String username);

}
