package com.mrpoll.restcontroller;

import com.mrpoll.entity.Poll;
import com.mrpoll.entity.User;
import com.mrpoll.model.FormUser;
import com.mrpoll.service.PollService;
import com.mrpoll.service.UserService;
import com.mrpoll.utils.CommonMethods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


//http://websystique.com/springmvc/spring-mvc-4-restful-web-services-crud-example-resttemplate/
//http://stackoverflow.com/questions/34728144/spring-boot-binding-and-validation-error-handling-in-rest-controller
//http://www.codesandnotes.be/2014/12/18/validating-spring-rest-controllers-beans-using-the-bean-validation-api-and-writing-the-tests-for-it/
@RestController
@RequestMapping("/api/poll")
public class PollRESTController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    PollService pollService;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Poll>> findAll() {
        //return new ResponseEntity<> (userService.findAll(), HttpStatus.OK);
        return new ResponseEntity<> (pollService.getAvailablePolls(), HttpStatus.OK);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody FormUser formUser, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(CommonMethods.retrieveErrorsFromBindingResult(result),HttpStatus.CONFLICT);
        }
        
        userService.saveFormUser(formUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
