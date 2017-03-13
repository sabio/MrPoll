package com.mrpoll.service;

import com.mrpoll.dao.RoleDao;
import com.mrpoll.model.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService{
    
    @Autowired
    private RoleDao roleDao;
    
    
    @Override
    public Role findRoleById(Integer roleId) {
        return roleDao.findById(roleId);
    }
    
    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }
}
