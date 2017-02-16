/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrpoll.service;

import com.mrpoll.model.Role;
import com.mrpoll.model.User;
import java.util.List;

/**
 *
 * @author armando
 */
public interface UserService {
    public User findByUsername(String username);
    
    Role findRoleById(int roleId);
}
