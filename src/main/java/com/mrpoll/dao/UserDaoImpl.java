package com.mrpoll.dao;

import com.mrpoll.model.Role;
import com.mrpoll.model.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    @Override
    public User findByUsername(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        User user = (User) crit.uniqueResult();
        
        /*
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        */
        return user;
    }


    @Override
    public Role findRoleById(int roleId) {
        Criteria criteria = getSession().createCriteria(Role.class);
        criteria.add(Restrictions.eq("id", roleId));
        
        Role role = (Role)criteria.uniqueResult();
        
        return role;
    }

}
