package com.nissandigital.components.common.authorisation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nissandigital.components.common.authorisation.model.Apps;
import com.nissandigital.components.common.authorisation.repository.AppRepository;

import io.swagger.annotations.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@javax.annotation.Generated(value = "com.nissandigital.components.common.authorisation.codegen.languages.SpringCodegen", date = "2018-11-14T08:34:28.245Z")

@Controller
public class AppsController {

    private static final Logger log = LoggerFactory.getLogger(AppsController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    
    @Autowired
    AppRepository appRepository;
    
    @Autowired
    Logger logger;

    @org.springframework.beans.factory.annotation.Autowired
    public AppsController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
	@CrossOrigin
    @GetMapping("/applications")
    public ModelAndView appPage() {
    	return new ModelAndView("applications");
    }
	@CrossOrigin
    @PostMapping(value = "/applications")
    public ResponseEntity addApp (@ApiParam(value = "App ID that is unique to an app to be registered, and the callback URL that is invoked to authenticate the user accessing the app."  )  @Valid @RequestBody Apps app) {
        //String accept = request.getHeader("Accept");
		String appId=app.getAppId();
		String callbackURL=app.getCallbackURL();
		double expiryTime=app.getExpiryTime();
		
		logger.info(appId);
        try {
        appRepository.saveApp(appId,callbackURL,expiryTime);
        return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e)
        {
       	 String error="App couldn't be registered successfully";

        return new ResponseEntity(error,HttpStatus.NOT_ACCEPTABLE);
        }
    }
	
	@CrossOrigin
	@GetMapping("/getApplicationList")
	public ResponseEntity showApplications() {
		Iterable<Apps> apps = appRepository.findAll();
		HashMap<String, Object> applications = new HashMap<>();
		List<String> appNameArray = new ArrayList<String>();
		List<String> callbackArray = new ArrayList<String>();
		for(Apps app: apps) {
			appNameArray.add(app.getAppId());
			callbackArray.add(app.getCallbackURL());
		}
		applications.put("AppName", appNameArray);
		applications.put("callbackUrl", callbackArray);
		logger.info(""+applications);
		return new ResponseEntity(applications, HttpStatus.OK);
		
	}
	@CrossOrigin
	@GetMapping("/getApps")
	public ResponseEntity<List<Apps>> showApps() {
		List<Apps> apps = (List<Apps>) appRepository.findAll();
		
		//logger.info(""+apps);
		return new ResponseEntity(apps,HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@PostMapping(value="/deleteApp")
	public ResponseEntity deleteApp(@RequestBody Apps app) {
		
		 try {
			    String appId=app.getAppId();
		        appRepository.deleteApp(appId);
		        return new ResponseEntity(HttpStatus.OK);
		     }
         catch(Exception e)
		     {
        	   String error="App couldn't be deleted successfully";
		        return new ResponseEntity(error,HttpStatus.NOT_ACCEPTABLE);
		     }
		 }
	@CrossOrigin
	@PostMapping(value="/editApp")
	public ResponseEntity editApp(@RequestBody Apps app) {
		
		 try {
			 
			    String appId=app.getAppId();
		        double expiryTime=app.getExpiryTime();
		        String callbackUrl=app.getCallbackURL();
		        appRepository.editApp(appId,expiryTime,callbackUrl);
		        return new ResponseEntity(HttpStatus.OK);
		     }
         catch(Exception e)
		     {
        	 String error="App couldn't be edited successfully";
		        return new ResponseEntity(error,HttpStatus.NOT_ACCEPTABLE);
		     }
		 }
}
