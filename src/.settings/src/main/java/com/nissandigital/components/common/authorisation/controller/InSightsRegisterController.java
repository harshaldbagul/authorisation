package com.nissandigital.components.common.authorisation.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nissandigital.components.common.authorisation.model.Users;
import com.nissandigital.components.common.authorisation.repository.UsersRepository;

@Controller
public class InSightsRegisterController {

	@Autowired
	UsersRepository userRepo;

    @Autowired
    Logger logger;
    
    @CrossOrigin
	@PostMapping(value="/InSightsRegister")
    public ResponseEntity addUser(HttpServletRequest request )
    {
    	String udid=request.getHeader("udid");
    	String name=request.getHeader("name");
    	String userId=request.getHeader("userId");
    	String appName=request.getHeader("appName");
    	String role="default";
    	try {
    		if(udid==null || userId==null || appName==null) 
    		{
    			throw new Exception();
    		}
    		userRepo.InSightsRegister(userId,role,appName,udid,name,false);
        	return new ResponseEntity(HttpStatus.CREATED);

    	}
    	catch(Exception e) {
    	  return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
         }
    }
    
    @CrossOrigin
    @GetMapping(value="/getInSightsUsers")
    public ResponseEntity getUsers() {
    	//List<String> userIdList = Arrays.asList(userRepo.findUserIds());
    	//List<String> userUDIDList = Arrays.asList(userRepo.findUserUDID());
    	List<List>users=userRepo.findUserInSights();
    	HashMap<String, Object> jsonUserObject = new HashMap<>();
    	jsonUserObject.put("users", users);
    
    	return new ResponseEntity(jsonUserObject,HttpStatus.CREATED);
    }
    

    @CrossOrigin
    @GetMapping(value="/InSightsApproval")
    public ModelAndView approvalPage() {
    	return new ModelAndView("InSightsApproval");
    	
    }
    
    @CrossOrigin
    @PostMapping(value="/InSightsApproval")
    public void approval( Users user ) {
    	logger.info("Inside InSights approval");    	
    	String uuid=user.getUDID();
		Boolean status=user.isGrantAccess();
		userRepo.changeGrantAcess(uuid,status);
    	
    }
}
