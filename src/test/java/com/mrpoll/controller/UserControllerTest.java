package com.mrpoll.controller;

import com.mrpoll.service.UserService;
import java.util.ArrayList;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sitemesh.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.mrpoll.configuration.AppConfig.class})
@WebAppConfiguration
public class UserControllerTest {
    
    private MockMvc mockMvc;
  
    @Test
    public void test() throws Exception{
        PageImpl<String> page = new PageImpl(new ArrayList<>());
        
        
        
        assertTrue(true);
    }
}
