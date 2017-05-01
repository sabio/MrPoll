package com.mrpoll.ejemplotestcontroller;

import com.mrpoll.controller.UserController;
import com.mrpoll.model.Role;
import com.mrpoll.model.User;
import com.mrpoll.service.RoleService;
import com.mrpoll.service.UserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.sitemesh.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {com.mrpoll.configuration.AppConfig.class})
//@WebAppConfiguration
public class UserControllerTest {
    
    private MockMvc mockMvc;
    private final Integer TOTAL_USERS = 10;
    Page<User> page;
    List<Role> roles;
    
    @InjectMocks
    private UserController userController;
    
    @Mock
    private UserService userService;
    
    @Mock
    private RoleService roleService;
    
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
        
        List<User> users = new ArrayList<>();
        for(int i=0; i<TOTAL_USERS; i++){
            users.add(new User(i, "User "+i, "Password "+i, true));
        }
        page = new PageImpl(users);
        
        roles = Arrays.asList(new Role(1,"ROLE_ADMIN"), new Role(2,"ROLE_USER"));
    }
    
    
    @Test
    public void testUserList() throws Exception{
//        int pageNumber = 2;
//        int pageSize= 10;
//        int sizeExpected = 10;
//        
//        when(userService.getUserListPage(pageNumber-1,pageSize)).thenReturn(page);
//        when(roleService.getRoles()).thenReturn(roles);
//        
//        mockMvc.perform(get("/userList?pageNumber="+pageNumber+"&pageSize="+pageSize))
//                .andExpect(status().isOk())
//                .andExpect(view().name("user/userList"))
//                .andExpect(model().attribute("list", hasSize(sizeExpected)));
//        
//        verify(userService, times(1)).getUserListPage(pageNumber-1,pageSize);
//        verifyNoMoreInteractions(userService);
    }
    
    
    
}
