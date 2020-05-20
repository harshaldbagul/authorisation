package com.nissandigital.components.common.authorisation.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nissandigital.components.common.authorisation.model.Apps;
import com.nissandigital.components.common.authorisation.model.UserId;
import com.nissandigital.components.common.authorisation.model.Users;
import com.nissandigital.components.common.authorisation.payload.UserPOJO;
import com.nissandigital.components.common.authorisation.repository.UsersRepository;
import com.nissandigital.components.common.authorisation.services.UserService;
@javax.annotation.Generated(value = "com.nissandigital.components.common.authorisation.codegen.languages.SpringCodegen", date = "2018-11-14T08:34:28.245Z")

@RestController
public class UsersController  {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    UserService userService;
    
    @Autowired
    Logger logger;
    
    @Autowired
    UsersRepository userRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    
	@CrossOrigin
    @GetMapping("/users")
    public ModelAndView userPage() {
    	return new ModelAndView("users");
    }
    
	@CrossOrigin
	@PostMapping(value="/users")
	public ResponseEntity addUser(@Valid @RequestBody List<UserPOJO> users) {
		try {
					userService.saveUser(users);
	    	        return new ResponseEntity(HttpStatus.OK);

		}
     catch(Exception e)
	     {
    	 String error="User couldn't be registered sucessfully";
  		
	        return new ResponseEntity(error,HttpStatus.NOT_ACCEPTABLE);
	     }
	}
	
	@CrossOrigin
	@GetMapping("/getUsersList")
	public ResponseEntity showApplications() {
		List<String> appIdList = Arrays.asList(userRepository.findAppId());
		List<String> roleList = Arrays.asList(userRepository.findRoleNames());
		List<String> userIdList = Arrays.asList(userRepository.findUserIds());
		List<Boolean> grantAccessList=Arrays.asList(userRepository.findGrantAccess());
		HashMap<String, Object> jsonUserObject = new HashMap<>();
		jsonUserObject.put("AppName", appIdList);
		jsonUserObject.put("role", roleList);
		jsonUserObject.put("userId", userIdList);
		jsonUserObject.put("grantAccess",grantAccessList);
		logger.info(""+jsonUserObject);
		return new ResponseEntity(jsonUserObject, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@GetMapping("/getUsers")
	public ResponseEntity<List<UserPOJO>> showusers() {
		
		List<UserPOJO> users=userService.showUsers();
		return new ResponseEntity(users,HttpStatus.OK);

	}
	@CrossOrigin
	@PostMapping(value="/deleteUser")
	public ResponseEntity deleteUser(@RequestBody UserPOJO user) {
		
		 try {
			    userService.deleteUser(user);
		        return new ResponseEntity(HttpStatus.OK);
		     }
         catch(Exception e)
		     {
        	 String error="User couldn't be deleted successfully";
     		
		        return new ResponseEntity(error,HttpStatus.NOT_ACCEPTABLE);
		     }
		 }
	@CrossOrigin
	@PostMapping(value="/editUser")
	public ResponseEntity editUser(@RequestBody UserPOJO user) {
		
		 try {
			    userService.editUser(user);
		        return new ResponseEntity(HttpStatus.OK);
		     }
         catch(Exception e)
		     {
        	 String error="User couldn't be edited successfully";
		      
		        return new ResponseEntity(error,HttpStatus.NOT_ACCEPTABLE);
		     }
		 }

}
