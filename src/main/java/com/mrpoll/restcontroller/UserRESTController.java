package com.mrpoll.restcontroller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRESTController {
    
    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public String getResource() {
        return "Hola mundo!!";
    }
}
