/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrpoll.service;

import com.mrpoll.model.Role;
import com.mrpoll.model.User2;
import com.mrpoll.model.UserRole;
import java.util.List;

/**
 *
 * @author armando
 */
public interface User2Service {
    public User2 findByUsername(String username);
    
    List<UserRole> findRolesByUser(User2 user);
    
    Role findRoleById(int roleId);
}
