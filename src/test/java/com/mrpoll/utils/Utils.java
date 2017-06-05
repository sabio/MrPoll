package com.mrpoll.utils;

import com.mrpoll.entity.Role;
import com.mrpoll.entity.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;


public class Utils {
    
    public static List getDataForUserListTest(){
        Integer TOTAL_USERS = 10;
        Page<User> page;
        List<Role> roles;
        
        List<User> users = new ArrayList<>();
        for(int i=0; i<TOTAL_USERS; i++){
            users.add(new User(i, "User "+i, "Password "+i, true));
        }
        page = new PageImpl(users);
        
        roles = Arrays.asList(new Role(1,Constants.ROLE_ADMIN), new Role(2,Constants.ROLE_USER));
        
        List data = new ArrayList();
        data.add(page);
        data.add(roles);
        
        return data;
    }
}
