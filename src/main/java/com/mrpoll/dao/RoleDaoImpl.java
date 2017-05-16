package com.mrpoll.dao;

import com.mrpoll.entity.Role;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl  extends AbstractDao<Integer, Role> implements RoleDao{
    
    @Override
    public Role findById(Integer id) {
        Criteria criteria = getSession().createCriteria(Role.class);
        criteria.add(Restrictions.eq("id", id));
        
        Role role = (Role)criteria.uniqueResult();
        
        return role;
    }
    
    @Override
    public List<Role> getRoles() {
        Criteria criteria = getSession().createCriteria(Role.class);
        return criteria.list();
    }
}
