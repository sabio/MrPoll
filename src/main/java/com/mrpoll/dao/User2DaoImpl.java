package com.mrpoll.dao;

import com.mrpoll.model.Role;
import com.mrpoll.model.User;
import com.mrpoll.model.User2;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("user2Dao")
public class User2DaoImpl extends AbstractDao<Integer, User2> implements User2Dao {

    @Override
    public User2 findByUsername(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        User2 user = (User2) crit.uniqueResult();
        
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
