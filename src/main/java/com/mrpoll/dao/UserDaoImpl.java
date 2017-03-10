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
    public User findById(Integer id) {
        User user = getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getRoles());
        }

        return user;
    }

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
    public void save(User user) {
        persist(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = getByKey(id);
        delete(user);
    }

    @Override
    public boolean passwordHasChanged(Integer id, String newPassword) {
        User user = getByKey(id);
        if (user == null) {
            return false;
        }
        boolean result = !user.getPassword().equals(newPassword);
        this.getSession().evict(user);

        return result;
    }

}
