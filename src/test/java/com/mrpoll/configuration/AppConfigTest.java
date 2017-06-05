package com.mrpoll.configuration;

import com.mrpoll.service.RoleService;
import com.mrpoll.service.UserService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class AppConfigTest {
    
    @Bean(name="userServiceMock")
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
    
    @Bean(name="roleServiceMock")
    public RoleService roleService() {
        return Mockito.mock(RoleService.class);
    }
    
    
}
