package com.mrpoll.controller;


import com.mrpoll.controller.UserController;
import static com.mrpoll.customMatchers.AttributeErrorsMatchers.attributeErrors;
import com.mrpoll.entity.Role;
import com.mrpoll.entity.User;
import com.mrpoll.model.FormUser;
import com.mrpoll.service.RoleService;
import com.mrpoll.service.UserService;
import com.mrpoll.utils.Constants;
import com.mrpoll.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static junit.framework.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = 
        {
            com.mrpoll.configuration.AppConfig.class,
            com.mrpoll.configuration.AppConfigTest.class
        })
public class UserControllerTest {
    
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    @Qualifier("userServiceMock")
    private UserService userService;
    
    @Autowired
    @Qualifier("roleServiceMock")
    private RoleService roleService;
    
    @Autowired
    private UserController userController;
    
    
    
    @Before
    public void setUp(){
        //MockitoAnnotations.initMocks(this);
        Mockito.reset(userService);
        Mockito.reset(roleService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userController.setRoleService(roleService);
        userController.setUserService(userService);
    }
    
    @Test
    public void testUserList() throws Exception{
        int pageNumber = 2;
        int pageSize= 10;
        int sizeExpected = 10;
        
        List dataForTest = Utils.getDataForUserListTest();
        Page<User> page = (Page<User>) dataForTest.get(0);
        List<Role> roles = (List<Role>) dataForTest.get(1);
        
        when(userService.getUserListPage(pageNumber-1,pageSize)).thenReturn(page);
        when(roleService.getRoles()).thenReturn(roles);
        
        mockMvc.perform(get("/userList?pageNumber="+pageNumber+"&pageSize="+pageSize))
                .andExpect(status().isOk())
                .andExpect(view().name("user/userList"))
                .andExpect(model().attribute("list", hasSize(sizeExpected)));
        
        verify(userService, times(1)).getUserListPage(pageNumber-1,pageSize);
        verifyNoMoreInteractions(userService);
    }
    
    @Test
    public void testFindById() throws Exception{
        FormUser formUser = new FormUser();
        when(userService.findFormUserById(1)).thenReturn(formUser);
        
        mockMvc.perform(get("/editUser/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/userForm"))
                .andExpect(model().attribute("formUser", formUser));
        
        verify(userService, times(1)).findFormUserById(1);
        verifyZeroInteractions(userService);
    }
    
    
    @Test
    public void testFindById_EntryNotFound() throws Exception{
        when(userService.findFormUserById(1)).thenReturn(null);
        
        mockMvc.perform(get("/editUser/1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("user/userNotFound"));
        
        verify(userService, times(1)).findFormUserById(1);
        verifyZeroInteractions(userService);
    }
    
    
    @Test
    public void add_EmptyNameAndInvalidEmailAndPasswordsDontMatch() throws Exception{
        mockMvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "")
                .param("email", "invalid email")
                .param("username","usernameCool")
                .param("password", "12345")
                .param("confirmPassword", "123458")
                .param("enabled", "true")
                .param("roles","1")
        )
//                .andExpect(status().isOk())
//                .andExpect(model().attributeHasFieldErrors("formUser","name","email"))
//                .andExpect(model().attributeHasFieldErrorCode("formUser", "name", "Size"))
//                .andExpect(model().attributeHasFieldErrorCode("formUser", "email", "Email"))
//                .andExpect(attributeErrors().hasAttributeError("formUser", "FieldMatch"))
//                .andExpect(model().errorCount(3))
//                .andExpect(model().attribute("formUser", hasProperty("id",nullValue())))
//                .andExpect(view().name("user/userForm"))
                ;
    }
    
    
    @Test
    public void add_NewEntry() throws Exception{
        mockMvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Pepe Garcia")
                .param("email", "pepeg@yahoo.com")
                .param("username","usernameCool")
                .param("password", "12345")
                .param("confirmPassword", "12345")
                .param("enabled", "true")
                .param("roles","1")
        )
//                .andExpect(status().is(302))
//                .andExpect(model().hasNoErrors())
//                .andExpect(view().name("redirect:/userList"))
                ;
    }
    
    
}
